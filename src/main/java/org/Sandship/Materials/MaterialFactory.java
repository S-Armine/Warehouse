package org.Sandship.Materials;

public class MaterialFactory {

    //this method gets string as argument and returns instance of respective material type class
    //returns null if given argument does not match any material type
    public static Material getMaterial(String materialType) {
        if (materialType.equalsIgnoreCase("IRON")) {
            return new IronMaterial();
        }
        if (materialType.equalsIgnoreCase("COPPER")) {
            return new CopperMaterial();
        }
        if (materialType.equalsIgnoreCase("BOLT")) {
            return new BoltMaterial();
        }
        return null;
    }

}
