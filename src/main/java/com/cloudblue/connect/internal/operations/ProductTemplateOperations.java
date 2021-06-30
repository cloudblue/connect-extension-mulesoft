package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.product.CBCTemplate;
import com.cloudblue.connect.api.parameters.products.GetProductTemplateParameter;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.*;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class ProductTemplateOperations {
    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Product Template")
    public CBCTemplate getProductTemplate(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Product Template Info") GetProductTemplateParameter productTemplateInfo
    ) throws CBCException {
        return (CBCTemplate) connection
                .newQ(new TypeReference<CBCTemplate>() {})
                .collection(PRODUCTS, productTemplateInfo.getProductId())
                .collection(TEMPLATES, productTemplateInfo.getTemplateId())
                .get();
    }
}
