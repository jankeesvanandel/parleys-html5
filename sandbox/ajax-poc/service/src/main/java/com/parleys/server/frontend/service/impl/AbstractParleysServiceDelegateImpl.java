package com.parleys.server.frontend.service.impl;

import com.parleys.io.amf.client.AMFClientFactory;
import com.parleys.server.frontend.service.ParleysServiceDelegate;
import com.parleys.server.service.ParleysService;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;

/**
 * @author Stephan Janssen
 */
public abstract class AbstractParleysServiceDelegateImpl implements ParleysServiceDelegate {

    // TODO Inject the AMF context root using a property value
    private static final String PARLEYS_CONTEXT = "http://www.parleys.com/parleysserver/";
    
    private static final String MESSAGEBROKER_AMF = "messagebroker/amf";

    @SuppressWarnings("unchecked")
    private <T> T getService(String serviceName, Class<T> serviceClass) throws ClientStatusException {
        AMFClientFactory clientFactory = new AMFClientFactory();
        clientFactory.setServiceClass(serviceClass);
        clientFactory.setServiceName(serviceName);
        clientFactory.setUrl(getAMFChannel());
        return (T) clientFactory.create();
    }

    /**
     * Returns either the AMF service or local bean (still to do) to operate on.
     *
     * @return ParleysService instance.
     * @throws flex.messaging.io.amf.client.exceptions.ClientStatusException flex AMF client status exception
     */
    protected ParleysService getParleysServiceProxy() throws ClientStatusException {
        return getService("parleysService", ParleysService.class);
    }

    /**
     *
     * @return the target AMF channel
     */
    protected String getAMFChannel() {
        return PARLEYS_CONTEXT + MESSAGEBROKER_AMF;
    }
}
