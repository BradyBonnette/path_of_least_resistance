package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

public class PathFinderTest {

    private PathFinder fiveByFivePathFinder;
    private int[][] fiveByFiveGrid;

    @Before
    public void setup() {
        fiveByFiveGrid = create5x5Grid();
        fiveByFivePathFinder = new PathFinder(fiveByFiveGrid);
    }
    
    @Test
    public void test_PathFinder_has_null_grid_by_default() {

        Assert.assertNull(new PathFinder().getGrid());
    }
    
    @Test
    public void test_PathFinder_returns_correct_integer_2d_array_when_given_one_via_constructor(){

        Assert.assertTrue(Arrays.deepEquals(fiveByFivePathFinder.getGrid(), fiveByFiveGrid));
    }
    
    @Test
    public void test_PathFinder_returns_correct_integer_2d_array_when_given_one_via_setter() {

        PathFinder pathFinder = new PathFinder();
        pathFinder.setGrid(fiveByFiveGrid);

        Assert.assertTrue(Arrays.deepEquals(pathFinder.getGrid(), fiveByFiveGrid));
    }
    
    @Test(expected = PathFinderException.class)
    public void test_PathFinder_throws_exception_when_findPath_is_issued_without_setting_a_grid_properly() throws PathFinderException{

        new PathFinder().findPath();
    }
    
    @Test
    public void test_PathFinder_returns_a_grid_height_of_five_when_given_a_2d_grid_with_a_height_of_five_via_constructor() {

        Assert.assertEquals(fiveByFivePathFinder.getGridHeight(), 5);
    }
    
    @Test
    public void test_PathFinder_returns_a_grid_height_of_five_when_given_a_2d_grid_with_a_height_of_five_via_setter() {
        
        PathFinder pathFinder = new PathFinder();
        pathFinder.setGrid(fiveByFiveGrid);
        
        Assert.assertEquals(pathFinder.getGridHeight(), 5);
    }

    @Test
    public void test_PathFinder_returns_a_grid_width_of_five_when_given_a_2d_grid_with_a_width_of_five_via_constructor() {

        Assert.assertEquals(fiveByFivePathFinder.getGridWidth(), 5);
    }

    @Test
    public void test_PathFinder_returns_a_grid_width_of_five_when_given_a_2d_grid_with_a_width_of_five_via_setter() {

        PathFinder pathFinder = new PathFinder();
        pathFinder.setGrid(fiveByFiveGrid);

        Assert.assertEquals(pathFinder.getGridWidth(), 5);
    }

