


var currentState = "EQUAL";

var STATE_EQUAL = "EQUAL";
var STATE_VIDEO_BIG = "STATE_VIDEO_BIG";
var STATE_SLIDE_BIG = "STATE_SLIDE_BIG";
var STATE_VIDEO_FULL = "STATE_VIDEO_FULL";
var STATE_SLIDE_FULL = "STATE_SLIDE_FULL";

var slideEqualRect = new Object();
var videoEqualRect = new Object();
var slideSmallRect = new Object();
var videoSmallRect = new Object();
var slideBigRect = new Object();
var videoBigRect = new Object();
var slideFullRect = new Object();
var videoFullRect = new Object();

var slideOriginalRect = new Object();
var videoOriginalRect = new Object();

var v;
var s;
var agenda;
var inner;

$(window).bind('resize', onResize);

$(document).bind('ready', function() {
  $("#videoContainer").single_double_click(onVideoClick,onVideoDoubleClick);
  $("#slidesContainer").single_double_click(onSlideClick,onSlideDoubleClick);


  slideOriginalRect.x = 0;
  slideOriginalRect.y = 0;
  slideOriginalRect.width = 400;
  slideOriginalRect.height = 300;
    

  videoOriginalRect.x = 0;
  videoOriginalRect.y = 0;
  videoOriginalRect.width = 400;
  videoOriginalRect.height = 300;

  v = $("#videoContainer");
  s = $("#slidesContainer");
  inner = $("#inner");
  agenda = $("#agenda");


  actuallyResize();

});




function onVideoDoubleClick(){
	if(currentState == STATE_VIDEO_FULL){
		currentState = STATE_EQUAL;
	}else{
		currentState = STATE_VIDEO_FULL;
	}
	changeState();
}

function onSlideDoubleClick(){
	if(currentState == STATE_SLIDE_FULL){
		currentState = STATE_EQUAL;
	}else{
		currentState = STATE_SLIDE_FULL;
	}
	changeState();
}

function onVideoClick(){
	//alert("video click");
	if (currentState != STATE_VIDEO_BIG) {
		currentState = STATE_VIDEO_BIG;
	}else{
		currentState = STATE_EQUAL;
	}

	changeState();
}

function onSlideClick(){
	//alert("slide click");
	if (currentState != STATE_SLIDE_BIG) {
		currentState = STATE_SLIDE_BIG;
	}else{
		currentState = STATE_EQUAL;
	}

	changeState();
}



function changeState(){
	if (currentState == STATE_EQUAL) {
	s.css("opacity","100");
	v.css("opacity","100");
  	applyRect(videoEqualRect,v);
	applyRect(slideEqualRect,s);
  }else if(currentState == STATE_VIDEO_BIG){
  	s.css("opacity","100");
	v.css("opacity","100");
  	applyRect(videoBigRect,v);
	applyRect(slideSmallRect,s);
  }else if(currentState == STATE_SLIDE_BIG){
  	s.css("opacity","100");
	v.css("opacity","100");
  	applyRect(videoSmallRect,v);
	applyRect(slideBigRect,s);
  }else if(currentState == STATE_SLIDE_FULL){
  	s.css("opacity","100");
	v.css("opacity","0");
	applyRect(slideFullRect,s);
  }else if(currentState == STATE_VIDEO_FULL){
  	s.css("opacity","0");
	v.css("opacity","100");
  	applyRect(videoFullRect,v);
  }
}



var resizeTimer;

function onResize(){
    clearTimeout(resizeTimer);
     resizeTimer = setTimeout(actuallyResize,1000);


}


function actuallyResize(){
	var contentWidth = $("#mainContent").width();
	var contentHeight = $("#mainContent").height();

	var contentEqualRight = new Object();
	contentEqualRight.x = contentWidth / 2;
	contentEqualRight.y = 0;
	contentEqualRight.width = contentWidth / 2;
	contentEqualRight.height = contentHeight;

	var contentEqualLeft = new Object();
	contentEqualLeft.x = 0;
	contentEqualLeft.y = 0;
	contentEqualLeft.width = contentWidth / 2;
	contentEqualLeft.height = contentHeight;

	var contentBig = new Object();
	contentBig.x = contentWidth * 0.2;
	contentBig.y = 0;
	contentBig.width = contentWidth * 0.8;
	contentBig.height = contentHeight;

	var contentSmall = new Object();
	contentSmall.x = 0;
	contentSmall.y = 0;
	contentSmall.width = contentWidth * 0.2;
	contentSmall.height = contentHeight;

	var contentFull = new Object();
	contentFull.x = 0;
	contentFull.y = 0;
	contentFull.width = contentWidth;
	contentFull.height = contentHeight;


	slideEqualRect = aspectFit(contentEqualLeft, slideOriginalRect);
	videoEqualRect = aspectFit(contentEqualRight, videoOriginalRect);
	slideSmallRect = aspectFit(contentSmall, slideOriginalRect);
	slideSmallRect.y = 0;
	videoSmallRect = aspectFit(contentSmall, videoOriginalRect);
	videoSmallRect.y = 0;
	slideBigRect = aspectFit(contentBig, slideOriginalRect);
	videoBigRect = aspectFit(contentBig, videoOriginalRect);
	slideFullRect = aspectFit(contentFull, slideOriginalRect);
	videoFullRect = aspectFit(contentFull, videoOriginalRect);

	var slideSmallUsedWidth = slideSmallRect.width + videoBigRect.width;
	var slideSmallStartX = contentWidth/2 - slideSmallUsedWidth/2;
	slideSmallRect.x = slideSmallStartX;
	videoBigRect.x = slideSmallStartX+slideSmallRect.width;


	var videoSmallUsedWidth = videoSmallRect.width + slideBigRect.width;
	var videoSmallStartX = contentWidth/2 - videoSmallUsedWidth/2;
	videoSmallRect.x = videoSmallStartX;
	slideBigRect.x = videoSmallStartX+videoSmallRect.width;



	if (currentState == STATE_EQUAL) {
		applyRect(videoEqualRect, v);
		applyRect(slideEqualRect, s);
	}
	else
		if (currentState == STATE_VIDEO_BIG) {
			applyRect(videoBigRect, v);
			applyRect(slideSmallRect, s);
		}
		else
			if (currentState == STATE_SLIDE_BIG) {
				applyRect(videoSmallRect, v);
				applyRect(slideBigRect, s);
			}
			else
				if (currentState == STATE_SLIDE_FULL) {
					applyRect(slideFullRect, s);
				}
				else
					if (currentState == STATE_VIDEO_FULL) {
						applyRect(videoFullRect, v);
					}


	var agenda = document.getElementById('agenda');

	if (currentState == STATE_EQUAL) {
		agenda.style.left = window.innerWidth / 2 - 190 + "px";
	}else{
		agenda.style.left = window.innerWidth / 2 - 400 + "px";
	}


    resizeChapters();

};



