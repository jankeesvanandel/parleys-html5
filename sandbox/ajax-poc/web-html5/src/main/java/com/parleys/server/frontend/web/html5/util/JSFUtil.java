package com.parleys.server.frontend.web.html5.util;

import com.parleys.server.domain.types.MembershipType;
import com.parleys.server.dto.PresentationOverviewDTO;
import com.parleys.server.dto.SpeakerDTO;
import org.joda.time.DateTime;
import org.joda.time.Days;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;

/**
 * @author Jan-Kees Vanandel
 * @author Stephan Janssen
 */
public class JSFUtil {

    private static DecimalFormat formatter = new DecimalFormat("###,###,###");

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

    // TODO The asset root context can be different depending on the space/channel, this should be reflected in this URL
    public static String spaceThumbnail(final long spaceId,
                                        final String thumbnail) {
        return String.format("http://www.parleys.com/assets/spaces/%1$s/%2$s", spaceId, thumbnail);
    }

    // TODO The asset root context can be different depending on the space/channel, this should be reflected in this URL
    public static String channelThumbnail(final long channelId,
                                          final String thumbnail) {
        return String.format("http://www.parleys.com/assets/channels/%1$s/%2$s", channelId, thumbnail);
    }

    // TODO The asset root context can be different depending on the space/channel, this should be reflected in this URL
    public static String presentationThumbnail(final long presentationId,
                                               final String thumbnail) {
        if (thumbnail.length() == 0) {
            return "./img/nothumb.jpg";
        } else {
            return String.format("http://www.parleys.com/assets/presentations/%1$s/%2$s", presentationId, thumbnail);
        }
    }

    public static String subLabel(final PresentationOverviewDTO presentation) {
        StringBuilder builder = new StringBuilder();
        builder.append("by ");

        for (SpeakerDTO speaker : presentation.getSpeakers()) {
            builder.append(speaker.getName());

            if (presentation.getSpeakers().size() > 1) {
                builder.append(", ");
            }
        }

        builder.append(" (Play time: ");

        float duration = presentation.getTotalDuration();

        int hours = (int)Math.floor(duration / (3600));
        int minutes = (int)Math.floor((duration % (3600)) / (60));
        int seconds = (int)Math.floor(duration) % 60;

        String minutesString = (minutes < 10) ? "0" + minutes : "" + minutes;
        String secondsString = (seconds < 10) ? "0" + seconds : "" + seconds;
        String hoursString = (hours < 10) ? "0" + hours : hours + ":";

        if (hours==0d){
            hoursString = "";
        }

        builder.append(hoursString);
        builder.append(minutesString);
        builder.append(":");
        builder.append(secondsString);

        builder.append(") ");
        builder.append(DateFormat.getDateInstance().format(presentation.getCreatedOn()));
        
        return builder.toString();
    }

    public static String formatNumber(final int value) {
        return formatter.format(value);
    }

    public static String formatDate(final Date date) {
        if (date == null) {
            return "";
        }

        final DateTime start = new DateTime(date.getTime());
        final DateTime end = new DateTime(new Date());

        final Days daysAgo = Days.daysBetween(start, end);
        final int days = daysAgo.getDays();

        String daysAgoPosted;

        if (days == 0) {
            daysAgoPosted = "today";
        } else if (days == 1) {
            daysAgoPosted = "yesterday";
        } else if(days < 7) {
            daysAgoPosted = days + " days ago";
        } else if (days < 32) {
            int week = Math.round(days/7);
            daysAgoPosted = week + (week > 1 ? " weeks ago" : " week ago");
        } else if (days < 365) {
            daysAgoPosted = Math.round(days/30) + " months ago";
        } else {
            int year = Math.round(days/365);
            daysAgoPosted = year + ((year > 1) ? " years ago" : " year ago");
        }

        return daysAgoPosted;
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
        builder.append("<div style='width:");

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
}
