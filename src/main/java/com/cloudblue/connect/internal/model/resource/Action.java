/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.model.resource;

public enum Action {
    GET, LIST, CREATE,
    UPDATE, DELETE, ACCEPT,
    IGNORE, APPROVE, INQUIRE,
    PENDING, PEND, FAIL,
    ASSIGN, UNASSIGN, PURCHASE,
    CLOSE, REGENERATE,
    BULK_CLOSE, REJECT, REPROCESS,
    SUBMIT, CREATE_PURCHASE_REQUEST,
    CREATE_ADMIN_HOLD_REQUEST,
    CREATE_BILLING_REQUEST, DOWNLOAD,
    DOWNLOAD_UPLOADED_FILE,
    DOWNLOAD_PROCESSED_FILE,
    UPLOAD, UPLOAD_RECON_FILE, RESOLVE;

    public static Action[] getCreateActions() {
        return new Action[] {
                CREATE,
                CREATE_PURCHASE_REQUEST,
                CREATE_ADMIN_HOLD_REQUEST,
                CREATE_BILLING_REQUEST,
        };
    }

    public static Action[] getDownloadActions() {
        return new Action[] {
                DOWNLOAD,
                DOWNLOAD_UPLOADED_FILE,
                DOWNLOAD_PROCESSED_FILE,
        };
    }

    public static Action[] getUploadActions() {
        return new Action[] {
                UPLOAD,
                UPLOAD_RECON_FILE,
        };
    }

    public static Action[] getAbstractActions() {
        return new Action[] {
                GET,
                LIST,
                CREATE,
                CREATE_PURCHASE_REQUEST,
                CREATE_ADMIN_HOLD_REQUEST,
                CREATE_BILLING_REQUEST,
                UPDATE,
                DOWNLOAD,
                DOWNLOAD_UPLOADED_FILE,
                DOWNLOAD_PROCESSED_FILE,
                UPLOAD,
                UPLOAD_RECON_FILE,
        };
    }
}
