package com.parleys.io.amf.client;

import flex.messaging.io.amf.client.AMFConnection;
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

    private final String url;

    private String username;

    private String password;

    /**
     * 
     * @param url
     * @param serviceName
     */
    public AMFClientProxy(final String url, final String serviceName) {
        this.serviceName = serviceName;
        this.url = url;
    }

    /**
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(final Object proxy,
                         final Method method,
                         final Object[] args) throws Throwable {

        AMFConnection connection = new AMFConnection();

        if (username != null) {
            final String userpasswd = username + ":" + password;
            final String encoding = new String(Base64.encodeBase64(userpasswd.getBytes()));
            connection.addHttpRequestHeader("Authorization", "Basic " + encoding);
        }

        connection.connect(url);

        Object[] params = args;
        if (null == params) {
            params = new Object[0];
        }

        Object callResult = null;
        try {
            callResult = connection.call(serviceName + "." + method.getName(), params);
        } finally {
            connection.close();
        }

        if ((method.getReturnType() != null) && method.getReturnType().equals(int.class)) {
            callResult = ((Double) callResult).intValue();
        } else if ((method.getReturnType() != null) && method.getReturnType().equals(Long.class)) {
            callResult = ((Double) callResult).longValue();
        } else if (method.getReturnType().isArray()) {
            Object[] arrayOfValidType = (Object[]) Array.newInstance(method.getReturnType().getComponentType(),
                    ((Object[]) callResult).length);
            System.arraycopy(callResult, 0, arrayOfValidType, 0, ((Object[]) callResult).length);
            callResult = arrayOfValidType;
        }

        return callResult;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
