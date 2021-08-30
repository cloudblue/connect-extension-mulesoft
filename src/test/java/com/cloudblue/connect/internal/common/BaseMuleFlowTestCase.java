/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.common;

import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;

public abstract class BaseMuleFlowTestCase extends MuleArtifactFunctionalTestCase {
    @Override
    protected String getConfigFile() {
        return "test-mule-config.xml";
    }
}
