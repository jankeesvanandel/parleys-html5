package com.parleys.server.security;

/**
 * @author Stephan Janssen
 */
public class AuthorizationException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -5254305053400819901L;

	public AuthorizationException() {
		super ();
	}

	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorizationException(String message) {
		super(message);
	}

	public AuthorizationException(Throwable cause) {
		super(cause);
	}
}