package com.parleys.server.frontend.repository.cache;

import com.parleys.server.frontend.domain.Channel;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 *
 */
@Component
public class CachedRestTemplate<C> extends RestTemplate {

    private static final Logger LOGGER = Logger.getLogger(CachedRestTemplate.class);

    private final JSONRestTemplate restTemplate;

    private final RestCallCache<C> cache;

    @Autowired
    public CachedRestTemplate(JSONRestTemplate restTemplate, RestCallCache<C> cache) {
        this.restTemplate = restTemplate;
        this.cache = cache;
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T> T getForObject(String url, Class<T> responseType, Object... urlVariables) throws RestClientException {
        try {
            final Class<C> clazz = (Class<C>) responseType;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Argument 'responseType' is not of type Class<C>");
        }

        UriTemplate uriTemplate = new UriTemplate(url);
        final String key = uriTemplate.expand(urlVariables).toString();

        final C cachedElement = cache.get(key);
        if (cachedElement == null) {
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("Invoking REST URL: " + url);
            }
            final T object = restTemplate.getForObject(url, responseType, urlVariables);
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("Putting object in cache. Key: " + url);
            }
            cache.put(key, (C) object);
            return object;
        } else {
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("Loading object from cache. Key: " + url);
            }
            return (T) cachedElement;
        }
    }

    @Override
    public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException {
        return this.getForObject(url.toString(), responseType);
    }
}
