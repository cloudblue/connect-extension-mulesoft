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
