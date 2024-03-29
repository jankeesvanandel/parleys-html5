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

var initialized = false;

document.addEventListener('DOMContentLoaded', loaded, false);

/** Interval for the photo slideshow */
var play = null;
/** Total width of all photos in the slideshow */
var photoWidth = 0;
/** The DOM element of the current photo (IMG element) */
var activePhoto = null;

function loaded() {
    initializeBanner();
    addMp4Check();
}

function addMp4Check() {
    $("img.thumbnailNotAvailable").each(function(index) {
        $(this).closest("a").attr("href", "http://www.parleys.com");
        $(this).closest("a").click(function() {
            return confirm("This cannot be viewed on iPad. Do you want to go to the Parleys.com Flex client instead?");
        });
    });
}

function initializeBanner() {
    calculateOrientation();

//    if (!initialized) {

        //Paging and Slider Function
        function rotate() {
            var photoIndex = activePhoto.attr("rel") - 1;
            var featuredPhotoListPosition = photoIndex * photoWidth;

            updateButtons(photoIndex);

            //Slider Animation
            $("#featuredPhotoList").css("left", -featuredPhotoListPosition);
        }

        $("#featuredPhotoPaging a").click(function() {
            activePhoto = $(this);

            //Reset Timer
            clearInterval(play);
            rotate();
            rotateSwitch();
            return false;
        });

        function updateButtons(index) {
            $("#featuredPhotoPaging a").removeClass('active');
            $("#featuredPhotoPaging a").eq(index).addClass('active');
            $("#featuredPhotoPaging .featuredPhotoinfo").hide();
            $("#featuredPhotoPaging .featuredPhotoinfo").eq(index).show();
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

        //Run function on launch
        rotateSwitch();

    if (!initialized) {
        updateButtons(0);

        if ('onorientationchange' in window) {
            window.onorientationchange = function() {
                clearInterval(play)
                $("#featuredPhotoPaging a").unbind();
                activePhoto = $('#featuredPhotoPaging a:first');
                calculateOrientation();
                initializeBanner();
            };
        }

        initialized = true;
    }
}

function calculateOrientation() {
    var orientation = window.orientation;
    if (orientation == 0 || orientation == 180) {
        //Set image list to full size of its children
        photoWidth = $("#featuredPhotoList img").first().width();
        var photoCount = $("#featuredPhotoList img").size();
        var photosTotalWidth = photoWidth * photoCount;

        $("#featuredPhotoList").css({'width' : photosTotalWidth});
    }
}

function featuredContentEvent(id) {
    try {
        $('#thumbnailsWrapper').fadeOut('fast', function() {
            $('#lowerThumbnailsContainer > img.loader').show();
            jsf.ajax.request(id, "event", {
                render: 'main:thumbnails',
                onevent: onFeaturedContentAjaxEvent,
                onerror: handlePossibleTimeout,
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
        addMp4Check();
    }
}

function loadMoreFeaturedContentEvent(id) {
    try {
        $('.showMoreThumbnails img.loader').show();
        jsf.ajax.request(id, "event", {
            render: 'main:thumbnails',
            onevent: onFeaturedContentAjaxEvent,
            onerror: handlePossibleTimeout,
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
        addMp4Check();
    }
}
