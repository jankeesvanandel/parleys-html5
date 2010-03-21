package com.parleys.server.frontend.repository.impl;

import com.parleys.server.frontend.domain.Speaker;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Component
public class SpeakersJSONConverter implements JSONConverter<List<Speaker>> {

    @Override
    public List<Speaker> getObject(String source) {
        try {
            JSONArray jsonArray = new JSONArray(source);

            final int length = jsonArray.length();
            final List<Speaker> ret = new ArrayList<Speaker>(length);
            for (int i = 0; i < length; i++) {
                final JSONObject jsonObject = jsonArray.getJSONObject(i);

                final long id = jsonObject.getLong("id");
                final String fullName = jsonObject.has("fullName") ? jsonObject.getString("fullName") : null;
                final String bio = jsonObject.has("bio") ? jsonObject.getString("bio") : null;
                ret.add(new Speaker(id, fullName, bio));
            }

            return ret;
        } catch (JSONException e) {
            //TODO: proper error handling
            throw new RuntimeException("error converting JSON response", e);
        }
    }
}
