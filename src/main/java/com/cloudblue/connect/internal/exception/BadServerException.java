/*
 * Copyright � 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.exception;

import com.cloudblue.connect.internal.error.CBCErrorType;
import org.mule.runtime.extension.api.exception.ModuleException;

public class BadServerException extends ModuleException {
    public BadServerException(String message) {
        super(message, CBCErrorType.BAD_SERVER);
    }

    public BadServerException(String message, Throwable cause) {
        super(message, CBCErrorType.BAD_SERVER, cause);
    }
}