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
package com.parleys.server.frontend.web.ipad.filters;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.Key;

/**
 * This program encrypts and decrypts Strings using AES.
 *
 * @author Jan-Kees van Andel
 */
public class AESEncrypter {

    private static final Logger LOGGER = Logger.getLogger(AESEncrypter.class);

    private static final String ENCODING = "UTF8";

    private static final Key KEY;

    static {
        final String property = System.getProperty("ipad.cookie.encryption.key");
        if (property == null) {
            throw new RuntimeException("ipad.cookie.encryption.key property not set");
        }
        try {
            KEY = new AESKey(property);
        } catch (Exception e) {
            LOGGER.fatal(e);
            throw new RuntimeException("Error generating key");
        }
    }

    public static final AESEncrypter INSTANCE = new AESEncrypter(KEY);

    private final Cipher ecipher;
    private final Cipher dcipher;

    private AESEncrypter(final Key key) {
        try {
            ecipher = Cipher.getInstance("AES");
            dcipher = Cipher.getInstance("AES");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
            LOGGER.fatal(e);
            throw new RuntimeException("Error generating key");
        }
    }

    public String encrypt(final String str) {
        try {
            // Encode the string into bytes using utf-8
            final byte[] utf8 = str.getBytes(ENCODING);

            // Encrypt
            final byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return Base64.encodeBase64URLSafeString(enc);
        } catch (Exception e) {
            LOGGER.fatal(e);
            throw new RuntimeException("Error encrypting string");
        }
    }

    public String decrypt(final String str) {
        try {
            // Decode base64 to get bytes
            final byte[] dec = Base64.decodeBase64(str);

            // Decrypt
            final byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, ENCODING);
        } catch (Exception e) {
            LOGGER.fatal(e);
            throw new RuntimeException("Error decrypting string");
        }
    }

    /**
     * Trivial key implementation for use with AES cipher.
     */
    private static final class AESKey implements Key {
        private final byte[] keyData;

        public AESKey(final String keyData) throws UnsupportedEncodingException {
            this.keyData = keyData.getBytes(ENCODING);
        }

        @Override
        public String getAlgorithm() {
            return "AES";
        }

        @Override
        public String getFormat() {
            return "RAW";
        }

        @Override
        public byte[] getEncoded() {
            return keyData;
        }
    }
}
