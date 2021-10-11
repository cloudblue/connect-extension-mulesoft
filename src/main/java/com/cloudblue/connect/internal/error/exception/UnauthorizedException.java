/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.error.exception;

import com.cloudblue.connect.internal.error.CBCErrorType;
import org.mule.runtime.extension.api.exception.ModuleException;

public class UnauthorizedException extends ModuleException {
    private static final long serialVersionUID = 6487822397087696596L;

    public UnauthorizedException(String message) {
        super(message, CBCErrorType.REQUEST_UNAUTHORIZED);
    }
}
