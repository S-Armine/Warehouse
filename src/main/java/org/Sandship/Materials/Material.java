package org.Sandship.Materials;

import java.util.Objects;

//abstract class that should be extended by any type of new material in a system
public abstract class Material {
    protected static String name;
    protected static String description;
    protected static String icon;
    protected static int maxCapacity;

    public  static String getName() {
        return name;
    }

    public static String getDescription() {
        return description;
    }

    public static String getIcon() {
        return icon;
    }

    public static int getMaxCapacity() {
        return maxCapacity;
    }

    //method that handles notifying players about new material type
    static void notifyingNewMaterial(String materialName) {
        NewMaterialObservable observable = NewMaterialObservable.getInstance();
        observable.notify(materialName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, icon, maxCapacity);
    }

    @Override
     public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (! (object instanceof Material material)) {
            return false;
        }
        return getName().equals(material.getName()) && this.getDescription().equals(material.getDescription())
                && this.getIcon().equals(material.getIcon()) && this.getMaxCapacity() == material.getMaxCapacity();
    }
}
