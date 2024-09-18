package com.example.printondemandsellerview;

import com.example.printondemandsellerview.amazonMerchOnDemand.AmazonMerchOnDemand;
import com.example.printondemandsellerview.amazonMerchOnDemand.AmazonMerchOnDemandService;
import com.example.printondemandsellerview.spreadshirt.Spreadshirt;
import com.example.printondemandsellerview.spreadshirt.SpreadshirtService;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@FxmlView("settings.fxml")
@Component
public class JavaFXSettingsController {

    @FXML private Label settingsLabel;
    @FXML private TextField emailAmazonMerchOnDemand;
    @FXML private PasswordField passwordAmazonMerchOnDemand;
    @FXML private TextField emailSpreadshirt;
    @FXML private PasswordField passwordSpreadshirt;
    @FXML private TextField emailRedbubble;
    @FXML private PasswordField passwordRedbubble;

    private Stage stage;
    private Scene scene;

    @Autowired public AmazonMerchOnDemandService amazonMerchOnDemandService;
    @Autowired public SpreadshirtService spreadshirtService;
    @Autowired public ConfigurableApplicationContext applicationContext;


    @FXML
    public void saveLogins() {

        //AmazonMerchOnDemand
        String emailAmazon = emailAmazonMerchOnDemand.getText();
        String passwordAmazon = passwordAmazonMerchOnDemand.getText();
        AmazonMerchOnDemand amazonMerchOnDemandLogin = new AmazonMerchOnDemand();
        amazonMerchOnDemandLogin.setId(1L);
        amazonMerchOnDemandLogin.setEmail(emailAmazon);
        amazonMerchOnDemandLogin.setPassword(passwordAmazon);
        amazonMerchOnDemandService.saveLogin(amazonMerchOnDemandLogin);

        //Spreadshirt
        String emailSpreadshirtString = emailSpreadshirt.getText();
        String passwordSpreadshirtString = passwordSpreadshirt.getText();
        Spreadshirt spreadshirtLogin = new Spreadshirt();
        spreadshirtLogin.setId(1L);
        spreadshirtLogin.setEmail(emailSpreadshirtString);
        spreadshirtLogin.setPassword(passwordSpreadshirtString);
        spreadshirtService.saveLogin(spreadshirtLogin);
    }


    @FXML
    public void openStartSceneFromSettingsScene() throws IOException {
        SpringFXMLLoader<Parent, JavaFXStartController> loader = SpringFXMLLoader.create()
                .applicationContext(applicationContext)
                .location(getClass().getClassLoader().getResource("start.fxml"))
                .build();
        Parent root = loader.load();
        stage = (Stage)(settingsLabel.getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void openSellerViewSceneFromSettingsScene() throws IOException {
        SpringFXMLLoader<Parent, JavaFXSellerViewController> loader = SpringFXMLLoader.create()
                .applicationContext(applicationContext)
                .location(getClass().getClassLoader().getResource("sellerView.fxml"))
                .build();
        Parent root = loader.load();
        stage = (Stage)(settingsLabel.getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
