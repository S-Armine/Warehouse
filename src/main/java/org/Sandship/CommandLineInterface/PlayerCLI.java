package org.Sandship.CommandLineInterface;

import org.Sandship.Player;

import java.util.List;
import java.util.Scanner;

public class PlayerCLI {

    //method is used for inputting name and password of a new user
    public static String[] inputUserInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input user name: ");
        String userName = scanner.nextLine();
        System.out.println("Input password: ");
        String password = scanner.nextLine();
        return new String[] {userName, password};
    }

    //creation of a player that does not have account, hence won't be registerd in application
    public static Player createPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input user name: ");
        String userName = scanner.next();
        return new Player(userName);
    }

    //registering a user in application
    public static Player signUp() {
        String[] userInfo = inputUserInfo();
        return new Player(userInfo[0], userInfo[1]);
    }

    //logging in account that was made and added in given list as argument
    public static Player logIn(List<Player> playerList) {
        String[] userInfo = inputUserInfo();
        for (Player player : playerList) {
            if (player.verifyUser(userInfo[0], userInfo[1])) {
                return player;
            }
        }
        return null;
    }
}
