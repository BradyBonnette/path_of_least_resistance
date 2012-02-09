package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GridParser {

    private BufferedReader reader;

    public int [][] parse(BufferedReader r) throws GridParserException {

        String line;
        
        int [][] arrayToReturn = null;
        
        reader = r;
        
        List<String> lines = new ArrayList<String>(); 
        
        // minimum: 1 row, 5 columns
        // maximum: 10 rows, 100 columns
        
        try {
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            throw new GridParserException("There was an error parsing data from the source: " + e);
        } finally {
            // Clean up if something happens.
            try { if (reader != null) reader.close(); } catch(IOException e) { /* Cant do anything here.*/ }
        }

        if (lines.isEmpty() || lines.size() > 10) {
            throw new GridParserException("Error parsing data: Please ensure that the supplied data is not empty and contains at most 10 rows");
        }

        arrayToReturn = new int[lines.size()][];
        
        int numberOfColumns = 0;
        int lineCounter = 0;
        

        for (String lineToParse : lines ) {

            String[] lineSplit = lineToParse.split("\\s");
            
            if (numberOfColumns == 0 && lineSplit.length > 0) {
                numberOfColumns = lineSplit.length;
            }
            else if (lineSplit.length != numberOfColumns) {
                throw new GridParserException("Error parsing data: Number of columns is not consistent across all rows.");
            }

            if (lineSplit.length < 5 || lineSplit.length > 100) {
                throw new GridParserException("Error parsing data: Number of columns per row must be between 5 and 100.");
            }
            
            arrayToReturn[lineCounter] = new int[numberOfColumns];
            
            int columnCounter = 0;
            
            for (String columnDataString : lineSplit ) {

                try {

                    int columnData = Integer.parseInt(columnDataString);
                    arrayToReturn[lineCounter][columnCounter] = columnData;

                } catch (NumberFormatException nfe) {
                    throw new GridParserException("Error parsing data: Column data '"+columnDataString+"' is not an integer.");
                }
                columnCounter++;
            }
            
            
            lineCounter++;
        }
        
        lines.clear();
        return arrayToReturn;
    }
}
