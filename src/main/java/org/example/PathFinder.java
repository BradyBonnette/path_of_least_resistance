package org.example;

import java.util.List;
import java.util.Stack;

public class PathFinder {
    
    private Stack<ResistanceCoordinate> traceStack = new Stack<ResistanceCoordinate>();

    private int gridHeight = 0;
    private int gridWidth = 0;
    private int[][] grid;
    private int shortestResistanceOverall = Integer.MAX_VALUE;
    private Stack<ResistanceCoordinate> currentShortestPath = new Stack<ResistanceCoordinate>();

    public PathFinder() {
    }

    public PathFinder(int[][] grid) {
        // TODO: refactor this, see #setGrid()
        this.grid = grid;
        calculateGridHeight();
        calculateGridWidth();
    }

    private void calculateGridWidth() {
        gridWidth = grid[0].length;
    }

    private void calculateGridHeight() {
        int height = 0;

        for (int[] row : grid) {
            height++;
        }
        this.gridHeight =  height;
    }

    // Depth First Search, from x,y parameters into
    // ResistanceCoordinate
    private void DFS(int fromX, int fromY) {
        DFS(new ResistanceCoordinate(fromX,fromY,grid[fromY][fromX]));
    }

    // The meat of the operation -- Depth First Search.  We aren't necessarily
    // searching for a node in particular, but this will ensure that all coordinates are
    // hit.
    private void DFS(ResistanceCoordinate thisCoordinate) {

        traceStack.push(thisCoordinate);

        // If the current Coordinate is at the far right edge of the grid,
        // then we will not be traversing any further East.  Return from here.
        if (thisCoordinate.getX() == (getGridWidth() - 1)) {

            int currentTotalResistance = calculateTotalResistance(traceStack);

            // Set the overall shortest path if we found a new shortest
            if (currentTotalResistance < shortestResistanceOverall) {
                currentShortestPath = copyStack(traceStack);
                shortestResistanceOverall = currentTotalResistance;
            }

            traceStack.pop();

            return;
        }

        // Recursion is awesome.
        DFS(northeastFrom(thisCoordinate));
        DFS(eastFrom(thisCoordinate));
        DFS(southEastFrom(thisCoordinate));

        traceStack.pop();
    }

    // This is what will call DFS above.  Its job is to essentially call DFS on each row that
    // exists in the grid, start at X-Coordinate 0. The path we return is a List, so the
    // caller can do whatever it wants with the path information.
    public Stack<ResistanceCoordinate> findPath() throws PathFinderException {

        if (grid == null)
            throw new PathFinderException("PathFinder contains a null integer 2d array grid.  Please set one using #setGrid() or via the constructor before calling #findPath()");
        
        for (int rowCounter = 0; rowCounter < getGridHeight(); rowCounter++)
            DFS(0,rowCounter);

        return currentShortestPath;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {

        // TODO: refactor this, see constructor
        this.grid = grid;
        calculateGridHeight();
        calculateGridWidth();
    }

    // Calculate new coordinates that are north and east from the given
    // set of coordinates. This will also take wrapping into consideration, ie
    // we will wrap from the top of the grid back around to the bottom.
    public ResistanceCoordinate northeastFrom(int fromX, int fromY) {

        int newX;
        int newY;

        // Wrap around y if we are going north above 0.
        // TODO:  Can we use modulo here for tightness?
        if (fromY == 0) {
            newY = getGridHeight() - 1;
        } else {
            newY = fromY - 1;
        }

        newX = fromX + 1;

        return new ResistanceCoordinate(newX, newY, grid[newY][newX]);
    }

    // Calculate new coordinates that are south and east from the given
    // set of coordinates. This will also take wrapping into consideration, ie
    // we will wrap from the bottom of the grid back around to the top.
    public ResistanceCoordinate southEastFrom(int fromX, int fromY) {
        
        int newX;
        int newY;
        
        // Wrap around y if we are going south below the Grid's height
        // TODO:  Can we use modulo here for tightness?
        if (fromY == getGridHeight() - 1) {
            newY = 0;
        } else {
            newY = fromY + 1;
        }
        
        newX = fromX + 1;
        
        return new ResistanceCoordinate(newX, newY, grid[newY][newX]);
        
    }

    public ResistanceCoordinate eastFrom(int fromX, int fromY) {
        return new ResistanceCoordinate(fromX + 1, fromY, grid[fromY][fromX + 1]);
    }

    public ResistanceCoordinate northeastFrom(ResistanceCoordinate fromCoordinate) {
        return northeastFrom(fromCoordinate.getX(), fromCoordinate.getY());
    }

    public ResistanceCoordinate eastFrom(ResistanceCoordinate fromCoordinate) {
        return eastFrom(fromCoordinate.getX(), fromCoordinate.getY());
    }

    public ResistanceCoordinate southEastFrom(ResistanceCoordinate fromCoordinate) {
        return southEastFrom(fromCoordinate.getX(), fromCoordinate.getY());
    }

    // Calculate total resistance from the given list.
    public int calculateTotalResistance(List<ResistanceCoordinate> listToCalculate) {

        int total = 0;

        for (ResistanceCoordinate resistanceCoordinate : listToCalculate ){
            total += resistanceCoordinate.getResistance();
        }

        return total;
    }

    public Stack<ResistanceCoordinate> copyStack(Stack<ResistanceCoordinate> stackToCopy) {
        
        Stack<ResistanceCoordinate> stackClone = new Stack<ResistanceCoordinate>();
        
        for (ResistanceCoordinate resistanceCoordinate : stackToCopy) {
            stackClone.add(resistanceCoordinate);
        }

        return stackClone;
    }
}
