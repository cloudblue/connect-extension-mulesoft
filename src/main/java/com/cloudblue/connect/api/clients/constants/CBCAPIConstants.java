package com.cloudblue.connect.api.clients.constants;

public class CBCAPIConstants {

    private CBCAPIConstants() {}

    public static class GenericFilterKeys {
        private GenericFilterKeys() {}

        public static final String ORDERING_KEY = "ordering";
    }

    public static class CollectionKeys {
        private CollectionKeys() {}

        public static final String NOTIFICATIONS = "notifications";
        public static final String WEBHOOKS = "webhooks";
        public static final String REQUESTS = "requests";
        public static final String ASSETS = "assets";
        public static final String HELPDESK = "helpdesk";
        public static final String CASES = "cases";
        public static final String USAGE = "usage";
        public static final String FILES = "files";
        public static final String RECORDS = "records";
        public static final String CHUNKS = "chunks";
        public static final String RECONCILIATIONS = "reconciliations";
        public static final String AGGREGATES = "aggregates";
    }
}
