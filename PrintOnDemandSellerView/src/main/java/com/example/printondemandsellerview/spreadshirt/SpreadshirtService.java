package com.example.printondemandsellerview.spreadshirt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpreadshirtService {

    @Autowired SpreadshirtRepository spreadshirtRepository;

    public void saveLogin(Spreadshirt spreadshirt) {
        spreadshirtRepository.save(spreadshirt);
    }

    public int loginAndCountSales() throws Exception {
        Optional<Spreadshirt> spreadshirtOptional = spreadshirtRepository.findById(2L);
        String email = spreadshirtOptional.get().getEmail();
        String password = spreadshirtOptional.get().getPassword();
        SpreadshirtScraper playwrightScraper = new SpreadshirtScraper();
        int sumSales = playwrightScraper.countSpreadshirtSales(email, password);
        return sumSales;
    }

}
