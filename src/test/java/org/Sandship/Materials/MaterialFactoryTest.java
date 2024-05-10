package org.Sandship.Materials;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//test class to check if material factory class works properly
class MaterialFactoryTest {

    @Test
    public void testGettingIronMaterial() {
        assertInstanceOf(IronMaterial.class, MaterialFactory.getMaterial("iron"));
    }

    @Test
    public void testGettingCopperMaterial() {
        assertInstanceOf(CopperMaterial.class, MaterialFactory.getMaterial("copper"));
    }

    @Test
    public void testGettingBoltMaterial() {
        assertInstanceOf(BoltMaterial.class, MaterialFactory.getMaterial("bolt"));
    }


    @Test
    public void testGettingNullAsMaterial() {
        assertNull(MaterialFactory.getMaterial("no such type"));
    }
}