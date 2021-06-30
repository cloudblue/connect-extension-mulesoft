package com.cloudblue.connect.test.api.client;

import com.cloudblue.connect.api.clients.parsers.ResponseUnmarshaller;
import com.cloudblue.connect.api.clients.parsers.jackson.JacksonResponseUnmarshaller;
import com.cloudblue.connect.api.models.subscription.CBCRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JacksonResponseUnmarshallerTestCase {

    ResponseUnmarshaller unmarshaller = null;

    @Before
    public void setUp() {
        unmarshaller = new JacksonResponseUnmarshaller();
    }

    @Test
    public void testUnmarshalJsonArrayAsString() throws Exception {
        String value = "[{'key': 'value'}]";
        String returnValue = unmarshaller.unmarshal(value, String.class);

        Assert.assertEquals(value, returnValue);
    }

    @Test
    public void testUnmarshalWithSigningKeyClass() throws Exception {
        String value = "sign-string-{'id': 'PR-000-000'}";
        CBCRequest request = unmarshaller.unmarshal(value, CBCRequest.class);

        Assert.assertNotNull(request);
        Assert.assertEquals("PR-000-000", request.getId());
    }

    @Test
    public void testUnmarshalWithSigningKeyType() throws Exception {
        String value = "sign-string-{'id': 'PR-000-000'}";
        CBCRequest request = (CBCRequest) unmarshaller.unmarshal(value, new TypeReference<CBCRequest>() {});

        Assert.assertNotNull(request);
        Assert.assertEquals("PR-000-000", request.getId());
    }
}
