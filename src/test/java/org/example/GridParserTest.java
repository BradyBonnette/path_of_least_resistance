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

    @Test
    public void test_GridParser_does_not_have_a_BufferedReader_by_default() {
        Assert.assertNull(gridParser.getReader());
    }

    @Test(expected = NullPointerException.class)
    public void test_GridParser_throws_exception_if_noArg_parse_is_called_without_setting_a_reader_first() {
        gridParser.parse();
    }
    
    @Test(expected = NullPointerException.class)
    public void test_GridParser_throws_exception_if_parse_is_given_a_null_reader() {
        gridParser.parse(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test_GridParser_throws_exception_if_given_an_empty_grid() {

        gridParser.parse(new BufferedReader(new StringReader("")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GridParser_throws_exception_if_given_1_row_and_4_columns() {

        gridParser.parse(new BufferedReader(new StringReader("1 2 3 4")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GridParser_throws_exception_if_given_1_row_and_101_columns() {

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 101; i++) {
            sb.append(i);
            if (i < 101)
                sb.append(" ");
        }

        gridParser.parse(new BufferedReader(new StringReader(sb.toString())));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GridParser_throws_exception_if_given_11_rows_and_1_column() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 11; i++) {
            sb.append(i);
            if (i < 11)
                sb.append("\n");
        }

        gridParser.parse(new BufferedReader(new StringReader(sb.toString())));

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GridParser_throws_exception_if_given_11_rows_and_5_columns() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 11; i++) {
            sb.append("1 2 3 4 5");
            if (i < 11)
                sb.append("\n");
        }

        gridParser.parse(new BufferedReader(new StringReader(sb.toString())));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GridParser_throws_exception_if_given_non_integer_data() {

        gridParser.parse(new BufferedReader(new StringReader("1 2 3 4 5 pie")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GridParser_throws_exception_if_two_rows_have_a_different_number_of_columns() {

        gridParser.parse(new BufferedReader(new StringReader("1 2 3 4 5 6\n1 2 3 4 5")));

    }

    @Test
    public void test_GridParser_does_not_return_a_null_array_when_reader_is_set_via_mutator() {
        
        GridParser parser = new GridParser();
        
        parser.setReader(new BufferedReader(new StringReader("1 2 3 4 5")));
        
        Assert.assertNotNull(parser.parse());
    }
    
    @Test
    public void test_GridParser_does_not_return_a_null_row_of_data_when_valid_data_is_given() {

        Assert.assertNotNull(gridParser.parse(new BufferedReader(new StringReader("1 2 3 4 5")))[0]);

    }

    @Test
    public void test_GridParser_returns_a_valid_single_row_5_column_integer_2d_array() {

        int[][] parsedArrayToTest = gridParser.parse(new BufferedReader(new StringReader("1 2 3 4 5")));
        int[][] arrayToTestAgainst = new int[][]{{1, 2, 3, 4, 5}};

        Assert.assertTrue(Arrays.deepEquals(parsedArrayToTest, arrayToTestAgainst));

    }

    @Test
    public void test_GridParser_returns_a_valid_10_row_100_column_integer_2d_array() {

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
    public void test_GridParser_parses_negative_integers() {

        Assert.assertTrue(Arrays.equals(gridParser.parse(new BufferedReader(new StringReader("-1 -2 -3 -4 -5")))[0],
                new int[]{-1,-2,-3,-4,-5}));

    }
}
