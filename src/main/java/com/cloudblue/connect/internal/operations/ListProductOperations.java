package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.product.*;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import com.fasterxml.jackson.core.type.TypeReference;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.*;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class ListProductOperations extends BaseListOperation {

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Product Templates")
    public List<CBCTemplate> listProductTemplates(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Product ID")
                    ResourceActionParameter productParameter
    ) throws CBCException {
        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCTemplate>>() {})
                .collection(PRODUCTS, productParameter.getId())
                .collection(TEMPLATES);
        resolve(q);
        return (List<CBCTemplate>) q.get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Product Actions")
    public List<CBCProductAction> listProductActions(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Product ID")
                    ResourceActionParameter productParameter
    ) throws CBCException {
        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCProductAction>>() {})
                .collection(PRODUCTS, productParameter.getId())
                .collection(ACTIONS);
        resolve(q);
        return (List<CBCProductAction>) q.get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Product Items")
    public List<CBCProductItem> listProductItems(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Product ID") ResourceActionParameter getProductParameter
    ) throws CBCException {

        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCProductItem>>() {})
                .collection(PRODUCTS, getProductParameter.getId())
                .collection(ITEMS);

        resolve(q);
        return (List<CBCProductItem>) q.get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Product Parameters")
    public List<CBCProductParameter> listProductParameters(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Product ID") ResourceActionParameter getProductParameter
    ) throws CBCException {
        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCProductParameter>>() {})
                .collection(PRODUCTS, getProductParameter.getId())
                .collection(PARAMETERS);
        resolve(q);
        return (List<CBCProductParameter>) q.get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Product Configuration Parameters")
    public List<CBCProductConfigurationParameter> listProductConfigurationParameters(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Product Id") ResourceActionParameter getProductId
    ) throws CBCException {
        Client.Q q = connection
                .newQ(new TypeReference<List<CBCProductConfigurationParameter>>() {})
                .collection(PRODUCTS, getProductId.getId())
                .collection(CONFIGURATIONS);
        resolve(q);
        return (List<CBCProductConfigurationParameter>) q.get();
    }
}
