package com.cloudblue.connect.api.polling;

import java.io.Serializable;

public class PollingAttributes implements Serializable {
    private static final long serialVersionUID = -492640902896281473L;

    String objectType;

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
}
