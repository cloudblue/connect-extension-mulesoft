package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.product.CBCTemplate;
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

public class ListProductTemplates extends BaseListOperation {

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
}
