package it.sromano.tennisgame;

/**
 * The Game class represents a tennis game. This class should be used as
 * follows:
 * 
 * // Create a new game: 
 * Game game = new Game("name1", "name2");
 *
 * // Get the name of the two players: 
 * String playerName1 = game.getPlayerName1(); 
 * String playerName2 = game.getPlayerName2();
 *
 * // Update the game by incrementing the score of either the first or second
 * player: 
 * game.incrementPlayerScore(playerName1);
 * game.incrementPlayerScore(playerName2);
 *
 * // Get the status of the game: 
 * String status = game.getGameStatus();
 * 
 * See the README.md file to understand the rules behind the game status returned by tennis_game
 * 
 */
public class Game {

	private static final String ADVANTAGE = "Advantage";
	private static final String DEUCE = "Deuce";
	private static final String SPACE = " ";
	private static final String DASH = "-";
	private static final String WINS = "wins";

	private Player player1;
	private Player player2;
	private String gameStatus;

	/**
	 * Creates a game given the names of two players. At the beginning the two
	 * players have a score equal to zero.
	 * 
	 * @param playerName1
	 * @param playerName2
	 * @throws DuplicatedPlayerException if the two players have the same name.
	 */
	public Game(String playerName1, String playerName2) throws DuplicatedPlayerException {
		if (playerName1.equals(playerName2)) {
			throw new DuplicatedPlayerException();
		}
		player1 = new Player(playerName1, 0);
		player2 = new Player(playerName2, 0);
		updateGameStatus();
	}

	/**
	 * Returns the name of the first player.
	 * 
	 * @return the name.
	 */
	public String getPlayerName1() {
		return player1.getName();
	}

	/**
	 * Returns the name of the second player.
	 * 
	 * @return the name.
	 */
	public String getPlayerName2() {
		return player2.getName();
	}

	/**
	 * Increments the score of a given player. Once the score has been incremented,
	 * this method updates the current status of this game.
	 * 
	 * @param playerName The name of the player whose score should be incremented.
	 * @throws GameHasAlreadyBeWonException if there is already a winner for this
	 *                                      game.
	 */
	public void incrementPlayerScore(String playerName) throws GameHasAlreadyBeWonException {
		if (!isThereAWinner()) {
			if (playerName.equals(getPlayerName1())) {
				player1.incrementScore();
			} else if (playerName.equals(getPlayerName2())) {
				player2.incrementScore();
			}
			updateGameStatus();
		} else {
			throw new GameHasAlreadyBeWonException();
		}
	}

	/**
	 * Returns the current status of this game.
	 * 
	 * @return the status of this game (e.g., "Federer fifteen - Nadal love")
	 */
	public String getGameStatus() {
		return this.gameStatus;
	}

	private void updateGameStatus() {
		StringBuilder result = new StringBuilder();
		if (isDeuce()) {
			result.append(DEUCE);
			this.gameStatus = result.toString();
		} else if (isThereAnAdvantagePlayer()) {
			result.append(ADVANTAGE).append(SPACE).append(advantagePlayer().getName());
			this.gameStatus = result.toString();
		} else if (isThereAWinner()) {
			result.append(theWinner().getName()).append(SPACE).append(WINS);
			this.gameStatus = result.toString();
		} else {
			result.append(player1.getName()).append(SPACE).append(player1.getScoreAsString());
			result.append(SPACE).append(DASH).append(SPACE);
			result.append(player2.getName()).append(SPACE).append(player2.getScoreAsString());
			this.gameStatus = result.toString();
		}
	}

	private boolean isThereAnAdvantagePlayer() {
		return advantagePlayer() != null;
	}

	private Player advantagePlayer() {
		Player result = null;
		if (player2.hasAtLeastFortyPoints() && player1.hasAtLeastFortyPoints()) {
			if (player1.hasOnePointAdvantageOn(player2))
				result = player1;
			else if (player2.hasOnePointAdvantageOn(player1))
				result = player2;
		}
		return result;
	}

	private boolean isDeuce() {
		return player1.isTieWith(player2) && player1.hasAtLeastFortyPoints();
	}

	private boolean isThereAWinner() {
		return theWinner() != null;
	}

	private Player theWinner() {
		Player theWinner = null;
		if (player1.hasMoreThanFourtyPoints() && player1.hasAtLeastTwoPointsAdvantageOn(player2))
			theWinner = player1;
		else if (player2.hasMoreThanFourtyPoints() && player2.hasAtLeastTwoPointsAdvantageOn(player1))
			theWinner = player2;
		return theWinner;
	}

}
