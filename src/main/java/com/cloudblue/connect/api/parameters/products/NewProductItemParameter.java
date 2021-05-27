package com.cloudblue.connect.api.parameters.products;

import com.cloudblue.connect.api.models.CBCProductItem;
import com.cloudblue.connect.api.models.CBCCommitment;
import com.cloudblue.connect.api.models.CBCEvents;
import com.cloudblue.connect.api.models.CBCUnit;
import com.cloudblue.connect.api.models.CBCItemUi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class NewProductItemParameter {
    @Parameter
    @Expression
    private String productId;

    @Parameter
    @Expression
    private String name;

    @Parameter
    @Expression
    private String mpn;

    @Parameter
    @Expression
    private String description;

    @Parameter
    @Expression
    private String type;

    @Parameter
    @Expression
    private String dynamic;

    @Parameter
    @Expression
    private String period;

    @Parameter
    @Expression
    private String commitmentCount;

    @Parameter
    @Expression
    private String unitId;

    @Parameter
    @Expression
    private String precision;

    @Parameter
    @Expression
    private String uiVisibility;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMpn() {
        return mpn;
    }

    public void setMpn(String mpn) {
        this.mpn = mpn;
    }    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }  

    public String getDynamic() {
        return dynamic;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }    

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }    

    public String getCommitmentCount() {
        return commitmentCount;
    }

    public void setCommitmentCount(String commitmentCount) {
        this.commitmentCount = commitmentCount;
    }    

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }    

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }    

    public String getUiVisibility() {
        return uiVisibility;
    }

    public void setUiVisibility(String uiVisibility) {
        this.uiVisibility = uiVisibility;
    }    

    @JsonIgnore
    public Object buildEntity() {

        CBCProductItem productItem = new CBCProductItem();
        productItem.setName(this.name);
        productItem.setMpn(this.mpn);
        productItem.setDescription(this.description);
        productItem.setType(this.type);
        productItem.setDynamic(this.dynamic);
        productItem.setPeriod(this.period);
        productItem.setName(this.name);
        CBCCommitment commitment = new CBCCommitment();
        commitment.setCount(this.commitmentCount);
        productItem.setCommitment(commitment);  
        CBCUnit unit = new CBCUnit();
        unit.setId(this.unitId);
        productItem.setUnit(unit);  
        productItem.setPrecision(this.precision);
        CBCItemUi visibility = new CBCItemUi();
        visibility.setVisibility(this.uiVisibility);
        productItem.setVisibility(visibility);;  
        return productItem;    
    }    
}
