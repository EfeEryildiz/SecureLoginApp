package org.example.secureloginapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeController {
    @FXML
    private Button signUpButton;

    @FXML
    private Button logInButton;

    @FXML
    private void onSignUpClick(ActionEvent event) throws IOException {
        SecureLoginApp.setRoot("SignUp.fxml");
    }

    @FXML
    private void onLogInClick(ActionEvent event) throws IOException {
        SecureLoginApp.setRoot("LogIn.fxml");
    }
}