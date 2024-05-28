package com.example.springboot.util;

import com.example.springboot.model.TenantResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ServiceDiscoveryUtil {
    @Value("${spring.resource.url}")
    private String url;
    @Value("${spring.tenant.id}")
    private String tenantId;
    @Value("${spring.resource.id}")
    private String resourceId;
    @Value("${spring.resource.type}")
    private String resourceType;
    @Value("${spring.resource.arn}")
    private String resourceArn;
    @Value("${spring.resource.region}")
    private String region;
    @Value("${spring.resource.ipaddress}")
    private String ipaddress;
    @Value("${spring.resource.version}")
    private String version;

    public void updateServiceDiscovery(String status) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TenantResource tenantResource = new TenantResource();
            tenantResource.setTenantId(tenantId);
            tenantResource.setResourceId(resourceId);
            tenantResource.setResourceType(resourceType);
            tenantResource.setResourceArn(resourceArn);
            tenantResource.setRegion(region);
            tenantResource.setIpAddress(ipaddress);
            tenantResource.setVersion(version);
            tenantResource.setStatus(status);
            // Converting the Java object into a JSON string
            var jsonStr = objectMapper.writeValueAsString(tenantResource);
            System.out.println(jsonStr);
            callServiceDiscoveryAPI(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void callServiceDiscoveryAPI(String payload) {

        try (CloseableHttpClient client1 = HttpClients.custom().setDefaultRequestConfig(RequestConfig.DEFAULT).build()) {

            HttpPut putRequest = new HttpPut(url);
            putRequest.setHeader("Content-Type", "application/json");
            StringEntity entity = new StringEntity(payload);
            putRequest.setEntity(entity);

            HttpResponse httpResponse = client1.execute(putRequest);
            if (httpResponse != null) {
                String httpCode = String.valueOf(httpResponse.getStatusLine().getStatusCode());
                System.out.println("httpCode -->" + httpCode);
                HttpEntity respEntity = httpResponse.getEntity();

                String response = EntityUtils.toString(respEntity);
                System.out.println("response -->" + response);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
