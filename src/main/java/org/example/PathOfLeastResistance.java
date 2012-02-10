package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class PathOfLeastResistance
{
    private static PathFinder pathFinder = new PathFinder();

    public static void main( String[] args )
    {
        if (args.length == 0) {
            System.out.println("Use:  PathOfLeastResistance <path to file>");
            System.exit(1);
        }

        GridParser gridParser = new GridParser();

        try {
            pathFinder.setGrid(gridParser.parse(new BufferedReader(new FileReader(args[0]))));
            List<ResistanceCoordinate> shortestPath = pathFinder.findPath();

            printDetails(shortestPath);

        } catch (GridParserException e) {
            System.out.println("There was an error parsing grid file "+args[0]+":\n" + e);
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.out.println("Error:  File " + args[0] + " was not found.  Bailing.\n" + e);
            System.exit(1);
        } catch (PathFinderException e) {
            System.out.println("There was an error finding a path with file" + args[0] + ". Bailing.\n" + e);
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