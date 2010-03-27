var videoPlayer;
var presentationSlides;
window.onload = function() {
    videoPlayer = document.getElementById("videoPlayer");
    videoPlayer.play();
    presentationSlides = document.getElementsByClassName("slide");
//    videoPlayer.ontimeupdate = updateActiveSlide;
//    updateActiveSlide();
};

function updateActiveSlide() {
    var now = videoPlayer.currentTime;
    for (var i=0;i<presentationSlides.length;i++) {
        var slide = presentationSlides[i];
        if (now >= slide.getAttribute("startTime")
         && now < slide.getAttribute("endTime")) {
            slide.style.display = "block";
        } else {
            slide.style.display = "none";
        }
    }
}