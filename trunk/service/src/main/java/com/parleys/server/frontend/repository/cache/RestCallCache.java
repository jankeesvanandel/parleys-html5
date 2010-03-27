package com.parleys.server.frontend.repository.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class RestCallCache<T> {
    private Cache cache;
    private static final Logger LOGGER = Logger.getLogger(RestCallCache.class);

    public T get(String key) {
        final Element element = getCache().get(key);
        if (element == null) {
            return null;
        } else {
            return (T) element.getValue();
        }
    }

    public void put(String key, T value) {
        getCache().put(new Element(key, value));
    }

    private synchronized Cache getCache() {
        if (cache == null) {
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("Initializing EhCache...");
            }

            cache = CacheManager.getInstance().getCache("restCalls");

            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("EhCache initialized");
            }
        }
        return cache;
    }

}
