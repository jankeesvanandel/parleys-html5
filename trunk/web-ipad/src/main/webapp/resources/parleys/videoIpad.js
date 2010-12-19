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

com.parleys.html5client.MainVideoLoop = function() {
    this._observers = [];
    this._observerTypes = [];
    this._currentTime = 0;
    this._currentChapter = 0;
    this._chapters = [];
    this._subClips = [];
    this._currentSubClip = null;
    this._currentSubClipIndex = 0;
    this._actualTime = 0;
};

com.parleys.html5client.MainVideoLoop.prototype = {

    addObserver: function(observer, observerType) {
        this._observers.push(observer);
        this._observerTypes.push(observerType);
    },

    timeChanged: function(subClipIndex, time, originType) {
        if (typeof(subClipIndex) === "undefined") {
            throw new Error("subClipIndex is undefined");
        }
        if (typeof(time) !== "number") {
            time = parseFloat(time);
        }
        if (!originType) {
            throw new Error("originType is not specified");
        }
        this._currentTime = time;
        this._currentSubClip = this._subClips[subClipIndex];
        this._currentSubClipIndex = subClipIndex;

        this.determineActualTime();
        this.determineCurrentChapter();
        this.notifyAllObservers(originType);
    },

    actualTimeChanged: function(actualTime, originType) {
        if (typeof(actualTime) !== "number") {
            actualTime = parseFloat(actualTime);
        }
        if (!originType) {
            throw new Error("originType is not specified");
        }

        for (var i = 0; i < this._subClips.length; i++) {
            var subClip = this._subClips[i];
            if (actualTime >= subClip.startTimeInSeconds
             && actualTime < subClip.endTimeInSeconds) {
                this._currentSubClipIndex = i;
                this._currentSubClip = subClip;
                this._currentTime = actualTime - subClip.startTimeInSeconds;
                break;
            }
        }

        this.determineCurrentChapter();

        this.loadSubClip();

        this.notifyAllObservers(originType);
    },

    notifyAllObservers: function(originType) {
        var timeChangedEvent = {
            currentTime: this._currentTime,
            actualTime: this._actualTime,
            currentSubClipIndex: this._currentSubClipIndex
        };
        for (var i = 0; i < this._observers.length; i++) {
            if (this._observerTypes[i] != originType) {
                this._observers[i](timeChangedEvent);
            }
        }
    },

    determineActualTime: function () {
        var startTimeOfSubClip = this._currentSubClip.startTimeInSeconds;
        this._actualTime = startTimeOfSubClip + this._currentTime;
    },

    determineCurrentChapter: function() {
        for (var i = 0; i < this._chapters.length; i++) {
            var chapter = this._chapters[i];
            if (this._actualTime >= chapter.startTime
                    && this._actualTime < chapter.endTime) {
                this._currentChapter = i;
                break;
            }
        }
    },

    loadSubClip: function() {
        $("#videoLoader").show();
        var videoPlayer = $("#videoPlayer")[0];
        if (videoPlayer.src != mainVideoLoop._currentSubClip.source) {
            videoPlayer.src = mainVideoLoop._currentSubClip.source;
            videoPlayer.load();
            videoPlayer.play();
            this.setCurrentTime(this._currentTime)
        } else {
            this.setCurrentTime(this._currentTime)
        }
    },

    setCurrentTime: function(time) {
        try {
            $("#videoPlayer")[0].currentTime = time;
            $("#videoLoader").hide();
        } catch (e) {
            setTimeout(function() {
                mainVideoLoop.setCurrentTime(time);
            }, 50);
        }
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
    initializeSubClips();
    initializeVideoNavigationBar();
    initializeVideoEventSources();
//    lazyLoadSlides();
    disableTextSelection();
    initializeControlsEventHandlers();
});

function initializeSubClips() {
    var videoSources = $("#videoContainer input");
    var totalDuration = 0;
    for (var i = 0; i < videoSources.length; i++) {
        var start = totalDuration;
        totalDuration += parseFloat(videoSources[i].getAttribute("duration"));
        mainVideoLoop._subClips.push({
            source: videoSources[i].getAttribute("source"),
            startTimeInSeconds: start,
            endTimeInSeconds: totalDuration
        });
    }
}

function addPlayPauzeListener(videoPlayer) {
    videoPlayer.addEventListener('pause', function() {
        $("#playButton").show();
        $("#pauzeButton").hide();
    }, false);
    videoPlayer.addEventListener('play', function() {
        $("#playButton").hide();
        $("#pauzeButton").show();
    }, false);
}

function addVideoLoadingIndicator(videoPlayer) {
//    videoPlayer.addEventListener('waiting', function() {
//        $("#videoLoader").show();
//    }, false);
//    videoPlayer.addEventListener('playing', function() {
//        $("#videoLoader").hide();
//    }, false);
}

