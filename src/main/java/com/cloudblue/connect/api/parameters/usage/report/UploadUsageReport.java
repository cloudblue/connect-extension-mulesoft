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
                .add("usage_file", new File(usageFile));
    }

    @Override
    public String action() {
        return "upload";
    }
}
