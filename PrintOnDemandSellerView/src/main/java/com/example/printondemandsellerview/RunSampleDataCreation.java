package com.example.printondemandsellerview;

import com.example.printondemandsellerview.amazonMerchOnDemand.AmazonMerchOnDemandSampleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class RunSampleDataCreation implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AmazonMerchOnDemandSampleData amazonMerchOnDemandSampleData;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        amazonMerchOnDemandSampleData.createAmazonMerchOnDemandLogin();
    }

}
