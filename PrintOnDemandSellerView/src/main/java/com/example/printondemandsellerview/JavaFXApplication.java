package com.example.printondemandsellerview;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavaFXApplication extends Application {

    public ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //wird verwendet, um den Spring-Kontext zu konfigurieren und zu starten und die Anwendung zu starten
    private ConfigurableApplicationContext applicationContext;

    /**
     * Quellklasse PrintOnDemandSellerViewApplication als Quelle für die Anwendung wird bestimmt
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.applicationContext = new SpringApplicationBuilder().sources(PrintOnDemandSellerViewApplication.class).run(args);
    }

    /**
     * Oberfläche der Anwendung wird erstellt und angezeigt
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        SpringFXMLLoader<Parent, JavaFXStartController> loader = SpringFXMLLoader.create()
                 .applicationContext(applicationContext)
                 .location(getClass().getClassLoader().getResource("start.fxml"))
                 .build();
        Parent root = loader.load();
        JavaFXStartController controller = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        this.applicationContext.close();
        Platform.exit();
    }

    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
}
