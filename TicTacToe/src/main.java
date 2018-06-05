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
		
		initializeUserOptions(userInput);
	}
	
	/**
	 * Prints user options to initialize a new game.
	 * Reads user input.
	 * 
	 * @param userInput scanner that reads console input stream
	 */
	private static void initializeUserOptions(Scanner userInput) {
		boolean playGame = true;
		
		while(playGame) {
			playGame(userInput);
			System.out.println("Would you like to play another game (y/n)?");
			int newGameFormatted = newGameFormat(userInput.nextLine());
			while(newGameFormatted == -1) {
				System.out.println("Enter either 'y' or 'n'.");
				newGameFormatted = newGameFormat(userInput.nextLine());
			}
			
			if(newGameFormatted == 0) {
				playGame = false;
			}	
		}
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
	private static void playGame(Scanner userInput) {
		Board currentBoard = new Board();
		int currentPlayer = 1;
		int winner;
		
		while(currentBoard.isPlaying()) {
			System.out.println("Player " + String.valueOf(currentPlayer) + " Move (1-9): ");
			int move = positionFormat(userInput.nextLine());
			
			while(move == 0 || currentBoard.setBoardValue(currentPlayer, move) == false) {
				System.out.println("Enter a value between 1 and 9 with an empty slot.");
				move = positionFormat(userInput.nextLine());
			}
			
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
	 * Prints the current board configuration to the console output.
	 * 
	 * @param board instance of the Board class
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
	private static int positionFormat(String input) {
		int result;
		try {
			result = Integer.parseInt(input);
		} catch(NumberFormatException e) {
			return 0;
		}
		
		if(result > 0 && result < 10) {
			return result;
		}
		return 0;
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
}
