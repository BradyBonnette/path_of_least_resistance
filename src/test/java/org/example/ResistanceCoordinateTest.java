package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResistanceCoordinateTest {
    
    private ResistanceCoordinate coord;
    
    @Before
    public void setup() {
        coord = new ResistanceCoordinate(1,2,3);
    }
    
    @Test
    public void test_ResistanceCoordinate_returns_correct_X_Coordinate() {
        Assert.assertEquals(1, coord.getX());
    }

    @Test
    public void test_ResistanceCoordinate_returns_correct_Y_Coordinate() {
        Assert.assertEquals(2, coord.getY());
    }

    @Test
    public void test_ResistanceCoordinate_returns_correct_Resistance() {
        Assert.assertEquals(3, coord.getResistance());
    }

}
