package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import com.cloudblue.connect.api.models.CBCProduct;
import com.cloudblue.connect.api.models.CBCProductItem;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import com.cloudblue.connect.api.parameters.products.SearchProductItemParameter;
import com.cloudblue.connect.api.parameters.products.NewProductParameter;
import com.cloudblue.connect.api.parameters.products.NewProductItemParameter;

import com.fasterxml.jackson.core.type.TypeReference;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;
import java.util.ArrayList;
import java.util.List; 

public class ProductOperations extends BaseListOperation {

    @MediaType(value = ANY, strict = false)
    @DisplayName("Create Product")
    public CBCProduct createProduct(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Create Product")
                    NewProductParameter newRequestParameter
    ) throws CBCException {
        return (CBCProduct) connection.newQ(new TypeReference<CBCProduct>() {})
            .collection("products")
            .create(newRequestParameter.buildEntity());
    }

    @MediaType(value = ANY, strict = false) 
    @DisplayName("Get Product")    
    public CBCProduct getProduct(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Product ID") ResourceActionParameter getProductParameter
    ) throws CBCException {
        return (CBCProduct) connection
            .newQ(new TypeReference<CBCProduct>() {})
            .collection("products", getProductParameter.getId())
            .get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Products")
    public List<CBCProduct> listProducts(
            @Connection CBCConnection connection
    ) throws CBCException {

        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCProduct>>() {})
                .collection("products");

        resolve(q);
        return (List<CBCProduct>) q.get();
    }    

    @MediaType(value = ANY, strict = false)
    @DisplayName("Create Product Item")
    public CBCProductItem createProductItem(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Create Product Item")
                    NewProductItemParameter newRequestParameter
    ) throws CBCException {
        return (CBCProductItem) connection.newQ(new TypeReference<CBCProductItem>() {})
        .collection("products", newRequestParameter.getProductId())
        .collection("items")
        .create(newRequestParameter.buildEntity());
    }

    @MediaType(value = ANY, strict = false) 
    @DisplayName("Get Product Items")    
    public CBCProductItem getProductItem(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Product ID") SearchProductItemParameter getProductParameter
    ) throws CBCException {
        return (CBCProductItem) connection
            .newQ(new TypeReference <CBCProductItem>() {})
            .collection("products", getProductParameter.getProductId())
            .collection("items", getProductParameter.getProductItemId())
            .get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Product Items")
    public List<CBCProductItem> listProductItems(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Product ID") ResourceActionParameter getProductParameter
    ) throws CBCException {

        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCProductItem>>() {})
                .collection("products", getProductParameter.getId())
                .collection("items");

        resolve(q);
        return (List<CBCProductItem>) q.get();
    } 
}
