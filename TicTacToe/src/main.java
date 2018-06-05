import java.util.Random;
import java.util.Scanner;

/**
 * Initializes game and controls input and output.
 * 
 * @author cyrilyared
 *
 */
public class main {
	
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
		int newGameFormatted = newGameFormat(userInput.nextLine());
		while(newGameFormatted == -1) {
			System.out.println("Enter either 'y' or 'n'.");
			newGameFormatted = newGameFormat(userInput.nextLine());
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
		int playerSetting = numberFormat(userInput.nextLine(), 1, 3);
		while(playerSetting == -1) {
			System.out.println("1 for Human vs. Human, 2 for Human vs. Random, 3 for Random vs. Random");
			playerSetting = numberFormat(userInput.nextLine(), 1, 3);
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
	 */
	private static void playGame(Scanner userInput, int playerSetting) {
		Board currentBoard = new Board();
		Random randomGen = new Random();
		int currentPlayer = randomNumberInRange(1, 2, randomGen);
		int winner;
		RandomPlayer randomPlayer = new RandomPlayer();
		
		
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
						System.out.println("Random Move: ");
					}
					break;
				case 3:
					move = randomPlayer.getMove(currentBoard);
					System.out.println("Random " + String.valueOf(currentPlayer) + " Move: ");
					break;
				default:
					break;
			}
			
			currentBoard.setBoardValue(currentPlayer, move);
			printBoard(currentBoard);
			
			if(currentPlayer == 1) {
				currentPlayer = 2;
			} else {
				currentPlayer = 1;
			}
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
	 * Prints the current board configuration to the console output.
	 * 
	 * @param board instance of class Board
	 */
	private static void printBoard(Board board) {
		int value;
		
		for(int i = 0; i < 3; i++) {
			System.out.print("| ");
			
			for (int j = 0; j < 3; j++) {
				value = board.getPositionValue(i*3+j);
				
				switch(value) {
					case 1:
						System.out.print("X ");
						break;
					case 2:
						System.out.print("O ");
						break;
					default:
						System.out.print("  ");
						break;
				}
			}
			System.out.println("|");
		}
	}
	
	/**
	 * Returns formatted user input string for position on board.
	 * 
	 * @param input string input from the user
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
	 * Returns 1 if user wants new game, 0 if user does not want new game or -1 if formatting error.
	 * 
	 * @param input string input from user
	 * @return integer formatted result
	 */
	private static int newGameFormat(String input) {
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
