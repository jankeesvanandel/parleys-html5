package com.parleys.server.frontend.web.html5.util;

import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * $Author$
 * $Revision$
 */
public class JSFUtil {

    public static int sizeOf(Collection coll) {
        return coll.size();
    }

    public static String shortenTo(int toLength, String input) {
        if (input.length() > toLength) {
            return input.substring(0, toLength - 3) + "...";
        } else {
            return input;
        }
    }

    public static String spaceThumbnail(long spaceId, String thumbnail) {
        return String.format("http://www.parleys.com/assets/spaces/%1$s%2$s",
                spaceId, thumbnail);
    }

    public static String channelThumbnail(long channelId, String thumbnail) {
        return String.format("http://www.parleys.com/assets/channels/%1$s%2$s",
                channelId, thumbnail);
    }

    public static String presentationThumbnail(long presentationId, String thumbnail) {
        return String.format("http://www.parleys.com/assets/presentations/%1$s%2$s",
                presentationId, thumbnail);
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
            return "on" + new SimpleDateFormat("dd-MM-yyyy").format(date);
        }
    }
}
