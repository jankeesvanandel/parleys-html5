package com.parleys.server.frontend.web.html5.util;

import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Jan-Kees Vanandel
 */
public class JSFUtil {

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
        return String.format("http://www.parleys.com/assets/presentations/%1$s/%2$s", presentationId, thumbnail);
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }

        date = DateUtils.truncate(date, Calendar.DATE);
        final Date today = DateUtils.truncate(new Date(), Calendar.DATE);
        final Calendar yesterdayCalendar = new GregorianCalendar();
        yesterdayCalendar.add(Calendar.DATE, -1);
        final Date yesterday = DateUtils.truncate(yesterdayCalendar.getTime(), Calendar.DATE);
        if (date.equals(today)) {
            return "today";
        } else if (date.equals(yesterday)) {
            return "yesterday";
        } else {
            return "on " + new SimpleDateFormat("dd-MM-yyyy").format(date);
        }
    }
}