    // ------------------------- "North East From" tests. -------------------------
    @Test
    public void test_PathFinder_wraps_grid_by_going_northEastFrom_from_0_0_on_the_5x5_grid() {

        ResistanceCoordinate coord = fiveByFivePathFinder.northeastFrom(0,0);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 4);
        Assert.assertEquals(coord.getResistance(), 22);
    }

    @Test
    public void test_PathFinder_returns_1_0_by_going_northEastFrom_0_1_on_the_5x5_grid() {

        ResistanceCoordinate coord = fiveByFivePathFinder.northeastFrom(0, 1);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 0);
        Assert.assertEquals(coord.getResistance(), 2);
    }

    @Test
    public void test_PathFinder_wraps_grid_by_going_northEastFrom_from_0_0_on_the_5x5_grid_using_ResistanceCoordinate() {

        ResistanceCoordinate start = new ResistanceCoordinate(0,0,fiveByFiveGrid[0][0]);

        ResistanceCoordinate coord = fiveByFivePathFinder.northeastFrom(start);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 4);
        Assert.assertEquals(coord.getResistance(), 22);
    }

    @Test
    public void test_PathFinder_returns_1_0_by_going_northEastFrom_0_1_on_the_5x5_grid_using_ResistanceCoordinate() {

        ResistanceCoordinate start = new ResistanceCoordinate(0,1,fiveByFiveGrid[1][0]);

        ResistanceCoordinate coord = fiveByFivePathFinder.northeastFrom(start);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 0);
        Assert.assertEquals(coord.getResistance(), 2);
    }

    // ------------------------- "East From" tests -------------------------
    
    @Test
    public void test_PathFinder_returns_1_0_by_going_eastFrom_0_0_on_the_5x5_grid() {

        ResistanceCoordinate coord = fiveByFivePathFinder.eastFrom(0, 0);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 0);
        Assert.assertEquals(coord.getResistance(), 2);
    }

    @Test
    public void test_PathFinder_returns_1_0_by_going_eastFrom_0_0_on_the_5x5_grid_using_ResistanceCoordinate() {

        ResistanceCoordinate start = new ResistanceCoordinate(0,0,fiveByFiveGrid[0][0]);

        ResistanceCoordinate coord = fiveByFivePathFinder.eastFrom(start);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 0);
        Assert.assertEquals(coord.getResistance(), 2);
    }

    
    // ------------------------- "South East From" tests. -------------------------
    @Test
    public void test_PathFinder_wraps_grid_by_going_southEastFrom_0_4_on_the_5x5_grid()  {

        ResistanceCoordinate coord = fiveByFivePathFinder.southEastFrom(0, 4);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 0);
        Assert.assertEquals(coord.getResistance(), 2);
    }

    @Test
    public void test_PathFinder_wraps_grid_by_going_southEastFrom_0_4_on_the_5x5_grid_using_ResistanceCoordinate()  {
        
        ResistanceCoordinate start = new ResistanceCoordinate(0,4,fiveByFiveGrid[4][0]);

        ResistanceCoordinate coord = fiveByFivePathFinder.southEastFrom(start);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 0);
        Assert.assertEquals(coord.getResistance(), 2);
    }
    
    @Test
    public void test_Pathfinder_returns_1_1_by_going_southEastFrom_0_0_on_the_5x5_grid() {

        ResistanceCoordinate coord = fiveByFivePathFinder.southEastFrom(0, 0);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 1);
        Assert.assertEquals(coord.getResistance(), 7);
    }

    @Test
    public void test_Pathfinder_returns_1_1_by_going_southEastFrom_0_0_on_the_5x5_grid_using_ResistanceCoordinate() {

        ResistanceCoordinate start = new ResistanceCoordinate(0,0,fiveByFiveGrid[0][0]);
        
        ResistanceCoordinate coord = fiveByFivePathFinder.southEastFrom(start);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 1);
        Assert.assertEquals(coord.getResistance(), 7);
    }
    
    @Test
    public void test_PathFinder_calculateTotalResistance() {

        Stack<ResistanceCoordinate> stackToCalculate = new Stack<ResistanceCoordinate>();
        
        stackToCalculate.push(new ResistanceCoordinate(0,0,5));
        stackToCalculate.push(new ResistanceCoordinate(0,1,5));
        stackToCalculate.push(new ResistanceCoordinate(0,2,5));
        stackToCalculate.push(new ResistanceCoordinate(0,3,5));
        stackToCalculate.push(new ResistanceCoordinate(0,4,5));
        
        Assert.assertEquals(fiveByFivePathFinder.calculateTotalResistance(stackToCalculate), 25);
    }
    
    @Test
    public void test_PathFinder_copyStack() {
        
        Stack<ResistanceCoordinate> stackToCopy = new Stack<ResistanceCoordinate>();

        stackToCopy.push(new ResistanceCoordinate(0, 0, 5));
        stackToCopy.push(new ResistanceCoordinate(0, 1, 5));
        stackToCopy.push(new ResistanceCoordinate(0, 2, 5));

        Stack<ResistanceCoordinate> copy = fiveByFivePathFinder.copyStack(stackToCopy);
        
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

    public int[][] create5x5Grid() {
        return new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
    }
}
