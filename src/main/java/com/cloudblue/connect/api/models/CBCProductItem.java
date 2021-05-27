package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCProductItem implements CBCEntity {
    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String status;

    @JsonProperty
    private CBCUnit unit;
    
    @JsonProperty
    private String mpn;

    @JsonProperty
    private String position;

    @JsonProperty
    private String type;

    @JsonProperty
    private String localId;

    @JsonProperty
    private String displayName;

    @JsonProperty
    private String period;

    @JsonProperty
    private String precision;

    @JsonProperty
    private CBCCommitment commitment;

    @JsonProperty
    private String dynamic;

    @JsonProperty
    private CBCExtensions replacement;

    @JsonProperty
    private String endOfSaleNotes;

    @JsonProperty
    private CBCItemUi visibility;

    @JsonProperty
    private String uiVisibility;

    @JsonProperty
    private String description;

    @JsonProperty
    private CBCExtensions parent;

    @JsonProperty
    private String depth;    

    @JsonProperty
    private CBCEvents events;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CBCUnit getUnit() {
        return unit;
    }

    public void setUnit(CBCUnit unit) {
        this.unit = unit;
    }
  
    public String getMpn() {
        return mpn;
    }

    public void setMpn(String mpn) {
        this.mpn = mpn;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }    

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }    
    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }    


    public CBCCommitment getCommitment() {
        return commitment;
    }

    public void setCommitment(CBCCommitment commitment) {
        this.commitment = commitment;
    }

    public String getDynamic() {
        return dynamic;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }

    public CBCExtensions getReplacement() {
        return replacement;
    }

    public void setreplacement(CBCExtensions replacement) {
        this.replacement = replacement;
    }

    public String getEndOfSaleNotes() {
        return endOfSaleNotes;
    }

    public void setEndOfSaleNotes(String endOfSaleNotes) {
        this.endOfSaleNotes = endOfSaleNotes;
    }

    public CBCItemUi getVisibility() {
        return visibility;
    }

    public void setVisibility(CBCItemUi visibility) {
        this.visibility = visibility;
    }
    
    public String getUiVisibility() {
        return uiVisibility;
    }

    public void setUiVisibility(String uiVisibility) {
        this.uiVisibility = uiVisibility;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CBCExtensions getParent() {
        return parent;
    }

    public void setParent(CBCExtensions parent) {
        this.parent = parent;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public CBCEvents getEvents() {
        return events;
    }

    public void setEvents(CBCEvents events) {
        this.events = events;
    }
}
