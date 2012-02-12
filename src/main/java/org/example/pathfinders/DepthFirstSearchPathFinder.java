package org.example.pathfinders;

import org.example.Grid;
import org.example.ResistanceCoordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchPathFinder implements PathFinder {

    private Grid grid;
    private Stack<ResistanceCoordinate> traceStack;
    private int shortestResistanceOverall  = Integer.MAX_VALUE;
    private ArrayList<ResistanceCoordinate> currentShortestPath;

    public DepthFirstSearchPathFinder() {
        currentShortestPath = new ArrayList<ResistanceCoordinate>();
        traceStack = new Stack<ResistanceCoordinate>();
    }

    private void DFS(int fromX, int fromY) {
        DFS(grid.getResistanceCoordinateFrom(fromX, fromY));
    }
    
    private void DFS(ResistanceCoordinate thisCoordinate) {
        
        traceStack.push(thisCoordinate);

        // If the current Coordinate is at the far right edge of the grid,
        // then we will not be traversing any further East.  Return from here.
        if (thisCoordinate.getX() == (grid.getWidth() - 1)) {

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
        DFS(grid.northeastFrom(thisCoordinate));
        DFS(grid.eastFrom(thisCoordinate));
        DFS(grid.southEastFrom(thisCoordinate));

        traceStack.pop();        
    }
    
    public List<ResistanceCoordinate> findPath(Grid gridToSearch) {
        
        if (gridToSearch == null)
            throw new NullPointerException("Grid to search is null.");
        
        grid = gridToSearch;
        
        for (int rowCounter = 0; rowCounter < grid.getHeight(); rowCounter++)
            DFS(0,rowCounter);
        
        return currentShortestPath;
    }

    public int calculateTotalResistance(Stack<ResistanceCoordinate> stackToCalculate) {
        int total = 0;

        for (ResistanceCoordinate resistanceCoordinate : stackToCalculate){
            total += resistanceCoordinate.getResistance();
        }

        return total;
    }

    public ArrayList<ResistanceCoordinate> copyStack(Stack<ResistanceCoordinate> stackToCopy) {

        ArrayList<ResistanceCoordinate> stackClone = new ArrayList<ResistanceCoordinate>();

        for (ResistanceCoordinate resistanceCoordinate : stackToCopy) {
            stackClone.add(resistanceCoordinate);
        }

        return stackClone;
    }
}
