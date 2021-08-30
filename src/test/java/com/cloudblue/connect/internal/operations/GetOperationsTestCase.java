/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.models.common.CBCCommonEntity;
import com.cloudblue.connect.api.models.product.*;
import com.cloudblue.connect.internal.model.helpdesks.ConversationMessage;
import com.cloudblue.connect.api.models.tier.CBCAccount;
import com.cloudblue.connect.api.models.subscription.CBCAsset;
import com.cloudblue.connect.api.models.subscription.CBCRequest;
import com.cloudblue.connect.internal.model.helpdesks.Case;
import com.cloudblue.connect.api.models.tier.CBCAccountRequest;
import com.cloudblue.connect.api.models.tier.CBCTierConfig;
import com.cloudblue.connect.api.models.tier.CBCTierConfigRequest;
import com.cloudblue.connect.api.models.usage.*;
import com.cloudblue.connect.internal.common.BaseMuleFlowTestCase;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;

import org.mule.runtime.api.event.Event;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.tck.junit4.rule.SystemProperty;

public class GetOperationsTestCase extends BaseMuleFlowTestCase {
    
    private static final String REQUEST_ID = "PR-0000-0000-0000-001";
    private static final String ASSERT_ID = "AS-0000-0000-0000";
    private static final String CASE_ID = "CA-0000-0000";
    private static final String CONVERSATION_ID = "CO-0000-0000";
    private static final String TIERACCOUNT_ID = "TA-0000-0000";
    private static final String TIERACCOUNTVERSION = "1";
    private static final String TIERACCOUNTREQUEST_ID = "TAR-0000-0000";
    private static final String TIERCONFIG_ID = "TC-0000-0000";
    private static final String TIERCONFIGREQUEST_ID = "TCR-0000-0000";
    private static final String USAGE_REPORT_ID = "UF-0000-00-0000-0000";
    private static final String PRODUCT_ID = "PRD-0000-0000";
    private static final String PRODUCTITEM_ID = "PRD-0000-0000-001";
    private static final String PRODUCTPARAMETER_ID = "PRM-000-000";
    private static final String USAGE_RECORD_ID = "UR-0000-00-0000-0000-0000-0000";
    private static final String USAGE_CHUNK_FILE_ID = "UFC-0000-00-0000-0000-001";
    private static final String USAGE_RECON_ID = "RCF-0000-00-0000-0000-001";
    private static final String PRODUCT_TEMPLATE_ID = "TMP-000-000";
    private static final String PRODUCT_ACTION_ID = "ACT-982-018-837-001";
    private static final String PRODUCT_ACTION_LINK = "https://www.example.com";

    
    @Rule
    public DynamicPort listenPort = new DynamicPort("port");
    
    @Rule
    public SystemProperty requestIdSystemProperty = new SystemProperty("request_id", REQUEST_ID);
    
    @Rule
    public SystemProperty assetIdSystemProperty = new SystemProperty("asset_id", ASSERT_ID);
    
    @Rule
    public SystemProperty caseIdSystemProperty = new SystemProperty("case_id", CASE_ID);

    @Rule
    public SystemProperty conversationMessagesIdSystemProperty = new SystemProperty("conversation_id", CONVERSATION_ID);

    @Rule
    public SystemProperty tierAccountIdSystemProperty = new SystemProperty("tierAccount_id", TIERACCOUNT_ID);

    @Rule
    public SystemProperty tierAccountVersionSystemProperty = new SystemProperty("tierAccountVersion", TIERACCOUNTVERSION);

    @Rule
    public SystemProperty tierAccountRequestIdSystemProperty = new SystemProperty("tierAccountRequest_id", TIERACCOUNTREQUEST_ID);

    @Rule
    public SystemProperty tierConfigIdSystemProperty = new SystemProperty("tierConfig_id", TIERCONFIG_ID);

    @Rule
    public SystemProperty tierConfigRequestIdSystemProperty = new SystemProperty("tierConfigRequest_id", TIERCONFIGREQUEST_ID);

    @Rule
    public SystemProperty usageReportIdSystemProperty = new SystemProperty("usage_report_id", USAGE_REPORT_ID);

    @Rule
    public SystemProperty productIdSystemProperty = new SystemProperty("product_id", PRODUCT_ID);

    @Rule
    public SystemProperty productItemIdSystemProperty = new SystemProperty("productItem_id", PRODUCTITEM_ID);

    @Rule
    public SystemProperty productParameterIdSystemProperty = new SystemProperty("productParameter_id", PRODUCTPARAMETER_ID);

    @Rule
    public SystemProperty usageRecordIdSystemProperty = new SystemProperty("usage_record_id", USAGE_RECORD_ID);

    @Rule
    public SystemProperty usageChunkFileIdSystemProperty = new SystemProperty("chunk_file_id", USAGE_CHUNK_FILE_ID);

    @Rule
    public SystemProperty usageReconIdSystemProperty = new SystemProperty("usage_recon_id", USAGE_RECON_ID);

    @Rule
    public SystemProperty versionSystemProperty = new SystemProperty("tierAccountVersion", TIERACCOUNTVERSION);

    @Rule
    public SystemProperty productTemplateSystemProperty = new SystemProperty("productTemplateId", PRODUCT_TEMPLATE_ID);

    @Rule
    public SystemProperty productActionSystemProperty = new SystemProperty("action_id", PRODUCT_ACTION_ID);

