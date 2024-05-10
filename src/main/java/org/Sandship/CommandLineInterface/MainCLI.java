package org.Sandship.CommandLineInterface;

import org.Sandship.Player;
import org.Sandship.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainCLI {
    private final static Scanner scanner = new Scanner(System.in);
    private static Player player;
    private static List<Player> playersList = new ArrayList<>();
    private static Warehouse warehouse;
    private static List<Warehouse> warehousesList = new ArrayList<>();

    //method that runs the application
    public static void run() {
        System.out.println("Welcome to Sandship.");
        boolean shouldExit;
        do {
            playerFragment();
            if (player == null) {
                return;
            }
            while (true) {
                warehouseFragment();
                if (warehouse == null) {
                    return;
                }
                operationsWithWarehouse();
                System.out.println("If you want to continue make changes to warehouses type 'yes', otherwise anything else");
                if (!scanner.next().trim().equalsIgnoreCase("yes")) {
                    break;
                }
                warehouse = null;
            }
            System.out.println("If you want to exit from application type 'yes', otherwise anything else.");
            shouldExit = scanner.nextLine().trim().equalsIgnoreCase("yes");
            player = null;
            warehousesList = new ArrayList<>();
        } while (! shouldExit);
        scanner.close();
    }

    static List<Warehouse> getWarehousesList() {
        return warehousesList;
    }

    //fragment of application that handles creation of a user
    private static void playerFragment() {
        String choice;
        while(player == null) {
            String menu = "Please choose how you want to proceed.\n" +
                    "1. Continue without account.\n" +
                    "2. Log in to your account.\n" +
                    "3. Register.\n" +
                    "0. Exit from application.";
            System.out.println(menu);
            choice = scanner.nextLine();
            switch (choice.trim()) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    player = PlayerCLI.createPlayer();
                }
                case "2" -> {
                    player = PlayerCLI.logIn(playersList);
                    if (player == null) {
                        System.out.println("Player does not exist");
                        continue;
                    }
                    warehousesList = player.getWarehouses();
                }
                case "3" -> {
                    player = PlayerCLI.signUp();
                    playersList.add(player);
                }
                default -> System.out.println("Invalid choice try again.");
            }
        }
    }

    //fragment of application that handles creation of warehouse
    private static void warehouseFragment() {
        String choice;
        boolean shouldStop = false;
        while (!shouldStop) {
            String menu = "Please choose how you want to proceed.\n" +
                    "1. Create warehouse.\n" +
                    "2. Get already made warehouse.\n" +
                    "0. Exit";
            System.out.println(menu);
            choice = scanner.nextLine();
            switch (choice.trim()) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    if (playersList.contains(player)) {
                        warehouse = WarehouseCLI.createWarehouse(player);
                    }
                    warehouse = WarehouseCLI.createWarehouseWithoutOwner();
                    warehousesList.add(warehouse);
                    shouldStop = true;
                }
                case "2" -> {
                    if (warehousesList.isEmpty()) {
                        System.out.println("There isn't any warehouse");
                        continue;
                    }
                    warehouse = WarehouseCLI.getWarehouse(warehousesList);
                    shouldStop = true;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    //fragment of application that handles operations with warehouse
    private static void operationsWithWarehouse() {
        String choice;
        if (warehouse == null) {
            System.out.println("Can not continue with warehouse operations");
            return;
        }
        while (true) {
            String menu = "Please choose how you want to proceed.\n" +
                    "1. Add material to warehouse.\n" +
                    "2. Remove material from warehouse.\n" +
                    "3. Move material from warehouse to other one.\n" +
                    "4. Print current state of warehouse.\n" +
                    "0. Exit.";
            System.out.println(menu);
            choice = scanner.next();
            switch (choice.trim()) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    if (WarehouseCLI.addMaterialToWarehouse(warehouse)) {
                        System.out.println("Material was added successfully");
                    }
                }
                case "2" -> {
                    if (WarehouseCLI.removeMaterialFromWarehouse(warehouse)) {
                        System.out.println("Material was removed successfully");
                    }
                }
                case "3" -> {
                    if (warehousesList.isEmpty()) {
                        System.out.println("There isn't any warehouse");
                        continue;
                    }
                    if (WarehouseCLI.moveMaterialBetweenWarehouses(warehouse)) {
                        System.out.println("Material was moved successfully");
                    }
                }
                case "4" -> {
                    System.out.println(warehouse.toString());
                }
                default -> {
                    System.out.println("Invalid choice");
                }
            }
        }
    }
}