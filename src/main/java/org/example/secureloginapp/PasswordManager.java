package org.example.secureloginapp;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordManager {
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 128;
    private static final int SALT_LENGTH = 32;

    // Generate a random salt
    public static String generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Hash the password with the salt
    public static String hashPassword(String password, String saltString) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = Base64.getDecoder().decode(saltString);

        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);

        byte[] hash = factory.generateSecret(keySpec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }

    // Verify a password against a stored hash and salt
    public static boolean verifyPassword(String password, String storedHash, String storedSalt) {
        try {
            String calculatedHash = hashPassword(password, storedSalt);
            return calculatedHash.equals(storedHash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.err.println("Error verifying password: " + e.getMessage());
            return false;
        }
    }
}
