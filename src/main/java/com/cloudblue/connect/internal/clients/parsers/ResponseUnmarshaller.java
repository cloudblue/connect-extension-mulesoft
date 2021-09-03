/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.clients.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ResponseUnmarshaller {
    <T> T unmarshal(String stringResponse, Class<T> responseType) throws JsonProcessingException;

    Object unmarshal(String stringResponse, Object typeInfo) throws JsonProcessingException;
}
