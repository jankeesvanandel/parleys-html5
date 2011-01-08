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
package com.parleys.server.frontend.web.jsf.util;

import com.parleys.server.domain.types.MembershipType;
import com.parleys.server.dto.PresentationOverviewDTO;
import com.parleys.server.dto.SpeakerDTO;
import org.joda.time.DateTime;
import org.joda.time.Days;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Generic functions to be used in the view.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
public class JSFUtil {

    private JSFUtil() {
        throw new AssertionError("utility class");
    }

    public static FacesContext fc() {
        return FacesContext.getCurrentInstance();
    }

    public static int sizeOf(final Collection coll) {
        if (coll != null) {
            return coll.size();
        } else {
            return 0;
        }
    }

    public static String shortenTo(final int toLength,
                                   final String input) {
        if (input.length() > toLength) {
            return input.substring(0, toLength - 3) + "...";
        } else {
            return input;
        }
    }

    public static int floor(double in) {
        return (int) Math.floor(in);
    }

    // TODO The asset root context can be different depending on the space/channel, this should be reflected in this URL
    public static String spaceThumbnail(final long spaceId, final String thumbnail) {
        return String.format("http://www.parleys.com/assets/spaces/%1$s/%2$s", spaceId, thumbnail);
    }

    // TODO The asset root context can be different depending on the space/channel, this should be reflected in this URL
    public static String channelThumbnail(final long channelId, final String thumbnail) {
        return String.format("http://www.parleys.com/assets/channels/%1$s/%2$s", channelId, thumbnail);
    }

    // TODO The asset root context can be different depending on the space/channel, this should be reflected in this URL
    public static String presentationThumbnail(final long presentationId, final String thumbnail) {
        if (thumbnail == null || thumbnail.length() == 0) {
            return "./img/nothumb.jpg";
        } else {
            return String.format("http://www.parleys.com/assets/presentations/%1$s/%2$s", presentationId, thumbnail);
        }
    }

    public static String title(final PresentationOverviewDTO presentation) {
        final float duration = presentation.getTotalDuration();
        final int hours = (int) Math.floor(duration / 3600);
        final int minutes = (int) Math.floor((duration % 3600) / 60);
        final int seconds = (int) Math.floor(duration) % 60;

        final String minutesString = (minutes < 10) ? "0" + minutes : "" + minutes;
        final String secondsString = (seconds < 10) ? "0" + seconds : "" + seconds;
        String hoursString = (hours < 10) ? "0" + hours + ":" : hours + ":";

        if (hours == 0) {
            hoursString = "";
        }

        final StringBuilder builder = new StringBuilder();
        builder.append(presentation.getTitle());
        builder.append(" (");
        builder.append(hoursString);
        builder.append(minutesString);
        builder.append(":");
        builder.append(secondsString);
        builder.append(") ");
        return builder.toString();        
    }

    public static String subLabel(final PresentationOverviewDTO presentation) {
        final StringBuilder builder = new StringBuilder();
        builder.append("by ");

        List<SpeakerDTO> speakers = presentation.getSpeakers();
        int counter = 1;
        for (SpeakerDTO speaker : speakers) {
            builder.append(speaker.getName());
            if (speakers.size() > 1 && counter++ < speakers.size()) {
                builder.append(", ");
            }
        }
        builder.append(" - ");
        builder.append(DateFormat.getDateInstance().format(presentation.getCreatedOn()));

        return builder.toString();
    }

    public static String formatNumber(final int value) {
        final DecimalFormat format = new DecimalFormat("###,###,###");

        return format.format(value);
    }

    public static String formatDate(final Date date) {
        if (date == null) {
            return "";
        }

        final DateTime start = new DateTime(date.getTime());
        final DateTime end = new DateTime(new Date());

        final Days daysAgo = Days.daysBetween(start, end);
        final int days = daysAgo.getDays();

        String whileAgoPosted;
        if (days == 0) {
            whileAgoPosted = "today";
        } else if (days == 1) {
            whileAgoPosted = "yesterday";
        } else if (days < 7) {
            whileAgoPosted = days + " days ago";
        } else if (days < 32) {
            int week = Math.round(days / 7);
            whileAgoPosted = week + (week > 1 ? " weeks ago" : " week ago");
        } else if (days < 365) {
            whileAgoPosted = Math.round(days / 30) + " months ago";
        } else {
            int year = Math.round(days / 365);
            whileAgoPosted = year + ((year > 1) ? " years ago" : " year ago");
        }

        return whileAgoPosted;
    }

    public static String subscription(final String membership) {
        if (membership.equals(MembershipType.SUBSCRIPTION.name())) {
            return "Subscription Based";
        } else {
            return "";
        }
    }

    public static String totalVotes(final int value) {
        if (value == 0) {
            return "no votes";
        } else if (value == 1) {
            return "1 vote";
        } else {
            return value + " votes";
        }
    }

    public static String ratingBar(final double value) {
        final StringBuilder builder = new StringBuilder();
        builder.append("<div class='rating_bar'>");
        builder.append("<div style='position:absolute;width:");

        // 14.6 equals 1 star in pixels and 1.36 is 1% of the actual total width of the 5 stars
        final double width = value * 14.6 * 1.36;
        builder.append(width);
        builder.append("%;'></div></div>");
        return builder.toString();
    }

    public static boolean validationErrors() {
        final FacesMessage.Severity maximumSeverity = fc().getMaximumSeverity();
        return maximumSeverity != null
            && (maximumSeverity.equals(FacesMessage.SEVERITY_ERROR)
            || maximumSeverity.equals(FacesMessage.SEVERITY_FATAL));
    }

    public static boolean theCurrentEventIsNoPageAction() {
        final FacesContext fc = fc();
        return fc.isPostback()
            || fc.isValidationFailed()
            || fc.getPartialViewContext().isAjaxRequest();
    }

    public static void writeComponentAttribute(UIComponent component, FacesContext fc, String name, Object value) throws IOException {
        ResponseWriter w = fc.getResponseWriter();
        ELContext elContext = fc.getELContext();
        ValueExpression expression = component.getValueExpression(name);
        if (expression != null) {
            value = expression.getValue(elContext);
        }
        w.writeAttribute(name, value, name);
    }
}
