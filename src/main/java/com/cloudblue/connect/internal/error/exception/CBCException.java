/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.error.exception;

import com.cloudblue.connect.api.models.common.CBCError;

public class CBCException extends Exception {
    private final String errorCode;
    private final String errorTitle;
    private final String detailedErrormessage;

    private final transient CBCError connectAPiError;

    public CBCException() {
        errorCode = null;
        errorTitle = null;
        detailedErrormessage = null;
        connectAPiError = null;
    }

    public CBCException(String message) {
        super(message);
        errorCode = null;
        errorTitle = null;
        detailedErrormessage = null;
        connectAPiError = null;
    }

    public CBCException(String message, Throwable throwable) {
        super(message, throwable);
        errorCode = null;
        errorTitle = null;
        detailedErrormessage = null;
        connectAPiError = null;
    }

    public CBCException(String errorCode, String errorTitle, String detailedErrormessage) {
        super(errorTitle);
        this.errorCode = errorCode;
        this.errorTitle = errorTitle;
        this.detailedErrormessage = detailedErrormessage;
        this.connectAPiError = null;
    }
    public CBCException(String errorCode, String errorTitle, String detailedErrormessage, Throwable throwable) {
        super(errorTitle, throwable);
        this.errorCode = errorCode;
        this.errorTitle = errorTitle;
        this.detailedErrormessage = detailedErrormessage;
        this.connectAPiError = null;
    }
    public CBCException(String errorCode, String errorTitle, CBCError error) {
        super(error.errorDetail());
        this.errorCode = errorCode;
        this.errorTitle = errorTitle;
        this.detailedErrormessage = error.errorDetail();
        this.connectAPiError =error;
    }

    public CBCException(String errorCode, String errorTitle, CBCError error, Throwable throwable) {
        super(error.errorDetail(), throwable);
        this.errorCode = errorCode;
        this.errorTitle = errorTitle;
        this.detailedErrormessage = error.errorDetail();
        this.connectAPiError =error;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public String getDetailedErrormessage() {
        return detailedErrormessage;
    }

    @Override
    public String toString() {
        return "Cloudblue-Connect-Error{" +
                "error code='" + errorCode + '\'' +
                ", error title='" + errorTitle + '\'' +
                ", error message='" + detailedErrormessage +
                '\'' + '}';
    }

    public CBCError getCBCAPiError() {
        return connectAPiError;
    }
}
