/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.extension;

import com.cloudblue.connect.api.parameters.filters.Filter;
import com.cloudblue.connect.api.parameters.filters.InFilter;
import com.cloudblue.connect.api.parameters.filters.ListFilter;
import com.cloudblue.connect.api.parameters.filters.MonoFilter;
import com.cloudblue.connect.api.parameters.filters.NotFilter;
import com.cloudblue.connect.api.parameters.filters.OutFilter;
import com.cloudblue.connect.api.parameters.filters.RawRQLFilter;
import com.cloudblue.connect.internal.config.CBCConfiguration;
import com.cloudblue.connect.internal.config.CBCWebhookConfig;
import com.cloudblue.connect.internal.error.CBCErrorType;
import org.mule.runtime.api.meta.Category;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.SubTypeMapping;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.error.ErrorTypes;
import org.mule.runtime.extension.api.annotation.license.RequiresEnterpriseLicense;


/**
 * This is the main class of an extension, is the entry point from which configurations,
 * connection providers, operations
 * and sources are going to be declared.
 */
@Xml(prefix = "cloudblue-connect")
@Extension(name = "CloudBlue Connect", category = Category.CERTIFIED, vendor = "CloudBlue")
@RequiresEnterpriseLicense(allowEvaluationLicense = true)
@Configurations({CBCConfiguration.class, CBCWebhookConfig.class})
@ErrorTypes(CBCErrorType.class)
@SubTypeMapping(
        baseType = Filter.class,
        subTypes = {
                ListFilter.class,
                MonoFilter.class,
                InFilter.class,
                OutFilter.class,
                NotFilter.class,
                RawRQLFilter.class
        })
public class CBCConnector {}
