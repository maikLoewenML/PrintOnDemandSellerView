package com.example.printondemandsellerview;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.BuilderFactory;

import org.springframework.context.ApplicationContext;

/**
 * Die Klasse ist verantwortlich für das Laden von FXML-Dateien in der Spring Boot-Anwendung und die Integration der
 * Spring-Beans in das JavaFX-Interface. Die Klasse erstellt eine Instanz von FXMLLoader und konfiguriert sie, sodass
 * sie Spring-Beans als Controller-Instanzen verwendet. Der generische Typ S repräsentiert den Root-Elementtyp der
 * FXML-Datei und T repräsentiert den Typ des Controllers.
 */
public class SpringFXMLLoader<S,T> {
    private final URL location ;
    private final InputStream inputStream ;
    private final ApplicationContext applicationContext ;
    private final ResourceBundle resources ;
    private final Charset charset ;
    private T controller ;

    private boolean loaded ;

    private SpringFXMLLoader(ApplicationContext applicationContext,
                             URL location, InputStream inputStream, ResourceBundle resources, Charset charset) {
        this.applicationContext = applicationContext;
        this.location = location;
        this.inputStream = inputStream ;
        this.resources = resources;
        this.charset = charset;
    }

    /**
     * Die Methode lädt die FXML-Datei und konfiguriert den FXMLLoader. Darin werden Charset, Ressourcen und
     * URL (falls vorhanden) gesetzt. Die ControllerFactory wird auf applicationContext::getBean gesetzt, um
     * Spring-Beans als Controller zu verwenden. Dann wird eine BuilderFactory erstellt, um Beans basierend auf ihrem
     * Typ zu laden. Danach wird die FXML-Datei geladen und der Controller gespeichert.
     * @return root
     * @throws IOException
     */
    public S load() throws IOException {
        if (loaded) {
            throw new IllegalStateException("Cannot load fxml multiple times");
        }
        FXMLLoader loader = new FXMLLoader();
        if (charset != null) {
            loader.setCharset(charset);
        }
        if (resources != null) {
            loader.setResources(resources);
        }
        if (location != null) {
            loader.setLocation(location);
        }
        loader.setControllerFactory(applicationContext::getBean);
        loader.setBuilderFactory(new BuilderFactory() {

            JavaFXBuilderFactory defaultFactory = new JavaFXBuilderFactory();

            @Override
            public javafx.util.Builder<?> getBuilder(Class<?> type) {
                String[] beanNames = applicationContext.getBeanNamesForType(type);
                if (beanNames.length == 1) {
                    return new javafx.util.Builder<Object>() {

                        @Override
                        public Object build() {
                            return applicationContext.getBean(beanNames[0]);
                        }

                    };
                } else {
                    return defaultFactory.getBuilder(type) ;
                }
            }
        });
        S root ;
        if (location != null) {
            root = loader.load();
        } else if (inputStream != null) {
            root = loader.load(inputStream);
        } else {
            throw new AssertionError("SpringFXMLLoader constructed without location or input stream");
        }
        controller = loader.getController() ;
        loaded = true ;
        return root ;
    }

    public T getController() {
        if (! loaded) {
            throw new IllegalStateException("Controller is only available after loading");
        }
        return controller ;
    }

    public static Builder create() {
        return new Builder();
    }

    /**
     * Die innere Builder-Klasse ermöglicht es, eine Instanz vom SpringFXMLLoader schrittweise zu erstellen.
     * Sie hat Methoden, um den ApplicationContext, die URL oder den InputStream, die Ressourcen und das Charset zu
     * setzen. Die Methode build() erstellt eine neue Instanz vom SpringFXMLLoader und load() lädt die FXML-Datei direkt.
     */
    public static class Builder {
        private ApplicationContext applicationContext ;
        private URL location ;
        private InputStream inputStream ;
        private ResourceBundle resources ;
        private Charset charset ;

        public Builder applicationContext(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext ;
            return this ;
        }

        public Builder location(URL location) {
            if (inputStream != null) {
                throw new IllegalStateException("Cannot specify location and input stream");
            }
            this.location = location ;
            return this ;
        }

        public Builder inputStream(InputStream inputStream) {
            if (location != null) {
                throw new IllegalStateException("Cannot specify location and input stream");
            }
            this.inputStream = inputStream ;
            return this ;
        }

        public Builder resources(ResourceBundle resources) {
            this.resources = resources ;
            return this ;
        }

        public Builder charset(Charset charset) {
            this.charset = charset ;
            return this ;
        }

        public <S,T> SpringFXMLLoader<S,T> build() {
            if (applicationContext == null) {
                throw new IllegalStateException("Application context not specified");
            }
            if (location == null && inputStream == null) {
                throw new IllegalStateException("Must specify exactly one of location or inputStream");
            }
            if (charset == null) {
                charset = Charset.defaultCharset() ;
            }
            return new SpringFXMLLoader<S, T>(applicationContext, location, inputStream, resources, charset);
        }

        public <S> S load() throws IOException {
            return this.<S, Object>build().load();
        }
    }


}
