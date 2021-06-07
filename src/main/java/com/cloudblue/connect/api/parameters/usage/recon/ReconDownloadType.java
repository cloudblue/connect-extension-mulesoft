package com.cloudblue.connect.api.parameters.usage.recon;

public enum ReconDownloadType {
    Uploaded("uploadedfile"), Processed("processedfile");

    private String action;

    public String getAction() {
        return action;
    }

    ReconDownloadType(String action) {
        this.action = action;
    }
}
