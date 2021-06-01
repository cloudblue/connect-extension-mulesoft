package com.cloudblue.connect.test.internal.operations;

import com.cloudblue.connect.api.models.*;
import com.cloudblue.connect.api.models.usage.CBCUsageRecord;
import com.cloudblue.connect.api.models.usage.CBCUsageReport;
import com.cloudblue.connect.test.internal.common.BaseMuleFlowTestCase;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;

import org.junit.runners.Parameterized;

import org.mule.runtime.api.event.Event;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.tck.junit4.rule.SystemProperty;
import org.mule.test.runner.RunnerDelegateTo;

@RunnerDelegateTo(Parameterized.class)
public class GetOperationsTestCase extends BaseMuleFlowTestCase {
    
    private static final String REQUEST_ID = "PR-0000-0000-0000-001";
    private static final String ASSERT_ID = "AS-0000-0000-0000";
    private static final String CASE_ID = "CA-0000-0000";
    private static final String CONVERSATION_ID = "CO-0000-0000";
    private static final String TIERACCOUNT_ID = "TA-0000-0000";
    private static final String TIERACCOUNTREQUEST_ID = "TAR-0000-0000";
    private static final String TIERCONFIG_ID = "TC-0000-0000";
    private static final String TIERCONFIGREQUEST_ID = "TCR-0000-0000";
    private static final String USAGE_REPORT_ID = "UF-0000-00-0000-0000";
    private static final String USAGE_RECORD_ID = "UR-0000-00-0000-0000-0000-0000";
    
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
    public SystemProperty tierAccountRequestIdSystemProperty = new SystemProperty("tierAccountRequest_id", TIERACCOUNTREQUEST_ID);

    @Rule
    public SystemProperty tierConfigIdSystemProperty = new SystemProperty("tierConfig_id", TIERCONFIG_ID);

    @Rule
    public SystemProperty tierConfigRequestIdSystemProperty = new SystemProperty("tierConfigRequest_id", TIERCONFIGREQUEST_ID);

    @Rule
    public SystemProperty usageReportIdSystemProperty = new SystemProperty("usage_report_id", USAGE_REPORT_ID);

    @Rule
    public SystemProperty usageRecordIdSystemProperty = new SystemProperty("usage_record_id", USAGE_RECORD_ID);

    private final String flow;
    private final Class clazz;
    private final String expectedIdValue;
    
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"getRequest", CBCRequest.class, REQUEST_ID},
                {"getAsset", CBCAsset.class, ASSERT_ID},
                {"getCase", CBCCase.class, CASE_ID},
                {"getTierAccount", CBCAccount.class, TIERACCOUNT_ID},
                {"getTierAccountRequest", CBCAccountRequest.class, TIERACCOUNTREQUEST_ID},
                {"getTierConfig", CBCTierConfig.class, TIERCONFIG_ID},
                {"getTierConfigRequest", CBCTierConfigRequest.class, TIERCONFIGREQUEST_ID},
                {"getUsageFile", CBCUsageReport.class, USAGE_REPORT_ID},
                {"getUsageRecords", CBCUsageRecord.class, USAGE_RECORD_ID},
                {"listRequestsWithFilter", null, null},
                {"listRequestsWithoutFilter", null, null},
                {"listAssetsWithFilter", null, null},
                {"listAssetsWithoutFilter", null, null},
                {"listCasesWithFilter", null, null},
                {"listCasesWithoutFilter", null, null},
                {"listConversationMessagesWithFilter", null, null},
                {"listConversationMessagesWithoutFilter", null, null},
                {"listTierAccountsWithFilter", null, null},
                {"listTierAccountsWithoutFilter", null, null},
                {"listTierAccountRequestsWithFilter", null, null},
                {"listTierAccountRequestsWithoutFilter", null, null},
                {"listTierConfigsWithFilter", null, null},
                {"listTierConfigsWithoutFilter", null, null},
                {"listTierConfigRequestsWithFilter", null, null},
                {"listTierConfigRequestsWithoutFilter", null, null},
                {"listUsageFiles", null, null},
                {"listUsageRecords", null, null}
            }
        );
    }

    public GetOperationsTestCase(String flow, Class clazz, String expectedIdValue) {
        this.flow = flow;
        this.clazz = clazz;
        this.expectedIdValue = expectedIdValue;
    }
    
    
    @Test
    public void testRetrieve() throws Exception {
        Event getRequest = flowRunner(this.flow).run();
        Object object = getRequest.getMessage().getPayload().getValue();

        if (this.clazz != null) {
            Method idMethod = this.clazz.getDeclaredMethod("getId");
            String idValue = (String)idMethod.invoke(object);

            assertThat(idValue, is(this.expectedIdValue));
        } else {
            assertTrue(object instanceof List);
            assertEquals(1, ((List)object).size());
        }

    }
}
