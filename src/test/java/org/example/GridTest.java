package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class GridTest {
    
    private int[][] fiveByFiveGridArray;
    private Grid fiveByFiveGrid;
    @Before
    public void setup() {

        fiveByFiveGridArray = new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        
        fiveByFiveGrid = createFiveByFiveGrid();
    }

    private Grid createFiveByFiveGrid() {
        GridParser parser = mock(GridParser.class);

        try {
            when(parser.parse()).thenReturn(fiveByFiveGridArray);
        } catch (GridParserException e) {
        }

        return Grid.createGrid(parser);        
    }

    @Test(expected = NullPointerException.class)
    public void test_createGrid_throws_NullPointerException_if_given_a_null_parser() throws NullPointerException{

        Grid.createGrid(null);
    }
    
    @Test
    public void test_verify_that_createGrid_calls_parse_on_GridParser() throws GridParserException {
        
        GridParser parser = mock(GridParser.class);

        when(parser.parse()).thenReturn(fiveByFiveGridArray);
        
        Grid.createGrid(parser);

        verify(parser).parse();
    }
    
    @Test
    public void test_createGrid_does_not_return_null_if_GridParser_parse_is_successful() throws GridParserException {
        
        GridParser parser = mock(GridParser.class);
        
        when(parser.parse()).thenReturn(new int[][]{{}});

        Assert.assertNotNull(Grid.createGrid(parser));
    }
    
    @Test
    public void test_Grid_returned_by_createGrid_has_correct_width_and_height() throws GridParserException {
        
        GridParser parser = mock(GridParser.class);

        when(parser.parse()).thenReturn(fiveByFiveGridArray);

        Grid grid = Grid.createGrid(parser);
        
        Assert.assertEquals(5, grid.getHeight());
        Assert.assertEquals(5, grid.getWidth());
    }

    // ------------------------- "North East From" tests. -------------------------
    @Test
    public void test_Grid_wraps_when_going_northEastFrom_0_0_on_the_5x5_grid() {
        
        ResistanceCoordinate coord = fiveByFiveGrid.northeastFrom(0,0);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 4);
        Assert.assertEquals(coord.getResistance(), 22);
    }

    @Test
    public void test_Grid_returns_1_0_by_going_northEastFrom_0_1_on_the_5x5_grid() {

        ResistanceCoordinate coord = fiveByFiveGrid.northeastFrom(0, 1);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 0);
        Assert.assertEquals(coord.getResistance(), 2);
    }

    @Test
    public void test_Grid_wraps_when_going_northEastFrom_0_0_on_the_5x5_grid_using_ResistanceCoordinate() {

        ResistanceCoordinate start = new ResistanceCoordinate(0,0,fiveByFiveGridArray[0][0]);

        ResistanceCoordinate coord = fiveByFiveGrid.northeastFrom(start);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 4);
        Assert.assertEquals(coord.getResistance(), 22);
    }

    @Test
    public void test_Grid_returns_1_0_by_going_northEastFrom_0_1_on_the_5x5_grid_using_ResistanceCoordinate() {

        ResistanceCoordinate start = new ResistanceCoordinate(0,1,fiveByFiveGridArray[1][0]);

        ResistanceCoordinate coord = fiveByFiveGrid.northeastFrom(start);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 0);
        Assert.assertEquals(coord.getResistance(), 2);
    }
    
    // ------------------------- "East From" tests -------------------------

    @Test
    public void test_Grid_returns_1_0_by_going_eastFrom_0_0_on_the_5x5_grid() {

        ResistanceCoordinate coord = fiveByFiveGrid.eastFrom(0, 0);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 0);
        Assert.assertEquals(coord.getResistance(), 2);
    }

    @Test
    public void test_Grid_returns_1_0_by_going_eastFrom_0_0_on_the_5x5_grid_using_ResistanceCoordinate() {

        ResistanceCoordinate start = new ResistanceCoordinate(0,0,fiveByFiveGridArray[0][0]);

        ResistanceCoordinate coord = fiveByFiveGrid.eastFrom(start);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 0);
        Assert.assertEquals(coord.getResistance(), 2);
    }

    // ------------------------- "South East From" tests. -------------------------

    @Test
    public void test_Grid_wraps_when_going_southEastFrom_0_4_on_the_5x5_grid()  {

        ResistanceCoordinate coord = fiveByFiveGrid.southEastFrom(0, 4);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 0);
        Assert.assertEquals(coord.getResistance(), 2);
    }

    @Test
    public void test_Grid_wraps_when_going_southEastFrom_0_4_on_the_5x5_grid_using_ResistanceCoordinate()  {

        ResistanceCoordinate start = new ResistanceCoordinate(0,4,fiveByFiveGridArray[4][0]);

        ResistanceCoordinate coord = fiveByFiveGrid.southEastFrom(start);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 0);
        Assert.assertEquals(coord.getResistance(), 2);
    }

    @Test
    public void test_Grid_returns_1_1_when_going_southEastFrom_0_0_on_the_5x5_grid() {

        ResistanceCoordinate coord = fiveByFiveGrid.southEastFrom(0, 0);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 1);
        Assert.assertEquals(coord.getResistance(), 7);
    }

    @Test
    public void test_Grid_returns_1_1_when_going_southEastFrom_0_0_on_the_5x5_grid_using_ResistanceCoordinate() {

        ResistanceCoordinate start = new ResistanceCoordinate(0,0,fiveByFiveGridArray[0][0]);

        ResistanceCoordinate coord = fiveByFiveGrid.southEastFrom(start);

        Assert.assertEquals(coord.getX(), 1);
        Assert.assertEquals(coord.getY(), 1);
        Assert.assertEquals(coord.getResistance(), 7);
    }

}
