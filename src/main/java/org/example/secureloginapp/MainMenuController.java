package org.example.secureloginapp;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Label welcomeLabel;

    @FXML
    private Button logoutButton;

    private User user;

    public MainMenuController() {
        // Default constructor needed for FXML
    }

    public MainMenuController(User user) {
        this.user = user;
    }
    
    public void setUser(User user) {
        this.user = user;
        updateWelcomeLabel();
    }

    @FXML
    private void initialize() {
        updateWelcomeLabel();
    }
    
    private void updateWelcomeLabel() {
        if (user != null && welcomeLabel != null) {
            welcomeLabel.setText("Welcome, " + user.getUsername() + "!");
        }
    }

    @FXML
    private void onLogoutClick(ActionEvent event) throws IOException {
        SecureLoginApp.setRoot("Home.fxml");
    }
}
