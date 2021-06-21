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
    Request(
            REQUESTS,
            new TypeReference<ArrayList<CBCRequest>>() {},
            new TypeReference<CBCRequest>() {},
            CBCRequest.class),

    Asset(
            ASSETS,
            new TypeReference<ArrayList<CBCAsset>>() {},
            new TypeReference<CBCAsset>() {},
            CBCAsset.class),

    TierAccount(
            "tier/accounts",
            new TypeReference<ArrayList<CBCAccount>>() {},
            new TypeReference<CBCAccount>() {},
            CBCAccount.class),

    TierAccountRequest(
            "tier/account-requests",
            new TypeReference<ArrayList<CBCAccountRequest>>() {},
            new TypeReference<CBCAccountRequest>() {},
            CBCAccountRequest.class),

    TierConfigRequest(
            "tier/config-requests",
            new TypeReference<ArrayList<CBCTierConfigRequest>>() {},
            new TypeReference<CBCTierConfigRequest>() {},
            CBCTierConfigRequest.class),

    TierConfig(
            "tier/configs",
            new TypeReference<ArrayList<CBCTierConfig>>() {},
            new TypeReference<CBCTierConfig>() {},
            CBCTierConfig.class),

    Product(
            PRODUCTS,
            new TypeReference<ArrayList<CBCProduct>>() {},
            new TypeReference<CBCProduct>() {},
            CBCProduct.class),

    UsageReport(
            USAGE + "/" + FILES,
            new TypeReference<ArrayList<CBCUsageReport>>() {},
            new TypeReference<CBCUsageReport>() {},
            CBCUsageReport.class),

    UsageRecord(
            USAGE + "/" + RECORDS,
            new TypeReference<ArrayList<CBCUsageRecord>>() {},
            new TypeReference<CBCUsageRecord>() {},
            CBCUsageRecord.class),

    UsageChunk(
            USAGE + "/" + CHUNKS,
            new TypeReference<ArrayList<CBCUsageChunkFile>>() {},
            new TypeReference<CBCUsageChunkFile>() {},
            CBCUsageChunkFile.class),

    UsageReconciliation(
            USAGE + "/" + RECONCILIATIONS,
            new TypeReference<ArrayList<CBCUsageReconciliation>>() {},
            new TypeReference<CBCUsageReconciliation>() {},
            CBCUsageReconciliation.class),

    UsageAggregate(
            USAGE + "/" + AGGREGATES,
            new TypeReference<ArrayList<CBCUsageAggregate>>() {},
            new TypeReference<CBCUsageAggregate>() {},
            CBCUsageAggregate.class
    ),

    Case(
            HELPDESK + "/" + CASES,
            new TypeReference<ArrayList<CBCCase>>() {},
            new TypeReference<CBCCase>() {},
            CBCCase.class);

    private String collection;
    private TypeReference listTypeRef;
    private TypeReference objectTypeRef;
    private Class clazz;

    ResourceType(String collection, TypeReference listTypeRef, TypeReference objectTypeRef, Class clazz) {
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
