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
package com.parleys.io.amf.client;

import java.lang.reflect.Proxy;

/**
 * AMF client service class from <a
 * href="http://bugs.adobe.com/jira/browse/BLZ-246">here</a>.
 *
 * @author <a href="mailto:rflament@laposte.net">Rémi Flament</a>
 */
public class AMFClientFactory {

    private Class<?> serviceClass;

    private String serviceUrl;

    private String username;

    private String password;

    private String serviceName;

    /**
     * Create a new ClientFactory for the set <code>serviceClass</code>.
     *
     * @return A newly created ClientFactory.
     */
    public Object create()  {
        AMFClientProxy handler = new AMFClientProxy(serviceUrl, serviceName);

        if (username != null) {
            handler.setUsername(username);
            handler.setPassword(password);
        }

        return Proxy.newProxyInstance(serviceClass.getClassLoader(), getImplementingClasses(), handler);
    }

    /**
     * Get the service class.
     * @return The service class.
     */
    public Class<?> getServiceClass() {
        return serviceClass;
    }

    /**
     * Set the service class.
     * @param serviceClass The service class.
     */
    public void setServiceClass(Class<?> serviceClass) {
        this.serviceClass = serviceClass;
    }

    /**
     * Get all implementing classes.
     * @return A new array with all implementing classes.
     */
    protected Class<?>[] getImplementingClasses() {
        return new Class[]{serviceClass};
    }

    /**
     * Get the URL.
     * @return The URL.
     */
    public String getServiceUrl() {
        return serviceUrl;
    }

    /**
     * Set the URL.
     * @param serviceUrl The URL.
     */
    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    /**
     * Get the username.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username.
     * @param username The username.
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Get the password.
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password.
     * @param password The password.
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Get the serviceName.
     * @return The serviceName.
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Set the serviceName.
     * @param serviceName The serviceName.
     */
    public void setServiceName(final String serviceName) {
        this.serviceName = serviceName;
    }
}

