package org.example.secureloginapp;

import java.util.regex.Pattern;

public class ValidatorTest {

    // Helper method to enable testing without showing alerts
    private boolean validateEmailNoAlert(String email) {
        // This would need a modified Validator class with a method that doesn't show alerts
        // For now, this is just a placeholder
        return email != null && email.matches("^[a-zA-Z0-9#$%&*\\-]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$");
    }

    private boolean validatePasswordNoAlert(String password) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }

        return password.length() >= 8 &&
               Pattern.compile("[a-z]").matcher(password).find() &&
               Pattern.compile("[A-Z]").matcher(password).find() &&
               Pattern.compile("\\d").matcher(password).find() &&
               Pattern.compile("[-+_!@#$%^&*]").matcher(password).find();
    }
    
    private boolean validateConfirmPasswordNoAlert(String password, String confirmPassword) {
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            return false;
        }
        
        return password.equals(confirmPassword);
    }
}
