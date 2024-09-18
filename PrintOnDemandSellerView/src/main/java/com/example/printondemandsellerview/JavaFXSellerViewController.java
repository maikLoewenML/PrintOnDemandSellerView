package com.example.printondemandsellerview;

import com.example.printondemandsellerview.amazonMerchOnDemand.AmazonMerchOnDemandService;
import com.example.printondemandsellerview.spreadshirt.SpreadshirtService;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@FxmlView("sellerView.fxml")
@Component
public class JavaFXSellerViewController {

    @FXML private Label sellerViewLabel;
    @FXML private Label salesAmazonMerchOnDemand;
    @FXML private Label salesSpreadshirt;
    @Autowired public AmazonMerchOnDemandService amazonMerchOnDemandService;
    @Autowired public SpreadshirtService spreadshirtService;
    @Autowired public ConfigurableApplicationContext applicationContext;

    private Stage stage;
    private Scene scene;

    @FXML
    public void openStartSceneFromSellerViewScene() throws IOException {
        SpringFXMLLoader<Parent, JavaFXSellerViewController> loader = SpringFXMLLoader.create()
                .applicationContext(applicationContext)
                .location(getClass().getClassLoader().getResource("start.fxml"))
                .build();
        Parent root = loader.load();
        stage = (Stage)(sellerViewLabel.getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void openSettingsSceneFromSellerViewScene() throws IOException {
        SpringFXMLLoader<Parent, JavaFXSellerViewController> loader = SpringFXMLLoader.create()
                .applicationContext(applicationContext)
                .location(getClass().getClassLoader().getResource("settings.fxml"))
                .build();
        Parent root = loader.load();
        stage = (Stage)(sellerViewLabel.getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void updateSalesForMBA() throws Exception {
        int sumSales = amazonMerchOnDemandService.loginAndCountSales();
        String textSumSales = String.valueOf(sumSales);
        salesAmazonMerchOnDemand.setText(textSumSales);
    }

    @FXML
    public void updateSalesForSpreadshirt() throws Exception {
        int sumSales = spreadshirtService.loginAndCountSales();
        String textSumSales = String.valueOf(sumSales);
        salesSpreadshirt.setText(textSumSales);
    }
}
