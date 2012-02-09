package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Arrays;

public class GridParserTest {

    private GridParser gridParser;

    @Before
    public void setup() {
        gridParser = new GridParser();
    }

    // minimum: 1 row, 5 columns
    // maximum: 10 rows, 100 columns
    @Test(expected = GridParserException.class)
    public void test_GridParser_throws_exception_if_given_an_empty_grid() throws GridParserException {

        gridParser.parse(new BufferedReader(new StringReader("")));

    }

    @Test(expected = GridParserException.class)
    public void test_GridParser_throws_exception_if_given_1_row_and_4_columns() throws GridParserException {

        gridParser.parse(new BufferedReader(new StringReader("1 2 3 4")));

    }

    @Test(expected = GridParserException.class)
    public void test_GridParser_throws_exception_if_given_1_row_and_101_columns() throws GridParserException {

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 101; i++) {
            sb.append(i);
            if (i < 101)
                sb.append(" ");
        }

        gridParser.parse(new BufferedReader(new StringReader(sb.toString())));
    }

    @Test(expected = GridParserException.class)
    public void test_GridParser_throws_exception_if_given_11_rows_and_1_column() throws GridParserException {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 11; i++) {
            sb.append(i);
            if (i < 11)
                sb.append("\n");
        }

        gridParser.parse(new BufferedReader(new StringReader(sb.toString())));

    }

    @Test(expected = GridParserException.class)
    public void test_GridParser_throws_exception_if_given_11_rows_and_5_columns() throws GridParserException {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 11; i++) {
            sb.append("1 2 3 4 5");
            if (i < 11)
                sb.append("\n");
        }

        gridParser.parse(new BufferedReader(new StringReader(sb.toString())));
    }

    @Test(expected = GridParserException.class)
    public void test_GridParser_throws_exception_if_given_non_integer_data() throws GridParserException {

        gridParser.parse(new BufferedReader(new StringReader("1 2 3 4 5 pie")));

    }

    @Test(expected = GridParserException.class)
    public void test_GridParser_throws_exception_if_two_rows_have_a_different_number_of_columns() throws GridParserException {

        gridParser.parse(new BufferedReader(new StringReader("1 2 3 4 5 6\n1 2 3 4 5")));

    }

    @Test
    public void test_GridParser_does_not_return_a_null_row_of_data_when_valid_data_is_given() throws GridParserException {

        Assert.assertNotNull(gridParser.parse(new BufferedReader(new StringReader("1 2 3 4 5")))[0]);

    }

    @Test
    public void test_GridParser_returns_a_valid_single_row_5_column_integer_2d_array() throws GridParserException {

        int[][] parsedArrayToTest = gridParser.parse(new BufferedReader(new StringReader("1 2 3 4 5")));
        int[][] arrayToTestAgainst = new int[][]{{1, 2, 3, 4, 5}};

        Assert.assertTrue(Arrays.deepEquals(parsedArrayToTest, arrayToTestAgainst));

    }

    @Test
    public void test_GridParser_returns_a_valid_10_row_100_column_integer_2d_array() throws GridParserException {

        StringBuffer sb = new StringBuffer();

        int[][] arrayToTestAgainst = new int[10][100];

        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 100; column++) {
                arrayToTestAgainst[row][column] = column;
                sb.append(column);
                if (column <= 98)
                    sb.append(" ");
            }
            if (row <= 8)
                sb.append("\n");
        }

        int[][] parsedArrayToTest = gridParser.parse(new BufferedReader(new StringReader(sb.toString())));
        Assert.assertTrue(Arrays.deepEquals(parsedArrayToTest, arrayToTestAgainst));

    }
    
    @Test
    public void test_GridParser_parses_negative_integers() throws GridParserException {

        Assert.assertTrue(Arrays.equals(gridParser.parse(new BufferedReader(new StringReader("-1 -2 -3 -4 -5")))[0],
                new int[]{-1,-2,-3,-4,-5}));

    }


}
