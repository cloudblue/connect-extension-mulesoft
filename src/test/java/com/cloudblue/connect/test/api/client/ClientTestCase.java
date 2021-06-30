package com.cloudblue.connect.test.api.client;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.clients.Config;
import com.cloudblue.connect.api.clients.constants.HttpMethod;
import com.cloudblue.connect.api.clients.rql.R;
import com.cloudblue.connect.api.clients.utils.Url;
import org.junit.Assert;
import org.junit.Test;

public class ClientTestCase {

    protected Config getConfig() {
        return new Config(
                "http://example.com",
                "TOKEN-001",
                20000
        );
    }

    @Test
    public void testQRawFilter() {
        Client client = new Client(getConfig());

        Client.Q q = client.newQ(null)
                .collection("xyz?cncb=hjgsf")
                .filter("asset.id", "AS-000-000-000")
                .limit(5);

        Assert.assertEquals(
                "/xyz?cncb=hjgsf&" + Url.encode("asset.id=AS-000-000-000&limit=5"),
                q.getFinalUrl(null)
        );
        Assert.assertEquals(
                "/xyz?cncb=hjgsf&" + Url.encode("asset.id=AS-000-000-000&limit=5"),
                q.getFinalUrl("")
        );
        Assert.assertEquals(
                "/xyz?cncb=hjgsf/suspend&" + Url.encode("asset.id=AS-000-000-000&limit=5"),
                q.getFinalUrl("suspend")
        );
    }

    @Test
    public void testMultiRQLFilter() {
        Client client = new Client(getConfig());

        Client.Q q = client.newQ(null)
                .collection("abc")
                .filter(R.eq("asset.id", "AS-000"))
                .filter(R.eq("status", "pending"))
                .limit(5);

        Assert.assertEquals(
                "/abc?" + Url.encode("(eq(asset.id,AS-000)&eq(status,pending))&limit=5"),
                q.getFinalUrl(null)
        );
    }

    @Test
    public void testEmptyCollection() {
        Client client = new Client(getConfig());
        Client.Q q = client.newQ(null)
                .filter("asset.id", "AS-000-000-000")
                .limit(5);

        Assert.assertEquals(
                "?" + Url.encode("asset.id=AS-000-000-000&limit=5"),
                q.getFinalUrl(null));

    }

    @Test
    public void testCollectionWithQuestionMark() {
        Client client = new Client(getConfig());
        Client.Q q = client.newQ(null)
                .collection("xyz?")
                .filter("asset.id", "AS-000-000-000")
                .limit(5);

        Assert.assertEquals(
                "/xyz?" + Url.encode("asset.id=AS-000-000-000&limit=5"),
                q.getFinalUrl(null)
        );

    }
}
