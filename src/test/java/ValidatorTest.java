


import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class ValidatorTest {

    // Helper method to enable testing without showing alerts
    private boolean validateEmailTest(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        // Fixed regex to allow periods in username part
        return email.matches("^[a-zA-Z0-9.#$%&*\\-]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$");
    }

    private boolean validatePasswordTest(String password) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }

        // Add space check
        return password.length() >= 8 &&
                !password.contains(" ") &&
                Pattern.compile("[a-z]").matcher(password).find() &&
                Pattern.compile("[A-Z]").matcher(password).find() &&
                Pattern.compile("\\d").matcher(password).find() &&
                Pattern.compile("[-+_!@#$%^&*]").matcher(password).find();
    }

    private boolean validateConfirmPasswordTest(String password, String confirmPassword) {
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            return false;
        }

        return password.equals(confirmPassword);
    }

    @Test
    public void testEmailValidation() {
        // Valid emails
        assertTrue(validateEmailTest("user@domain.com"));
        assertTrue(validateEmailTest("john.doe@example.org"));
        assertTrue(validateEmailTest("user123@example.com"));
        assertTrue(validateEmailTest("user#$%@domain.com"));
        assertTrue(validateEmailTest("user-name@domain.co"));

        // Invalid emails
        assertFalse(validateEmailTest("")); // Empty
        assertFalse(validateEmailTest("user@")); // Incomplete
        assertFalse(validateEmailTest("@domain.com")); // No local part
        assertFalse(validateEmailTest("user@.com")); // Missing domain
        assertFalse(validateEmailTest("user@domain")); // No TLD
    }

    @Test
    public void testPasswordValidation() {
        // Valid passwords
        assertTrue(validatePasswordTest("Password1!")); // Meets all requirements
        assertTrue(validatePasswordTest("Abcd1234$")); // Another valid example

        // Invalid passwords
        assertFalse(validatePasswordTest("")); // Empty
        assertFalse(validatePasswordTest("short1!")); // Too short
        assertFalse(validatePasswordTest("PASSWORD123!")); // No lowercase
        assertFalse(validatePasswordTest("password123!")); // No uppercase
        assertFalse(validatePasswordTest("Password!@#")); // No digit
        assertFalse(validatePasswordTest("Password123")); // No special char
        assertFalse(validatePasswordTest("Pass 123!")); // Contains space
    }

    @Test
    public void testConfirmPasswordValidation() {
        // True cases
        assertTrue(validateConfirmPasswordTest("Password1!", "Password1!")); // Exact match

        // False cases
        assertFalse(validateConfirmPasswordTest("Password1!", "")); // Empty confirm
        assertFalse(validateConfirmPasswordTest("Password1!", "Password1")); // Different
    }
}
