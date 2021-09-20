/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.error;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;
import org.mule.runtime.extension.api.error.MuleErrors;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public enum CBCErrorType implements ErrorTypeDefinition<CBCErrorType> {
    BAD_REQUEST(MuleErrors.VALIDATION),
    REQUEST_UNAUTHORIZED(MuleErrors.SERVER_SECURITY),
    RESOURCE_NOT_FOUND,
    WEBHOOK_ERROR(MuleErrors.CRITICAL);

    private ErrorTypeDefinition<MuleErrors> parentError;

    CBCErrorType(ErrorTypeDefinition<MuleErrors> parentError) {
        this.parentError = parentError;
    }

    CBCErrorType() {}

    @Override
    public Optional<ErrorTypeDefinition<?>> getParent() {
        return ofNullable(parentError);
    }
}
