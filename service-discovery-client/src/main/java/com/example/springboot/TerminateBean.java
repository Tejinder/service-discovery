package com.example.springboot;

import com.example.springboot.util.ServiceDiscoveryUtil;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;

public class TerminateBean {

    @Autowired
    private ServiceDiscoveryUtil serviceDiscoveryUtil;
    @PreDestroy
    public void onDestroy() throws Exception {
        System.out.println("Spring Container is destroyed!");
        serviceDiscoveryUtil.updateServiceDiscovery("DOWN");
    }
}