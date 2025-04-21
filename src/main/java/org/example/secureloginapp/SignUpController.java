package org.example.secureloginapp;

import  org.example.secureloginapp.User;
import  org.example.secureloginapp.DBManager;
import  org.example.secureloginapp.PasswordManager;
import  org.example.secureloginapp.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.example.secureloginapp.DBManager.registerUser;
import static org.example.secureloginapp.Validator.validateEmail;
import static org.example.secureloginapp.Validator.validatePassword;

public class SignUpController {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button signUpButton;

    @FXML
    private Button backButton;

    @FXML
    private void onSignUpClick(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validate input
        if (username == null || username.trim().isEmpty()) {
            showAlert("Username cannot be empty");
            return;
        }

        if (!validateEmail(email)) {
            return;
        }

        if (!validatePassword(password)) {
            return;
        }

        if (!Validator.validateConfirmPassword(password, confirmPassword)) {
            return;
        }

        // Check if email already exists
        if (DBManager.emailExists(email)) {
            showAlert("Email already exists. Please use a different email.");
            return;
        }

        try {
            // Generate salt and hash password
            String salt = PasswordManager.generateSalt();
            String passwordHash = PasswordManager.hashPassword(password, salt);

            // Create user
            User user = new User(username, email, passwordHash, salt);

            // Register user
            boolean success = DBManager.registerUser(user);

            if (success) {
                showAlert(AlertType.INFORMATION, "Registration Successful",
                        "You have been registered successfully. Please log in.");
                SecureLoginApp.setRoot("LogIn.fxml");
            } else {
                showAlert("Registration failed. Please try again.");
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            showAlert("An error occurred during registration: " + e.getMessage());
        }
    }

    @FXML
    private void onBackClick(ActionEvent event) throws IOException {
        SecureLoginApp.setRoot("Home.fxml");
    }

    private void showAlert(String message) {
        showAlert(AlertType.ERROR, "Error", message);
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
