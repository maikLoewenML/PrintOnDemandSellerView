package com.example.printondemandsellerview.spreadshirt;

import com.microsoft.playwright.*;

import java.util.List;

public class SpreadshirtScraper {

    public int countSpreadshirtSales(String email, String password) {
        String sumSales = null;
        String einnahmen;
        try (Playwright playwright = Playwright.create()) {
            BrowserType browserType = playwright.chromium(); // Oder playwright.firefox() bzw. playwright.webkit() für andere Browser
            Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false)); // setHeadless(false) zeigt den Browser an
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://partner.spreadshirt.de/dashboard");
            System.out.println(page.title());
            page.waitForTimeout(5000);
            page.click("#onetrust-accept-btn-handler");
            page.waitForTimeout(2000);
            System.out.println("akzeptieren wurde angeklickt");

            //Login
            for (int i = 0; i < 3; i++) {
                page.keyboard().press("Tab");
                page.waitForTimeout(100);
            }
            page.keyboard().type(email);
            page.waitForTimeout(1000);
            page.keyboard().press("Tab");
            page.waitForTimeout(100);
            page.keyboard().type(password);
            page.waitForTimeout(1000);
            page.keyboard().press("Tab");
            page.waitForTimeout(100);
            page.keyboard().press("Tab");
            page.waitForTimeout(100);
            page.keyboard().press("Enter");
            page.waitForTimeout(5000);

            //reCAPTCHA
            if (page.querySelector("#login-submit") == null) {
                System.out.println("bitte das reCAPTCHA lösen und auf Login klicken");
                page.waitForTimeout(20000);
            } else {
                page.waitForTimeout(2000);
                page.click("#login-submit");
            }

            //Verkäufe und Einnahmen auslesen
            List<ElementHandle> h1Elements = page.querySelectorAll("h1");
            for (ElementHandle h1Element : h1Elements) {
                String element = h1Element.toString();
                String elementContent = h1Element.innerText();
                String holeElement = element + "; " + elementContent;
                if(holeElement.contains("data-v-b9a83d46") && element.contains("€")) {
                    System.out.println("Einnahmen: " + elementContent);
                    einnahmen = elementContent;
                }
                else if(holeElement.contains("data-v-b9a83d46")) {
                    System.out.println("Anzahl Verkäufe: " + elementContent);
                    sumSales = elementContent;
                }
                else continue;
            }
        }
        return Integer.parseInt(sumSales);
    }
}
