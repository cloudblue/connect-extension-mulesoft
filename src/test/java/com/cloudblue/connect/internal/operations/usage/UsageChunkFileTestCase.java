/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operations.usage;

import com.cloudblue.connect.api.models.usage.CBCUsageChunkFile;
import com.cloudblue.connect.internal.common.BaseMuleFlowTestCase;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.Parameterized;

import org.mule.runtime.api.event.Event;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.tck.junit4.rule.SystemProperty;
import org.mule.test.runner.RunnerDelegateTo;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunnerDelegateTo(Parameterized.class)
public class UsageChunkFileTestCase extends BaseMuleFlowTestCase {

    private static final String USAGE_CHUNK_FILE_ID = "UFC-0000-00-0000-0000-001";

    @Rule
    public DynamicPort listenPort = new DynamicPort("port");

    @Rule
    public SystemProperty usageChunkFileIdSystemProperty = new SystemProperty("chunk_file_id", USAGE_CHUNK_FILE_ID);

    @Rule
    public SystemProperty filePath = new SystemProperty(
            "filePath",
            System.getProperty("java.io.tmpdir")
    );

    private final String flow;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"updateChunkFileFlow"},
                {"closeChunkFileFlow"},
                {"downloadChunkFileFlow"}
        });
    }

    @After
    public void tearDown() throws Exception {
        File file = new File(System.getProperty("java.io.tmpdir") + File.separator + USAGE_CHUNK_FILE_ID + ".txt");
        if (file.exists())
            file.delete();
    }

    public UsageChunkFileTestCase(String flow) {
        this.flow = flow;
    }

    @Override
    protected String getConfigFile() {
        return "test-mule-config-chunk-files.xml";
    }

    @Test
    public void testUsageRecord() throws Exception {
        Event event = flowRunner(this.flow).run();
        CBCUsageChunkFile usageChunkFile = (CBCUsageChunkFile)event.getMessage().getPayload().getValue();
        assertThat(usageChunkFile.getId(), is(USAGE_CHUNK_FILE_ID));
    }
}
