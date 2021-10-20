/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.metadata;

import com.cloudblue.connect.api.parameters.ResourceType;
import com.cloudblue.connect.internal.metadata.fulfillment.ApproveRequestMetadataProvider;
import com.cloudblue.connect.internal.metadata.fulfillment.AssignRequestMetadataProvider;
import com.cloudblue.connect.internal.metadata.fulfillment.FailRequestMetadataProvider;
import com.cloudblue.connect.internal.metadata.fulfillment.InquireRequestMetadataProvider;
import com.cloudblue.connect.internal.metadata.helpdesk.CloseCaseMetadataProvider;
import com.cloudblue.connect.internal.metadata.tier.ApproveTcrMetadataProvider;
import com.cloudblue.connect.internal.metadata.tier.FailTcrMetadataProvider;
import com.cloudblue.connect.internal.metadata.tier.IgnoreTarMetadataProvider;
import com.cloudblue.connect.internal.metadata.usage.chunk.CloseChunkFileMetadataProvider;
import com.cloudblue.connect.internal.metadata.usage.record.BulkCloseMetadataProvider;
import com.cloudblue.connect.internal.metadata.usage.record.CloseRecordMetadataProvider;
import com.cloudblue.connect.internal.metadata.usage.report.AcceptReportMetadataProvider;
import com.cloudblue.connect.internal.metadata.usage.report.RejectReportMetadataProvider;
import com.cloudblue.connect.internal.model.resource.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.ACCOUNT_REQUESTS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.ACTIONS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.AGGREGATES;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.ASSETS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.ATTRIBUTES;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.CASES;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.CHUNKS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.CONFIGURATIONS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.CONFIG_REQUESTS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.CONVERSATION;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.FILES;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.ITEMS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.MESSAGES;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.PARAMETERS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.PRODUCTS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.RECONCILIATIONS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.RECORDS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.REQUESTS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.SUBSCRIPTION_ASSETS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.SUBSCRIPTION_REQUESTS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.TEMPLATES;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.TIER_ACCOUNTS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.TIER_CONFIG;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.VERSIONS;
import static com.cloudblue.connect.internal.metadata.Keys.ACTION_ID;
import static com.cloudblue.connect.internal.metadata.Keys.ASSET_ID;
import static com.cloudblue.connect.internal.metadata.Keys.CASE_ID;
import static com.cloudblue.connect.internal.metadata.Keys.CONVERSATION_ID;
import static com.cloudblue.connect.internal.metadata.Keys.ITEM_ID;
import static com.cloudblue.connect.internal.metadata.Keys.MESSAGE_ID;
import static com.cloudblue.connect.internal.metadata.Keys.PARAMETER_ID;
import static com.cloudblue.connect.internal.metadata.Keys.PRODUCT_ID;
import static com.cloudblue.connect.internal.metadata.Keys.REQUEST_ID;
import static com.cloudblue.connect.internal.metadata.Keys.TAR_ID;
import static com.cloudblue.connect.internal.metadata.Keys.TA_ID;
import static com.cloudblue.connect.internal.metadata.Keys.TA_VERSION_ID;
import static com.cloudblue.connect.internal.metadata.Keys.TCR_ID;
import static com.cloudblue.connect.internal.metadata.Keys.TC_ID;
import static com.cloudblue.connect.internal.metadata.Keys.TEMPLATE_ID;
import static com.cloudblue.connect.internal.metadata.Keys.UPLOAD_NOTE;
import static com.cloudblue.connect.internal.metadata.Keys.USAGE_CHUNK_ID;
import static com.cloudblue.connect.internal.metadata.Keys.USAGE_RECON_ID;
import static com.cloudblue.connect.internal.metadata.Keys.USAGE_RECORD_ID;
import static com.cloudblue.connect.internal.metadata.Keys.USAGE_REPORT_ID;

