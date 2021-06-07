package com.cloudblue.connect.api.models.usage;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.cloudblue.connect.api.models.common.CBCEvents;
import com.cloudblue.connect.api.models.enums.CBCUsageReconStatus;
import com.cloudblue.connect.api.models.marketplace.CBCHubBinding;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCUsageReconciliation implements CBCEntity {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static final class ReconNotes {
        @JsonProperty
        private String uploaded;

        @JsonProperty
        private String processing;

        public String getUploaded() {
            return uploaded;
        }

        public void setUploaded(String uploaded) {
            this.uploaded = uploaded;
        }

        public String getProcessing() {
            return processing;
        }

        public void setProcessing(String processing) {
            this.processing = processing;
        }
    }

    @JsonProperty
    private String id;

    @JsonProperty
    private CBCUsageReconStatus status;

    @JsonProperty("usagefile")
    private CBCUsageReport usageReport;

    @JsonProperty("partusagefile")
    private CBCUsageChunkFile usageChunkFile;

    @JsonProperty
    private CBCHubBinding binding;

    @JsonProperty
    private Map<String, String> error;

    @JsonProperty
    private ReconNotes notes;

    @JsonProperty
    private CBCEvents events;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CBCUsageReconStatus getStatus() {
        return status;
    }

    public void setStatus(CBCUsageReconStatus status) {
        this.status = status;
    }

    public CBCUsageReport getUsageReport() {
        return usageReport;
    }

    public void setUsageReport(CBCUsageReport usageReport) {
        this.usageReport = usageReport;
    }

    public CBCUsageChunkFile getUsageChunkFile() {
        return usageChunkFile;
    }

    public void setUsageChunkFile(CBCUsageChunkFile usageChunkFile) {
        this.usageChunkFile = usageChunkFile;
    }

    public CBCHubBinding getBinding() {
        return binding;
    }

    public void setBinding(CBCHubBinding binding) {
        this.binding = binding;
    }

    public Map<String, String> getError() {
        return error;
    }

    public void setError(Map<String, String> error) {
        this.error = error;
    }

    public ReconNotes getNotes() {
        return notes;
    }

    public void setNotes(ReconNotes notes) {
        this.notes = notes;
    }

    public CBCEvents getEvents() {
        return events;
    }

    public void setEvents(CBCEvents events) {
        this.events = events;
    }
}
