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

/**
 * @author Stephan Janssen
 */
public abstract class AbstractParleysServiceDelegateImpl implements ParleysService {

    // TODO Inject the AMF context root using a property value
    private static final String PARLEYS_CONTEXT = "http://www.parleys.com/parleysserver/";

    private static final String MESSAGEBROKER_AMF = "messagebroker/amf";

    @SuppressWarnings("unchecked")
    private <T> T getService(String serviceName, Class<T> serviceClass) {
        AMFClientFactory clientFactory = new AMFClientFactory();
        clientFactory.setServiceClass(serviceClass);
        clientFactory.setServiceName(serviceName);
        clientFactory.setServiceUrl(getAMFChannel());
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
     * @return the target AMF channel
     */
    protected String getAMFChannel() {
        return PARLEYS_CONTEXT + MESSAGEBROKER_AMF;
    }
}
