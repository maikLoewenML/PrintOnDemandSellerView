package com.example.printondemandsellerview;

import com.example.printondemandsellerview.amazonMerchOnDemand.AmazonMerchOnDemandService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;;

@FxmlView("start.fxml")
@Controller
public class JavaFXStartController {

    @FXML private Label startLabel;

    private Stage stage;
    private Scene scene;

    @Autowired public AmazonMerchOnDemandService amazonMerchOnDemandService;
    @Autowired public ConfigurableApplicationContext applicationContext;

    @FXML
    public void openSettingsSceneFromStartScene() throws IOException {
        SpringFXMLLoader<Parent, JavaFXSettingsController> loader = SpringFXMLLoader.create()
                .applicationContext(applicationContext)
                .location(getClass().getClassLoader().getResource("settings.fxml"))
                .build();
        Parent root = loader.load();
        stage = (Stage)(startLabel.getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void openSellerViewSceneFromStartScene() throws IOException {
        SpringFXMLLoader<Parent, JavaFXSellerViewController> loader = SpringFXMLLoader.create()
                .applicationContext(applicationContext)
                .location(getClass().getClassLoader().getResource("sellerView.fxml"))
                .build();
        Parent root = loader.load();
        stage = (Stage)(startLabel.getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
