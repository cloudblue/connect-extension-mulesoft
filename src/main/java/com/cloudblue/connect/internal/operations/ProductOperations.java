package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.constants.HttpMethod;
import com.cloudblue.connect.api.models.product.*;
import com.cloudblue.connect.api.parameters.products.*;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import java.util.HashMap;
import java.util.Map;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;
import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.*;

public class ProductOperations {

    @MediaType(value = ANY, strict = false)
    @DisplayName("Create Product")
    public CBCProduct createProduct(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Create Product")
                    NewProductParameter newRequestParameter
    ) throws CBCException {
        return (CBCProduct) connection.newQ(new TypeReference<CBCProduct>() {})
            .collection(PRODUCTS)
            .create(newRequestParameter.buildEntity());
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Create Product Item")
    public CBCProductItem createProductItem(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Create Product Item")
                    NewProductItemParameter newRequestParameter
    ) throws CBCException {
        return (CBCProductItem) connection.newQ(new TypeReference<CBCProductItem>() {})
        .collection(PRODUCTS, newRequestParameter.getProductId())
        .collection(ITEMS)
        .create(newRequestParameter.buildEntity());
    }

    @MediaType(value = ANY, strict = false) 
    @DisplayName("Get Product Items")    
    public CBCProductItem getProductItem(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Product Item Details") SearchProductItemParameter getProductParameter
    ) throws CBCException {
        return (CBCProductItem) connection
            .newQ(new TypeReference<CBCProductItem>() {})
            .collection(PRODUCTS, getProductParameter.getProductId())
            .collection(ITEMS, getProductParameter.getProductItemId())
            .get();
    }

    @MediaType(value = ANY, strict = false) 
    @DisplayName("Get Product Parameter")    
    public CBCProductParameter getProductParameter(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Product Parameter") SearchProductParameterParameter getProductParameter
    ) throws CBCException {
        return (CBCProductParameter) connection
            .newQ(new TypeReference<CBCProductParameter>() {})
            .collection(PRODUCTS, getProductParameter.getProductId())
            .collection(PARAMETERS, getProductParameter.getParameterId())
            .get();
    }


    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Product Action")
    public CBCProductAction getProductAction(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Product Action Details") GetProductActionParameter productActionParameter
    ) throws CBCException {
        return (CBCProductAction) connection
                .newQ(new TypeReference<CBCProductAction>() {})
                .collection(PRODUCTS, productActionParameter.getProductId())
                .collection(ACTIONS, productActionParameter.getActionId())
                .get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Product Action Link")
    public String getProductActionLink(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Product Action Details") GetProductActionLinkParameter productActionLinkParameter
    ) throws CBCException {
        String link = null;
        Map<String, String> actionLink = (Map<String, String>) connection
                .newQ(new TypeReference<HashMap<String, String>>() {})
                .encode(false)
                .collection(PRODUCTS, productActionLinkParameter.getProductId())
                .collection(ACTIONS, productActionLinkParameter.getActionId())
                .filter("asset_id", productActionLinkParameter.getAssetId())
                .action("actionLink", HttpMethod.GET, null);

        if (actionLink != null && actionLink.keySet().contains("link")) {
            link = actionLink.get("link");
        }

        return link;
    }
}
