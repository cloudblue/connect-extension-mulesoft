/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata.resource.action;

import com.cloudblue.connect.api.parameters.ActionIdentifier;
import com.cloudblue.connect.internal.metadata.BaseMetadataResolverTest;
import com.cloudblue.connect.internal.metadata.Keys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.mule.metadata.api.model.ArrayType;
import org.mule.metadata.api.model.MetadataType;
import org.mule.metadata.api.model.ObjectType;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.InputTypeResolver;

import java.util.Arrays;
import java.util.Collection;

import static com.cloudblue.connect.internal.metadata.Keys.*;

@RunWith(Parameterized.class)
public class ResourceActionInputResolverTest extends BaseMetadataResolverTest {

    @Parameterized.Parameter
    public InputTypeResolver<ActionIdentifier> inputTypeResolver;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                        { new ResourceActionInputResolver(),
                                "REQUEST", "APPROVE", 3,
                                new Keys[] { REQUEST_ID, TEMPLATE_ID, EFFECTIVE_DATE },
                                new MetadataType[] { getStringType(), getStringType(), getStringType() },
                                new Boolean[] {true, true, false}
                        },
                        { new ResourceActionInputResolver(),
                                "REQUEST", "ASSIGN", 3,
                                new Keys[] { REQUEST_ID, EMAIL, FORCE },
                                new MetadataType[] { getStringType(), getStringType(), getBooleanType() },
                                new Boolean[] {true, true, false}
                        },
                        { new ResourceActionInputResolver(),
                                "REQUEST", "FAIL", 2,
                                new Keys[] { REQUEST_ID, REASON },
                                new MetadataType[] { getStringType(), getStringType() },
                                new Boolean[] {true, true}
                        },
                        { new ResourceActionInputResolver(),
                                "REQUEST", "INQUIRE", 2,
                                new Keys[] { REQUEST_ID, TEMPLATE_ID },
                                new MetadataType[] { getStringType(), getStringType() },
                                new Boolean[] {true, true}
                        },
                        { new ResourceActionInputResolver(),
                                "REQUEST", "PURCHASE", 1,
                                new Keys[] { REQUEST_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true}
                        },
                        { new ResourceActionInputResolver(),
                                "REQUEST", "UNASSIGN", 1,
                                new Keys[] { REQUEST_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true}
                        },
                        { new ResourceActionInputResolver(),
                                "REQUEST", "PENDING", 1,
                                new Keys[] { REQUEST_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true}
                        },
                        { new ResourceActionInputResolver(),
                                "TIER_ACCOUNT_REQUEST", "ACCEPT", 1,
                                new Keys[] { TAR_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true}
                        },
                        { new ResourceActionInputResolver(),
                                "TIER_ACCOUNT_REQUEST", "IGNORE", 2,
                                new Keys[] { TAR_ID, REASON },
                                new MetadataType[] { getStringType(), getStringType() },
                                new Boolean[] {true, false}
                        },
                        { new ResourceActionInputResolver(),
                                "TIER_CONFIG_REQUEST", "APPROVE", 3,
                                new Keys[] { TCR_ID, EFFECTIVE_DATE },
                                new MetadataType[] { getStringType(), getStringType() },
                                new Boolean[] {true, false }
                        },
                        { new ResourceActionInputResolver(),
                                "TIER_CONFIG_REQUEST", "INQUIRE", 1,
                                new Keys[] { TCR_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true }
                        },
                        { new ResourceActionInputResolver(),
                                "TIER_CONFIG_REQUEST", "PENDING", 1,
                                new Keys[] { TCR_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true }
                        },
                        { new ResourceActionInputResolver(),
                                "TIER_CONFIG_REQUEST", "FAIL", 2,
                                new Keys[] { TCR_ID, REASON },
                                new MetadataType[] { getStringType(), getStringType() },
                                new Boolean[] {true, false }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_CHUNK", "REGENERATE", 1,
                                new Keys[] { USAGE_CHUNK_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_CHUNK", "CLOSE", 3,
                                new Keys[] { USAGE_CHUNK_ID, EXTERNAL_BILLING_ID, EXTERNAL_BILLING_NOTE },
                                new MetadataType[] { getStringType(), getStringType(), getStringType() },
                                new Boolean[] {true, true, false }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_CHUNK", "DOWNLOAD", 1,
                                new Keys[] { USAGE_CHUNK_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_RECORD", "CLOSE", 3,
                                new Keys[] { USAGE_RECORD_ID, EXTERNAL_BILLING_ID, EXTERNAL_BILLING_NOTE },
                                new MetadataType[] { getStringType(), getStringType(), getStringType() },
                                new Boolean[] {true, true, false }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_RECORD", "BULK_CLOSE", 3,
                                new Keys[] { ID, EXTERNAL_BILLING_ID, EXTERNAL_BILLING_NOTE },
                                new MetadataType[] { getStringType(), getStringType(), getStringType() },
                                new Boolean[] {true, true, false }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_REPORT", "UPLOAD", 1,
                                new Keys[] { USAGE_REPORT_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_REPORT", "UPLOAD_RECON_FILE", 1,
                                new Keys[] { USAGE_REPORT_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_REPORT", "CLOSE", 1,
                                new Keys[] { USAGE_REPORT_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_REPORT", "SUBMIT", 1,
                                new Keys[] { USAGE_REPORT_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_REPORT", "REPROCESS", 1,
                                new Keys[] { USAGE_REPORT_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_REPORT", "DELETE", 1,
                                new Keys[] { USAGE_REPORT_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_REPORT", "REJECT", 2,
                                new Keys[] { USAGE_REPORT_ID, REJECTION_NOTE },
                                new MetadataType[] { getStringType(), getStringType() },
                                new Boolean[] {true, true }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_REPORT", "ACCEPT", 2,
                                new Keys[] { USAGE_REPORT_ID, ACCEPTANCE_NOTE },
                                new MetadataType[] { getStringType(), getStringType() },
                                new Boolean[] {true, true }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_RECONCILIATION", "UPLOAD", 2,
                                new Keys[] { USAGE_RECON_ID, UPLOAD_NOTE },
                                new MetadataType[] { getStringType(), getStringType() },
                                new Boolean[] {true, true }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_RECONCILIATION", "DOWNLOAD_PROCESSED_FILE", 1,
                                new Keys[] { USAGE_RECON_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true }
                        },
                        { new ResourceActionInputResolver(),
                                "USAGE_RECONCILIATION", "DOWNLOAD_UPLOADED_FILE", 1,
                                new Keys[] { USAGE_RECON_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true }
                        },
                        { new ResourceActionInputResolver(),
                                "CASE", "PENDING", 1,
                                new Keys[] { CASE_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true }
                        },
                        { new ResourceActionInputResolver(),
                                "CASE", "INQUIRE", 1,
                                new Keys[] { CASE_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true }
                        },
                        { new ResourceActionInputResolver(),
                                "CASE", "RESOLVE", 1,
                                new Keys[] { CASE_ID },
                                new MetadataType[] { getStringType() },
                                new Boolean[] {true }
                        },
                        { new ResourceActionInputResolver(),
                                "CASE", "CLOSE", 3,
                                new Keys[] { CASE_ID, RATING, FEEDBACK },
                                new MetadataType[] { getStringType(), getNumberType(), getStringType() },
                                new Boolean[] {true, false, false }
                        }
                }
        );
    }

    @Test
    public void getInputMetadataTest() throws MetadataResolvingException, ConnectionException {
        ActionIdentifier actionIdentifier = action(resource, action);
        MetadataType metadataType = inputTypeResolver.getInputMetadata(context, actionIdentifier);

        if (metadataType instanceof ArrayType) {
            verify((ArrayType) metadataType);
        } else {
            verify((ObjectType) metadataType);
        }
    }
}
