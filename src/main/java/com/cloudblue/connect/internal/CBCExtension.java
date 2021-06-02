package com.cloudblue.connect.internal;

import com.cloudblue.connect.api.parameters.filters.*;
import com.cloudblue.connect.api.parameters.requests.*;
import com.cloudblue.connect.api.parameters.usage.chunk.ChunkFileAction;
import com.cloudblue.connect.api.parameters.usage.chunk.CloseChunkFile;
import com.cloudblue.connect.api.parameters.usage.chunk.RegenerateChunkFile;
import com.cloudblue.connect.api.parameters.usage.report.*;
import com.cloudblue.connect.internal.operations.CBCConfiguration;
import com.cloudblue.connect.internal.sources.CBCWebhookConfig;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.SubTypeMapping;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.Configurations;


/**
 * This is the main class of an extension, is the entry point from which configurations,
 * connection providers, operations
 * and sources are going to be declared.
 */
@Xml(prefix = "cloudblue-connect")
@Extension(name = "CloudBlue Connect", vendor = "CloudBlue")
@Configurations({CBCConfiguration.class, CBCWebhookConfig.class})
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
@SubTypeMapping(
        baseType = RequestAction.class,
        subTypes = {
                ApproveRequest.class,
                AssignRequest.class,
                InquireRequest.class,
                PendRequest.class,
                PurchaseRequest.class,
                RejectRequest.class,
                UnassignRequest.class
        })
@SubTypeMapping(
        baseType = UsageReportAction.class,
        subTypes = {
                AcceptUsageReport.class,
                CloseUsageReport.class,
                DeleteUsageReport.class,
                ReconcileUsageReport.class,
                RejectUsageReport.class,
                ReprocessUsageFile.class,
                SubmitUsageReport.class,
                UploadUsageReport.class
        })
@SubTypeMapping(
        baseType = ChunkFileAction.class,
        subTypes = {
                CloseChunkFile.class,
                RegenerateChunkFile.class
        })
public class CBCExtension {}
