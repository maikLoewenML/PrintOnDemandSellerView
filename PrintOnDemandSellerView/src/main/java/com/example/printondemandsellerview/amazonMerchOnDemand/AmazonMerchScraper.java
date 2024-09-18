package com.example.printondemandsellerview.amazonMerchOnDemand;

import com.microsoft.playwright.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AmazonMerchScraper {

    public int countAmazonMerchSales(String email, String password) throws Exception {
        int sumSales;
        try (Playwright playwright = Playwright.create()) {
            BrowserType browserType = playwright.chromium(); // Oder playwright.firefox() bzw. playwright.webkit() für andere Browser
            Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false)); // setHeadless(false) zeigt den Browser an
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://merch.amazon.com/dashboard");
            System.out.println(page.title());
            page.waitForTimeout(5000);

            //Login
            page.fill("#ap_email", email);
            page.fill("#ap_password", password);
            page.waitForTimeout(5000);
            page.click("#signInSubmit");
            page.waitForTimeout(10000);

            //CountSales
            String salesUSD = page.evaluate("document.querySelector('#currency-summary-sold-USD').textContent").toString();
            System.out.println("SalesUSD: " + salesUSD);
            String salesGBP = page.evaluate("document.querySelector('#currency-summary-sold-GBP').textContent").toString();
            System.out.println("SalesGBP: " + salesGBP);
            String salesEUR = page.evaluate("document.querySelector('#currency-summary-sold-EUR').textContent").toString();
            System.out.println("SalesEUR: " + salesEUR);
            String salesJPY = page.evaluate("document.querySelector('#currency-summary-sold-JPY').textContent").toString();
            System.out.println("SalesJPY: " + salesJPY);
            page.waitForTimeout(5000);
            int calcSalesUSD = Integer.parseInt(salesUSD);
            int calcSalesGBP = Integer.parseInt(salesGBP);
            int calcSalesEUR = Integer.parseInt(salesEUR);
            int calcSalesJPY = Integer.parseInt(salesJPY);
            sumSales = calcSalesUSD + calcSalesGBP + calcSalesEUR + calcSalesJPY;
            System.out.println(sumSales);
        } catch (Exception e) {
            throw new Exception("Es konnte kein AmazonMerchScraper geladen werden");
        }
        return sumSales;
    }
}
