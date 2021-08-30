/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operation.helpdesk;

import com.cloudblue.connect.api.models.enums.CBCCaseStatus;
import com.cloudblue.connect.api.parameters.ResourceKeys;
import com.cloudblue.connect.internal.metadata.Keys;

import org.mule.metadata.api.builder.ObjectTypeBuilder;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.InputTypeResolver;


public class CaseStatusChangeInputEntityResolver implements InputTypeResolver<CBCCaseStatus> {
    @Override
    public MetadataType getInputMetadata(MetadataContext context, CBCCaseStatus key)
            throws MetadataResolvingException, ConnectionException {

        final ObjectTypeBuilder objectBuilder = context.getTypeBuilder().objectType();

        objectBuilder.addField().key(ResourceKeys.CASE_ID).value().stringType();

        if (key == CBCCaseStatus.CLOSED) {
            objectBuilder.addField().key(Keys.RATING.getField()).label(Keys.RATING.getLabel()).value().numberType();
            objectBuilder.addField().key(Keys.FEEDBACK.getField()).label(Keys.FEEDBACK.getLabel()).value().stringType();
        }

        return objectBuilder.build();
    }

    @Override
    public String getCategoryName() {
        return "CaseStatusChange";
    }
}
