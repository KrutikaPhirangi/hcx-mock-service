package org.swasth.hcx.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.swasth.hcx.dto.ResponseError;

import java.util.HashMap;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private long timestamp = System.currentTimeMillis();
    @JsonProperty("correlation_id")
    private String correlationId;
    @JsonProperty("api_call_id")
    private String apiCallId;
    @JsonProperty("subscription_id")
    private String subscriptionId;

    private String recipientCode;
    private String senderCode ;
    private ResponseError error;
    private Map<String, Object> result;

    private String workflowId;
    public Response() {}

    public Response(String workflowId, String senderCode, String recipientCode) {
        this.workflowId = workflowId;
        this.senderCode = senderCode;
        this.recipientCode = recipientCode;
    }

    public Response(String key, Object val) {
        this.result = new HashMap<>();
        this.put(key, val);
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public void setApiCallId(String apiCallId) {
        this.apiCallId = apiCallId;
    }

    public ResponseError getError() {
        return error;
    }

    public void setError(ResponseError error) {
        this.error = error;
    }

    public Object get(String key) {
        return result.get(key);
    }

    public Response put(String key, Object vo) {
        result.put(key, vo);
        return this;
    }

    public Response(Map<String, Object> result) {
        this.timestamp = System.currentTimeMillis();
        this.result = result;
    }


}

