/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.error.provider;

import com.cloudblue.connect.internal.error.CBCErrorType;

import org.mule.runtime.extension.api.annotation.error.ErrorTypeProvider;
import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

import java.util.HashSet;
import java.util.Set;

public class OperationErrorTypeProvider implements ErrorTypeProvider {

    public Set<ErrorTypeDefinition> getErrorTypes() {
        Set<ErrorTypeDefinition> errors = new HashSet<>();

        errors.add(CBCErrorType.RESOURCE_NOT_FOUND);
        errors.add(CBCErrorType.BAD_REQUEST);
        errors.add(CBCErrorType.REQUEST_UNAUTHORIZED);
        errors.add(CBCErrorType.WEBHOOK_ERROR);

        return errors;
    }
}
