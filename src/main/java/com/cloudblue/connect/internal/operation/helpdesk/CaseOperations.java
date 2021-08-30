/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operation.helpdesk;

import com.cloudblue.connect.api.models.CBCResponseAttributes;
import com.cloudblue.connect.api.models.enums.CBCCaseStatus;
import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.metadata.Keys;
import com.cloudblue.connect.internal.model.helpdesks.Case;

import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.TypeResolver;
import org.mule.runtime.extension.api.annotation.metadata.fixed.InputJsonType;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

import static com.cloudblue.connect.api.clients.constants.APIConstants.CollectionKeys.*;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;
import static org.mule.runtime.http.api.HttpConstants.*;


public class CaseOperations {

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Create Helpdesk Case")
    @OutputJsonType(schema = "Case-schema.json")
    public Result<InputStream, CBCResponseAttributes> createHelpdeskCase(
            @Connection CBCConnection connection,
            @InputJsonType(schema = "NewCase-schema.json") InputStream newCaseParameter
    ) throws MuleException {
        return connection.newQ()
                .collection(HELPDESK)
                .collection(CASES)
                .create(newCaseParameter);
    }

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Change Helpdesk Case Status")
    @OutputJsonType(schema = "Case-schema.json")
    public Result<InputStream, CBCResponseAttributes> changeStatusHelpdeskCase(
            @Connection CBCConnection connection,
            @MetadataKeyId CBCCaseStatus newStatus,
            @TypeResolver(CaseStatusChangeInputEntityResolver.class) Map<String, Object> caseStatusChangeParameters
    ) throws MuleException {

        Case aCase = null;

        if (newStatus == CBCCaseStatus.CLOSED) {
            aCase = new Case();
            aCase.setRating((Integer) caseStatusChangeParameters.get(Keys.RATING.getField()));
            aCase.setFeedback((String) caseStatusChangeParameters.get(Keys.FEEDBACK.getField()));
        }

        String caseId = (String) caseStatusChangeParameters.get(Keys.CASE_ID.getField());

        return connection.newQ()
                .collection(HELPDESK)
                .collection(CASES, caseId)
                .action(newStatus.getOperation(), Method.POST, aCase);
    }    
}
