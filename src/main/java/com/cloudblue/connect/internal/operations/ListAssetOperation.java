package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.CBCAsset;
import com.cloudblue.connect.api.parameters.filters.Filter;
import com.cloudblue.connect.api.parameters.filters.OrderBy;
import com.cloudblue.connect.internal.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.util.ArrayList;
import java.util.List;


public class ListAssetOperation extends BaseListOperation {

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Assets")
    public List<CBCAsset> listAssets(
            @Connection CBCConnection connection
    ) throws CBCException {

        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCAsset>>() {})
                .collection("assets");

        resolve(q);

        return (List<CBCAsset>) q.get();
    }
}
