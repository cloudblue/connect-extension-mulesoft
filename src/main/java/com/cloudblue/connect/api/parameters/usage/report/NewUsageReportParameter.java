/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.parameters.usage.report;

import com.cloudblue.connect.api.models.contract.CBCContract;
import com.cloudblue.connect.api.models.product.CBCProduct;
import com.cloudblue.connect.api.models.usage.CBCUsageReport;

import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

public class NewUsageReportParameter extends BaseUsageReportParameter {
    @Parameter
    @Placement(order = 2)
    private String productId;

    @Parameter
    @Placement(order = 3)
    private String contractId;

    @Parameter
    @Placement(order = 1)
    private String name;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object buildEntity() {
        CBCUsageReport report = (CBCUsageReport) super.buildEntity();

        report.setProduct(new CBCProduct());
        report.getProduct().setId(getProductId());

        report.setContract(new CBCContract());
        report.getContract().setId(getContractId());

        report.setName(getName());

        return report;
    }
}