function showPlayButtonOverlay() {
    $("#playButtonOverlay").css("display", "block");
    $("#playButtonOverlayBackground").css("display", "block");
    $("#agenda").css("display", "none");

    // Hide the agenda to keep the play button clickable
    $("#playButtonOverlay a").click(function() {
        var videoPlayer = $("#videoPlayer")[0];
        $("#playButtonOverlay").css("display", "none");
        $("#playButtonOverlayBackground").css("display", "none");

        addVideoLoadingIndicator(videoPlayer);

        addPlayPauzeListener(videoPlayer);

        mainVideoLoop._currentSubClip = mainVideoLoop._subClips[0];
        mainVideoLoop._currentSubClipIndex = 0;

        videoPlayer.src = mainVideoLoop._currentSubClip.source;
        videoPlayer.load();
        videoPlayer.play();
        videoPlayer.addEventListener('ended', function() {
            mainVideoLoop._currentSubClipIndex++;
            if (mainVideoLoop._currentSubClipIndex < mainVideoLoop._subClips.length) {
                mainVideoLoop._currentSubClip = mainVideoLoop._subClips[mainVideoLoop._currentSubClipIndex];
                videoPlayer.src = mainVideoLoop._currentSubClip.source;
                videoPlayer.load();
                videoPlayer.play();
            } else {
                mainVideoLoop._currentSubClipIndex = 0;
                mainVideoLoop._currentSubClip = mainVideoLoop._subClips[0];
                videoPlayer.src = mainVideoLoop._currentSubClip.source;
            }
        }, false);
        return false;
    });
}

function initializeControlsEventHandlers() {
    $("#prevChapterButton").click(function() {
        var previousChapter = mainVideoLoop._currentChapter - 1;
        var time = getStartTimeForChapter(previousChapter);
        mainVideoLoop.actualTimeChanged(time, OBSERVER_TYPE_PROGRESSBAR);
        return false;
    });

    $("#nextChapterButton").click(function() {
        var nextChapter = mainVideoLoop._currentChapter + 1;
        var time = getStartTimeForChapter(nextChapter);
        mainVideoLoop.actualTimeChanged(time, OBSERVER_TYPE_PROGRESSBAR);
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

//var allSlides;
//function loadSlide(index) {
//    var slide = allSlides.eq(index);
//
//    if (typeof(slide.attr("src")) === "undefined") {
//        slide.attr("src", slide.attr("source"));
//        slide.removeAttr("source");
//    }
//
//    if (index < allSlides.size()) {
//        setTimeout(function() {
//            loadSlide(index + 1);
//        }, 50);
//    }
//}
//
//function lazyLoadSlides() {
//    allSlides = $("#slidesContainer img");
//    loadSlide(0);
//}

function disableTextSelection() {
    document.onselectstart = function() {
        return false; // ie
    };
    document.onmousedown = function() {
        return false; // mozilla
    };
}

//function setInitialPosition() {
//    var href = document.location.href;
//    var indexOfAnchor = href.indexOf("#");
//    if (indexOfAnchor != -1 && indexOfAnchor < href.length - 1) {
//        var slideId = href.substr(indexOfAnchor + 1);
//        var slide = findSlideById(slideId);
//        var time = parseFloat(slide.attr("startTime"));
//        try {
//            $("#videoPlayer")[0].currentTime = time;
//        } catch (e) {
//            console.log("Error setting initial position to time " + time + ": " + e)
//        }
//    }
//}

function initializeProgressBarEventSource() {
    $("#chapters div").not("#videoNavigationBarCursor").bind("click", function() {
        var href = $(this).attr("cuepoint");
        mainVideoLoop.actualTimeChanged(href, OBSERVER_TYPE_PROGRESSBAR);
        return false;
    });

    $("#agenda a").bind("click", function() {
        var href = $(this).attr("href");
        var id = href.substr(1);
        var slide = findSlideById(id);
        toggleAgenda();
        mainVideoLoop.actualTimeChanged(slide.attr("startTime"), OBSERVER_TYPE_PROGRESSBAR);
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
//    try {
//        $("#videoPlayer")[0].currentTime = timeChangedEvent.currentTime;
//    } catch (e) {
//        // only alert a couple of times because otherwise we're spammed with alerts
//        if (updateVideoCatchAlert < 5) {
//            updateVideoCatchAlert++;
//            console.log("Error updating video to time " + timeChangedEvent.currentTime + ": " + e)
//        }
//    }
}

function initializeVideoEventSource() {
    $("#videoPlayer")[0].addEventListener('timeupdate', function() {
        var now = $("#videoPlayer")[0].currentTime;
        mainVideoLoop.timeChanged(mainVideoLoop._currentSubClipIndex, now, OBSERVER_TYPE_VIDEO);
        resizeElements();
    }, false);
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
//            loadSlide(i);
            slide.css("display", "block");
        } else {
            slide.css("display", "none");
        }
    }
}

function updateProgressBar(timeChangedEvent) {
    var now = timeChangedEvent.actualTime;
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

function getNumberOfSlides() {
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
//
//function onTimeUpdateHandler() {
//    var now = $("#videoPlayer")[0].currentTime;
//    mainVideoLoop.timeChanged(mainVideoLoop._currentSubClipIndex, now, OBSERVER_TYPE_VIDEO);
//    resizeElements();
//}

function resizeElements() {
    // Resize the Chapters
    initializeVideoNavigationBar();
}

var resizeTimeout;
$(window).resize(function() {
    clearTimeout(resizeTimeout);
    resizeTimeout = setTimeout(resizeElements, 100);
});
