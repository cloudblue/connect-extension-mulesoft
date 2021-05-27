package com.cloudblue.connect.internal.sources.utils;

import com.cloudblue.connect.api.models.CBCAccount;
import com.cloudblue.connect.api.models.CBCAsset;
import com.cloudblue.connect.api.models.CBCRequest;
import com.cloudblue.connect.api.parameters.enums.PollingType;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.ASSETS;
import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.REQUESTS;

public class PollingCollection {
    public static final class PollingObjectInfo {
        private String url;
        private TypeReference typeRef;
        private Class clazz;

        public PollingObjectInfo(String url, TypeReference typeRef, Class clazz) {
            this.url = url;
            this.typeRef = typeRef;
            this.clazz = clazz;
        }

        public String getUrl() {
            return url;
        }

        public TypeReference getTypeRef() {
            return typeRef;
        }

        public Class getClazz() {
            return clazz;
        }
    }
    private static Map<PollingType, PollingObjectInfo> infoMap = new HashMap<>();

    static {
        infoMap.put(PollingType.Requests, new PollingObjectInfo(
                REQUESTS, new TypeReference<ArrayList<CBCRequest>>() {}, CBCRequest.class));
        infoMap.put(PollingType.Assets, new PollingObjectInfo(
                ASSETS, new TypeReference<ArrayList<CBCAsset>>() {}, CBCAsset.class));
        infoMap.put(PollingType.TierAccounts, new PollingObjectInfo(
                "tier/accounts", new TypeReference<ArrayList<CBCAccount>>() {}, CBCAccount.class));
    }

    public static PollingObjectInfo resolve(PollingType objectType) {
        return infoMap.get(objectType);
    }

}
