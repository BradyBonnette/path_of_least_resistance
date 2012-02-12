package org.example;

public class Grid {
    
    private int[][] gridArray;
    private int height;
    private int width;

    public static Grid createGrid(GridParser gridParser) {
        
        int[][] array = null;
        
        if (gridParser == null) {
            throw new NullPointerException("Cannot create a grid from a null Grid Parser.");
        }

        try {
            array = gridParser.parse();
        } catch (GridParserException e) {
            throw new RuntimeException("There was an error parsing Grid data: \n" + e);
        }

        return new Grid(array);
    }

    private Grid(int[][] gridArray) {
        this.gridArray = gridArray;
        setWidth(calculateGridWidth(this.gridArray));
        setHeight(calculateGridHeight(this.gridArray));
        
    }

    private static int calculateGridWidth(int[][] grid) {
        return grid[0].length;
    }
    
    private static int calculateGridHeight(int[][] grid) {
        
        int height = 0;

        for (int[] row : grid) {
            height++;
        }
        
        return height;
    }
    
    private void setHeight(int height) {
        this.height = height;
    }
    
    private void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ResistanceCoordinate northeastFrom(int fromX, int fromY) {
        // Wrap around y if we are going north above 0.
        int newY = (fromY == 0) ? height - 1 : fromY - 1;
        int newX = fromX + 1;

        return new ResistanceCoordinate(newX, newY, gridArray[newY][newX]);
    }

    public ResistanceCoordinate northeastFrom(ResistanceCoordinate fromCoordinate) {
        return northeastFrom(fromCoordinate.getX(), fromCoordinate.getY());
    }

    public ResistanceCoordinate eastFrom(int fromX, int fromY) {
        return new ResistanceCoordinate(fromX + 1, fromY, gridArray[fromY][fromX + 1]);
    }

    public ResistanceCoordinate eastFrom(ResistanceCoordinate fromCoordinate) {
        return eastFrom(fromCoordinate.getX(), fromCoordinate.getY());
    }

    public ResistanceCoordinate southEastFrom(int fromX, int fromY) {

        // Wrap around y if we are going south below the Grid's height
        int newY = (fromY == height - 1) ? 0 : fromY + 1;
        int newX = fromX + 1;

        return new ResistanceCoordinate(newX, newY, gridArray[newY][newX]);
    }

    public ResistanceCoordinate southEastFrom(ResistanceCoordinate fromCoordinate) {
        return southEastFrom(fromCoordinate.getX(), fromCoordinate.getY());
    }

    public ResistanceCoordinate getResistanceCoordinateFrom(int fromX, int fromY) {
        return new ResistanceCoordinate(fromX, fromY, gridArray[fromY][fromX]);
    }
}
