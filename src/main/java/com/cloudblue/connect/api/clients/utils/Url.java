package com.cloudblue.connect.api.clients.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Url {
    private Url() {}

    public static String encode(String filters) {
        try {
            return URLEncoder.encode(filters, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return filters;
        }
    }
}
