package com.cloudblue.connect.api.parameters.usage.chunk;

import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Path;

public class DownloadChunkFile extends ResourceActionParameter {

    @Parameter
    @Path
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
