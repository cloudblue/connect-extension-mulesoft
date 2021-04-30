package com.cloudblue.connect.api.clients.utils;

import com.cloudblue.connect.api.clients.constants.CBCAPIConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestUtil {

    private RequestUtil() {}

    public static String buildQueryParameters(Map<String, String> queryParams) {
        List<String> literals = new ArrayList<>();
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            String separator = (entry.getKey()!=null
                    && entry.getKey().equalsIgnoreCase(CBCAPIConstants.GenericFilterKeys.ORDERING_KEY))?"":"=";
            literals.add(entry.getKey().concat(separator).concat(entry.getValue()));
        }

        return String.join("&", literals);
    }
}
