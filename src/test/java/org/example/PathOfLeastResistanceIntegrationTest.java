package org.example;

import org.example.pathfinders.DepthFirstSearchPathFinder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

// Test real data using both path finders and GridParser
public class PathOfLeastResistanceIntegrationTest
{
    private GridParser gridParser;
    private DepthFirstSearchPathFinder dfsPathFinder;

    private String example1;
    private String example2;
    private String example3;
    
    private String example1Filename;
    private String example2Filename;
    private String example3Filename;

    @Before
    public void setup() {
        
        gridParser = new GridParser();
        dfsPathFinder = new DepthFirstSearchPathFinder();

        example1 =  "3 4 1 2 8 6\n" +
                    "6 1 8 2 7 4\n" +
                    "5 9 3 9 9 5\n" +
                    "8 4 1 3 2 6\n" +
                    "3 7 2 8 6 4";
        
        example2 =  "3 4 1 2 8 6\n" +
                    "6 1 8 2 7 4\n" +
                    "5 9 3 9 9 5\n" +
                    "8 4 1 3 2 6\n" +
                    "3 7 2 1 2 3";
        
        example3 =  "19 10 19 10 19\n" +
                    "21 23 20 19 12\n" +
                    "20 12 20 11 10";
        
        example1Filename = "src/test/resources/Example1.txt";
        example2Filename = "src/test/resources/Example2.txt";
        example3Filename = "src/test/resources/Example3.txt";
    }
    
    @Test
    public void test_example1_parsed_from_a_StringReader() {

        gridParser.setReader(new BufferedReader(new StringReader(example1)));
        Grid grid = Grid.createGrid(gridParser);

        List<ResistanceCoordinate> shortestPath = dfsPathFinder.findPath(grid);

        Assert.assertTrue(waterMadeItAllTheWayThrough(shortestPath, 6));
        Assert.assertEquals(totalResistanceOf(shortestPath),16);
        Assert.assertArrayEquals(getPathArray(shortestPath), new int[]{1, 2, 3, 4, 4, 5});
    }
    
    @Test
    public void test_example1_parsed_from_a_FileReader() throws FileNotFoundException {

        gridParser.setReader(new BufferedReader(new FileReader(example1Filename)));
        Grid grid = Grid.createGrid(gridParser);

        List<ResistanceCoordinate> shortestPath = dfsPathFinder.findPath(grid);

        Assert.assertTrue(waterMadeItAllTheWayThrough(shortestPath, 6));
        Assert.assertEquals(totalResistanceOf(shortestPath),16);
        Assert.assertArrayEquals(getPathArray(shortestPath), new int[]{1, 2, 3, 4, 4, 5});
    }

    @Test
    public void test_example2_parsed_from_a_StringReader() {

        gridParser.setReader(new BufferedReader(new StringReader(example2)));
        Grid grid = Grid.createGrid(gridParser);

        List<ResistanceCoordinate> shortestPath = dfsPathFinder.findPath(grid);

        Assert.assertTrue(waterMadeItAllTheWayThrough(shortestPath, 6));
        Assert.assertEquals(totalResistanceOf(shortestPath),11);
        Assert.assertArrayEquals(getPathArray(shortestPath), new int[]{1,2,1,5,4,5});
    }

    @Test
    public void test_example2_parsed_from_a_FileReader() throws FileNotFoundException {

        gridParser.setReader(new BufferedReader(new FileReader(example2Filename)));
        Grid grid = Grid.createGrid(gridParser);

        List<ResistanceCoordinate> shortestPath = dfsPathFinder.findPath(grid);

        Assert.assertTrue(waterMadeItAllTheWayThrough(shortestPath, 6));
        Assert.assertEquals(totalResistanceOf(shortestPath),11);
        Assert.assertArrayEquals(getPathArray(shortestPath), new int[]{1,2,1,5,4,5});
    }

    @Test
    public void test_example3_parsed_from_a_StringReader() {

        gridParser.setReader(new BufferedReader(new StringReader(example3)));
        Grid grid = Grid.createGrid(gridParser);

        List<ResistanceCoordinate> shortestPath = dfsPathFinder.findPath(grid);

        Assert.assertFalse(waterMadeItAllTheWayThrough(shortestPath, 5));
        Assert.assertEquals(totalResistanceOf(shortestPath),48);
        Assert.assertArrayEquals(getPathArray(shortestPath), new int[]{1,1,1});
    }

    @Test
    public void test_example3_parsed_from_a_FileReader() throws FileNotFoundException {

        gridParser.setReader(new BufferedReader(new FileReader(example3Filename)));
        Grid grid = Grid.createGrid(gridParser);

        List<ResistanceCoordinate> shortestPath = dfsPathFinder.findPath(grid);

        Assert.assertFalse(waterMadeItAllTheWayThrough(shortestPath, 5));
        Assert.assertEquals(totalResistanceOf(shortestPath),48);
        Assert.assertArrayEquals(getPathArray(shortestPath), new int[]{1,1,1});
    }

    // Helper methods
    private int[] getPathArray(List<ResistanceCoordinate> shortestPath) {

        int total = 0;
        int ctr = 0;
        
        for (ResistanceCoordinate resistanceCoordinate : shortestPath) {
            
            total += resistanceCoordinate.getResistance();
            ctr++;
            
            if (total > 50) {
                ctr--;
                break;
            }
        }
        
        int[] arrayToReturn = new int[ctr];
        
        for (int i = 0; i < ctr; i++){
            arrayToReturn[i] = shortestPath.get(i).getY() + 1;
        }
        
        return arrayToReturn;
    }

    private int totalResistanceOf(List<ResistanceCoordinate> shortestPath) {
        int total = 0;
        
        for (ResistanceCoordinate resistanceCoordinate : shortestPath ) {
            total += resistanceCoordinate.getResistance();
            if (total > 50)
                return total - resistanceCoordinate.getResistance();
        }

        return total;
    }

    private boolean waterMadeItAllTheWayThrough(List<ResistanceCoordinate> shortestPath, int width) {
        
        int total = 0;

        for (ResistanceCoordinate resistanceCoordinate : shortestPath ) {
            total += resistanceCoordinate.getResistance();
            if (total > 50)
                return false;
        }
        
        return true;
    }

}
