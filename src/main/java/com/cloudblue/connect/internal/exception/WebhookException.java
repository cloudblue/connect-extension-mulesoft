/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.exception;

import com.cloudblue.connect.internal.error.CBCErrorType;
import org.mule.runtime.extension.api.exception.ModuleException;

public class WebhookException extends ModuleException {
    public WebhookException(String message) {
        super(message, CBCErrorType.REQUEST_UNAUTHORIZED);
    }

    public WebhookException(String message, Throwable cause) {
        super(message, CBCErrorType.REQUEST_UNAUTHORIZED, cause);
    }
}
