package org.example.secureloginapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SecureLoginApp extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(SecureLoginApp.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Secure Login App");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SecureLoginApp.class.getResource(fxml));
        primaryStage.getScene().setRoot(fxmlLoader.load());
    }

    public static void setRoot(String fxml, Object controllerData) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SecureLoginApp.class.getResource(fxml));
        primaryStage.getScene().setRoot(fxmlLoader.load());
        
        Object controller = fxmlLoader.getController();
        
        if (controller instanceof MainMenuController && controllerData instanceof User) {
            MainMenuController mainMenuController = (MainMenuController) controller;
            mainMenuController.setUser((User) controllerData);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
