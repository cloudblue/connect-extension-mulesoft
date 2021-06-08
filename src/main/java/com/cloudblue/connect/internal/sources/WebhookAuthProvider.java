package com.cloudblue.connect.internal.sources;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class WebhookAuthProvider {
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
            return false;
        }
    }
}
