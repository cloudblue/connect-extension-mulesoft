/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.config;

import com.cloudblue.connect.internal.connection.provider.CBCConnectionProvider;
import com.cloudblue.connect.internal.operation.*;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;

@Operations({
        ListResourcesOperation.class,
        GetResourceOperation.class,
        CreateResourceOperation.class,
        UpdateResourceOperation.class,
        ResourceActionOperation.class,
        DownloadResourceFileOperation.class,
        UploadResourceFileOperation.class
})
@ConnectionProviders(CBCConnectionProvider.class)
public class CBCConfiguration {}
