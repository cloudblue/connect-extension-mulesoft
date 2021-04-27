package com.cloudblue.connect.internal.sources;

public class WebhookAuthProvider {
    private String token;

    public static final class Builder {
        private WebhookAuthProvider authProvider = new WebhookAuthProvider();

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
        return this.token.equals(token);
    }
}