public class CollectionInfoUtil {
    private static final Map<ResourceType, CollectionInfo> COLLECTION_INFO_STORE = new EnumMap<>(ResourceType.class);

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
    public static final String NEW_CONVERSATION_MESSAGES_SCHEMA = "NewConversationMessage-schema.json";
    public static final String NEW_CASE_SCHEMA = "NewCase-schema.json";
    public static final String NEW_USAGE_REPORT_SCHEMA = "NewUsageReport-schema.json";
    public static final String NEW_PURCHASE_REQUEST_SCHEMA = "NewPurchaseRequest-schema.json";
    public static final String NEW_BILLING_REQUEST_SCHEMA = "NewBillingRequest-schema.json";
    public static final String NEW_ADMIN_HOLD_REQUEST_SCHEMA = "NewAdminHoldRequest-schema.json";
    public static final String NEW_TIER_ACCOUNT_SCHEMA = "NewTierAccount-schema.json";
    public static final String NEW_TIER_ACCOUNT_REQUEST_SCHEMA = "NewTierAccountRequest-schema.json";
    public static final String NEW_TIER_ACCOUNT_CONFIG_REQUEST_SCHEMA = "NewTierConfigRequest-schema.json";
    public static final String PRODUCT_ACTION_LINK_SCHEMA = "ProductActionLink-schema.json";
    public static final String BILLING_REQUEST_ATTRIBUTES_SCHEMA = "BillingRequestAttributes-schema.json";
    public static final String UPDATE_TCR_SCHEMA = "UpdateTierConfigRequest-schema.json";
    public static final String UPDATE_USAGE_REPORT_SCHEMA = "UpdateUsageReport-schema.json";
    public static final String UPDATE_REQUEST_SCHEMA = "UpdateRequest-schema.json";
    public static final String UPDATE_CHUNK_FILE_SCHEMA = "UpdateUsageChunkFile-schema.json";
    public static final String WEBHOOK_EVENT_SCHEMA = "WebhookEvent-schema.json";
    public static final String NO_OUTPUT_SCHEMA = "Null";

