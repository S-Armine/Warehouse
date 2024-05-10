package org.Sandship;

import org.Sandship.Materials.Material;
import org.Sandship.Materials.MaterialFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    private Material material = MaterialFactory.getMaterial("copper");

    //test adding more count of material that material's max capacity with permission of adding partially
    @Test
    public void addingMoreThanMaxCapacityWithPermission() {
        Warehouse warehouse = new Warehouse();
        int quantity = material.getMaxCapacity() + 1;
        assertTrue(warehouse.addMaterial(material, quantity, true));
    }

    //test adding more count of material that material's max capacity without permission of adding partially
    @Test
    public void addingMoreThanMaxCapacityWithoutPermission() {
        Warehouse warehouse = new Warehouse();
        int quantity = material.getMaxCapacity() + 1;
        assertFalse(warehouse.addMaterial(material, quantity, false));
    }

    //test removing more count of material that warehouse has with permission of removing partially
    @Test
    public void removingMoreThanCurrentAmountWithPermission() {
        Warehouse warehouse = new Warehouse();
        warehouse.addMaterial(material, 14, true);
        assertTrue(warehouse.removeMaterial(material, 15, true));
    }

    //test removing more count of material that warehouse has without permission of removing partially
    @Test
    public void removingMoreThanCurrentAmountWithoutPermission() {
        Warehouse warehouse = new Warehouse();
        warehouse.addMaterial(material, 14, true);
        assertFalse(warehouse.removeMaterial(material, 15, false));
    }

    //test removing more as much material as warehouse has
    @Test
    public void removingCurrentAmount() {
        Warehouse warehouse = new Warehouse();
        warehouse.addMaterial(material, 14, true);
        assertTrue(warehouse.removeMaterial(material, 14, true));
    }

    //test moving more count of material that warehouse has with permission of moving partially
    @Test
    public void movingMoreThanCurrentAmountWithPermission() {
        Warehouse warehouseFrom = new Warehouse();
        Warehouse warehouseTo = new Warehouse();
        warehouseFrom.addMaterial(material, 14, true);
        assertTrue(warehouseFrom.moveMaterial(warehouseTo, material, 15, true));
    }

    //test moving more count of material that warehouse has without permission of moving partially
    @Test
    public void movingMoreThanCurrentAmountWithoutPermission() {
        Warehouse fromWarehouse = new Warehouse();
        Warehouse toWarehouse = new Warehouse();
        fromWarehouse.addMaterial(material, 14, true);
        assertFalse(fromWarehouse.moveMaterial(toWarehouse, material, 15, false));
    }

    //test moving material that exceeds max capacity of warehouse with permission of removing partially
    @Test
    public void movingExceedsMaxCapacityWithPermission() {
        Warehouse warehouseFrom = new Warehouse();
        Warehouse warehouseTo = new Warehouse();
        warehouseFrom.addMaterial(material, 5, true);
        warehouseTo.addMaterial(material, material.getMaxCapacity() - 2, true);
        assertTrue(warehouseFrom.moveMaterial(warehouseTo, material, 3, true));
    }

    //test moving material that exceeds max capacity of warehouse with permission of removing partially
    @Test
    public void movingExceedsMaxCapacityWithoutPermission() {
        Warehouse warehouseFrom = new Warehouse();
        Warehouse warehouseTo = new Warehouse();
        warehouseFrom.addMaterial(material, 5, true);
        warehouseTo.addMaterial(material, material.getMaxCapacity() - 2, true);
        assertFalse(warehouseFrom.moveMaterial(warehouseTo, material, 3, false));
    }
}