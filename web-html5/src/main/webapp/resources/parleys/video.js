var isCursorDragging = false;

var com;
if (!com) com = {};
if (!com.parleys) com.parleys = {};
if (!com.parleys.html5client) com.parleys.html5client = {};

var OBSERVER_TYPE_VIDEO = "video";
var OBSERVER_TYPE_SLIDES = "slides";
var OBSERVER_TYPE_PROGRESSBAR = "progressbar";

com.parleys.html5client.MainVideoLoop = function() {
    this._observers = [];
    this._observerTypes = [];
    this._currentTime = 0;
    this._currentChapter = 0;
    this._chapters = [];

    this.notifyAllObservers = function(originType) {
        var timeChangedEvent = {
            currentTime: this._currentTime
        };
        for (var i = 0; i < this._observers.length; i++) {
            if (this._observerTypes[i] != originType) {
                this._observers[i](timeChangedEvent);
            }
        }
    };
};

com.parleys.html5client.MainVideoLoop.prototype = {

    addObserver: function(observer, observerType) {
        this._observers.push(observer);
        this._observerTypes.push(observerType);
    },

    timeChanged: function(time, originType) {
        if (typeof time != "number") {
            time = parseFloat(time);
        }
        if (!originType) {
            throw new Error("originType is not specified");
        }
        this._currentTime = time;

        for (var i = 0; i < this._chapters.length; i++) {
            var chapter = this._chapters[i];
            if (chapter.startTime <= time
             && chapter.endTime > time) {
                this._currentChapter = i;
                break;
            }
        }

        this.notifyAllObservers(originType);
    },

    setChapters: function(chapters) {
        this._chapters = chapters;
    }
};

var mainVideoLoop = new com.parleys.html5client.MainVideoLoop();
mainVideoLoop.addObserver(updateVideo, OBSERVER_TYPE_VIDEO);
mainVideoLoop.addObserver(updateSlide, OBSERVER_TYPE_SLIDES);
mainVideoLoop.addObserver(updateProgressBar, OBSERVER_TYPE_PROGRESSBAR);

$(document).ready(function() {
    initializeVideoNavigationBar();
});

window.onload = function() {
    initializeVideoEventSources();
    setInitialPosition();
    disableTextSelection();
    initializeControlsEventHandlers();

    $("#videoPlayer")[0].play();
};

function initializeControlsEventHandlers() {
    $("#prevChapterButton").click(function() {
        var previousChapter = mainVideoLoop._currentChapter - 1;
        var time = getStartTimeForChapter(previousChapter);
        mainVideoLoop.timeChanged(time, OBSERVER_TYPE_PROGRESSBAR);
        return false;
    });

    $("#nextChapterButton").click(function() {
        var nextChapter = mainVideoLoop._currentChapter + 1;
        var time = getStartTimeForChapter(nextChapter);
        mainVideoLoop.timeChanged(time, OBSERVER_TYPE_PROGRESSBAR);
        return false;
    });

    $("#playButton").click(function() {
        $("#playButton").hide();
        $("#pauzeButton").show();
        $("#videoPlayer")[0].play();
        return false;
    });

    $("#pauzeButton").click(function() {
        $("#playButton").show();
        $("#pauzeButton").hide();
        $("#videoPlayer")[0].pause();
        return false;
    });
}

function disableTextSelection() {
    document.onselectstart = function() {
        return false; // ie
    };
    document.onmousedown = function() {
        return false; // mozilla
    };
}

function setInitialPosition() {
    var href = document.location.href;
    var indexOfAnchor = href.indexOf("#");
    if (indexOfAnchor != -1 && indexOfAnchor < href.length - 1) {
        var slideId = href.substr(indexOfAnchor + 1);
        var slide = findSlideById(slideId);
        var time = parseFloat(slide.attr("startTime"));
        $("#videoPlayer")[0].currentTime = time;
    }
}

function initializeProgressBarEventSource() {
    $("#videoNavigationBar a").bind("click", function() {
        var href = $(this).attr("href");
        var id = href.substr(1);
        var slide = findSlideById(id);

        mainVideoLoop.timeChanged(slide.attr("startTime"), OBSERVER_TYPE_PROGRESSBAR);
    });
}

function initializeProgressBarCursor() {
    $("#videoNavigationBarCursor").draggable({
        axis: 'x',
        containment: 'parent',
        cursor: 'pointer',
        start: function() {
            isCursorDragging = true;
        },
        stop: function() {
            var position = $("#videoNavigationBarCursor").position().left - (150 - 7);
            var items = $("#videoNavigationBar li");
            var lastItem = items.last();
            var totalWidth = parseFloat(lastItem.position().left + lastItem.width());
            var totalDuration = parseFloat($("#slidesContainer img").last().attr("endTime"));
            var positionInPercentage = position / totalWidth;
            var time = positionInPercentage * totalDuration;

            mainVideoLoop.timeChanged(time, OBSERVER_TYPE_PROGRESSBAR);
            isCursorDragging = false;
        }
    });
}

