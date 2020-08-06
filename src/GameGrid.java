/**
 * 
 * @author Luiz Augusto Dias 2018429
 *
 */

/**
 * Board of the game.
 *
 */
public class GameGrid {

    // Game board
    final String[][] grid;
    // Game grid size
    final int gridSize;

    String winner = null;

    // Constructor that defines de board size.
    public GameGrid(int gridSize) {
        this.gridSize = gridSize;
        grid = new String[gridSize][gridSize];

        // init the board with blank Strings
        initBoardMoves();
    }

    /**
     * Retrieves the Winner (Player or Computer)
     */
    public String getWinner() {
        return winner;
    }

    /**
     * Return if the player or computer won the match
     */
    public boolean hasWinner() {
        String result = WinnerChecker.hasWinner(grid);
        winner = result;
        return result != null && result != "";
    }

    /**
     * Check if the board still has available moves
     */
    public boolean hasAvailableMove() {

        // Iterate over the grid checking if the row and column has empty move.
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] == null || grid[i][j] == "") {
                    return true;
                }
            }
        }
        return false;
    }

    // Set the player move.
    public boolean setPlayerMove(int column) {

    	// check if the row and column is empty on the board
    	int row = stackUp(column);
    	// It's stackable
        if (row > -1) {
            grid[row][column] = "P";
            return true;
        }

        return false;
    }

    // Set the computer move.
    public boolean setComputerMove(int column) {
    	
    	// check if the row and column is empty on the board
    	int row = stackUp(column);
    	// It's stackable
        if (row > -1) {
            grid[row][column] = "C";
            return true;
        }

        return false;
    }
    
    // Returns a valid row. Otherwise -1
    private int stackUp(int column) {
    	
    	for (int row = gridSize-1; row >= 0; row--) {
    		if (isValidMove(row, column))
    			return row;
		}
    	return -1;
    }


    // Print the board on the console
    public void printBoard() {

        // Define the first line as empty
        System.out.print("  ");
        // Fill the first line with the number of the column starting on 1 till the board size,
        for (int i = 1; i <= gridSize; i++) {
            System.out.print(i + "   ");
        }

        // Print the board
        for (int row = 0; row < gridSize; row++) {

            System.out.println("");
            System.out.println(repeatString("-", gridSize * 4));

            for (int column = 0; column < gridSize; column++) {

                if (grid[row][column] == "") {
                    System.out.print("| " + " " + " ");
                } else {
                    System.out.print("| " + grid[row][column] + " ");
                }

            }

            // Print the board column
            System.out.print("| ");
        }
        System.out.println("");
        System.out.println(repeatString("-", gridSize * 4));
    }


    // Repeat the string as much as necessary
    private String repeatString(String value, int times) {
        String repeated = "";
        for(int i = 0; i <= times; i++) {
            repeated += value;
        }
        return repeated;
    }

    // init the board moves with empty strings
    private void initBoardMoves() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = "";
            }
        }
    }

    private boolean isValidMove(int x, int y) {
        String currentPosition = grid[x][y];
        if (currentPosition == null || currentPosition == "") {
            return true;
        }
        return false;
    }
}