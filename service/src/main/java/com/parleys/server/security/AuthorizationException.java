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
package com.parleys.server.security;

/**
 * @author Stephan Janssen
 */
public class AuthorizationException extends Exception {

    private static final long serialVersionUID = -5254305053400819901L;

    /**
     * Full constructor.
     *
     * @param message The error message.
     * @param cause The cause.
     */
    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     *
     * @param message The error message.
     */
    public AuthorizationException(String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param cause The cause.
     */
    public AuthorizationException(Throwable cause) {
        super(cause);
    }
}
