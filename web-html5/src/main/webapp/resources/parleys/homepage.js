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

var myScroll;

function setHeight() {
	var headerH = document.getElementById('logoBar').offsetHeight;
	var wrapperH = window.innerHeight - headerH;
	document.getElementById('innerContainer').style.height = wrapperH + 'px';
}

window.addEventListener('onorientationchange' in window ? 'orientationchange' : 'resize', setHeight, false);
document.addEventListener('touchstart', function(e){ e.preventDefault(); }, false);
document.addEventListener('DOMContentLoaded', loaded, false);

//$(document).ready(function() {
function loaded() {
	setHeight();
	myScroll = new iScroll('content');

    $("#logoBar a").each(function(index) { this.ontouchend=onTouchEndTriggerClick; });
    $("#footer a").each(function(index) { this.ontouchend=onTouchEndTriggerClick; })
}
//});

function onTouchEndTriggerClick() {
    window.location = this.href;
}

function featuredContentEvent(id) {
    try {
        $('#thumbnailsWrapper').fadeOut('fast', function() {
            $('#thumbnailsWrapperOuter img.loader').show();
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
        $('#thumbnailsWrapperOuter img.loader').hide();
        $('#thumbnailsWrapper').fadeIn('fast');
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
