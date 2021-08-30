/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.clients.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Url {

    private static final Logger logger = LoggerFactory.getLogger(Url.class);

    private Url() {}

    public static String encode(String filters) {
        try {
            return URLEncoder.encode(filters, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("Error during encoding filters.", e);
            return filters;
        }
    }
}
