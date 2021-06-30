package com.cloudblue.connect.api.parameters;

import com.cloudblue.connect.api.models.product.CBCProduct;
import com.cloudblue.connect.api.models.subscription.CBCAsset;
import com.cloudblue.connect.api.models.subscription.CBCRequest;
import com.cloudblue.connect.api.models.ticketing.CBCCase;
import com.cloudblue.connect.api.models.tier.CBCAccount;
import com.cloudblue.connect.api.models.tier.CBCAccountRequest;
import com.cloudblue.connect.api.models.tier.CBCTierConfig;
import com.cloudblue.connect.api.models.tier.CBCTierConfigRequest;
import com.cloudblue.connect.api.models.usage.*;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.*;

public enum ResourceType {
    REQUEST(
            REQUESTS,
            new TypeReference<ArrayList<CBCRequest>>() {},
            new TypeReference<CBCRequest>() {},
            CBCRequest.class),

    ASSET(
            ASSETS,
            new TypeReference<ArrayList<CBCAsset>>() {},
            new TypeReference<CBCAsset>() {},
            CBCAsset.class),

    TIER_ACCOUNT(
            "tier/accounts",
            new TypeReference<ArrayList<CBCAccount>>() {},
            new TypeReference<CBCAccount>() {},
            CBCAccount.class),

    TIER_ACCOUNT_REQUEST(
            "tier/account-requests",
            new TypeReference<ArrayList<CBCAccountRequest>>() {},
            new TypeReference<CBCAccountRequest>() {},
            CBCAccountRequest.class),

    TIER_CONFIG_REQUEST(
            "tier/config-requests",
            new TypeReference<ArrayList<CBCTierConfigRequest>>() {},
            new TypeReference<CBCTierConfigRequest>() {},
            CBCTierConfigRequest.class),

    TIER_CONFIG(
            "tier/configs",
            new TypeReference<ArrayList<CBCTierConfig>>() {},
            new TypeReference<CBCTierConfig>() {},
            CBCTierConfig.class),

    PRODUCT(
            PRODUCTS,
            new TypeReference<ArrayList<CBCProduct>>() {},
            new TypeReference<CBCProduct>() {},
            CBCProduct.class),

    USAGE_REPORT(
            USAGE + "/" + FILES,
            new TypeReference<ArrayList<CBCUsageReport>>() {},
            new TypeReference<CBCUsageReport>() {},
            CBCUsageReport.class),

    USAGE_RECORD(
            USAGE + "/" + RECORDS,
            new TypeReference<ArrayList<CBCUsageRecord>>() {},
            new TypeReference<CBCUsageRecord>() {},
            CBCUsageRecord.class),

    USAGE_CHUNK(
            USAGE + "/" + CHUNKS,
            new TypeReference<ArrayList<CBCUsageChunkFile>>() {},
            new TypeReference<CBCUsageChunkFile>() {},
            CBCUsageChunkFile.class),

    USAGE_RECONCILIATION(
            USAGE + "/" + RECONCILIATIONS,
            new TypeReference<ArrayList<CBCUsageReconciliation>>() {},
            new TypeReference<CBCUsageReconciliation>() {},
            CBCUsageReconciliation.class),

    USAGE_AGGREGATE(
            USAGE + "/" + AGGREGATES,
            new TypeReference<ArrayList<CBCUsageAggregate>>() {},
            new TypeReference<CBCUsageAggregate>() {},
            CBCUsageAggregate.class),

    CASE(
            HELPDESK + "/" + CASES,
            new TypeReference<ArrayList<CBCCase>>() {},
            new TypeReference<CBCCase>() {},
            CBCCase.class);

    private final String collection;
    private final TypeReference<? extends Object> listTypeRef;
    private final TypeReference<? extends Object> objectTypeRef;
    private final Class<? extends Object> clazz;

    ResourceType(String collection,
                 TypeReference<? extends Object> listTypeRef,
                 TypeReference<? extends Object> objectTypeRef,
                 Class<? extends Object> clazz) {
        this.collection = collection;
        this.listTypeRef = listTypeRef;
        this.objectTypeRef = objectTypeRef;
        this.clazz = clazz;
    }

    public String getCollection() {
        return collection;
    }

    public TypeReference getListTypeRef() {
        return listTypeRef;
    }

    public Class getClazz() {
        return clazz;
    }

    public TypeReference getObjectTypeRef() {
        return objectTypeRef;
    }
}
