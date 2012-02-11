package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GridParser {

    public static final int MAX_COLUMNS = 100;
    public static final int MAX_ROWS = 10;
    public static final int MIN_COLUMNS = 5;

    private int currentValidNumberOfColumns;
    private BufferedReader reader;

    public int [][] parse(BufferedReader reader) throws GridParserException {

        // Create a List of all the lines given to us by the reader.
        List<String> lines = extractLinesFromReader(reader);

        // Now that we know the number of lines (rows) that we have, we can create a proper
        // 2D integer array
        int [][] arrayToReturn = new int[lines.size()][];
        
        int lineIndex = 0;

        for (String lineToParse : lines) {

            // Split on one whitespace character
            String[] columnStrings = lineToParse.split("\\s");

            // Create a new int[] row now after we know the number of column entries in this line is valid.
            arrayToReturn[lineIndex] = new int[getNumberOfColumnsFrom(columnStrings)];
            
            int columnIndex = 0;

            // Loop over the column strings, parse each into an integer, then put into the 2D array.
            for (String columnDataString : columnStrings) {

                try {

                    arrayToReturn[lineIndex][columnIndex] = Integer.parseInt(columnDataString);

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

    // Extract and test the number of columns that was extracted from the current line of data.
    private int getNumberOfColumnsFrom(String[] columnStrings) throws GridParserException {

        if (currentValidNumberOfColumns == 0 && columnStrings.length > 0) {
            currentValidNumberOfColumns = columnStrings.length;
        }

        // Make sure that we have the correct amount of columns
        verifyNumberOfColumns(columnStrings);

        return currentValidNumberOfColumns;
    }

    // Sanity Checks
    private void verifyNumberOfColumns(String[] columnStrings) throws GridParserException {

        if (columnStrings.length != currentValidNumberOfColumns) {
            throw new GridParserException("Error parsing data: Number of columns is not consistent across all rows.");
        }

        if (columnStrings.length < MIN_COLUMNS || columnStrings.length > MAX_COLUMNS) {
            throw new GridParserException("Error parsing data: Number of columns per row must be between "+MIN_COLUMNS+" and "+MAX_COLUMNS+".");
        }
    }

    private List<String> extractLinesFromReader(BufferedReader reader) throws GridParserException {

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

        if (lines.isEmpty() || lines.size() > MAX_ROWS) {
            throw new GridParserException("Error parsing data: Please ensure that the supplied data is not empty and contains at most 10 rows");
        }

        return lines;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public int [][] parse() throws GridParserException {
        return parse(this.reader);
    }

    public void setReader(BufferedReader bufferedReader) {
        reader = bufferedReader;
    }
}
