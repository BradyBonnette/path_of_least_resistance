package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GridParser {

    private BufferedReader reader;

    private int numberOfColumns = 0;

    public int [][] parse(BufferedReader r) throws GridParserException {

        reader = r;

        int [][] arrayToReturn = null;

        // Create a List of all the lines given to us by the reader.
        List<String> lines = extractLinesFromReader();

        if (lines.isEmpty() || lines.size() > 10) {
            throw new GridParserException("Error parsing data: Please ensure that the supplied data is not empty and contains at most 10 rows");
        }

        // Now that we know the number of lines (rows) that we have, we can create a proper
        // 2D integer array
        arrayToReturn = new int[lines.size()][];
        
        int lineIndex = 0;

        for (String lineToParse : lines ) {

            // Split on one whitespace character
            String[] lineSplit = lineToParse.split("\\s");

            // Set the number of columns extracted from the line here.
            if (numberOfColumns == 0 && lineSplit.length > 0) {
                numberOfColumns = lineSplit.length;
            }

            // Make sure that we have the correct amount of columns
            veryifyLineSplit(lineSplit);

            // Create a new int[] row now that we know the number of column entries is valid.
            arrayToReturn[lineIndex] = new int[numberOfColumns];
            
            int columnIndex = 0;

            // Loop over the column strings, parse each into an integer, then put into the 2D array.
            for (String columnDataString : lineSplit ) {

                try {

                    int columnData = Integer.parseInt(columnDataString);

                    arrayToReturn[lineIndex][columnIndex] = columnData;

                } catch (NumberFormatException nfe) {
                    throw new GridParserException("Error parsing data: Column data '"+columnDataString+"' is not an integer.");
                }

                columnIndex++;
            }
           
            lineIndex++;
        }
        
        lines.clear();

        return arrayToReturn;
    }

    private void veryifyLineSplit(String[] lineSplit) throws GridParserException {

        if (lineSplit.length != numberOfColumns) {
            throw new GridParserException("Error parsing data: Number of columns is not consistent across all rows.");
        }

        if (lineSplit.length < 5 || lineSplit.length > 100) {
            throw new GridParserException("Error parsing data: Number of columns per row must be between 5 and 100.");
        }
    }

    private List<String> extractLinesFromReader() throws GridParserException {

        String line;
        List<String> lines = new ArrayList<String>();

        if (reader == null)
            throw new GridParserException("GridParser received a null reader.");
        
        try {
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            throw new GridParserException("There was an error parsing data from the source: " + e);
        } finally {
            // Close up the reader when finished or if we hit an exception.
            try { if (reader != null) reader.close(); } catch(IOException e) { /* Cant do anything here.*/ }
        }
        
        return lines;
    }
}
