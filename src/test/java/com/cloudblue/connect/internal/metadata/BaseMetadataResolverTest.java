/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata;

import com.cloudblue.connect.api.parameters.ActionIdentifier;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.mule.metadata.api.builder.BaseTypeBuilder;
import org.mule.metadata.api.model.*;
import org.mule.runtime.api.metadata.MetadataContext;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mule.metadata.api.model.MetadataFormat.JAVA;

public abstract class BaseMetadataResolverTest {

    protected MetadataContext context = mock(MetadataContext.class);
    protected static final BaseTypeBuilder typeBuilder = BaseTypeBuilder.create(JAVA);

    @Parameterized.Parameter(1)
    public String resource;

    @Parameterized.Parameter(2)
    public String action;

    @Parameterized.Parameter(3)
    public int fieldSize;

    @Parameterized.Parameter(4)
    public Keys[] keys;

    @Parameterized.Parameter(5)
    public MetadataType[] types;

    @Parameterized.Parameter(6)
    public Boolean[] required;

    @Before
    public void setUp() {
        when(context.getTypeBuilder())
                .thenReturn(BaseTypeBuilder.create(MetadataFormat.JAVA));
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

    protected ActionIdentifier action(String resource, String action) {
        ActionIdentifier actionIdentifier = new ActionIdentifier();
        actionIdentifier.setResourceType(resource);
        actionIdentifier.setAction(action);

        return actionIdentifier;
    }

    protected static MetadataType getStringType() {
        return typeBuilder.stringType().build();
    }

    protected static MetadataType getBooleanType() {
        return typeBuilder.booleanType().build();
    }

    protected static MetadataType getNumberType() {
        return typeBuilder.numberType().build();
    }

    protected void verify(ObjectType input) {
        assertThat(input.getFields().size(), equalTo(fieldSize));

        for (int count = 0; count < keys.length; count++) {
            assertFieldOfType(input, keys[count].getField(), types[count]);
            assertFieldRequirement(input, keys[count].getField(), required[count]);
        }
    }

    protected void verify(ArrayType array) {
        ObjectType input = (ObjectType) array.getType();
        verify(input);
    }
}
