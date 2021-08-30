/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operation;

import com.cloudblue.connect.api.models.CBCResponseAttributes;
import com.cloudblue.connect.internal.connection.CBCConnection;

import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.extension.api.annotation.metadata.fixed.InputJsonType;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.http.api.HttpConstants;

import java.io.InputStream;

import static com.cloudblue.connect.api.clients.constants.APIConstants.CollectionKeys.*;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

public class ProductOperations {

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Create Product")
    public Result<InputStream, CBCResponseAttributes> createProduct(
            @Connection CBCConnection connection,
            @InputJsonType(schema = "NewProduct-schema.json")
                    InputStream newRequestParameter
    ) throws MuleException {
        return connection.newQ()
                .collection(PRODUCTS)
                .create(newRequestParameter);
    }

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Create Product Item")
    public Result<InputStream, CBCResponseAttributes> createProductItem(
            @Connection CBCConnection connection,
            String productId,
            @InputJsonType(schema = "NewProductItem-schema.json")
                    InputStream newRequestParameter
    ) throws MuleException {
        return connection.newQ()
                .collection(PRODUCTS, productId)
                .collection(ITEMS)
                .create(newRequestParameter);
    }

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Get Product Action Link")
    public Result<InputStream, CBCResponseAttributes> getProductActionLink(
            @Connection CBCConnection connection,
            String productId,
            String actionId,
            String assetId
    ) throws MuleException {
        return connection.newQ()
                .encode(false)
                .collection(PRODUCTS, productId)
                .collection(ACTIONS, actionId)
                .filter("asset_id", assetId)
                .action("actionLink", HttpConstants.Method.GET, null);
    }
}
