package com.cloudblue.connect.test.internal.operations;

import com.cloudblue.connect.internal.operations.connections.CBCConnectionProvider;
import org.junit.Test;

public class CBCConnectionProviderTestCase {
    @Test(expected = Test.None.class)
    public void testDisconnectNegative() {
        CBCConnectionProvider provider = new CBCConnectionProvider();
        provider.disconnect(null);
    }
}
