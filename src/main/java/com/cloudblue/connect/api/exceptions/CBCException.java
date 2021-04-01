package com.cloudblue.connect.api.exceptions;

import com.cloudblue.connect.api.models.CBCError;

public class CBCException extends Exception {
    private String errorCode;
    private String errorTitle;
    private String detailedErrormessage;

    private CBCError connectAPiError;

    public CBCException() {}

    public CBCException(String message) {
        super(message);
    }

    public CBCException(String errorCode, String errorTitle, String detailedErrormessage) {
        super(errorTitle);
        this.errorCode = errorCode;
        this.errorTitle = errorTitle;
        this.detailedErrormessage = detailedErrormessage;
    }
    public CBCException(String errorCode, String errorTitle, CBCError error) {
        super(error.errorDetail());
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
