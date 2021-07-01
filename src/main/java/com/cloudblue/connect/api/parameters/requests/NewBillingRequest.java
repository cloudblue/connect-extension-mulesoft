package com.cloudblue.connect.api.parameters.requests;

import com.cloudblue.connect.api.models.enums.CBCRequestType;
import com.cloudblue.connect.api.models.subscription.*;
import com.cloudblue.connect.api.parameters.Embeddable;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

import java.util.List;
import java.util.Map;


public class NewBillingRequest implements Embeddable {

    public enum Type {
        VENDOR, PROVIDER
    }

    @Parameter
    private String assetId;

    @Parameter
    private Type type;

    @Parameter
    private List<CBCRequestItem> items;

    @Parameter
    @DisplayName("Period From")
    private String from;

    @Parameter
    @DisplayName("Period To")
    private String to;

    @Parameter
    @DisplayName("Delta")
    private Integer delta;

    @Parameter
    @DisplayName("OUM")
    private String oum;

    @Parameter
    @Optional
    @Placement(tab = Placement.ADVANCED_TAB)
    private Map<String, String> attributeList;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<CBCRequestItem> getItems() {
        return items;
    }

    public void setItems(List<CBCRequestItem> items) {
        this.items = items;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getDelta() {
        return delta;
    }

    public void setDelta(Integer delta) {
        this.delta = delta;
    }

    public String getOum() {
        return oum;
    }

    public void setOum(String oum) {
        this.oum = oum;
    }

    public Map<String, String> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(Map<String, String> attributeList) {
        this.attributeList = attributeList;
    }

    @Override
    public Object buildEntity() {
        CBCRequest request = new CBCRequest();
        request.setType(CBCRequestType.valueOf(type.name()));

        request.setAsset(new CBCAsset());
        request.getAsset().setItems(this.items);
        request.getAsset().setId(this.assetId);

        request.setPeriod(new CBCBillingPeriod());
        request.getPeriod().setFrom(this.from);
        request.getPeriod().setTo(this.to);
        request.getPeriod().setDelta(this.delta);
        request.getPeriod().setUom(this.oum);

        request.setAttributes(new CBCBillingAttributes());

        if (type == Type.VENDOR)
            request.getAttributes().setVendor(this.attributeList);
        else
            request.getAttributes().setProvider(this.attributeList);

        return request;
    }

}
