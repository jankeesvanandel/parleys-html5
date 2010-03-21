package com.parleys.server.frontend.repository.impl;

import com.parleys.server.frontend.domain.Presentation;
import com.parleys.server.frontend.domain.Speaker;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *
 */
@Component
public class PresentationsJSONConverter implements JSONConverter<List<Presentation>> {

    private final SpeakersJSONConverter speakersJSONConverter;

    @Autowired
    public PresentationsJSONConverter(SpeakersJSONConverter speakersJSONConverter) {
        this.speakersJSONConverter = speakersJSONConverter;
    }

    @Override
    public List<Presentation> getObject(String source) {
        try {
            JSONArray jsonArray = new JSONArray(source);

            final int length = jsonArray.length();
            final List<Presentation> ret = new ArrayList<Presentation>(length);
            for (int i = 0; i < length; i++) {
                final JSONObject jsonObject = jsonArray.getJSONObject(i);

                final long id = jsonObject.getLong("id");
                final String summary = jsonObject.getString("summary");
                final double duration = jsonObject.getDouble("duration");
                final String title = jsonObject.getString("title");
                final String thumbnail = jsonObject.getString("thumbnail");
                final Set<String> keywords = getKeywords(jsonObject);
                final String mp3url = jsonObject.has("mp3url") ? jsonObject.getString("mp3url") : null;
                final long pageId = jsonObject.getLong("pageId");
                final int totalVotes = jsonObject.getInt("totalVotes");
                final int totalDownloads = jsonObject.getInt("totalDownloads");
                final Set<Speaker> speakers = getSpeakers(jsonObject);
                ret.add(new Presentation(
                        id, summary, duration, title, thumbnail, keywords, mp3url,
                        pageId, totalVotes, totalDownloads, speakers));
            }

            return ret;
        } catch (JSONException e) {
            //TODO: proper error handling
            throw new RuntimeException("error converting JSON response", e);
        }
    }

    private Set<String> getKeywords(JSONObject jsonObject) throws JSONException {
        final String keywords = jsonObject.getString("keywords");

        return new HashSet<String>(Arrays.asList(keywords.split(",")));
    }

    private Set<Speaker> getSpeakers(JSONObject jsonObject) throws JSONException {
        final String speakersString = jsonObject.getString("speakers");

        final List<Speaker> speakerList = speakersJSONConverter.getObject(speakersString);

        return new HashSet<Speaker>(speakerList);
    }
}
