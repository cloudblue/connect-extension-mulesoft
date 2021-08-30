/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata;

import com.cloudblue.connect.api.parameters.ResourceType;
import com.cloudblue.connect.internal.metadata.fulfillment.*;
import com.cloudblue.connect.internal.metadata.tier.ApproveTcrMetadataProvider;
import com.cloudblue.connect.internal.metadata.tier.FailTcrMetadataProvider;
import com.cloudblue.connect.internal.metadata.tier.IgnoreTarMetadataProvider;
import com.cloudblue.connect.internal.metadata.usage.chunk.CloseChunkFileMetadataProvider;
import com.cloudblue.connect.internal.metadata.usage.chunk.UpdateChunkFileMetadataProvider;
import com.cloudblue.connect.internal.metadata.usage.record.BulkCloseMetadataProvider;
import com.cloudblue.connect.internal.metadata.usage.record.CloseRecordMetadataProvider;
import com.cloudblue.connect.internal.metadata.usage.report.AcceptReportMetadataProvider;
import com.cloudblue.connect.internal.metadata.usage.report.RejectReportMetadataProvider;
import com.cloudblue.connect.internal.model.resource.Action;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cloudblue.connect.internal.metadata.Keys.*;
import static com.cloudblue.connect.api.clients.constants.APIConstants.CollectionKeys.*;

public class MetadataUtil {
    private static final Map<ResourceType, Metadata> METADATA_STORE = new EnumMap<>(ResourceType.class);

    public static final String TIER_ACCOUNT_SCHEMA = "TierAccount-schema.json";
    public static final String TIER_ACCOUNT_REQUEST_SCHEMA = "TierAccountRequest-schema.json";
    public static final String TIER_CONFIG_REQUEST_SCHEMA = "TierConfigRequest-schema.json";
    public static final String TIER_CONFIG_SCHEMA = "TierConfig-schema.json";
    public static final String FULFILLMENT_REQUEST_SCHEMA = "Request-schema.json";
    public static final String ASSET_SCHEMA = "Asset-schema.json";
    public static final String PRODUCT_SCHEMA = "Product-schema.json";
    public static final String PRODUCT_TEMPLATE_SCHEMA = "Template-schema.json";
    public static final String PRODUCT_ACTION_SCHEMA = "ProductAction-schema.json";
    public static final String PRODUCT_ITEM_SCHEMA = "ProductItem-schema.json";
    public static final String PRODUCT_PARAMETER_SCHEMA = "ProductParameter-schema.json";
    public static final String PRODUCT_CONFIGURATION_SCHEMA = "ProductConfigurationParameter-schema.json";
    public static final String CHUNK_FILE_SCHEMA = "UsageChunkFile-schema.json";
    public static final String RECORD_BULK_CLOSE_RESPONSE_SCHEMA = "UsageRecordBulkCloseResponse-schema.json";
    public static final String USAGE_RECORD_SCHEME = "UsageRecord-schema.json";
    public static final String USAGE_REPORT_SCHEMA = "UsageReport-schema.json";
    public static final String USAGE_RECON_SCHEMA = "UsageReconciliation-schema.json";
    public static final String USAGE_AGGREGATE_SCHEMA = "UsageAggregate-schema.json";
    public static final String CASE_SCHEMA = "Case-schema.json";
    public static final String CONVERSATION_MESSAGES_SCHEMA = "ConversationMessage-schema.json";
    public static final String NO_OUTPUT_SCHEMA = "Null";

