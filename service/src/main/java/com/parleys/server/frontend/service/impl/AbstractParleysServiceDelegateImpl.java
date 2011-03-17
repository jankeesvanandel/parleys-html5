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
package com.parleys.server.frontend.service.impl;

import com.parleys.io.amf.client.AMFClientFactory;
import com.parleys.server.frontend.service.ParleysService;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Stephan Janssen
 */
public abstract class AbstractParleysServiceDelegateImpl implements ParleysService {

    private String amfEndPoint = "http://www.parleys.com/parleysserver/";

    private static final String MESSAGEBROKER_AMF = "messagebroker/amf";

    @SuppressWarnings("unchecked")
    private <T> T getService(final String serviceName,
                             final Class<T> serviceClass) {
        final AMFClientFactory clientFactory = new AMFClientFactory();
        clientFactory.setServiceClass(serviceClass);
        clientFactory.setServiceName(serviceName);
        clientFactory.setServiceUrl(getAMFChannel());
        return (T) clientFactory.create();
    }

    @SuppressWarnings("unchecked")
    private <T> T getServiceWithCredentials(final String serviceName,
                                            final Class<T> serviceClass, String username,
                                            final String password) {
        final AMFClientFactory clientFactory = new AMFClientFactory();
        clientFactory.setServiceClass(serviceClass);
        clientFactory.setServiceName(serviceName);
        clientFactory.setServiceUrl("http://www.parleys.com/parleysserver/messagebroker/amf");
        clientFactory.setUsername(username);
        clientFactory.setPassword(password);
        return (T) clientFactory.create();
    }

    /**
     * Returns either the AMF service or local bean (still to do) to operate on.
     *
     * @return ParleysService instance.
     */
    protected com.parleys.server.service.ParleysService getParleysServiceProxy() {
        return getService("parleysService", com.parleys.server.service.ParleysService.class);
    }

    /**
     * Returns an authenticated AMF service.
     *
     * @param username user name
     * @param password user password
     * @return ParleysService instance.
     */
    protected com.parleys.server.service.ParleysService getParleysServiceWithCredentialsProxy(final String username,
                                                                                              final String password) {
        return getServiceWithCredentials("parleysService",
                                         com.parleys.server.service.ParleysService.class,
                                         username, password);
    }

    /**
     * @return the target AMF channel
     */
    protected String getAMFChannel() {
        return amfEndPoint + MESSAGEBROKER_AMF;
    }

    // @Autowired
    public void setAmfEndPoint(final String amfEndPoint) {
    }

    public String getAmfEndPoint() {
        return amfEndPoint;
    }
}
