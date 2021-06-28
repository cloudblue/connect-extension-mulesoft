package com.cloudblue.connect.api.models.marketplace;

import com.cloudblue.connect.api.models.common.CBCCommonEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCMarketplace extends CBCCommonEntity {}
