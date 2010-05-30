//var videoPlayer;
//var presentationSlides;
//var videoNavigationBar;
//var videoDuration;
//var videoNavigationBarWidth;
//var videoNavigationBarCursor;
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
        this.notifyAllObservers(originType);
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

    $("#videoPlayer")[0].play();
};

function setInitialPosition() {
    var time = 0;

    var href = document.location.href;
    var indexOfAnchor = href.indexOf("#");
    if (indexOfAnchor != -1) {
        var slideId = href.substr(indexOfAnchor + 1);
        var slide = findSlideById(slideId);
        time = parseFloat(slide.attr("startTime"));
    }

    $("#videoPlayer")[0].currentTime = time;
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
            var position = $("#videoNavigationBarCursor").position().left + 7;
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
    var now = timeChangedEvent.currentTime;
    var searchingForSlide = true;
    var presentationSlides = $("#slidesContainer img");
    for (var i = 0; i < presentationSlides.length; i++) {
        var slide = $(presentationSlides[i]);
        if (searchingForSlide
         && now >= parseFloat(slide.attr("startTime"))
         && now < parseFloat(slide.attr("endTime"))) {
            searchingForSlide = false;
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
    }
}

function findSlideById(id) {
    return $("#slidesContainer img#slide_" + id);
}

function initializeVideoNavigationBar() {
    $("#videoNavigationBar").css("visibility", "hidden");

    var videoNavigationBar = $("#videoNavigationBar");
    var videoNavigationBarWidth = videoNavigationBar.width();
    var items = $("#videoNavigationBar li");
    var presentationSlides = $("#slidesContainer img");
    var noOfSlides = presentationSlides.length;
    var lastSlide = presentationSlides.last();
    var videoDuration = parseFloat(lastSlide.attr("endTime"));

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
    }

    $("#videoNavigationBar").css("visibility", "visible");
}

function min(i1, i2) {
    if (i1 < i2) return i1;
    else return i2;
}

function ontimeupdateHandler() {
    var now = $("#videoPlayer")[0].currentTime;
    mainVideoLoop.timeChanged(now, OBSERVER_TYPE_VIDEO);
}


//$(items[i]).find("a").click(function() {
//    var slideId = $(this).attr("href").substr(1);
//    jumpToSlideById(slideId);
//    addAnchorToUrl(slideId);
//    return false;
//});


//var anchorIndex = document.location.href.indexOf("#");
//if (anchorIndex != -1) {
//    var slideIdFromUrl = document.location.href.substring(anchorIndex+1);
//    jumpToSlideById(slideIdFromUrl);
//}



//window.onload = function() {
//    videoPlayer = $("#videoPlayer")[0];
//    presentationSlides = $("img");
//    videoNavigationBar = $("#videoNavigationBar");
//    videoNavigationBarCursor = $("#videoNavigationBarCursor");
//    initializeVideoNavigationBar();
//
//    // disable selection
//    document.onselectstart = function() { return false; }; // IE
//    document.onmousedown = function() { return false; }; // Mozilla
//
//    videoPlayer.play();
//};
//
//function addAnchorToUrl(anchor) {
//    var anchorIndex = document.location.href.indexOf("#");
//    if (anchorIndex != -1) {
//        document.location = document.location.href.substring(0, anchorIndex) + "#" + anchor;
//    } else {
//        document.location = document.location + "#" + anchor;
//    }
//}
//function initializeVideoNavigationBar() {
//    videoNavigationBar = $("#videoNavigationBar");
//    videoNavigationBarWidth = videoNavigationBar.width();
//    var items = $("#videoNavigationBar li");
//    var noOfSlides = presentationSlides.length;
//    var lastSlide = presentationSlides.last();
//    videoDuration = parseFloat(lastSlide.attr("endTime"));
//
//    var lastEndTime = 0;
//    for (var i = 0; i < noOfSlides; i++) {
//        var slide = $(presentationSlides[i]);
//        var startTime = parseFloat(slide.attr("startTime"));
//        lastEndTime = startTime;
//        var endTime = min(parseFloat(slide.attr("endTime")), videoDuration);
//
//        var chapterDuration = endTime - startTime;
//        var durationPercentage = chapterDuration / videoDuration;
//        var totalBorderWidthsInPixels = (noOfSlides * 2);
//        var width = durationPercentage * (videoNavigationBarWidth - totalBorderWidthsInPixels);
//
//        $(items[i]).width(width);
//
//        $(items[i]).find("a").click(function() {
//            var slideId = $(this).attr("href").substr(1);
//            jumpToSlideById(slideId);
//            addAnchorToUrl(slideId);
//            return false;
//        });
//    }
//
//    videoNavigationBar.css("visibility", "visible");
//
//    videoNavigationBarCursor.draggable({
//        axis: 'x',
//        containment: 'parent',
//        cursor: 'pointer',
//        start: function(event, ui) {
//            isDragging = true;
//        },
//        stop: function(event, ui) {
//            jumpToSlideByPosition();
//            isDragging = false;
//        }
//    });
//
//    var anchorIndex = document.location.href.indexOf("#");
//    if (anchorIndex != -1) {
//        var slideIdFromUrl = document.location.href.substring(anchorIndex+1);
//        jumpToSlideById(slideIdFromUrl);
//    }
//}
//
//function jumpToSlideByPosition() {
//    var position = videoNavigationBarCursor.position().left + 7;
//    var items = $("#videoNavigationBar li");
//    var lastItem = items.last();
//    var totalWidth = parseFloat(lastItem.position().left + lastItem.width());
//    var totalDuration = parseFloat(presentationSlides.last().attr("endTime"));
//    var positionInPercentage = position / totalWidth;
//    var position = positionInPercentage * totalDuration;
//
//    videoPlayer.currentTime = position;
//    updateActiveSlide(false);
//}
//
//function updateActiveSlide(shouldMoveCursor) {
//    if (shouldMoveCursor) {
//        moveCursor();
//    }
//
//    var now = videoPlayer.currentTime;
//    var searchingForSlide = true;
//    for (var i = 0; i < presentationSlides.length; i++) {
//        var slide = $(presentationSlides[i]);
//        if (searchingForSlide
//                && now >= slide.attr("startTime")
//                && now < slide.attr("endTime")) {
//            searchingForSlide = false;
//            slide.css("display", "block");
//        } else {
//            slide.css("display", "none");
//        }
//    }
//}
//
//function moveCursor() {
//    if (!isDragging) {
//        var now = videoPlayer.currentTime;
//        var position = (videoPlayer.currentTime / videoDuration) * videoNavigationBarWidth;
//        position -= 7; // Half the cursor width
//        videoNavigationBarCursor.css("left", position + "px");
//    }
//}
//
//function jumpToSlideById(slideId) {
//    var slide;
//    for (var i = 0; i < presentationSlides.length; i++) {
//        if (presentationSlides[i].id == "slide_" + slideId) {
//            slide = presentationSlides[i];
//            break;
//        }
//    }
//
//    if (slide) {
//        updateActiveSlide(true);
//        videoPlayer.currentTime = parseFloat($(slide).attr("startTime"));
//    }
//}
