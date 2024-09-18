package com.example.printondemandsellerview;

import com.example.printondemandsellerview.amazonMerchOnDemand.AmazonMerchOnDemand;
import com.example.printondemandsellerview.amazonMerchOnDemand.AmazonMerchOnDemandRepository;
import com.example.printondemandsellerview.amazonMerchOnDemand.AmazonMerchOnDemandSampleData;
import com.example.printondemandsellerview.amazonMerchOnDemand.AmazonMerchOnDemandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = JavaFXApplication.class)
class PrintOnDemandSellerViewApplicationTests {

    @Autowired private AmazonMerchOnDemandRepository amazonMerchOnDemandRepository;
    @Autowired private AmazonMerchOnDemandSampleData amazonMerchOnDemandSampleData;
    @Autowired private AmazonMerchOnDemandService amazonMerchOnDemandService;

    @BeforeEach
    public void setupData() {
        amazonMerchOnDemandSampleData.createAmazonMerchOnDemandLogin();
    }

    @Test
    public void testHasAmazonMerchOnDemandLoginRightPasswordAndRightEmail() {
        assertTrue(amazonMerchOnDemandRepository.existsById(1L));
        Optional<AmazonMerchOnDemand> amazonMerchOnDemandOptional = amazonMerchOnDemandRepository.findById(1L);
        assertFalse(amazonMerchOnDemandOptional.isEmpty());
        AmazonMerchOnDemand amazonMerchOnDemandLogin = amazonMerchOnDemandOptional.get();
        assertEquals("password", amazonMerchOnDemandLogin.getPassword());
        assertEquals("test@gmail.com", amazonMerchOnDemandLogin.getEmail());
        amazonMerchOnDemandService.saveLogin(amazonMerchOnDemandLogin);
    }

}
