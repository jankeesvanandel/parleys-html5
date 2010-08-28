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

//var myScroll;
//
//function setHeight() {
//	var headerH = document.getElementById('logoBar').offsetHeight;
//	var wrapperH = window.innerHeight - headerH;
//	document.getElementById('innerContainer').style.height = wrapperH + 'px';
//}

//window.addEventListener('onorientationchange' in window ? 'orientationchange' : 'resize', setHeight, false);
//document.addEventListener('touchstart', function(e){ e.preventDefault(); }, false);
document.addEventListener('DOMContentLoaded', loaded, false);

function loaded() {
//	setHeight();
//	myScroll = new iScroll('content');

//    $("#logoBar a").each(function(index) { this.ontouchend=onTouchEndTriggerClick; });
//    $("#footer a").each(function(index) { this.ontouchend=onTouchEndTriggerClick; });

    initializeBanner();
}

function initializeBanner() {
    var photoWidth = $("#featuredPhotoWrapper").width();
    var photoCount = $("#featuredPhotoList img").size();
    var photosTotalWidth = photoWidth * photoCount;

    //Adjust the image reel to its new size
    $("#featuredPhotoList").css({'width' : photosTotalWidth});

    function updateButtons(index) {
        $("#featuredPhotoPaging a").removeClass('active');
        $("#featuredPhotoPaging a").eq(index).addClass('active');
        $("#featuredPhotoPaging h2").hide();
        $("#featuredPhotoPaging h2").eq(index).show();
        $("#featuredPhotoPaging h3").hide();
        $("#featuredPhotoPaging h3").eq(index).show();
    }

    updateButtons(0);

    //Paging and Slider Function
    function rotate() {
        var photoIndex = activePhoto.attr("rel") - 1;
        var featuredPhotoListPosition = photoIndex * photoWidth;

        updateButtons(photoIndex);

        //Slider Animation
        $("#featuredPhotoList").animate({
            left: -featuredPhotoListPosition
        }, 500);
    }

    //Rotation  and Timing Event
    function rotateSwitch(){
        play = setInterval(function() {
            activePhoto = $('#featuredPhotoPaging a.active').next();
            if (activePhoto.length === 0) {
                activePhoto = $('#featuredPhotoPaging a:first');
            }
            rotate();
        }, 5000);
    }

    rotateSwitch(); //Run function on launch

	$("#featuredPhotoPaging a").click(function() {
		activePhoto = $(this);

		//Reset Timer
		clearInterval(play);
		rotate();
		rotateSwitch();
		return false;
	});

    window.addEventListener('onorientationchange' in window ? 'orientationchange' : 'resize', function() {
        $("#featuredPhotoPaging a").unbind();
        clearInterval(play);
        delete activePhoto;
        initializeBanner();
    }, false);
}

//function onTouchEndTriggerClick() {
//    window.location = this.href;
//}

function featuredContentEvent(id) {
    try {
        $('#thumbnailsWrapper').fadeOut('fast', function() {
            $('#lowerThumbnailsContainer > img.loader').show();
            jsf.ajax.request(id, "event", {
                render: 'main:thumbnails',
                onevent: onFeaturedContentAjaxEvent,
                'javax.faces.behavior.event': 'action'
            });
        });
    } catch (e) {
        alert(e.message);
    }

    return false;
}

function onFeaturedContentAjaxEvent(evt) {
    if (evt.status == 'complete') {
        $('#lowerThumbnailsContainer > img.loader').hide();
        $('#thumbnailsWrapper').fadeIn('fast');
//        setTimeout(function () { myScroll.refresh() }, 0)
    }
}

function loadMoreFeaturedContentEvent(id) {
    try {
        $('.showMoreThumbnails img.loader').show();
        jsf.ajax.request(id, "event", {
            render: 'main:thumbnails',
            onevent: onFeaturedContentAjaxEvent,
            'javax.faces.behavior.event': 'action'
        });
    } catch (e) {
        alert(e.message);
    }

    return false;
}

function onLoadMoreFeaturedContentAjaxEvent(evt) {
    if (evt.status == 'complete') {
        $('.showMoreThumbnails img.loader').hide();
//        setTimeout(function () { myScroll.refresh() }, 0)
    }
}

function newsPaginationEvent(id) {
    try {
        $('#newsContainerWrapper').fadeOut('fast', function() {
            $('#newsContainerWrapperOuter img.loader').show();
            jsf.ajax.request(id, "event", {
                render: 'main:newsContainer',
                onevent: onNewsPaginationAjaxEvent,
                'javax.faces.behavior.event': 'action'
            });
        });
    } catch (e) {
        alert(e.message);
    }

    return false;
}

function onNewsPaginationAjaxEvent(evt) {
    if (evt.status == 'complete') {
        $('#newsContainerWrapperOuter img.loader').hide();
        $('#newsContainerWrapper').fadeIn('fast');
    }
}
