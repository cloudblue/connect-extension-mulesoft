/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.parameters.usage.report;

import com.cloudblue.connect.api.clients.entity.FileEntity;

import org.mule.runtime.api.meta.model.display.PathModel;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Path;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

import java.io.File;

public class UploadUsageReport extends UsageReportAction {

    @Parameter
    @DisplayName("File Path")
    @Path(type = PathModel.Type.FILE, location = PathModel.Location.EXTERNAL)
    @Expression
    @Placement(order = 2)
    String usageFile;

    public String getUsageFile() {
        return usageFile;
    }

    public void setUsageFile(String usageFile) {
        this.usageFile = usageFile;
    }

    @Override
    public Object buildEntity() {
        return new FileEntity()
                .addFile("usage_file", new File(usageFile));
    }

    @Override
    public String action() {
        return "upload";
    }
}
