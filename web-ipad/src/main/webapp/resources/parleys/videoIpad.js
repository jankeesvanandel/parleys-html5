/*
 * Copyright (C) 2010 Parleys.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//var isCursorDragging = false;
var timeDisplayWidth = 150;

var com;
if (!com) com = {};
if (!com.parleys) com.parleys = {};
if (!com.parleys.html5client) com.parleys.html5client = {};

var OBSERVER_TYPE_VIDEO = "video";
var OBSERVER_TYPE_SLIDES = "slides";
var OBSERVER_TYPE_PROGRESSBAR = "progressbar";

var logger = {
    log: function(message) {
        alert(message); // Only uncomment during development
    }
};

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
    showPlayButtonOverlay();
    initializeVideoNavigationBar();
    initializeVideoEventSources();
    setInitialPosition();
    lazyLoadSlides();
    disableTextSelection();
    initializeControlsEventHandlers();
});

function showPlayButtonOverlay() {
    $("#playButtonOverlay").css("display", "block");
    $("#playButtonOverlayBackground").css("display", "block");
    $("#agenda").css("display", "none"); // Hide the agenda to keep the play button clickable
    $("#playButtonOverlay a").click(function(e) {
        $("#playButtonOverlay").css("display", "none");
        $("#playButtonOverlayBackground").css("display", "none");
        $("#videoPlayer")[0].play();
        return false;
    });
}

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

var allSlides;
function loadSlide(index, lazy) {
    var slide = allSlides.eq(index);

    if (typeof(slide.attr("src")) === "undefined") {
        slide.attr("src", slide.attr("source"));
        slide.removeAttr("source");
    }

    if (index < allSlides.size()) {
        if (lazy) {
            setTimeout(function() {
                loadSlide(index+1);
            }, 500);
        }
    }
}

function lazyLoadSlides() {
    allSlides = $("#slidesContainer img");
    var initialShownSlide = $(allSlides.eq(0));
    initialShownSlide.attr("src", initialShownSlide.attr("source"));
    initialShownSlide.removeAttr("source");

    setTimeout(function() {
        loadSlide(1, true);
    }, 500);
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
        try {
            $("#videoPlayer")[0].currentTime = time;
        } catch (e) {
            logger.log("Error setting initial position to time " + time + ": " + e)
        }
    }
}

function initializeProgressBarEventSource() {
    $("#chapters div").not("#videoNavigationBarCursor").bind("click", function() {
        var href = $(this).attr("cuepoint");
        mainVideoLoop.timeChanged(href, OBSERVER_TYPE_PROGRESSBAR);
        return false;
    });

    $("#agenda a").bind("click", function() {
        var href = $(this).attr("href");
        var id = href.substr(1);
        var slide = findSlideById(id);
        toggleAgenda();
        mainVideoLoop.timeChanged(slide.attr("startTime"), OBSERVER_TYPE_PROGRESSBAR);
        $("#videoPlayer")[0].play();

        return false;
    });

    $("a#agendaButton").bind("click", function() {
        toggleAgenda();
        return false;
    });
}

function getStartTimeForChapter(chapterIndex) {
    var presentationSlides = $("#slidesContainer img");

    chapterIndex = max(0, chapterIndex);
    chapterIndex = min(presentationSlides, chapterIndex);

    var slide = $(presentationSlides[chapterIndex]);
    return parseFloat(slide.attr("startTime"));
}

var updateVideoCatchAlert = 0;
function updateVideo(timeChangedEvent) {
    try {
        $("#videoPlayer")[0].currentTime = timeChangedEvent.currentTime;
    } catch (e) {
        // only alert a couple of times because otherwise we're spammed with alerts
        if (updateVideoCatchAlert < 5) {
            updateVideoCatchAlert++;
            logger.log("Error updating video to time " + timeChangedEvent.currentTime + ": " + e)
        }
    }
}

function initializeVideoEventSource() {
    $("#videoPlayer")[0].ontimeupdate = function() {
        mainVideoLoop.timeChanged($("#videoPlayer")[0].currentTime, OBSERVER_TYPE_VIDEO);
    };
}

function initializeVideoEventSources() {
    initializeVideoEventSource();
    initializeProgressBarEventSource();
}

function updateSlide(timeChangedEvent) {
    var presentationSlides = $("#slidesContainer img");
    for (var i = 0; i < presentationSlides.length; i++) {
        var slide = $(presentationSlides[i]);
        if (mainVideoLoop._currentChapter == i) {
            loadSlide(i, false);
            slide.css("display", "block");
        } else {
            slide.css("display", "none");
        }
    }
}

function updateProgressBar(timeChangedEvent) {
    var now = timeChangedEvent.currentTime;
    var chapters = $("#chapters");
    var totalDuration = parseFloat($("#chapters").attr("totalduration"));

    var videoNavigationBarWidth = $("#chapters").width();

    var position = (now / totalDuration) * videoNavigationBarWidth;
    position -= 7; // Half the cursor width
    $("#videoNavigationBarCursor").css("left", position + "px");

    updateVideoPositionIndicator(now);
}

function findSlideById(id) {
    return $("#slidesContainer img#slide_" + id);
}

function getNumberOfSlides(){
    var presentationSlides = $("#slidesContainer img");
    var noOfSlides = presentationSlides.length;
    return noOfSlides;
}

function initializeVideoNavigationBar() {
    $("#videoNavigationBar").css("visibility", "hidden");

    var chapters = [];
    var videoNavigationBar = $("#videoNavigationBar");
    var videoNavigationBarWidth = videoNavigationBar.width() - timeDisplayWidth; // subtract the videoTimeAndPosition width
    var items = $("#videoNavigationBar li");
    var presentationSlides = $("#slidesContainer img");
    var noOfSlides = presentationSlides.length;
    var lastSlide = presentationSlides.last();
    var videoDuration = parseFloat($("#chapters").attr("totalduration"));

    updateVideoDurationIndicator(videoDuration);

    var lastEndTime = 0;
    var totalBorderWidthsInPixels = (noOfSlides);
    for (var i = 0; i < noOfSlides; i++) {
        var slide = $(presentationSlides[i]);
        var startTime = parseFloat(slide.attr("startTime"));
        lastEndTime = startTime;
        var endTime = min(parseFloat(slide.attr("endTime")), videoDuration);

        var chapterDuration = endTime - startTime;
        var durationPercentage = chapterDuration / videoDuration;

        var width = durationPercentage * (videoNavigationBarWidth-totalBorderWidthsInPixels);

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
    resizeElements();
}

function resizeElements() {
    // Resize the Chapters
    initializeVideoNavigationBar();
}

var resizeTimeout;
$(window).resize(function() {
    clearTimeout(resizeTimeout);
    resizeTimeout = setTimeout(resizeElements, 100);
});
