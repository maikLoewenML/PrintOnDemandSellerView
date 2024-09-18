package com.example.printondemandsellerview.amazonMerchOnDemand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AmazonMerchOnDemandService {

    @Autowired AmazonMerchOnDemandRepository amazonMerchOnDemandRepository;

    public void saveLogin(AmazonMerchOnDemand amazonMerchOnDemand) {
        amazonMerchOnDemandRepository.save(amazonMerchOnDemand);
    }

    public int loginAndCountSales() throws Exception {
        Optional<AmazonMerchOnDemand> amazonMerchOnDemandOptional = amazonMerchOnDemandRepository.findById(1L);
        String email = amazonMerchOnDemandOptional.get().getEmail();
        String password = amazonMerchOnDemandOptional.get().getPassword();
        AmazonMerchScraper playwrightScraper = new AmazonMerchScraper();
        int sumSales = playwrightScraper.countAmazonMerchSales(email, password);
        return sumSales;
    }
}
