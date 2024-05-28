package com.example.springboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShutDownController implements ApplicationContextAware {
    
    private ApplicationContext context;
    
    @PostMapping("/shutdownContext")
    public void shutdownContext() {
        System.out.println("Calling shutDownEndpoint");
		((ConfigurableApplicationContext) context).close();
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.context = ctx;
        
    }
}