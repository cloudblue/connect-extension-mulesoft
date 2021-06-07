package com.cloudblue.connect.api.parameters.usage.recon;

import com.cloudblue.connect.api.clients.entity.FileEntity;
import com.cloudblue.connect.api.parameters.Embeddable;

import org.mule.runtime.api.meta.model.display.PathModel;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Path;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

import java.io.File;

public class CreateUsageReconParameters implements Embeddable {
    @Parameter
    @DisplayName("File Path")
    @Path(type = PathModel.Type.FILE, location = PathModel.Location.EXTERNAL)
    @Expression
    @Placement(order = 2)
    String reconFile;

    @Parameter
    @Optional
    private String uploadNote;

    public String getReconFile() {
        return reconFile;
    }

    public void setReconFile(String reconFile) {
        this.reconFile = reconFile;
    }

    public String getUploadNote() {
        return uploadNote;
    }

    public void setUploadNote(String uploadNote) {
        this.uploadNote = uploadNote;
    }

    @Override
    public Object buildEntity() {
        return new FileEntity()
                .addFile("recon_file", new File(reconFile))
                .addValue("upload_note", uploadNote);
    }
}
