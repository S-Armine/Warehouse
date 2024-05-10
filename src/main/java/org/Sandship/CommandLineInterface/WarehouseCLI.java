package org.Sandship.CommandLineInterface;

import org.Sandship.*;
import org.Sandship.Materials.Material;
import org.Sandship.Materials.MaterialFactory;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class WarehouseCLI {

    public static Warehouse createWarehouseWithoutOwner() {
        return new Warehouse();
    }

    public static Warehouse createWarehouse(Player owner) {
        Warehouse warehouse = new Warehouse(owner);
        owner.addWarehouse(warehouse);
        return warehouse;
    }

    //gets a warehouse from given list with certain index
    public static Warehouse getWarehouse(List<Warehouse> warehousesList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a warehouse from list given below.");
        for (int i = 0; i < warehousesList.size(); i++) {
            System.out.println("Warehouse number " + i);
            System.out.println(warehousesList.get(i).toString());
        }
        int index;
        try {
            System.out.print("Enter number of warehouse: ");
            index = scanner.nextInt();
            scanner.close();
        } catch (InputMismatchException e) {
            System.out.println("Operation failed. Input must have been an integer.");
            return null;
        }
        if (index >= warehousesList.size()) {
            System.out.println("There isn't a warehouse with these number.");
            return null;
        }
        return warehousesList.get(index);
    }

    //adds a material to warehouse
    public static boolean addMaterialToWarehouse(Warehouse warehouse) {
        return performMaterialAction(warehouse, "add");
    }

    //removes a material from warehouse
    public static boolean removeMaterialFromWarehouse(Warehouse warehouse){
        return performMaterialAction(warehouse, "remove");
    }

    //moves a material from 1 warehouse to another
    public static boolean moveMaterialBetweenWarehouses(Warehouse fromWarehouse) {
        return performMaterialAction(fromWarehouse, "move");
    }

    //checks the necessary arguments and if one of them is not valid fails operation, otherwise operation is ending being succeeded
    public static boolean performMaterialAction(Warehouse warehouse, String action) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input what type of material do you want to " + action + ". Type can be iron, copper, or bolt");
        String type = scanner.nextLine();
        Material material = MaterialFactory.getMaterial(type);
        if (material == null) {
            System.out.println("Operation failed. There isn't such type.");
            return false;
        }
        System.out.println("Input quantity of material you want to " + action + ".");
        int quantity;
        try {
            quantity = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Operation failed. Quantity should be a number");
            return false;
        }
        System.out.println("Type 'yes' if you want to " + action + " partially if a mismatch occurs, anything else otherwise.");
        String choice = scanner.next();
        boolean isPartial = choice.trim().equalsIgnoreCase("yes");
        boolean isActionDone = false;
        switch (action) {
            case "add" -> isActionDone = warehouse.addMaterial(material, quantity, isPartial);
            case "remove" -> isActionDone = warehouse.removeMaterial(material, quantity, isPartial);
            case "move" -> {
                Warehouse toWarehouse = WarehouseCLI.getWarehouse(MainCLI.getWarehousesList());
                isActionDone = warehouse.moveMaterial(toWarehouse, material, quantity, isPartial);
            }
            default -> System.out.println("Invalid action.");
        }
        return isActionDone;
    }

}
