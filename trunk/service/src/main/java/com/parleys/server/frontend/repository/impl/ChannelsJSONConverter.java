package com.parleys.server.frontend.repository.impl;

import com.parleys.server.frontend.domain.Channel;
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
public class ChannelsJSONConverter implements JSONConverter<List<Channel>> {

    @Override
    public List<Channel> getObject(String source) {
        try {
            JSONArray jsonArray = new JSONArray(source);

            final int length = jsonArray.length();
            final List<Channel> ret = new ArrayList<Channel>(length);
            for (int i = 0; i < length; i++) {
                final JSONObject jsonObject = jsonArray.getJSONObject(i);

                final long id = jsonObject.getLong("id");
                final String name = jsonObject.getString("name");
                final String description = jsonObject.getString("description");
                final String thumbnail = jsonObject.getString("thumbnail");
                ret.add(new Channel(id, name, description, thumbnail));
            }

            return ret;
        } catch (JSONException e) {
            //TODO: proper error handling
            throw new RuntimeException("error converting JSON response", e);
        }
    }
}
