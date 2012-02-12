package org.example.pathfinders;

import org.example.Grid;
import org.example.GridParser;
import org.example.GridParserException;
import org.example.ResistanceCoordinate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.mockito.Mockito.*;

public class DepthFirstSearchPathFinderTest {

    private DepthFirstSearchPathFinder pathFinder;
    
    @Before
    public void setup() {
        pathFinder = new DepthFirstSearchPathFinder();
    }
    
    @Test(expected = NullPointerException.class)
    public void test_DFSPathFinder_throws_NPE_when_calling_findPath_given_a_null_grid() {

        pathFinder.findPath(null);
    }
    
    @Test
    public void test_DFSPathfinder_does_not_return_null_when_findPath_is_given_a_grid() {

        Grid mockGrid = mock(Grid.class);
        
        Assert.assertNotNull(pathFinder.findPath(mockGrid));
    }

    @Test
    public void test_DFSPathFinder_calculateTotalResistance() {

        Stack<ResistanceCoordinate> stackToCalculate = new Stack<ResistanceCoordinate>();

        stackToCalculate.push(new ResistanceCoordinate(0,0,5));
        stackToCalculate.push(new ResistanceCoordinate(0,1,5));
        stackToCalculate.push(new ResistanceCoordinate(0,2,5));
        stackToCalculate.push(new ResistanceCoordinate(0,3,5));
        stackToCalculate.push(new ResistanceCoordinate(0,4,5));

        Assert.assertEquals(25,pathFinder.calculateTotalResistance(stackToCalculate));
    }

    @Test
    public void test_DFSPathFinder_copyStack() {

        Stack<ResistanceCoordinate> stackToCopy = new Stack<ResistanceCoordinate>();

        stackToCopy.push(new ResistanceCoordinate(0, 0, 5));
        stackToCopy.push(new ResistanceCoordinate(0, 1, 5));
        stackToCopy.push(new ResistanceCoordinate(0, 2, 5));

        ArrayList<ResistanceCoordinate> copy = pathFinder.copyStack(stackToCopy);

        Assert.assertEquals(copy.size(), 3);

        Assert.assertEquals(copy.get(0).getX(), 0);
        Assert.assertEquals(copy.get(0).getY(), 0);
        Assert.assertEquals(copy.get(0).getResistance(), 5);

        Assert.assertEquals(copy.get(1).getX(), 0);
        Assert.assertEquals(copy.get(1).getY(), 1);
        Assert.assertEquals(copy.get(1).getResistance(), 5);

        Assert.assertEquals(copy.get(2).getX(), 0);
        Assert.assertEquals(copy.get(2).getY(), 2);
        Assert.assertEquals(copy.get(2).getResistance(), 5);
    }
    
    @Test
    public void test_DFSPathFinder_findPath_returns_a_list_of_5_items_when_given_a_5x5_grid() throws GridParserException {

        GridParser gridParser = mock(GridParser.class);
        
        when(gridParser.parse()).thenReturn(new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}});

        List list = pathFinder.findPath(Grid.createGrid(gridParser));
        
        Assert.assertEquals(5, list.size());
    }

    @Test
    public void test_DFSPathFinder_findPath_returns_a_list_of_5_items_when_given_a_5x5_grid_of_negative_integers() throws GridParserException {

        GridParser gridParser = mock(GridParser.class);

        when(gridParser.parse()).thenReturn(new int[][]{{-1,-2,-3,-4,-5},{-6,-7,-8,-9,-10},{-11,-12,-13,-14,-15},{-16,-17,-18,-19,-20},{-21,-22,-23,-24,-25}});

        List list = pathFinder.findPath(Grid.createGrid(gridParser));

        Assert.assertEquals(5, list.size());
    }
}
