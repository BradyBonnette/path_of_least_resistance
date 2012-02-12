package org.example;

import org.example.pathfinders.DepthFirstSearchPathFinder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class PathOfLeastResistance
{
    public static void main( String[] args )
    {
        if (args.length == 0) {
            System.out.println("Use:  PathOfLeastResistance <path to file>");
            System.exit(1);
        }

        GridParser gridParser = new GridParser();
        DepthFirstSearchPathFinder dfsPathFinder = new DepthFirstSearchPathFinder();

        try {

            gridParser.setReader(new BufferedReader(new FileReader(args[0])));
            Grid grid = Grid.createGrid(gridParser);
            
            List<ResistanceCoordinate> shortestPath = dfsPathFinder.findPath(grid);

            printDetails(shortestPath);

        } catch (FileNotFoundException e) {
            System.out.println("Error:  File " + args[0] + " was not found.  Bailing.\n" + e);
            System.exit(1);
        }
    }

    private static void printDetails(List<ResistanceCoordinate> shortestPath) {

        if (waterMadeItAllTheWayThrough(shortestPath)){
            System.out.println("Yes\n");
        }else{
            System.out.println("No\n");
        }
        
        System.out.println(totalResistanceOf(shortestPath) + "\n");
        
        System.out.println(getPathString(shortestPath));
    }

    private static String getPathString(List<ResistanceCoordinate> shortestPath) {
        
        int total = 0;

        StringBuilder sb = new StringBuilder();

        for (ResistanceCoordinate resistanceCoordinate : shortestPath) {

            total += resistanceCoordinate.getResistance();

            if (total > 50) {
                break;
            }
            
            sb.append(resistanceCoordinate.getY() + 1).append(" ");
        }
        
        return sb.toString().trim();
    }

    private static int totalResistanceOf(List<ResistanceCoordinate> shortestPath) {

        int total = 0;

        for (ResistanceCoordinate resistanceCoordinate : shortestPath ) {
            total += resistanceCoordinate.getResistance();
            if (total > 50)
                return total - resistanceCoordinate.getResistance();
        }

        return total;
    }

    private static boolean waterMadeItAllTheWayThrough(List<ResistanceCoordinate> shortestPath) {

        int total = 0;

        for (ResistanceCoordinate resistanceCoordinate : shortestPath ) {
            total += resistanceCoordinate.getResistance();
            if (total > 50)
                return false;
        }

        return true;
    }
}