package org.Sandship.Materials;

public class BoltMaterial extends Material {

    //static initializer that initializes fields with proper values and notifies users about its creation during class loading
    static {
        name = "Bolt";
        description = "Bolts, threaded fasteners with a head and shaft, are pivotal in joining components securely, " +
                "serving diverse purposes across industries from construction to automotive engineering.";
        icon = "src/main/resources/bolt.png";
        maxCapacity = 20_000;
        Material.notifyingNewMaterial(name);
    }

}
