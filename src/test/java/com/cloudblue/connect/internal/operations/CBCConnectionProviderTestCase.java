/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.internal.connection.provider.CBCConnectionProvider;
import org.junit.Test;

public class CBCConnectionProviderTestCase {
    @Test(expected = Test.None.class)
    public void testDisconnectNegative() {
        CBCConnectionProvider provider = new CBCConnectionProvider();
        provider.disconnect(null);
    }
}