    static {
        METADATA_STORE.put(ResourceType.TIER_ACCOUNT,
                new Metadata()
                        .collection(TIER_ACCOUNTS)
                        .id(TA_ID)
                        .schema(TIER_ACCOUNT_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        METADATA_STORE.put(ResourceType.TIER_ACCOUNT_VERSION,
                new Metadata()
                        .collection(VERSIONS)
                        .id(TA_VERSION_ID)
                        .isSubCollection(true)
                        .parentCollection(TIER_ACCOUNTS)
                        .parentId(TA_ID)
                        .schema(TIER_ACCOUNT_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        METADATA_STORE.put(ResourceType.TIER_ACCOUNT_REQUEST,
                new Metadata()
                        .collection(ACCOUNT_REQUESTS)
                        .id(TAR_ID)
                        .schema(TIER_ACCOUNT_REQUEST_SCHEMA)
                        .includeGetAction()
                        .includeListAction()
                        .addActionMetaData(Action.ACCEPT, new ActionMetadata()
                                .input(new BaseMetadataProvider())
                                .includePayload(false))
                        .addActionMetaData(Action.IGNORE, new ActionMetadata()
                                .input(new IgnoreTarMetadataProvider())));

        METADATA_STORE.put(ResourceType.TIER_CONFIG_REQUEST,
                new Metadata()
                        .collection(CONFIG_REQUESTS)
                        .id(TCR_ID)
                        .schema(TIER_CONFIG_REQUEST_SCHEMA)
                        .includeGetAction()
                        .includeListAction()
                        .addActionMetaData(Action.APPROVE, new ActionMetadata()
                                .input(new ApproveTcrMetadataProvider()))
                        .addActionMetaData(Action.INQUIRE, new ActionMetadata()
                                .output(NO_OUTPUT_SCHEMA)
                                .input(new BaseMetadataProvider())
                                .includePayload(false))
                        .addActionMetaData(Action.PENDING, new ActionMetadata()
                                .output(NO_OUTPUT_SCHEMA)
                                .input(new BaseMetadataProvider())
                                .includePayload(false)
                                .action(Action.PEND.name().toLowerCase()))
                        .addActionMetaData(Action.FAIL, new ActionMetadata()
                                .output(NO_OUTPUT_SCHEMA)
                                .input(new FailTcrMetadataProvider())
                                .includePayload(false)));

        METADATA_STORE.put(ResourceType.TIER_CONFIG,
                new Metadata()
                        .collection(TIER_CONFIG)
                        .id(TC_ID)
                        .schema(TIER_CONFIG_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        METADATA_STORE.put(ResourceType.REQUEST,
                new Metadata()
                        .collection(REQUESTS)
                        .id(REQUEST_ID)
                        .schema(FULFILLMENT_REQUEST_SCHEMA)
                        .includeGetAction()
                        .includeListAction()
                        .addActionMetaData(Action.APPROVE, new ActionMetadata()
                                .input(new ApproveRequestMetadataProvider()))
                        .addActionMetaData(Action.ASSIGN, new ActionMetadata()
                                .input(new AssignRequestMetadataProvider()))
                        .addActionMetaData(Action.FAIL, new ActionMetadata()
                                .input(new FailRequestMetadataProvider()))
                        .addActionMetaData(Action.INQUIRE, new ActionMetadata()
                                .input(new InquireRequestMetadataProvider()))
                        .addActionMetaData(Action.PURCHASE, new ActionMetadata()
                                .includePayload(false)
                                .input(new BaseMetadataProvider()))
                        .addActionMetaData(Action.UNASSIGN, new ActionMetadata()
                                .includePayload(false)
                                .input(new BaseMetadataProvider()))
                        .addActionMetaData(Action.PENDING, new ActionMetadata()
                                .input(new BaseMetadataProvider())
                                .action(Action.PEND.name().toLowerCase()))
                        .addActionMetaData(Action.UPDATE, new ActionMetadata()
                                .input(new UpdateRequestMetadataProvider())));

        METADATA_STORE.put(ResourceType.ASSET,
                new Metadata()
                        .collection(ASSETS)
                        .id(ASSET_ID)
                        .schema(ASSET_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        METADATA_STORE.put(ResourceType.SUBSCRIPTION_REQUEST,
                new Metadata()
                        .collection(SUBSCRIPTION_REQUESTS)
                        .id(REQUEST_ID)
                        .schema(FULFILLMENT_REQUEST_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        METADATA_STORE.put(ResourceType.SUBSCRIPTION_ASSET,
                new Metadata()
                        .collection(SUBSCRIPTION_ASSETS)
                        .id(ASSET_ID)
                        .schema(ASSET_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        METADATA_STORE.put(ResourceType.PRODUCT,
                new Metadata()
                        .collection(PRODUCTS)
                        .id(PRODUCT_ID)
                        .schema(PRODUCT_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        METADATA_STORE.put(ResourceType.PRODUCT_TEMPLATE,
                new Metadata()
                        .collection(TEMPLATES)
                        .id(TEMPLATE_ID)
                        .isSubCollection(true)
                        .parentCollection(PRODUCTS)
                        .parentId(PRODUCT_ID)
                        .schema(PRODUCT_TEMPLATE_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        METADATA_STORE.put(ResourceType.PRODUCT_ACTION,
                new Metadata()
                        .collection(ACTIONS)
                        .id(ACTION_ID)
                        .isSubCollection(true)
                        .parentCollection(PRODUCTS)
                        .parentId(PRODUCT_ID)
                        .schema(PRODUCT_ACTION_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        METADATA_STORE.put(ResourceType.PRODUCT_ITEM,
                new Metadata()
                        .collection(ITEMS)
                        .id(ITEM_ID)
                        .isSubCollection(true)
                        .parentCollection(PRODUCTS)
                        .parentId(PRODUCT_ID)
                        .schema(PRODUCT_ITEM_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        METADATA_STORE.put(ResourceType.PRODUCT_PARAMETER,
                new Metadata()
                        .collection(PARAMETERS)
                        .id(PARAMETER_ID)
                        .isSubCollection(true)
                        .parentCollection(PRODUCTS)
                        .parentId(PRODUCT_ID)
                        .schema(PRODUCT_PARAMETER_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        METADATA_STORE.put(ResourceType.PRODUCT_CONFIGURATION,
                new Metadata()
                        .collection(CONFIGURATIONS)
                        .isSubCollection(true)
                        .parentCollection(PRODUCTS)
                        .parentId(PRODUCT_ID)
                        .schema(PRODUCT_CONFIGURATION_SCHEMA)
                        .includeListAction());

        METADATA_STORE.put(ResourceType.USAGE_CHUNK,
                new Metadata()
                        .collection(CHUNKS)
                        .id(USAGE_CHUNK_ID)
                        .schema(CHUNK_FILE_SCHEMA)
                        .includeGetAction()
                        .includeListAction()
                        .addActionMetaData(Action.REGENERATE, new ActionMetadata()
                                .input(new BaseMetadataProvider())
                                .includePayload(false))
                        .addActionMetaData(Action.CLOSE, new ActionMetadata()
                                .input(new CloseChunkFileMetadataProvider()))
                        .addActionMetaData(Action.UPDATE, new ActionMetadata()
                                .input(new UpdateChunkFileMetadataProvider())));

        METADATA_STORE.put(ResourceType.USAGE_RECORD,
                new Metadata()
                        .collection(RECORDS)
                        .id(USAGE_RECORD_ID)
                        .schema(USAGE_RECORD_SCHEME)
                        .includeGetAction()
                        .includeListAction()
                        .addActionMetaData(Action.CLOSE, new ActionMetadata()
                                .input(new CloseRecordMetadataProvider()))
                        .addActionMetaData(Action.BULK_CLOSE, new ActionMetadata()
                                .output(RECORD_BULK_CLOSE_RESPONSE_SCHEMA)
                                .input(new BulkCloseMetadataProvider())
                                .collectionAction(true)
                                .action("close-records")));

        METADATA_STORE.put(ResourceType.USAGE_REPORT,
                new Metadata()
                        .collection(FILES)
                        .id(USAGE_REPORT_ID)
                        .schema(USAGE_REPORT_SCHEMA)
                        .includeGetAction()
                        .includeListAction()
                        .addActionMetaData(Action.CLOSE, new ActionMetadata()
                                .includePayload(false)
                                .input(new BaseMetadataProvider()))
                        .addActionMetaData(Action.SUBMIT, new ActionMetadata()
                                .includePayload(false)
                                .input(new BaseMetadataProvider()))
                        .addActionMetaData(Action.REPROCESS, new ActionMetadata()
                                .includePayload(false)
                                .input(new BaseMetadataProvider()))
                        .addActionMetaData(Action.DELETE, new ActionMetadata()
                                .includePayload(false)
                                .input(new BaseMetadataProvider())
                                .action(Action.DELETE.name().toLowerCase()))
                        .addActionMetaData(Action.REJECT, new ActionMetadata()
                                .input(new RejectReportMetadataProvider()))
                        .addActionMetaData(Action.ACCEPT, new ActionMetadata()
                                .input(new AcceptReportMetadataProvider())));

        METADATA_STORE.put(ResourceType.USAGE_RECONCILIATION,
                new Metadata()
                        .collection(RECONCILIATIONS)
                        .id(USAGE_RECON_ID)
                        .schema(USAGE_RECON_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        METADATA_STORE.put(ResourceType.USAGE_AGGREGATE,
                new Metadata()
                        .collection(AGGREGATES)
                        .schema(USAGE_AGGREGATE_SCHEMA)
                        .includeListAction());

        METADATA_STORE.put(ResourceType.CASE,
                new Metadata()
                        .collection(CASES)
                        .id(CASE_ID)
                        .schema(CASE_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        METADATA_STORE.put(ResourceType.ASSET_USAGE_AGGREGATE,
                new Metadata()
                        .collection(AGGREGATES)
                        .isSubCollection(true)
                        .parentCollection(ASSETS)
                        .parentId(ASSET_ID)
                        .schema(USAGE_AGGREGATE_SCHEMA)
                        .includeListAction());

        METADATA_STORE.put(ResourceType.CONVERSATION_MESSAGES,
                new Metadata()
                        .collection(MESSAGES)
                        .id(CONVERSATION_ID)
                        .isSubCollection(true)
                        .parentCollection(CONVERSATION)
                        .parentId(CONVERSATION_ID)
                        .schema(CONVERSATION_MESSAGES_SCHEMA)
                        .includeGetAction()
                        .includeListAction());
    }

    private MetadataUtil() {}

    public static List<String> getActions(String resourceType) {

        ResourceType type = ResourceType.valueOf(resourceType.toUpperCase());
        Metadata metadata = METADATA_STORE.get(type);

        if (metadata == null || metadata.getActionMetadata().isEmpty()) {
            return new ArrayList<>();
        } else {
            return metadata.getActionMetadata()
                    .keySet()
                    .stream()
                    .map(Enum::name)
                    .collect(Collectors.toList());
        }
    }

    public static List<String> getActionResourceTypes() {
        return METADATA_STORE.keySet()
                .stream()
                .filter(resourceType -> METADATA_STORE.get(resourceType)
                        .getActionMetadata()
                        .keySet()
                        .stream()
                        .anyMatch(action -> action != Action.GET && action != Action.LIST))
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public static List<String> getListResourceTypes() {
        return METADATA_STORE.keySet()
                .stream()
                .filter(resourceType -> METADATA_STORE.get(resourceType)
                        .getActionMetadata()
                        .containsKey(Action.LIST))
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public static List<String> getGetResourceTypes() {
        return METADATA_STORE.keySet()
                .stream()
                .filter(resourceType -> METADATA_STORE.get(resourceType)
                        .getActionMetadata()
                        .containsKey(Action.GET))
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public static Metadata getMetadata(String resourceType) {
        ResourceType type = ResourceType.valueOf(resourceType.toUpperCase());
        return METADATA_STORE.get(type);
    }

    public static ActionMetadata getActionMetadata(String resourceType, String action) {
        Metadata metadata = getMetadata(resourceType);
        return metadata.getActionMetadata().get(Action.valueOf(action.toUpperCase()));
    }
}
