package com.parleys.io.amf.client;

import flex.messaging.io.amf.client.exceptions.ClientStatusException;

import java.lang.reflect.Proxy;

/**
 * @author Stephan Janssen
 */
/**
 * AMF client service class from <a
 * href="http://bugs.adobe.com/jira/browse/BLZ-246">here</a>.
 *
 * @author <a href="mailto:rflament@laposte.net">RŽmi Flament</a>
 *
 */
public class AMFClientFactory {
	private Class<?> serviceClass;

	private String url;

	private String username;

	private String password;

	private String serviceName;

	public Class<?> getServiceClass() {
		return serviceClass;
	}

	public void setServiceClass(Class<?> serviceClass) {
		this.serviceClass = serviceClass;
	}

	public Object create() throws ClientStatusException {

		AMFClientProxy handler = new AMFClientProxy(url, serviceName);

		if (username != null) {
			handler.setUsername(username);
			handler.setPassword(password);
		}

		return Proxy.newProxyInstance(serviceClass.getClassLoader(), getImplementingClasses(), handler);
	}

	protected Class<?>[] getImplementingClasses() {
		return new Class[] { serviceClass };
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(final String serviceName) {
		this.serviceName = serviceName;
	}
}

