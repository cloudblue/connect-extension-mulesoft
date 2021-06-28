package com.cloudblue.connect.api.parameters.usage.recon;

public enum ReconDownloadType {
    UPLOADED_FILE("uploadedfile"), PROCESSED_FILE("processedfile");

    private String action;

    public String getAction() {
        return action;
    }

    ReconDownloadType(String action) {
        this.action = action;
    }
}
