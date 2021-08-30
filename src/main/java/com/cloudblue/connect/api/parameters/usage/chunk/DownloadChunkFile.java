/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.parameters.usage.chunk;

import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import org.mule.runtime.api.meta.model.display.PathModel;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Path;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

public class DownloadChunkFile extends ResourceActionParameter {

    @Parameter
    @DisplayName("Download Location")
    @Path(type = PathModel.Type.DIRECTORY, location = PathModel.Location.EXTERNAL)
    @Expression
    @Placement(order = 2)
    private String location;

    @Parameter
    private String fileName;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