    @Rule
    public SystemProperty productActionLinkSystemProperty = new SystemProperty("action_link", PRODUCT_ACTION_LINK);


    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"getRequest", CBCRequest.class, REQUEST_ID},
                {"getAsset", CBCAsset.class, ASSERT_ID},
                {"getCase", Case.class, CASE_ID},
                {"getTierAccount", CBCAccount.class, TIERACCOUNT_ID},
                {"getTierAccountVersion", CBCAccount.class, TIERACCOUNT_ID},
                {"getTierAccountRequest", CBCAccountRequest.class, TIERACCOUNTREQUEST_ID},
                {"getTierConfig", CBCTierConfig.class, TIERCONFIG_ID},
                {"getTierConfigRequest", CBCTierConfigRequest.class, TIERCONFIGREQUEST_ID},
                {"getProduct", CBCProduct.class, PRODUCT_ID},
                {"getProductItem", CBCProductItem.class, PRODUCTITEM_ID},
                {"getProductParameter", CBCProductParameter.class, PRODUCTPARAMETER_ID},
                {"getUsageFile", CBCUsageReport.class, USAGE_REPORT_ID},
                {"getUsageRecords", CBCUsageRecord.class, USAGE_RECORD_ID},
                {"getUsageChunkFiles", CBCUsageChunkFile.class, USAGE_CHUNK_FILE_ID},
                {"getUsageReconciliations", CBCUsageReconciliation.class, USAGE_RECON_ID},
                {"getProductTemplate", CBCTemplate.class, PRODUCT_TEMPLATE_ID},
                {"getBillingRequestAttributes", null, null},
                {"getProductAction", CBCProductAction.class, PRODUCT_ACTION_ID},
                {"listRequestsWithFilter", CBCRequest.class, REQUEST_ID},
                {"listRequestsWithoutFilter", CBCRequest.class, REQUEST_ID},
                {"listAssetsWithFilter", CBCAsset.class, ASSERT_ID},
                {"listAssetsWithoutFilter", CBCAsset.class, ASSERT_ID},
                {"listCasesWithFilter", Case.class, CASE_ID},
                {"listCasesWithoutFilter", Case.class, CASE_ID},
                {"listConversationMessagesWithFilter", ConversationMessage.class, CONVERSATION_ID},
                {"listConversationMessagesWithoutFilter", ConversationMessage.class, CONVERSATION_ID},
                {"listTierAccountsWithFilter", CBCAccount.class, TIERACCOUNT_ID},
                {"listTierAccountsWithoutFilter", CBCAccount.class, TIERACCOUNT_ID},
                {"listTierAccountVersions", CBCAccount.class, TIERACCOUNT_ID},
                {"listTierAccountRequestsWithFilter", CBCAccountRequest.class, TIERACCOUNTREQUEST_ID},
                {"listTierAccountRequestsWithoutFilter", CBCAccountRequest.class, TIERACCOUNTREQUEST_ID},
                {"listTierConfigsWithFilter", CBCTierConfig.class, TIERCONFIG_ID},
                {"listTierConfigsWithoutFilter", CBCTierConfig.class, TIERCONFIG_ID},
                {"listTierConfigRequestsWithFilter", CBCTierConfigRequest.class, TIERCONFIGREQUEST_ID},
                {"listTierConfigRequestsWithoutFilter", CBCTierConfigRequest.class, TIERCONFIGREQUEST_ID},
                {"listProductsWithFilter", CBCProduct.class, PRODUCT_ID},
                {"listProductsWithoutFilter", CBCProduct.class, PRODUCT_ID},
                {"listProductItemsWithFilter", CBCProductItem.class, PRODUCTITEM_ID},
                {"listProductItemsWithoutFilter", CBCProductItem.class, PRODUCTITEM_ID},
                {"listProductParametersWithFilter", CBCProductParameter.class, PRODUCTPARAMETER_ID},
                {"listProductParametersWithoutFilter", CBCProductParameter.class, PRODUCTPARAMETER_ID},
                {"listUsageFiles", CBCUsageReport.class, USAGE_REPORT_ID},
                {"listUsageRecords", CBCUsageRecord.class, USAGE_RECORD_ID},
                {"listUsageChunkFiles", CBCUsageChunkFile.class, USAGE_CHUNK_FILE_ID},
                {"listUsageReconciliations", CBCUsageReconciliation.class, USAGE_RECON_ID},
                {"listUsageAggregates", null, null},
                {"listAssetUsageAggregates", null, null},
                {"listProductTemplates", CBCTemplate.class, PRODUCT_TEMPLATE_ID},
                {"listProductActions", CBCProductAction.class, PRODUCT_ACTION_ID}
            }
        );
    }

    @Test
    public void testRetrieve() throws Exception {
        for (Object[] objects : data()) {
            Event getRequest = flowRunner((String) objects[0]).run();
            Object object = getRequest.getMessage().getPayload().getValue();

            if (object instanceof List) {
                List list = (List)object;
                assertEquals(1, list.size());
                object = (list.get(0));
            }

            if (objects[1] != null) {
                Class<?> aClass = (Class)objects[1];

                Class<?> superClass = aClass.getSuperclass();

                if (superClass == CBCCommonEntity.class)
                    aClass = superClass;


                Method idMethod = aClass.getDeclaredMethod("getId");
                String idValue = (String)idMethod.invoke(object);

                assertThat(idValue, is(objects[2]));
            }
        }
    }

    @Test
    public void testActionLink() throws Exception {
        Event getRequest = flowRunner("getProductActionLink").run();
        String url = (String) getRequest.getMessage().getPayload().getValue();

        assertThat(url, is(PRODUCT_ACTION_LINK));
    }
}
