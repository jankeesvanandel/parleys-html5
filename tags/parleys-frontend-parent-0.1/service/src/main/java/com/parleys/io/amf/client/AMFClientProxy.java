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

import com.parleys.server.common.exception.AuthorizationException;
import flex.messaging.io.amf.ASObject;
import flex.messaging.io.amf.client.AMFConnection;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import flex.messaging.io.amf.client.exceptions.ServerStatusException;
import org.apache.commons.codec.binary.Base64;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * AMF client service class from <a
 * href="http://bugs.adobe.com/jira/browse/BLZ-246">here</a>.
 *
 * @author <a href="mailto:rflament@laposte.net">Rémi Flament</a>
 */
public class AMFClientProxy implements InvocationHandler {

    private final String serviceName;

    private final String serviceUrl;

    private String username;

    private String password;
    private static final String AUTHORIZATIONEXCEPTION_CLASSNAME = AuthorizationException.class.getName();

    /**
     * Full constructor.
     *
     * @param serviceUrl The service URL.
     * @param serviceName The service name.
     */
    public AMFClientProxy(final String serviceUrl, final String serviceName) {
        this.serviceName = serviceName;
        this.serviceUrl = serviceUrl;
    }

    /**
     * Invoke the proxy.
     *
     * @param proxy The proxy.
     * @param method The method to invoke.
     * @param args The method arguments.
     * @return The return value of the method.
     * @throws ClientStatusException If an error occurs on the client during the service call.
     * @throws ServerStatusException If an error occurs on the server during the service call.
     */
    public Object invoke(final Object proxy,
                         final Method method,
                         final Object[] args) throws ClientStatusException, ServerStatusException {
        AMFConnection connection = new AMFConnection();

        if (username != null) {
            final String userpasswd = username + ":" + password;
            final String encoding = new String(Base64.encodeBase64(userpasswd.getBytes()));
            connection.addHttpRequestHeader("Authorization", "Basic " + encoding);
        }

        connection.connect(serviceUrl);

        Object[] params = args;
        if (null == params) {
            params = new Object[0];
        }

        Object callResult = null;
        try {
            callResult = connection.call(serviceName + "." + method.getName(), params);
        } catch (ServerStatusException e) {
            ASObject asObject = (ASObject) e.getData();
            Object cause = asObject.get("rootCause");
            if (cause instanceof AuthorizationException) {
                throw (AuthorizationException) cause;
            } else {
                throw e;
            }
        } finally {
            connection.close();
        }

        if ((method.getReturnType() != null) && method.getReturnType().equals(int.class)) {
            callResult = ((Double) callResult).intValue();
        } else if ((method.getReturnType() != null) && method.getReturnType().equals(Long.class)) {
            callResult = ((Double) callResult).longValue();
        } else if (method.getReturnType().isArray()) {
            callResult = convertReturnValueToArray(method, callResult);
        }

        return callResult;
    }

    @SuppressWarnings("SuspiciousSystemArraycopy")
    private Object convertReturnValueToArray(Method method, Object callResult) {
        Class<?> componentType = method.getReturnType().getComponentType();
        int length = ((Object[]) callResult).length;
        Object[] arrayOfValidType = (Object[]) Array.newInstance(componentType, length);
        System.arraycopy(callResult, 0, arrayOfValidType, 0, ((Object[]) callResult).length);
        callResult = arrayOfValidType;
        return callResult;
    }

    /**
     * Get the username.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the username.
     * @param username The username.
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Get the username.
     * @return The username.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the password.
     * @param password The password.
     */
    public void setPassword(final String password) {
        this.password = password;
    }
}
