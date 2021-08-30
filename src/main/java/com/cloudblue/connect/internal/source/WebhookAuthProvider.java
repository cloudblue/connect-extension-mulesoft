/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.source;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebhookAuthProvider {
    private static final Logger logger = LoggerFactory.getLogger(WebhookAuthProvider.class);

    private String token;

    public static final class Builder {
        private final WebhookAuthProvider authProvider = new WebhookAuthProvider();

        public Builder token(String token) {
            authProvider.token = token;
            return this;
        }

        public WebhookAuthProvider build() {
            return authProvider;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean authenticate(String token) {
        try {
            if (token != null) {
                Algorithm algorithm = Algorithm.HMAC256(this.token);
                JWTVerifier verifier = JWT.require(algorithm).build();
                verifier.verify(token);

                return true;
            } else {
                return false;
            }
        } catch (JWTVerificationException exception){
            logger.error("Error during webhook auth validation.", exception);
            return false;
        }
    }
}
