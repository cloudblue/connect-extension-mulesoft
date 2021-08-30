/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.parameters.usage.recon;

import com.cloudblue.connect.api.parameters.common.AbstractResourceMultiAction;
import org.mule.runtime.api.meta.model.display.PathModel;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Path;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

public class UsageReconFileDownload extends AbstractResourceMultiAction {
    @Parameter
    @DisplayName("Download Location")
    @Path(type = PathModel.Type.DIRECTORY, location = PathModel.Location.EXTERNAL)
    @Expression
    @Placement(order = 2)
    private String location;

    @Parameter
    @DisplayName("File name")
    private String fileName;

    @Parameter
    @DisplayName("Download File Type")
    private ReconDownloadType fileType;

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

    public ReconDownloadType getFileType() {
        return fileType;
    }

    public void setFileType(ReconDownloadType fileType) {
        this.fileType = fileType;
    }

    @Override
    public Object buildEntity() {
        return null;
    }

    @Override
    public String action() {
        return fileType.getAction();
    }
}
