package org.example.secureloginapp;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogInController {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button logInButton;

    @FXML
    private Button backButton;

    @FXML
    private void onLogInClick(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validate input
        if (!Validator.validateEmail(email)) {
            return;
        }

        if (password == null || password.trim().isEmpty()) {
            showAlert("Password cannot be empty");
            return;
        }

        // Get user from DB
        User user = DBManager.getUserByEmail(email);

        if (user == null) {
            showAlert("Email does not exist");
            return;
        }

        // Verify password
        boolean isPasswordValid = PasswordManager.verifyPassword(
                password, user.getPasswordHash(), user.getSalt());

        if (isPasswordValid) {
            SecureLoginApp.setRoot("MainMenu.fxml", user);
        } else {
            showAlert("Invalid password");
        }
    }

    @FXML
    private void onBackClick(ActionEvent event) throws IOException {
        SecureLoginApp.setRoot("Home.fxml");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
