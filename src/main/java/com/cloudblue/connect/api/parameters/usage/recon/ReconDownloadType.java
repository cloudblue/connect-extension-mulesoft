/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

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
