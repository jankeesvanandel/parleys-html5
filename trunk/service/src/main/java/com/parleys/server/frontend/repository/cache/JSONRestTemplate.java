package com.parleys.server.frontend.repository.cache;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

/**
 *
 */
@Component
public class JSONRestTemplate extends RestTemplate {

    private final RestTemplate restTemplate;

    @Autowired
    public JSONRestTemplate(@Qualifier("parleysTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> T getForObject(String url, Class<T> responseType, Object... urlVariables) throws RestClientException {
        try {
//            System.out.println("url = " + url);
//            System.out.println("urlVariables = " + Arrays.toString(urlVariables));
            final String stringResult = restTemplate.getForObject(url, String.class, urlVariables);
            ObjectMapper mapper = new ObjectMapper();
            final T object = mapper.readValue(stringResult, responseType);

            return object;
        } catch (IOException e) {
            throw new RestClientException("Error reading JSON response", e);
        }
    }

    @Override
    public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException {
        return this.getForObject(url.toString(), responseType);
    }
}
