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

var DIGG = "digg";
var FACEBOOK = "faceBook";
var GOOGLE_BOOKMARK = "googleBookmark";
var MYSPACE = "myspace";
var STUMBLE_UPON = "stumbleUpon";
var TECHNORATI = "technorati";
var TWITTER = "twitter";
var YAHOO_BOOKMARK = "yahooBookmark";

function post(to, link, title) {
    var targetURL = "";
    switch (to) {
        case DIGG :
            targetURL = "http://digg.com/submit?phase=2&url=" + link + "&title=" + title;
            break;
        case FACEBOOK :
            targetURL = "http://www.facebook.com/sharer.php?u=" + link + "&t=" + title;
            break;
        case GOOGLE_BOOKMARK :
            targetURL = "http://www.google.com/bookmarks/mark?op=add&bkmk=" + link + "&title=" + title;
            break;
        case MYSPACE :
            targetURL = "http://www.myspace.com/Modules/PostTo/Pages/?u=" + link + "&t=" + title;
            break;
        case STUMBLE_UPON :
            targetURL = "http://www.stumbleupon.com/submit?url=" + link;
            break;
        case TECHNORATI :
            targetURL = "http://technorati.com/faves/?add=" + link;
            break;
        case TWITTER :
            targetURL = "http://twitter.com/home?status=" + link + " " + title;
            break;
        case YAHOO_BOOKMARK :
            targetURL = "http://myweb2.search.yahoo.com/myresults/bookmarklet?u=" + link + "&t=" + title;
            break;
        default :
            break;
    }

    var load = window.open(targetURL, '', 'scrollbars=no,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no')
}

var presentationId;
var presentationTitle;

$(document).bind('ready', function() {
    $("#tabs").tabs({ fx: {opacity: 'toggle' } });

    $('#tabs').bind('tabsselect', function(event, ui) {
        if (ui.index == 2) {
            $("#tabs").css("left", "0px");
            $("#tabs").css("width", "750px");
        } else {
            $("#tabs").css("left", "0px");
            $("#tabs").css("width", "550px");
        }
    });

    presentationId = $("#mainContent .presentationId").html();
    presentationTitle = $("#mainContent .presentationTitle").html();

    $("#shareButton").click(function() {
        toggleShare();
    });

    $("#tabs-1 a").bind("click", function() {
        var type = $(this).attr("type");
        var link = "http://www.parleys.com/d/" + presentationId;

        post(type, link, presentationTitle);
        return false;
    });
});

var isShareOpen = false;
function toggleShare() {
    if (isShareOpen) {
        $("#tabs").css("left", "-710px");
        isShareOpen = false;
    } else {
        $("#tabs").css("left", "0px");
        $("#tabs").css("width", "550px");
        isShareOpen = true;
    }
}
