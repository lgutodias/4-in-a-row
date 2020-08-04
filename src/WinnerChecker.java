/**
 * 
 * @author Luiz Augusto Dias 2018429
 *
 */

// Method to check the winner

public final class WinnerChecker {

    private WinnerChecker () {

    }
    
    //Checking rows to get a winner

    private static String checkRows(final String[][] matrix)
    {
        for (int row = 0; row < matrix.length; row++)
        {
            for (int col = 0; col < matrix[row].length - 3; col++)
            {
                String element = matrix[row][col];
                if (element == matrix[row][col + 1] &&
                        element == matrix[row][col + 2] &&
                        element == matrix[row][col + 3])
                {
                    return element;
                }
            }
        }

        return null;
    }
    
    //Checking columns to get a winner

    private static String checkColumns(String[][] matrix)
    {
        for (int row = 0; row < matrix.length - 3; row++)
        {
            for (int col = 0; col < matrix[row].length; col++)
            {
                String element = matrix[row][col];
                if (element == matrix[row + 1][col] &&
                        element == matrix[row + 2][col] &&
                        element == matrix[row + 3][col])
                {
                    return element;
                }
            }
        }
        return null;
    }
    
    //Checking diagonals to get a winner

    private static String checkDiagonalLeftToRight(String[][] matrix)
    {
        for (int row = 0; row < matrix.length - 3; row++)
        {
            for (int col = 0; col < matrix[row].length - 3; col++)
            {
                String element = matrix[row][col];
                if (element == matrix[row + 1][col + 1] &&
                        element == matrix[row + 2][col + 2] &&
                        element == matrix[row + 3][col + 3])
                {
                    return element;
                }
            }
        }
        return null;
    }

    private static String checkDiagonalRightToLeft(String[][] matrix)
    {
        for (int row = 0; row < matrix.length - 3; row++)
        {
            for (int col = 3; col < matrix[row].length; col++)
            {
                String element = matrix[row][col];
                if ( element == matrix[row + 1][col - 1] &&
                        element == matrix[row + 2][col - 2] &&
                        element == matrix[row + 3][col - 3])
                {
                    return element;
                }
            }
        }
        return null;
    }

    public static String hasWinner(String[][] matrix) {

        String result = checkRows(matrix);
        if( result != null && result != "") {
            return result;
        }

        result = checkColumns(matrix);
        if( result != null && result != "") {
            return result;
        }

        result = checkDiagonalLeftToRight(matrix);
        if( result != null && result != "") {
            return result;
        }

        result = checkDiagonalRightToLeft(matrix);
        if( result != null && result != "") {
            return result;
        }

        return null;
    }
}