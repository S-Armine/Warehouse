package org.Sandship;

import org.mindrot.jbcrypt.BCrypt;


public class PasswordUtil {

    //this method hashes given password for security
    public static String hashingPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    //this method is used to verify user, when user want to log in to the account
    public static boolean verification(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
