package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.ticketing.CBCCase;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.util.ArrayList;
import java.util.List;


public class ListCaseOperation extends BaseListOperation {

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Helpdesk Cases")
    public List<CBCCase> listCases(
            @Connection CBCConnection connection
    ) throws CBCException {

        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCCase>>() {})
                .collection("helpdesk").collection("cases");

        resolve(q);

        return (List<CBCCase>) q.get();
    }
}
