# Tic-Tac-Toe

A simple version of the game Tic-Tac-Toe implemented in Java.
Currently allows person vs. person, person vs. random, random vs. random, person vs. minimax, minimax vs. random, and minimax vs. minimax.

It will eventually serve as a basis for experiments involving machine learning algorithms. It has the ability to print training data for a machine learning algorithm. By selecting `n` to `Would you like to play a new game (y/n)?`, the user has the option to print training data. The training data is *appended* to the filename selected if the file currently exists. Otherwise, it creates a new file. I would recommend saving the file as a `.txt` file or `.csv` file.

The training data is formatted as follows. Each row is a new training example with comma-delimited data. The first nine numbers in each row represent the current board configuration (X<sup>(i)</sup>). The last number represents the move that player one selected after analyzing the current board configuration (Y<sup>(i)</sup>). I would recommend selecting `Minimax vs. Random` as the players to generate training examples quickly. 

Eventually, I plan on building a back propogation engine in Python to train the neural network and then adding a new class to the Java project, which uses the trained weights and biases in forward propogation to make a decision. At first, I will train the neural network to emulate the minimax algorithm. Later, I plan on using reinforcement learning to train the neural network.

* **TicTacToe** Class: Provides a simple UI for interacting with the board.
* **Board** Class: Provides all the necessary methods for setting and getting the state of the board.
* **RandomPlayer** Class: Provides a simple framework for generating a random player.
* **Minimax** Class: Creates a player which obtains moves using a variation of the minimax algorithm that considers depth. This algorithm is further optimized with alpha-beta pruning.
