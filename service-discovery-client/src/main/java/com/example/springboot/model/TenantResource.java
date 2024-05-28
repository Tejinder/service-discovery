package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantResource {

    @JsonProperty("tenant_id")
    private String tenantId;
    @JsonProperty("resource_id")
    private String resourceId;
    @JsonProperty("resource_type")
    private String resourceType;
    @JsonProperty("resource_arn")
    private String resourceArn;
    private String region;
    @JsonProperty("ip_address")
    private String ipAddress;
    private String version;
    private String status;

}
