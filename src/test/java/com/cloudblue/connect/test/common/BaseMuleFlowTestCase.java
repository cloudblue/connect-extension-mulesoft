package com.cloudblue.connect.test.common;

import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;

public abstract class BaseMuleFlowTestCase extends MuleArtifactFunctionalTestCase {
    @Override
    protected String getConfigFile() {
        return "test-mule-config.xml";
    }
}
