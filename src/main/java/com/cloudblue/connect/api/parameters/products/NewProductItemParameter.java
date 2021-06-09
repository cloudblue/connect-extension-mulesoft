package com.cloudblue.connect.api.parameters.products;

import com.cloudblue.connect.api.models.CBCCommitment;
import com.cloudblue.connect.api.models.enums.CBCProductItemPrecision;
import com.cloudblue.connect.api.models.enums.CBCProductItemType;
import com.cloudblue.connect.api.models.product.CBCItemUi;
import com.cloudblue.connect.api.models.product.CBCProductItem;
import com.cloudblue.connect.api.models.product.CBCUnit;
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
    private CBCProductItemType type;

    @Parameter
    @Expression
    private boolean dynamic;

    @Parameter
    @Expression
    private String period;

    @Parameter
    @Expression
    private Integer commitmentCount;

    @Parameter
    @Expression
    private String unitId;

    @Parameter
    @Expression
    private CBCProductItemPrecision precision;

    @Parameter
    @Expression
    private boolean uiVisibility;

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

    public CBCProductItemType getType() {
        return type;
    }

    public void setType(CBCProductItemType type) {
        this.type = type;
    }  

    public Boolean getDynamic() {
        return dynamic;
    }

    public void setDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
    }    

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }    

    public Integer getCommitmentCount() {
        return commitmentCount;
    }

    public void setCommitmentCount(Integer commitmentCount) {
        this.commitmentCount = commitmentCount;
    }    

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }    

    public CBCProductItemPrecision getPrecision() {
        return precision;
    }

    public void setPrecision(CBCProductItemPrecision precision) {
        this.precision = precision;
    }    

    public Boolean getUiVisibility() {
        return uiVisibility;
    }

    public void setUiVisibility(Boolean uiVisibility) {
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
