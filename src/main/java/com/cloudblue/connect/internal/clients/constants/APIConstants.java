/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.clients.constants;

public class APIConstants {

    private APIConstants() {}

    public static class GenericFilterKeys {
        private GenericFilterKeys() {}

        public static final String ORDERING_KEY = "ordering";
    }

    public static class CollectionKeys {
        private CollectionKeys() {}

        public static final String SEPERATOR = "/";
        public static final String NOTIFICATIONS = "notifications";
        public static final String WEBHOOKS = "webhooks";
        public static final String REQUESTS = "requests";
        public static final String ASSETS = "assets";
        public static final String HELPDESK = "helpdesk";
        public static final String CASES = HELPDESK + SEPERATOR + "cases";
        public static final String USAGE = "usage";
        public static final String FILES = USAGE + SEPERATOR + "files";
        public static final String RECORDS = USAGE +  SEPERATOR + "records";
        public static final String CHUNKS = USAGE +  SEPERATOR + "chunks";
        public static final String RECONCILIATIONS = USAGE +  SEPERATOR + "reconciliations";
        public static final String AGGREGATES = USAGE +  SEPERATOR + "aggregates";
        public static final String PRODUCTS = "products";
        public static final String ACTIONS = "actions";
        public static final String ITEMS = "items";
        public static final String PARAMETERS = "parameters";
        public static final String CONFIGURATIONS = "configurations";
        public static final String TIER = "tier";
        public static final String TIER_ACCOUNTS = TIER +  SEPERATOR + "accounts";
        public static final String VERSIONS = "versions";
        public static final String TIER_CONFIG = TIER +  SEPERATOR + "configs";
        public static final String ACCOUNT_REQUESTS = TIER +  SEPERATOR + "account-requests";
        public static final String CONFIG_REQUESTS = TIER +  SEPERATOR + "config-requests";
        public static final String TEMPLATES = "templates";
        public static final String SUBSCRIPTIONS = "subscriptions";
        public static final String SUBSCRIPTION_REQUESTS = SUBSCRIPTIONS + SEPERATOR + REQUESTS;
        public static final String SUBSCRIPTION_ASSETS = SUBSCRIPTIONS + SEPERATOR + ASSETS;
        public static final String ATTRIBUTES = "attributes";
        public static final String CONVERSATION = "conversations";
        public static final String MESSAGES = "messages";
    }
}
