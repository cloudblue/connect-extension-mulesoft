package com.cloudblue.connect.api.parameters.usage.record;

import com.cloudblue.connect.api.models.usage.CBCUsageRecord;
import com.cloudblue.connect.api.parameters.Embeddable;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import java.util.ArrayList;
import java.util.List;

public class BulkCloseUsageRecord implements Embeddable {

    @Parameter
    private List<CloseUsageRecord> bulkCloseDetails;

    public List<CloseUsageRecord> getBulkCloseDetails() {
        return bulkCloseDetails;
    }

    public void setBulkCloseDetails(List<CloseUsageRecord> bulkCloseDetails) {
        this.bulkCloseDetails = bulkCloseDetails;
    }

    @Override
    public Object buildEntity() {
        List<CBCUsageRecord> records = new ArrayList<>();

        for (CloseUsageRecord closeUsageRecord: bulkCloseDetails) {
            records.add((CBCUsageRecord)closeUsageRecord.buildEntity());
        }

        return records;
    }
}
