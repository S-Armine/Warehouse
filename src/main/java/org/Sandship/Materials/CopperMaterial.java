package org.Sandship.Materials;

public class CopperMaterial extends Material {

    //static initializer that initializes fields with proper values and notifies users about its creation during class loading
    static {
        name = "Copper";
        description = "Copper, known for its excellent conductivity and distinct reddish hue, " +
                "is utilized in various applications from electrical wiring to artistic endeavors.";
        icon = "src/main/resources/copper.png";
        maxCapacity = 1960;
        Material.notifyingNewMaterial(name);
    }

}
