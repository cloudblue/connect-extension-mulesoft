/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata;

import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.metadata.api.builder.BaseTypeBuilder;
import org.mule.metadata.api.model.MetadataType;
import org.mule.metadata.api.model.ObjectFieldType;
import org.mule.metadata.api.model.ObjectType;
import org.mule.runtime.api.component.location.Location;
import org.mule.runtime.api.meta.model.operation.OperationModel;
import org.mule.runtime.api.metadata.MetadataKey;
import org.mule.runtime.api.metadata.MetadataKeyBuilder;
import org.mule.runtime.api.metadata.MetadataService;
import org.mule.runtime.api.metadata.descriptor.ComponentMetadataDescriptor;
import org.mule.runtime.api.metadata.resolving.MetadataResult;

import javax.inject.Inject;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isAllBlank;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mule.metadata.api.model.MetadataFormat.JAVA;
import static org.mule.runtime.api.component.location.Location.builder;

public abstract class MetadataProviderTest
        extends MuleArtifactFunctionalTestCase {

    @Inject
    protected MetadataService metadataService;

    protected final BaseTypeBuilder typeBuilder = BaseTypeBuilder.create(JAVA);

    public MetadataKey buildMetadataKey(String first, String second) {
        MetadataKeyBuilder builder = MetadataKeyBuilder.newKey(first);

        if (second != null) {
            builder.withChild(MetadataKeyBuilder.newKey(second).build());
        }

        return builder.build();
    }

    protected MetadataResult<ComponentMetadataDescriptor<OperationModel>> getMetadata(
            String flow, int operationPosition, String firstKey, String secondKey) {
        Location location = builder().globalName(flow)
                .addProcessorsPart()
                .addIndexPart(operationPosition)
                .build();

        return isAllBlank(firstKey, secondKey) ? metadataService.getOperationMetadata(location)
                : metadataService.getOperationMetadata(location, buildMetadataKey(firstKey, secondKey));
    }

    protected MetadataType getInputMetadata(
            String flow, int operationPosition,
            String firstKey, String secondKey,
            String inputParameterName) {

        MetadataResult<ComponentMetadataDescriptor<OperationModel>> metadata = getMetadata(
                flow, operationPosition, firstKey, secondKey);

        assertThat(metadata.isSuccess(), is(true));
        return metadata.get().getModel().getAllParameterModels().stream()
                .filter(p -> p.getName().equals(inputParameterName))
                .findFirst()
                .get()
                .getType();
    }

    protected MetadataType getOutputMetadata(
            String flow, int operationPosition,
            String firstKey, String secondKey,
            String inputParameterName) {

        MetadataResult<ComponentMetadataDescriptor<OperationModel>> metadata = getMetadata(
                flow, operationPosition, firstKey, secondKey);

        assertThat(metadata.isSuccess(), is(true));
        return metadata.get().getModel().getOutput().getType();
    }

    protected void assertFieldRequirement(ObjectType record, String name, boolean required) {
        Optional<ObjectFieldType> field = record.getFieldByName(name);
        assertThat(field.isPresent(), is(true));
        assertThat(field.get().isRequired(), is(required));
    }

    protected void assertFieldOfType(ObjectType record, String name, MetadataType type) {
        Optional<ObjectFieldType> field = record.getFieldByName(name);
        assertThat(field.isPresent(), is(true));
        assertThat(field.get().getValue(), equalTo(type));
    }
}
