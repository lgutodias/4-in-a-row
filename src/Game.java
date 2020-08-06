/**
 * 
 * @author Luiz Augusto Dias 2018429
 *
 */

import java.util.Scanner;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

  // Regex to validate the email
  static Pattern pattern = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");

  // Scanner to retrieve data from Console
  static Scanner scanner = new Scanner(System.in);


  // Main method that executes the Game
  public static void main(String[] args) {

    // Fill the user inputs (name, email, age)
    Player player = fillPlayerInputs();

    // Boolean to define if the user will keep playing
    boolean keepPlaying = true;

    // loop to keep the user playing
    while (keepPlaying) {

      // get the gameType if it is 5x5 or 10x10 for Short or Long
      int boardSize = getGameType();


      // logic to execute the game based on the board size.
      GameGrid gameGrid = executeGame(boardSize);

      // Check if the user or the computer won the match
      if (gameGrid.getWinner() == "P") {
        System.out.println("The player " + player.getName() + " won the match");
        // Increment the score for the player
        player.incrementScore();
      } else {
        System.out.println("Computer won the game");
      }

      // Check if the user wants to keep playing based on the console input
      System.out.println("Please type 'yes' to play again. or 'no' to end the game.");
      String continuePlaying = scanner.next();
      if ("yes".contains(continuePlaying.toLowerCase())) {
        keepPlaying = true;
      } else {
        keepPlaying = false;
      }
    }
    System.out.println("Game has ended");
  }

  private static int getGameType() {
    System.out.println("Please enter SHORT for a grid 5x5 or LONG for a grid 10x10");
    String gameType = scanner.nextLine();

    if(gameType.toUpperCase().contains("SHORT")) {
      return 5;
    } else if (gameType.toUpperCase().contains("LONG")) {
      return 10;
    }

    System.out.println("Please enter a valid game type.");
    return getGameType();
  }

  private static GameGrid executeGame(int boardSize) {
    GameGrid gameGrid = new GameGrid(boardSize);
    gameGrid.printBoard();

    Random computer = new Random();
    boolean isPlayer = true;
    while (gameGrid.hasAvailableMove()) {

      if (isPlayer) {

        int col = getMove(boardSize);

        if (col > boardSize || col < 0) {
          System.out.println("Invalid move. Please enter a value between 1 and " + boardSize);
          continue;
        }

        boolean moveResult = gameGrid.setPlayerMove(col - 1);
        if (!moveResult) {
          continue;
        }
      } else {


        int column = computer.nextInt(boardSize);

        boolean moveResult = gameGrid.setComputerMove(column);
    	 
        if (!moveResult) {
          continue;
        }
      }

      gameGrid.printBoard();
      isPlayer = !isPlayer;

      if (gameGrid.hasWinner()) {
        break;
      }
    }
    return gameGrid;
  }

  private static int getMove(int boardSize) {

    try {
    	
    	System.out.println("Please select the COLUMN between 1 and " + boardSize);

    	return scanner.nextInt();
    } catch (Exception e) {
      return getMove(boardSize);
    }
  }

  private static Player fillPlayerInputs() {

    Player player = new Player();

    setPlayerName(player);

    setPlayerAge(player);

    setPlayerEmail(player);


    return player;
  }

  private static void setPlayerName(Player player) {
    System.out.println("Please enter your name");
    String name = scanner.nextLine();

    String[] fullName = name.split(" ");

    player.setName(fullName[0]);

    if(fullName.length > 1) {
      player.setSurname(fullName[1]);
    }
  }

  /**
   * Set player age from console
   */
  private static void setPlayerAge(Player player) {

    // Retrieving data from console.
    System.out.println("Please enter your age");
    String ageAsString = scanner.nextLine();

    try {
      int age = Integer.valueOf(ageAsString.trim());
      // validating if the age is between 12 and 100
      if (age < 12 || age > 100) {
        System.out.println("Please enter a valid age between 12 and 100 years old.");
        setPlayerAge(player);
        return;
      }

      player.setAge(age);

    } catch (Exception e) {
      // catching exception in case of user enter an invalid value for age
      System.out.println("Please enter a valid age between 12 and 100");
      setPlayerAge(player);
    }
  }

  /**
   * Set player email from console
   */
  private static void setPlayerEmail(Player  player ) {

    // Retrieving data from console
    System.out.println("Please enter your email");
    String email = scanner.nextLine();

    // Creating a matcher to validate the email
    Matcher matcher = pattern.matcher(email);

    // Check if the email matches the requirement using Regex
    boolean isValidEmail = matcher.matches();

    // While the email is not valid the user will need to keep entering the email.
    while (!isValidEmail) {
      System.out.println("Please enter a valid email");
      email = scanner.next();
      isValidEmail = pattern.matcher(email).matches();
    }
  }
}