package com.example.printondemandsellerview.amazonMerchOnDemand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmazonMerchOnDemandSampleData {

    @Autowired
    public AmazonMerchOnDemandRepository amazonMerchOnDemandRepository;

    public void createAmazonMerchOnDemandLogin() {
        AmazonMerchOnDemand amazonMerchOnDemandLogin = new AmazonMerchOnDemand();
        amazonMerchOnDemandLogin.setId(1L);
        amazonMerchOnDemandLogin.setEmail("test@gmail.com");
        amazonMerchOnDemandLogin.setPassword("password");
        amazonMerchOnDemandRepository.save(amazonMerchOnDemandLogin);
    }
}
