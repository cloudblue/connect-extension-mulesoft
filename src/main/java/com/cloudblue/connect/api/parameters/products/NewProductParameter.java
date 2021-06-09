package com.cloudblue.connect.api.parameters.products;

import com.cloudblue.connect.api.models.common.CBCCommonEntity;
import com.cloudblue.connect.api.models.product.CBCProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class NewProductParameter {
    @Parameter
    @Expression
    private String name;

    @Parameter
    @Expression
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }    

    @JsonIgnore
    public Object buildEntity() {

        CBCProduct product = new CBCProduct();
        product.setName(this.name);
        CBCCommonEntity category = new CBCCommonEntity();
        category.setId(this.category);
        product.setCategory(category);
        return product;    
    }    
}
