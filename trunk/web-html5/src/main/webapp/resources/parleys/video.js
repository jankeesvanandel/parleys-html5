var videoPlayer;
var presentationSlides;
var videoNavigationBar;
var videoDuration;
var videoNavigationBarWidth;
var videoNavigationBarCursor;

$(document).ready(function() {
    videoPlayer = $("#videoPlayer")[0];
    presentationSlides = $("img");
    videoNavigationBar = $("#videoNavigationBar");
    videoNavigationBarCursor = $("#videoNavigationBarCursor");
    initializeVideoNavigationBar();
    videoPlayer.play();
});

function initializeVideoNavigationBar() {
    videoNavigationBar = $("#videoNavigationBar");
    videoNavigationBarWidth = videoNavigationBar.width();
    var items = $("#videoNavigationBar li");
    var noOfSlides = presentationSlides.length;
    var lastSlide = presentationSlides.last();
    videoDuration = parseFloat(lastSlide.attr("endTime"));

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

        $(items[i]).find("a").click(function() {
            var slideId = $(this).attr("href").substr(1);
            jumpToSlideById(slideId);
            return false;
        });
    }

    videoNavigationBarCursor.draggable({
        axis: 'x',
        containment: 'parent',
        cursor: 'pointer',
        stop: function(event, ui) {
            jumpToSlideByPosition();
        }
    });
}

function jumpToSlideByPosition() {
    var position = videoNavigationBarCursor.left() + 7;
    var slide;
    for (var i = 0; i < presentationSlides.length; i++) {
        if ($(presentationSlides[i]).left() <= position
                && $(presentationSlides[i]).right() >= position) {
            slide = presentationSlides[i];
            break;
        }
    }

    if (slide) {
        videoPlayer.currentTime = parseFloat($(slide).attr("startTime"));
        updateActiveSlide();
    }
}

function min(i1, i2) {
    if (i1 < i2) return i1;
    else return i2;
}

function updateActiveSlide() {
    moveCursor();

    var now = videoPlayer.currentTime;
    var searchingForSlide = true;
    for (var i = 0; i < presentationSlides.length; i++) {
        var slide = $(presentationSlides[i]);
        if (searchingForSlide
                && now >= slide.attr("startTime")
                && now < slide.attr("endTime")) {
            searchingForSlide = false;
            slide.css("display", "block");
        } else {
            slide.css("display", "none");
        }
    }
}

function moveCursor() {
    var now = videoPlayer.currentTime;
    var position = (videoPlayer.currentTime / videoDuration) * videoNavigationBarWidth;
    position -= 7; // Half the cursor width
    videoNavigationBarCursor.css("left", position + "px");
}

function jumpToSlideById(slideId) {
    var slide;
    for (var i = 0; i < presentationSlides.length; i++) {
        if (presentationSlides[i].id == "slide_" + slideId) {
            slide = presentationSlides[i];
            break;
        }
    }

    if (slide) {
        videoPlayer.currentTime = parseFloat($(slide).attr("startTime"));
        updateActiveSlide();
    }
}
