/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.sources;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.cloudblue.connect.internal.source.WebhookAuthProvider;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;

public class WebhookAuthProviderTestCase {

    @Test
    public void testPositive() throws UnsupportedEncodingException {
        String secret = "Test-token-key-for-webhook-based-authentication";
        WebhookAuthProvider authProvider = WebhookAuthProvider.builder()
                .token(secret).build();

        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withExpiresAt(Date.from(Instant.ofEpochSecond(new Date().getTime() + 20000)))
                .sign(algorithm);

        Assert.assertTrue(authProvider.authenticate(token));
    }

    @Test
    public void testNegative() {
        String wrongSecret = "Test-token-key-for-webhook-based-authentication";
        WebhookAuthProvider authProvider = WebhookAuthProvider.builder()
                .token(wrongSecret).build();

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ3ZWJob29rX2lk" +
                "IjoiV0gtNjMyLTcyNS0xNjkiLCJ3ZWJob29rX25hbWUiOiJXZWJob29rIGZv" +
                "ciBNdWxlIEV4dGVuc2lvbiBTb3VyY2UiLCJvYmplY3RfY2xhc3MiOiJmdWxma" +
                "WxsbWVudF9yZXF1ZXN0IiwiYWNjb3VudF9pZCI6IlZBLTY3NC0yNTMiLCJwcm" +
                "9kdWN0X2lkIjoiUFJELTYzNS03NTItMjA0IiwiYXBpX3VybCI6Imh0dHBzOi8" +
                "vYXBpLmNvbm5lY3QuY2xvdWQuaW0vcHVibGljL3YxIiwidHJpZ2dlcmVkX2F0" +
                "IjoiMjAyMS0wNi0wN1QwOTo0MDo1OSswMDowMCIsImxhc3Rfc3VjY2Vzc19hd" +
                "CI6bnVsbCwibGFzdF9mYWlsdXJlX2F0IjoiMjAyMS0wNi0wN1QwOTowNzo1MS" +
                "swMDowMCIsInByb2Nlc3NpbmdfdGltZW91dCI6NjAwLCJkYXRhIjp7fSwiZXh" +
                "wIjoxNjIzMDU5NDYwfQ.rpRDfz2z-jIID4tmyXMEIRwl1y3MtSfWFx5RUM9LptI";

        Assert.assertFalse(authProvider.authenticate(token));
    }
}
