import java.util.Random;
import java.util.Scanner;

/**
 * Initializes game and controls input and output.
 * 
 * @author cyrilyared
 *
 */
public class TicTacToe {
	
	public static void main(String[] args) {
		Scanner userInput;
		userInput = new Scanner(System.in);
		
		while(userOptions(userInput)) {
			int playerSetting = playerSetting(userInput);
			playGame(userInput, playerSetting);
		}
	}
	
	/**
	 * Prints user options to initialize a new game.
	 * Reads user input.
	 * 
	 * @param userInput scanner that reads console input stream
	 * @return boolean based on user input
	 */
	private static boolean userOptions(Scanner userInput) {
		System.out.println("Would you like to play a new game (y/n)?");
		int newGameFormatted = yesNoOption(userInput.nextLine());
		
		while(newGameFormatted == -1) {
			System.out.println("Enter either 'y' or 'n'.");
			newGameFormatted = yesNoOption(userInput.nextLine());
		}
			
		if(newGameFormatted == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Prints player settings and reads user input.
	 * 
	 * @param userInput scanner that reads console input stream
	 * @return playerSetting selected
	 */
	private static int playerSetting(Scanner userInput) {
		System.out.println("Who are the players?");
		System.out.println("1 for Human vs. Human, 2 for Human vs. Random, 3 for Random vs. Random");
		System.out.println("4 for Human vs. Minimax, 5 for Minimax vs. Random, 6 for Minimax vs. Minimax");
		int playerSetting = numberFormat(userInput.nextLine(), 1, 6);
		
		while(playerSetting == -1) {
			System.out.println("1 for Human vs. Human, 2 for Human vs. Random, 3 for Random vs. Random");
			System.out.println("4 for Human vs. Minimax, 5 for Minimax vs. Random, 6 for Minimax vs. Minimax");
			playerSetting = numberFormat(userInput.nextLine(), 1, 6);
		}
		return playerSetting;
	}
	
	/**
	 * Creates an object of the class Board.
	 * Prints user option and allows move selection.
	 * Reads user input and ensures correct format.
	 * Sets the new board values.
	 * Prints the current state and switches users.
	 * Checks for any potential wins or draws.
	 * 
	 * @param userInput scanner that reads console input stream
	 * @param playerSetting setting selected for user type
	 */
	private static void playGame(Scanner userInput, int playerSetting) {
		Board currentBoard = new Board();
		Random randomGen = new Random();
		RandomPlayer randomPlayer = new RandomPlayer();
		int currentPlayer = randomNumberInRange(1, 2, randomGen);
		int winner;
		
		while(currentBoard.isPlaying()) {
			int move = 0;
			
			switch(playerSetting) {
				case 1:
					move = playerMove(userInput, currentBoard, currentPlayer);
					break;
				case 2:
					if(currentPlayer == 1) {
						move = playerMove(userInput, currentBoard, currentPlayer);
					} else {
						move = randomPlayer.getMove(currentBoard);
						System.out.println("Random " + String.valueOf(currentPlayer) + " Move: ");
					}
					break;
				case 3:
					move = randomPlayer.getMove(currentBoard);
					System.out.println("Random " + String.valueOf(currentPlayer) + " Move: ");
					break;
				case 4:
					if(currentPlayer == 1) {
						move = playerMove(userInput, currentBoard, currentPlayer);
					} else {
						Minimax minimaxPlayer = new Minimax(currentPlayer);
						move = minimaxPlayer.getMove(currentBoard);
						System.out.println("Minimax " + String.valueOf(currentPlayer) + " Move: ");
					}
					break;
				case 5:
					if(currentPlayer == 1) {
						Minimax minimaxPlayer = new Minimax(currentPlayer);
						move = minimaxPlayer.getMove(currentBoard);
						System.out.println("Minimax " + String.valueOf(currentPlayer) + " Move: ");
					} else {
						move = randomPlayer.getMove(currentBoard);
						System.out.println("Random " + String.valueOf(currentPlayer) + " Move: ");
					}
					break;
				case 6:
					Minimax minimaxPlayer = new Minimax(currentPlayer);
					move = minimaxPlayer.getMove(currentBoard);
					System.out.println("Minimax " + String.valueOf(currentPlayer) + " Move: ");
					break;
				default:
					break;
			}
			
			currentBoard.setBoardValue(currentPlayer, move);
			currentBoard.printBoard();
			currentPlayer = currentBoard.switchPlayer(currentPlayer);
		}
		winner = currentBoard.checkWin();
		
		if(winner == 0) {
			System.out.println("No player has won."); 
		} else {
			System.out.println("Player " +  String.valueOf(winner) + " has won.");
		}	
	}
	
	/**
	 * Reads user input to select move.
	 * Verifies move available.
	 * 
	 * @param userInput scanner that reads console input stream
	 * @param currentBoard instance of class Board
	 * @param currentPlayer player selecting move
	 * @return selected move
	 */
	private static int playerMove(Scanner userInput, Board currentBoard, int currentPlayer) {
		System.out.println("Player " + String.valueOf(currentPlayer) + " Move (1-9): ");
		int move = numberFormat(userInput.nextLine(), 1, 9);
		
		while(move == -1 || currentBoard.isPosEmpty(move) == false) {
			System.out.println("Enter a value between 1 and 9 with an empty slot.");
			move = numberFormat(userInput.nextLine(), 1, 9);
		}
		return move;
	}
	
	/**
	 * Returns integer from user input string.
	 * 
	 * @param input string input from the user
	 * @param min minimum integer accepted
	 * @param max maximum integer accepted
	 * @return integer formatted result
	 */
	private static int numberFormat(String input, int min, int max) {
		int result;
		try {
			result = Integer.parseInt(input);
		} catch(NumberFormatException e) {
			return -1;
		}
		
		if(result >= min && result <= max) {
			return result;
		}
		return -1;
	}
	
	/**
	 * Returns 1 if yes selected, 0 if no selected or -1 if formatting error.
	 * 
	 * @param input string input from user
	 * @return integer result
	 */
	private static int yesNoOption(String input) {
		if(input.toUpperCase().equals("Y")) {
			return 1;
		} else if(input.toUpperCase().equals("N")) {
			return 0;
		} else {
			return -1;
		}
	}
	
	/**
	 * Returns a random integer in a specified range.
	 * 
	 * @param min minimum of range
	 * @param max maximum of range
	 * @param randomGen instance of class random
	 * @return random integer in range
	 */
	public static int randomNumberInRange(int min, int max, Random randomGen) {
		return randomGen.nextInt(max - min + 1) + min;
	}
}