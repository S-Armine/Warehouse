package org.Sandship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private final String USERNAME = "example username";
    private final String PASSWORD = "example password";
    private Player player = new Player(USERNAME, PASSWORD);

    //tests verification of log in information with right username, but slightly changed password
    @Test
    void shouldNotBeVerifiedDueToPassword() {
        assertFalse(player.verifyUser("example username", "exanple password"));
    }

    //tests verification of log in information with right password and username, but with extra spaces
    @Test
    void shouldNotBeVerifiedDueToWhiteSpaces() {
        assertFalse(player.verifyUser("    example   username  ", "example   password   "));
    }

    //tests verification of log in information with right password, but slightly changed username
    @Test
    void shouldNotBeVerifiedDueToUserName() {
        assertFalse(player.verifyUser("example usename", "example password"));
    }

    //tests verification of log in information with wrong username and password
    @Test
    void shouldNotBeVerified() {
        assertFalse(player.verifyUser("other name", "other psw"));
    }

    //tests verification of log in information with right username and password
    @Test
    void shouldBeVerified() {
        assertTrue(player.verifyUser("example username", "example password"));
    }

    //tests verification of log in information with null username and password
    @Test
    void shouldNotBeVerifiedWithNullArguments() {
        assertFalse(player.verifyUser(null, null));
    }

    //tests verification of log in information with right password and username, but with extra special characters
    @Test
    void shouldBeVerifiedWithSpecialCharacters() {
        assertFalse(player.verifyUser("example_@_username_#", "example__$_&password"));
    }

}