function resizeChapters(){
    var chapters = $("#chapters div").not("#videoNavigationBarCursor");
    var chapterContainerWidth = $("#chapters").width();
    var noOfChapters = chapters.length;
    var startX = 0;
    var totalDuration = parseFloat($("#chapters").attr("totalduration"));
    for (var i = 0; i < noOfChapters; i++) {
        var chapter = $(chapters[i]);
        var cuepoint = parseFloat(chapter.attr("cuepoint"));
        var duration = parseFloat(chapter.attr("duration"));


       var percentage = duration/totalDuration;
       var chapterWidth = chapterContainerWidth*percentage;
       chapter.css("width",chapterWidth-1+"px");
       chapter.css("left",startX+"px");
       startX+=chapterWidth;

    }
}


function applyRect(rect,item){
	item.css("left", rect.x + "px")
	item.css("top", rect.y + "px")
  	item.css("width", rect.width + "px")
  	item.css("height", rect.height + "px")
}



function aspectFit(outer,inner){
		fixedRatio = outer.width/outer.height;
		innerRatio = inner.width/inner.height;
		newRect = new Object();
		if(innerRatio >= fixedRatio){
			newRect.width = outer.width;
			newRect.height = inner.height/(inner.width/outer.width);
		}else{
			newRect.height = outer.height;
			newRect.width = inner.width/(inner.height/outer.height);
		}
		newRect.x =outer.x + ( outer.width/2-newRect.width/2);
		newRect.y = outer.y + ( outer.height/2-newRect.height/2);
		return newRect;
}







function toggleAgenda(){

	 if (currentState == STATE_EQUAL) {
		agenda.css("left",window.innerWidth / 2 - 190 + "px");

	}else{
		agenda.css("left",window.innerWidth / 2 - 400 + "px");
	}

	if (agenda_btn.value=="Show Agenda"){

		if (currentState == STATE_EQUAL) {
			v.css("webkitTransform", "rotateY(-45deg) translateZ(-250px) translate(100px,0)");
			s.css("webkitTransform", "rotateY(45deg) translateZ(-250px) translate(-100px,0)");
		}else{
			inner.css("webkitTransform", "rotateY(-45deg) translateZ(-500px) translate(100px,0)");
		}
		agenda.css("opacity","100");
		 if (currentState == STATE_EQUAL) {
		 	agenda.css("webkitTransform","rotateY(0deg) translateZ(0px)");
		 }else{
		 	agenda.css("webkitTransform","rotateY(20deg) translateZ(-200px)");
		 }
        agenda_btn.value="Hide Agenda";
    }else{
		setTimeout(hideAgenda,600);
		v.css("webkitTransform","rotateY(0deg)");
		s.css("webkitTransform","rotateY(0deg)");
		inner.css("webkitTransform","rotateY(0deg) translateZ(0px)");
		agenda_btn.value="Show Agenda";
		agenda.css("opacity","0");
		agenda.css("webkitTransform","rotateY(0deg) translateZ(500px)");
	}


}

function hideAgenda(){
		//var agenda = document.getElementById('agenda');
		//agenda.style.display = "none";
	}



jQuery.fn.single_double_click = function(single_click_callback, double_click_callback, timeout) {
  return this.each(function(){
    var clicks = 0, self = this;
    jQuery(this).click(function(event){
      clicks++;
      if (clicks == 1) {
        setTimeout(function(){
          if(clicks == 1) {
            single_click_callback.call(self, event);
          } else {
            double_click_callback.call(self, event);
          }
          clicks = 0;
        }, timeout || 300);
      }
    });
  });
}
