package org.Sandship.Materials;

public class IronMaterial extends Material {

    //static initializer that initializes fields with proper values and notifies users about its creation during class loading
    static {
        name = "Iron";
        icon = "src/main/resources/iron.png";
        maxCapacity = 62_280;
        Material.notifyingNewMaterial(name);
    }

}
