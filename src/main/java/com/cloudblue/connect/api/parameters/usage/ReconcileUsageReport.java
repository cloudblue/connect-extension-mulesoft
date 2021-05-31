package com.cloudblue.connect.api.parameters.usage;

import com.cloudblue.connect.api.clients.entity.FileEntity;
import org.mule.runtime.api.meta.model.display.PathModel;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Path;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

import java.io.File;

public class ReconcileUsageReport  extends UsageReportAction {

    @Parameter
    @DisplayName("File Path")
    @Path(type = PathModel.Type.FILE, location = PathModel.Location.EXTERNAL)
    @Expression
    @Placement(order = 2)
    String reconFile;

    public String getReconFile() {
        return reconFile;
    }

    public void setReconFile(String reconFile) {
        this.reconFile = reconFile;
    }

    @Override
    public Object buildEntity() {
        return new FileEntity()
                .add("recon_file", new File(reconFile));
    }

    @Override
    public String action() {
        return "reconciliation";
    }
}
