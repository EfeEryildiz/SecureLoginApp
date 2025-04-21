package org.example.secureloginapp;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.regex.Pattern;

public class Validator {
    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9#$%&*\\-]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$"
    );

    // Password validation pattern
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*]).{8,}$"
    );

    // Validate email
    public static boolean validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            showError("Email cannot be empty");
            return false;
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            showError("Email must follow the format: localpart@domain.extension");
            return false;
        }

        return true;
    }

    // Validate password
    public static boolean validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            showError("Password cannot be empty");
            return false;
        }

        if (password.length() < 8) {
            showError("Password must be at least 8 characters long");
            return false;
        }

        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            showError("Password must contain at least one lowercase letter");
            return false;
        }

        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            showError("Password must contain at least one uppercase letter");
            return false;
        }

        if (!Pattern.compile("\\d").matcher(password).find()) {
            showError("Password must contain at least one digit");
            return false;
        }

        if (!Pattern.compile("[-+_!@#$%^&*]").matcher(password).find()) {
            showError("Password must contain at least one special character (-+_!@#$%^&*)");
            return false;
        }

        return true;
    }

    // Validate confirm password
    public static boolean validateConfirmPassword(String password, String confirmPassword) {
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            showError("Confirm Password cannot be empty");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match");
            return false;
        }

        return true;
    }

    // Show error message
    private static void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
