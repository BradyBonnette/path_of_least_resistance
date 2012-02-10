package org.example;

// A simple class that holds Resistance information, as well
// as its (x,y) coordinates within a grid.
public class ResistanceCoordinate {
    
    private int xCoord;
    private int yCoord;
    private int resistance;
    
    public ResistanceCoordinate(int xCoord, int yCoord, int resistance) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.resistance = resistance;
    }
    
    public int getX() {
        return xCoord;
    }
    
    public int getY() {
        return yCoord;
    }
    
    public int getResistance() {
        return resistance;
    }

}