    static {
        COLLECTION_INFO_STORE.put(ResourceType.TIER_ACCOUNT,
                new CollectionInfo()
                        .collection(TIER_ACCOUNTS)
                        .id(TA_ID)
                        .schema(TIER_ACCOUNT_SCHEMA)
                        .includeGetAction()
                        .includeListAction()
                        .addActionMetaData(Action.CREATE, new ActionInfo()
                                .input(NEW_TIER_ACCOUNT_SCHEMA))
                        .addActionMetaData(Action.UPDATE, new ActionInfo()
                                .input(NEW_TIER_ACCOUNT_SCHEMA)));

        COLLECTION_INFO_STORE.put(ResourceType.TIER_ACCOUNT_VERSION,
                new CollectionInfo()
                        .collection(VERSIONS)
                        .id(TA_VERSION_ID)
                        .isSubCollection(true)
                        .parentCollection(TIER_ACCOUNTS)
                        .parentId(TA_ID)
                        .schema(TIER_ACCOUNT_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        COLLECTION_INFO_STORE.put(ResourceType.TIER_ACCOUNT_REQUEST,
                new CollectionInfo()
                        .collection(ACCOUNT_REQUESTS)
                        .id(TAR_ID)
                        .schema(TIER_ACCOUNT_REQUEST_SCHEMA)
                        .includeGetAction()
                        .includeListAction()
                        .addActionMetaData(Action.CREATE, new ActionInfo()
                                .input(NEW_TIER_ACCOUNT_REQUEST_SCHEMA))
                        .addActionMetaData(Action.ACCEPT, new ActionInfo()
                                .input(new BaseMetadataProvider())
                                .includePayload(false))
                        .addActionMetaData(Action.IGNORE, new ActionInfo()
                                .input(new IgnoreTarMetadataProvider())));

        COLLECTION_INFO_STORE.put(ResourceType.TIER_CONFIG_REQUEST,
                new CollectionInfo()
                        .collection(CONFIG_REQUESTS)
                        .id(TCR_ID)
                        .schema(TIER_CONFIG_REQUEST_SCHEMA)
                        .includeGetAction()
                        .includeListAction()
                        .addActionMetaData(Action.CREATE, new ActionInfo()
                                .input(NEW_TIER_ACCOUNT_CONFIG_REQUEST_SCHEMA))
                        .addActionMetaData(Action.UPDATE, new ActionInfo()
                                .input(UPDATE_TCR_SCHEMA))
                        .addActionMetaData(Action.APPROVE, new ActionInfo()
                                .input(new ApproveTcrMetadataProvider()))
                        .addActionMetaData(Action.INQUIRE, new ActionInfo()
                                .output(NO_OUTPUT_SCHEMA)
                                .input(new BaseMetadataProvider())
                                .includePayload(false))
                        .addActionMetaData(Action.PENDING, new ActionInfo()
                                .output(NO_OUTPUT_SCHEMA)
                                .input(new BaseMetadataProvider())
                                .includePayload(false)
                                .action(Action.PEND.name().toLowerCase()))
                        .addActionMetaData(Action.FAIL, new ActionInfo()
                                .output(NO_OUTPUT_SCHEMA)
                                .input(new FailTcrMetadataProvider())
                                .includePayload(false)));

        COLLECTION_INFO_STORE.put(ResourceType.TIER_CONFIG,
                new CollectionInfo()
                        .collection(TIER_CONFIG)
                        .id(TC_ID)
                        .schema(TIER_CONFIG_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        COLLECTION_INFO_STORE.put(ResourceType.REQUEST,
                new CollectionInfo()
                        .collection(REQUESTS)
                        .id(REQUEST_ID)
                        .schema(FULFILLMENT_REQUEST_SCHEMA)
                        .includeGetAction()
                        .includeListAction()
                        .addActionMetaData(Action.CREATE_PURCHASE_REQUEST, new ActionInfo()
                                .input(NEW_PURCHASE_REQUEST_SCHEMA))
                        .addActionMetaData(Action.CREATE_ADMIN_HOLD_REQUEST, new ActionInfo()
                                .input(NEW_ADMIN_HOLD_REQUEST_SCHEMA))
                        .addActionMetaData(Action.CREATE_BILLING_REQUEST, new ActionInfo()
                                .input(NEW_BILLING_REQUEST_SCHEMA))
                        .addActionMetaData(Action.APPROVE, new ActionInfo()
                                .input(new ApproveRequestMetadataProvider()))
                        .addActionMetaData(Action.ASSIGN, new ActionInfo()
                                .input(new AssignRequestMetadataProvider()))
                        .addActionMetaData(Action.FAIL, new ActionInfo()
                                .input(new FailRequestMetadataProvider()))
                        .addActionMetaData(Action.INQUIRE, new ActionInfo()
                                .input(new InquireRequestMetadataProvider()))
                        .addActionMetaData(Action.PURCHASE, new ActionInfo()
                                .includePayload(false)
                                .input(new BaseMetadataProvider()))
                        .addActionMetaData(Action.UNASSIGN, new ActionInfo()
                                .includePayload(false)
                                .input(new BaseMetadataProvider()))
                        .addActionMetaData(Action.PENDING, new ActionInfo()
                                .input(new BaseMetadataProvider())
                                .action(Action.PEND.name().toLowerCase()))
                        .addActionMetaData(Action.UPDATE, new ActionInfo()
                                .input(UPDATE_REQUEST_SCHEMA)));

        COLLECTION_INFO_STORE.put(ResourceType.ASSET,
                new CollectionInfo()
                        .collection(ASSETS)
                        .id(ASSET_ID)
                        .schema(ASSET_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        COLLECTION_INFO_STORE.put(ResourceType.SUBSCRIPTION_REQUEST,
                new CollectionInfo()
                        .collection(SUBSCRIPTION_REQUESTS)
                        .id(REQUEST_ID)
                        .schema(FULFILLMENT_REQUEST_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        COLLECTION_INFO_STORE.put(ResourceType.SUBSCRIPTION_ASSET,
                new CollectionInfo()
                        .collection(SUBSCRIPTION_ASSETS)
                        .id(ASSET_ID)
                        .schema(ASSET_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        COLLECTION_INFO_STORE.put(ResourceType.PRODUCT,
                new CollectionInfo()
                        .collection(PRODUCTS)
                        .id(PRODUCT_ID)
                        .schema(PRODUCT_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        COLLECTION_INFO_STORE.put(ResourceType.PRODUCT_TEMPLATE,
                new CollectionInfo()
                        .collection(TEMPLATES)
                        .id(TEMPLATE_ID)
                        .isSubCollection(true)
                        .parentCollection(PRODUCTS)
                        .parentId(PRODUCT_ID)
                        .schema(PRODUCT_TEMPLATE_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        COLLECTION_INFO_STORE.put(ResourceType.PRODUCT_ACTION,
                new CollectionInfo()
                        .collection(ACTIONS)
                        .id(ACTION_ID)
                        .isSubCollection(true)
                        .parentCollection(PRODUCTS)
                        .parentId(PRODUCT_ID)
                        .schema(PRODUCT_ACTION_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        COLLECTION_INFO_STORE.put(ResourceType.PRODUCT_ITEM,
                new CollectionInfo()
                        .collection(ITEMS)
                        .id(ITEM_ID)
                        .isSubCollection(true)
                        .parentCollection(PRODUCTS)
                        .parentId(PRODUCT_ID)
                        .schema(PRODUCT_ITEM_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        COLLECTION_INFO_STORE.put(ResourceType.PRODUCT_PARAMETER,
                new CollectionInfo()
                        .collection(PARAMETERS)
                        .id(PARAMETER_ID)
                        .isSubCollection(true)
                        .parentCollection(PRODUCTS)
                        .parentId(PRODUCT_ID)
                        .schema(PRODUCT_PARAMETER_SCHEMA)
                        .includeGetAction()
                        .includeListAction());

        COLLECTION_INFO_STORE.put(ResourceType.PRODUCT_CONFIGURATION,
                new CollectionInfo()
                        .collection(CONFIGURATIONS)
                        .isSubCollection(true)
                        .parentCollection(PRODUCTS)
                        .parentId(PRODUCT_ID)
                        .schema(PRODUCT_CONFIGURATION_SCHEMA)
                        .includeListAction());

        COLLECTION_INFO_STORE.put(ResourceType.USAGE_CHUNK,
                new CollectionInfo()
                        .collection(CHUNKS)
                        .id(USAGE_CHUNK_ID)
                        .schema(CHUNK_FILE_SCHEMA)
                        .includeGetAction()
                        .includeListAction()
                        .addActionMetaData(Action.REGENERATE, new ActionInfo()
                                .input(new BaseMetadataProvider())
                                .includePayload(false))
                        .addActionMetaData(Action.CLOSE, new ActionInfo()
                                .input(new CloseChunkFileMetadataProvider()))
                        .addActionMetaData(Action.DOWNLOAD, new ActionInfo()
                                .input(new BaseMetadataProvider()))
                        .addActionMetaData(Action.UPDATE, new ActionInfo()
                                .input(UPDATE_CHUNK_FILE_SCHEMA)));

        COLLECTION_INFO_STORE.put(ResourceType.USAGE_RECORD,
                new CollectionInfo()
                        .collection(RECORDS)
                        .id(USAGE_RECORD_ID)
                        .schema(USAGE_RECORD_SCHEME)
                        .includeGetAction()
                        .includeListAction()
                        .addActionMetaData(Action.CLOSE, new ActionInfo()
                                .input(new CloseRecordMetadataProvider()))
                        .addActionMetaData(Action.BULK_CLOSE, new ActionInfo()
                                .output(RECORD_BULK_CLOSE_RESPONSE_SCHEMA)
                                .input(new BulkCloseMetadataProvider())
                                .collectionAction(true)
                                .action("close-records")));

        COLLECTION_INFO_STORE.put(ResourceType.USAGE_REPORT,
                new CollectionInfo()
                        .collection(FILES)
                        .id(USAGE_REPORT_ID)
                        .schema(USAGE_REPORT_SCHEMA)
                        .includeGetAction()
                        .includeListAction()
                        .addActionMetaData(Action.UPLOAD, new ActionInfo()
                                .input(new BaseMetadataProvider())
                                .fileName("usage_file"))
                        .addActionMetaData(Action.UPLOAD_RECON_FILE, new ActionInfo()
                                .input(new BaseMetadataProvider())
                                .fileName("recon_file")
                                .action("reconciliation"))
                        .addActionMetaData(Action.UPDATE, new ActionInfo()
                                .input(UPDATE_USAGE_REPORT_SCHEMA))
                        .addActionMetaData(Action.CREATE, new ActionInfo()
                                .input(NEW_USAGE_REPORT_SCHEMA))
                        .addActionMetaData(Action.CLOSE, new ActionInfo()
                                .includePayload(false)
                                .input(new BaseMetadataProvider()))
                        .addActionMetaData(Action.SUBMIT, new ActionInfo()
                                .includePayload(false)
                                .input(new BaseMetadataProvider()))
                        .addActionMetaData(Action.REPROCESS, new ActionInfo()
                                .includePayload(false)
                                .input(new BaseMetadataProvider()))
                        .addActionMetaData(Action.DELETE, new ActionInfo()
                                .includePayload(false)
                                .input(new BaseMetadataProvider())
                                .action(Action.DELETE.name().toLowerCase()))
                        .addActionMetaData(Action.REJECT, new ActionInfo()
                                .input(new RejectReportMetadataProvider()))
                        .addActionMetaData(Action.ACCEPT, new ActionInfo()
                                .input(new AcceptReportMetadataProvider())));

        COLLECTION_INFO_STORE.put(ResourceType.USAGE_RECONCILIATION,
                new CollectionInfo()
                        .collection(RECONCILIATIONS)
                        .id(USAGE_RECON_ID)
                        .schema(USAGE_RECON_SCHEMA)
                        .includeGetAction()
                        .includeListAction()
                        .addActionMetaData(Action.UPLOAD, new ActionInfo()
                                .input(new BaseMetadataProvider())
                                .fileName("recon_file")
                                .formAttributes(UPLOAD_NOTE)
                                .collectionAction(true))
                        .addActionMetaData(Action.DOWNLOAD_PROCESSED_FILE, new ActionInfo()
                                .input(new BaseMetadataProvider())
                                .action("processedfile"))
                        .addActionMetaData(Action.DOWNLOAD_UPLOADED_FILE, new ActionInfo()
                                .input(new BaseMetadataProvider())
                                .action("uploadedfile")));

        COLLECTION_INFO_STORE.put(ResourceType.USAGE_AGGREGATE,
                new CollectionInfo()
                        .collection(AGGREGATES)
                        .schema(USAGE_AGGREGATE_SCHEMA)
                        .includeListAction());

        COLLECTION_INFO_STORE.put(ResourceType.CASE,
                new CollectionInfo()
                        .collection(CASES)
                        .id(CASE_ID)
                        .schema(CASE_SCHEMA)
                        .includeGetAction()
                        .includeListAction()
                        .addActionMetaData(Action.PENDING, new ActionInfo()
                                .input(new BaseMetadataProvider())
                                .action(Action.PEND.name().toLowerCase()))
                        .addActionMetaData(Action.INQUIRE, new ActionInfo()
                                .input(new BaseMetadataProvider()))
                        .addActionMetaData(Action.RESOLVE, new ActionInfo()
                                .input(new BaseMetadataProvider()))
                        .addActionMetaData(Action.CLOSE, new ActionInfo()
                                .input(new CloseCaseMetadataProvider()))
                        .addActionMetaData(Action.CREATE, new ActionInfo()
                                .input(NEW_CASE_SCHEMA)));

        COLLECTION_INFO_STORE.put(ResourceType.ASSET_USAGE_AGGREGATE,
                new CollectionInfo()
                        .collection(AGGREGATES)
                        .isSubCollection(true)
                        .parentCollection(ASSETS)
                        .parentId(ASSET_ID)
                        .schema(USAGE_AGGREGATE_SCHEMA)
                        .includeListAction());

        COLLECTION_INFO_STORE.put(ResourceType.CONVERSATION_MESSAGES,
                new CollectionInfo()
                        .collection(MESSAGES)
                        .id(MESSAGE_ID)
                        .isSubCollection(true)
                        .parentCollection(CONVERSATION)
                        .parentId(CONVERSATION_ID)
                        .schema(CONVERSATION_MESSAGES_SCHEMA)
                        .includeListAction()
                        .addActionMetaData(Action.CREATE, new ActionInfo()
                                .input(NEW_CONVERSATION_MESSAGES_SCHEMA)));

        COLLECTION_INFO_STORE.put(ResourceType.PRODUCT_ACTION_LINK,
                new CollectionInfo()
                        .collection(ACTIONS)
                        .id(ACTION_ID)
                        .isSubCollection(true)
                        .parentCollection(PRODUCTS)
                        .parentId(PRODUCT_ID)
                        .schema(PRODUCT_ACTION_LINK_SCHEMA)
                        .includeGetAction(ASSET_ID));

        COLLECTION_INFO_STORE.put(ResourceType.BILLING_REQUEST_ATTRIBUTE,
                new CollectionInfo()
                        .collection(ATTRIBUTES)
                        .isSubCollection(true)
                        .parentCollection(SUBSCRIPTION_REQUESTS)
                        .parentId(REQUEST_ID)
                        .schema(BILLING_REQUEST_ATTRIBUTES_SCHEMA)
                        .addActionMetaData(Action.UPDATE,
                                new ActionInfo()
                                        .collectionAction(true)
                                        .input(BILLING_REQUEST_ATTRIBUTES_SCHEMA)));
    }

    private CollectionInfoUtil() {}

    private static List<String> getSpecificResourceAction(String resourceType, Action[] allActions) {
        ResourceType type = ResourceType.valueOf(resourceType.toUpperCase());
        CollectionInfo collectionInfo = COLLECTION_INFO_STORE.get(type);

        if (collectionInfo == null || collectionInfo.getActionMetadata().isEmpty()) {
            return new ArrayList<>();
        } else {
            return collectionInfo.getActionMetadata()
                    .keySet()
                    .stream()
                    .filter(action -> Arrays.stream(allActions)
                            .anyMatch(abstractAction -> action == abstractAction))
                    .map(Enum::name)
                    .collect(Collectors.toList());
        }
    }

    public static List<String> getActions(String resourceType) {

        ResourceType type = ResourceType.valueOf(resourceType.toUpperCase());
        CollectionInfo collectionInfo = COLLECTION_INFO_STORE.get(type);

        if (collectionInfo == null || collectionInfo.getActionMetadata().isEmpty()) {
            return new ArrayList<>();
        } else {
            return collectionInfo.getActionMetadata()
                    .keySet()
                    .stream()
                    .filter(action -> Arrays.stream(Action.getAbstractActions())
                            .noneMatch(abstractAction -> action == abstractAction))
                    .map(Enum::name)
                    .collect(Collectors.toList());
        }
    }

    public static List<String> getCreateActions(String resourceType) {
        return getSpecificResourceAction(resourceType, Action.getCreateActions());
    }

    public static List<String> getDownloadActions(String resourceType) {
        return getSpecificResourceAction(resourceType, Action.getDownloadActions());
    }

    public static List<String> getUploadActions(String resourceType) {
        return getSpecificResourceAction(resourceType, Action.getUploadActions());
    }

    public static List<String> getActionResourceTypes() {
        return COLLECTION_INFO_STORE.keySet()
                .stream()
                .filter(resourceType -> COLLECTION_INFO_STORE.get(resourceType)
                        .getActionMetadata()
                        .keySet()
                        .stream()
                        .anyMatch(action -> Arrays.stream(Action.getAbstractActions())
                                .noneMatch(abstractAction -> action == abstractAction)))
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    private static List<String> getResourceTypes(Action... actions) {
        return COLLECTION_INFO_STORE.keySet()
                .stream()
                .filter(resourceType -> Arrays.stream(actions)
                        .anyMatch(action -> COLLECTION_INFO_STORE
                                .get(resourceType)
                                .getActionMetadata()
                                .containsKey(action)))
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public static List<String> getListResourceTypes() {
        return getResourceTypes(Action.LIST);
    }

    public static List<String> getGetResourceTypes() {
        return getResourceTypes(Action.GET);
    }

    public static List<String> getCreateResourceTypes() {
        return getResourceTypes(Action.getCreateActions());
    }

    public static List<String> getUpdateResourceTypes() {
        return getResourceTypes(Action.UPDATE);
    }

    public static List<String> getDownloadResourceTypes() {
        return getResourceTypes(Action.getDownloadActions());
    }

    public static List<String> getUploadResourceTypes() {
        return getResourceTypes(Action.getUploadActions());
    }

    public static CollectionInfo getCollectionInfo(String resourceType) {
        ResourceType type = ResourceType.valueOf(resourceType.toUpperCase());
        return COLLECTION_INFO_STORE.get(type);
    }

    public static ActionInfo getActionInfo(String resourceType, String action) {
        CollectionInfo collectionInfo = getCollectionInfo(resourceType);
        return collectionInfo.getActionMetadata().get(Action.valueOf(action.toUpperCase()));
    }
}
