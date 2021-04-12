package com.cloudblue.connect.api.clients.utils;

import com.cloudblue.connect.api.clients.constants.CBCAPIConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestUtil {

    public static String expandUri(String uri, Map<String, String> uriVariables) {
        if (uriVariables != null && !uriVariables.isEmpty()) {
            for (Map.Entry<String, String> entry : uriVariables.entrySet()) {
                String variableKey = "{" + entry.getKey() + "}";
                if (uri.contains(variableKey)) {
                    uri = uri.replace(variableKey, entry.getValue());
                }
            }
        }
        return uri;
    }

    public static String addQueryParams(String uri, Map<String, Object> queryParams) {
        if (queryParams!=null && !queryParams.isEmpty()){

            for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
                String separator = (entry.getKey()!=null 
                        && entry.getKey().equalsIgnoreCase(CBCAPIConstants.GenericFilterKeys.ORDERING_KEY))?"":"=";
                if (uri.contains("?")){
                    uri =uri.concat("&".concat(entry.getKey()).concat(separator).concat(entry.getValue().toString()));
                }
                else{
                    uri =uri.concat("?".concat(entry.getKey()).concat(separator).concat(entry.getValue().toString()));
                }
            }
        }
        return uri;
    }

    public static String buildQueryParameters(Map<String, String> queryParams) {
        List<String> literals = new ArrayList<>();
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            String separator = (entry.getKey()!=null
                    && entry.getKey().equalsIgnoreCase(CBCAPIConstants.GenericFilterKeys.ORDERING_KEY))?"":"=";
            literals.add(entry.getKey().concat(separator).concat(entry.getValue().toString()));
        }

        return String.join("&", literals);
    }
}