function getStartTimeForChapter(chapterIndex) {
    var presentationSlides = $("#slidesContainer img");

    chapterIndex = max(0, chapterIndex);
    chapterIndex = min(presentationSlides, chapterIndex);

    var slide = $(presentationSlides[chapterIndex]);
    return parseFloat(slide.attr("startTime"));
}

function getTimeFromCursorPosition() {
    var position = $("#videoNavigationBarCursor").position().left - (150 - 7);
    var items = $("#videoNavigationBar li");
    var lastItem = items.last();
    var totalWidth = parseFloat(lastItem.position().left + lastItem.width());
    var totalDuration = parseFloat($("#slidesContainer img").last().attr("endTime"));
    var positionInPercentage = position / totalWidth;
    var time = positionInPercentage * totalDuration;
    return time;
}

function updateVideo(timeChangedEvent) {
    $("#videoPlayer")[0].currentTime = timeChangedEvent.currentTime;
}

function initializeVideoEventSource() {
    $("#videoPlayer")[0].ontimeupdate = function() {
        mainVideoLoop.timeChanged($("#videoPlayer")[0].currentTime, OBSERVER_TYPE_VIDEO);
    };
}

function initializeVideoEventSources() {
    initializeVideoEventSource();
    initializeProgressBarEventSource();
    initializeProgressBarCursor();
}

function updateSlide(timeChangedEvent) {
    var presentationSlides = $("#slidesContainer img");
    for (var i = 0; i < presentationSlides.length; i++) {
        var slide = $(presentationSlides[i]);
        if (mainVideoLoop._currentChapter == i) {
            slide.css("display", "block");
        } else {
            slide.css("display", "none");
        }
    }
}

function updateProgressBar(timeChangedEvent) {
    if (!isCursorDragging) {
        var now = timeChangedEvent.currentTime;
        var videoNavigationBarWidth = $("#videoNavigationBar").width();
        var videoDuration = parseFloat($("#slidesContainer img").last().attr("endTime"));

        var position = (now / videoDuration) * videoNavigationBarWidth;
        position -= 7; // Half the cursor width
        $("#videoNavigationBarCursor").css("left", position + "px");

        updateVideoPositionIndicator(now);
    }
}

function findSlideById(id) {
    return $("#slidesContainer img#slide_" + id);
}

function initializeVideoNavigationBar() {
    $("#videoNavigationBar").css("visibility", "hidden");

    var chapters = [];
    var videoNavigationBar = $("#videoNavigationBar");
    var videoNavigationBarWidth = videoNavigationBar.width() - 150; // subtract the videoTimeAndPosition width
    var items = $("#videoNavigationBar li");
    var presentationSlides = $("#slidesContainer img");
    var noOfSlides = presentationSlides.length;
    var lastSlide = presentationSlides.last();
    var videoDuration = parseFloat(lastSlide.attr("endTime"));

    updateVideoDurationIndicator(videoDuration);

    var lastEndTime = 0;
    for (var i = 0; i < noOfSlides; i++) {
        var slide = $(presentationSlides[i]);
        var startTime = parseFloat(slide.attr("startTime"));
        lastEndTime = startTime;
        var endTime = min(parseFloat(slide.attr("endTime")), videoDuration);

        var chapterDuration = endTime - startTime;
        var durationPercentage = chapterDuration / videoDuration;
        var totalBorderWidthsInPixels = (noOfSlides * 2);
        var width = durationPercentage * (videoNavigationBarWidth - totalBorderWidthsInPixels);

        $(items[i]).width(width);
        chapters.push({
            startTime: startTime,
            endTime: endTime
        });
    }

    mainVideoLoop.setChapters(chapters);

    $("#videoNavigationBar").css("visibility", "visible");
}

function updateVideoDurationIndicator(videoDuration) {
    var string = secondsToString(videoDuration);
    $("#videoTimeAndPosition span.duration").text(string);
}

function updateVideoPositionIndicator(position) {
    var string = secondsToString(position);
    $("#videoTimeAndPosition span.position").text(string + " / ");
}

function secondsToString(videoDuration) {
    var hours = parseInt((videoDuration / 3600) % 24);
    if (hours < 10) hours = "0" + hours;
    var minutes = parseInt((videoDuration / 60) % 60);
    if (minutes < 10) minutes = "0" + minutes;
    var seconds = parseInt(videoDuration % 60);
    if (seconds < 10) seconds = "0" + seconds;

    return hours + ":" + minutes + ":" + seconds;
}

function min(i1, i2) {
    if (i1 < i2) return i1;
    else return i2;
}

function max(i1, i2) {
    if (i1 > i2) return i1;
    else return i2;
}

function ontimeupdateHandler() {
    var now = $("#videoPlayer")[0].currentTime;
    mainVideoLoop.timeChanged(now, OBSERVER_TYPE_VIDEO);
}
