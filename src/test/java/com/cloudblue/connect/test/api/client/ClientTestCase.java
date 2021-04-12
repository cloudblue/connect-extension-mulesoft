package com.cloudblue.connect.test.api.client;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.clients.Config;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.CBCRequest;
import com.cloudblue.connect.api.parameters.common.MonoFilter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ClientTestCase {
    private Client client;
    @Before
    public void setUp() throws Exception {
        Config config = new Config(
                "https://api.connect.cloud.im/public/v1",
                "ApiKey SU-994-668-095:46b457af6ec55d609e7cbe2f08c431ba1c5679d2",
                20000
        );

        this.client = new Client(config);
    }

    @Test
    public void test_list_request() throws CBCException {
        MonoFilter filter = new MonoFilter();
        filter.setType(MonoFilter.Type.eq);
        filter.setProperty("asset.id");
        filter.setValue("AS-0000-0000");

        List<CBCRequest> requests = this.client.newQ(new ArrayList<CBCRequest>())
                .ns("requests")
                .filter(filter.toRQL())
                .get();
    }
}
