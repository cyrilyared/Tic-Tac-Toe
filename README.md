# Tic-Tac-Toe

A simple version of the game Tic-Tac-Toe implemented in Java.
Currently allows person vs. person, person vs. random, random vs. random, person vs. minimax, minimax vs. random, and minimax vs. minimax.

It will eventually serve as a basis for experiments involving machine learning algorithms.

* **TicTacToe** Class: Provides a simple UI for interacting with the board.
* **Board** Class: Provides all the necessary methods for setting and getting the state of the board.
* **RandomPlayer** Class: Provides a simple framework for generating a random player.
* **Minimax** Class: Creates a player which obtains moves using a variation of the minimax algorithm that considers depth. This algorithm is further optimized with alpha-beta pruning.
