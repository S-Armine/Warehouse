package org.Sandship;

import org.Sandship.CustomExceptions.MaxCapacityExceededException;
import org.Sandship.CustomExceptions.NotEnoughMaterialException;
import org.Sandship.CustomExceptions.WarehouseException;
import org.Sandship.Materials.Material;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    private final Map<Material, Integer> materials;
    private Player owner;

    public Warehouse() {
        this.materials = new HashMap<>();
    }

    public Warehouse(Player owner) {
        this.materials = new HashMap<>();
        this.owner = owner;
    }

    public Warehouse(Map<Material, Integer> materials) {
        this.materials = materials;
    }

    public Map<Material, Integer> getMaterials() {
        return materials;
    }

    //method that adds material without any check, marked as private because using it outside of class can be dangerous
    private void addingMaterial(Material material, int newQuantity) {
        if (materials.containsKey(material)) {
            materials.replace(material, newQuantity);
        }
        else {
            materials.put(material, newQuantity);
        }
    }

    //method used for adding material to warehouse
    //arguments are material that needs to be added, and it's quantity,
    // also boolean argument that indicates allowing warehouse to do partial adding during mismatches
    //returns true if adding was completed without any exception
    //returns false if adding was failed due to exception
    public boolean addMaterial(Material material, int quantity, boolean allowPartialAdding) {
        try {
            int currentQuantity = materials.getOrDefault(material, 0);
            int newQuantity = currentQuantity + quantity;
            if (newQuantity > material.getMaxCapacity()) {
                if (allowPartialAdding) {
                    System.out.println("Warning: Maximum capacity was exceeded. Adding partially.");
                    this.addingMaterial(material, material.getMaxCapacity());
                }
                else {
                    throw new MaxCapacityExceededException("Operation failed because maximum amount of material was exceeded.");
                }
            }
            else {
                this.addingMaterial(material, newQuantity);
            }
            return true;
        } catch (MaxCapacityExceededException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //method used for removing material to warehouse
    //arguments are material that needs to be removed, and it's quantity,
    //also boolean argument that indicates allowing warehouse to do partial removing during mismatches
    //returns true if removing was completed without any exception
    //returns false if removing was failed due to exception
    public boolean removeMaterial(Material material, int quantity, boolean allowPartialRemove) {
        try {
            if (!materials.containsKey(material) || this.getMaterials().get(material) == 0) {
                throw new WarehouseException("Warehouse doesn't have given material.");
            }
            if (quantity <= materials.get(material)) {
                materials.replace(material, materials.get(material) - quantity);
            }
            else if (allowPartialRemove) {
                System.out.println("Warning: Removing partially. Current amount is 0.");
                materials.replace(material, 0);
            }
            else {
                throw new NotEnoughMaterialException("There is not enough material in warehouse");
            }
            return true;
        } catch (WarehouseException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //method used for moving material from warehouse to another warehouse
    //arguments are warehouse where materials should be moved, material that needs to be added, and it's quantity,
    // also boolean argument that indicates allowing warehouse to do partial moving during mismatches
    //returns true if moving was completed without any exception
    //returns false if moving was failed due to exception
    public boolean moveMaterial(Warehouse toWarehouse, Material material, int quantity, boolean allowPartialMove){
        try {
            if (!this.getMaterials().containsKey(material) || this.getMaterials().get(material) == 0) {
                throw new NotEnoughMaterialException("Operation can't be completed due to absence of material.");
            }
            int availableAmountToMove = this.getMaterials().get(material);
            int currentAmount = toWarehouse.getMaterials().getOrDefault(material, 0);
            if (availableAmountToMove > quantity && currentAmount + quantity <= material.getMaxCapacity()) {
                toWarehouse.addingMaterial(material, quantity);
                this.materials.replace(material, materials.get(material) - quantity);
            }
            else {
                if (allowPartialMove) {
                    System.out.println("Warning: Partially moving is executed.");
                    int canMove = Math.min(availableAmountToMove, quantity);
                    int movingQuantity = canMove + currentAmount <= material.getMaxCapacity() ?
                            canMove + currentAmount : material.getMaxCapacity() - canMove;
                    toWarehouse.addingMaterial(material, movingQuantity);
                    this.materials.replace(material, availableAmountToMove - movingQuantity);
                }
                else {
                    if (availableAmountToMove < quantity) {
                        throw new NotEnoughMaterialException("Not enough material to move.");
                    }
                    else if (currentAmount + quantity > material.getMaxCapacity()) {
                        throw new MaxCapacityExceededException("Execution failed. Maximum capacity is exceeded.");
                    }
                }
            }
            return true;
        }
        catch (NotEnoughMaterialException | MaxCapacityExceededException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //method for getting current state of warehouse
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (var material : materials.entrySet()) {
            builder.append(material.getKey().getName()).append(" : ").append(material.getValue()).append('\n');
        }
        return builder.toString();
    }
}