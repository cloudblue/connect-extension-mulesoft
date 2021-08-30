/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.clients.utils;

import com.cloudblue.connect.api.clients.constants.APIConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestUtil {

    private RequestUtil() {}

    public static String buildQueryParameters(Map<String, String> queryParams) {
        List<String> literals = new ArrayList<>();
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            String separator = (entry.getKey()!=null
                    && entry.getKey().equalsIgnoreCase(APIConstants.GenericFilterKeys.ORDERING_KEY))?"":"=";
            literals.add(entry.getKey().concat(separator).concat(entry.getValue()));
        }

        return String.join("&", literals);
    }
}
