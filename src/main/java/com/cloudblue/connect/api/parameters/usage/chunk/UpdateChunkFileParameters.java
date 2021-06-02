package com.cloudblue.connect.api.parameters.usage.chunk;

import com.cloudblue.connect.api.parameters.Embeddable;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import java.util.HashMap;
import java.util.Map;

public class UpdateChunkFileParameters extends ResourceActionParameter implements Embeddable {

    @Parameter
    private String externalId;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Override
    public Object buildEntity() {
        Map<String, String> payload = new HashMap<>();
        payload.put("external_id", externalId);

        return payload;
    }
}
