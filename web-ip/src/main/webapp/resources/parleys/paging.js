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

$(document).ready(initializeClickableList);

function initializeClickableList() {
    $("div.overviewList li").each(function() {
        // Disable links in the text
        $(this).find(".overviewListText a").click(function() {
            // Redirect the link to the same location as the rest of the row
            var newLocation = $(this).closest("li").find(".overviewListThumbnail a").attr("href");
            document.location = newLocation;

            // Disable default link action
            return false;
        });

        // Make each row behave like a link
        $(this).click(function() {
            var newLocation = $(this).find(".overviewListThumbnail a").attr("href");
            document.location = newLocation;
        });

        // And make the rows look like links
        $(this).css("cursor", "pointer");
    });
}

function showInlineLoader() {
    $('img.inlineLoader').show();
}

function onPagingAjaxEvent(evt) {
    if (evt.status == 'success') {
        $('img.inlineLoader').hide();
        initializeClickableList();
    }
}
