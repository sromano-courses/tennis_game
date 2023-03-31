package it.sromano.tennisgame;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The player class represents a player.
 * 
 */
public class Player {
	
	private static final Map<Integer, String> scoreMapping;
	
	static{
		Map<Integer, String> map = new HashMap<>();
		map = new HashMap<>(); 
		map.put(0, "love");
		map.put(1, "fifteen");
		map.put(2, "thirty");
		map.put(3, "forty");
		scoreMapping = Collections.unmodifiableMap(map);
	}
	
	private String name;
	private int score;
	
	/**
	 * Creates a player with a given name and score.
	 * @param name
	 * @param score
	 */
	public Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	/**
	 * Returns the name of this player.
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the score of this player.
	 * @return the score.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Increments the score of this player by one unit.
	 */
	public void incrementScore(){
		score++;
	}
	
	/**
	 * Translates the score of this player into a string that is peculiar to tennis.
	 * That is, 0 is translated into "love", 1 is translated into "fifteen", 2 is
	 * translated into "thirty", and 3 is translated into "forty".
	 * 
	 * @return the translation of the score as a string (or null if there is no translation).
	 */
	public String getScoreAsString(){
		return scoreMapping.get(score);		
	}
	
	/**
	 * Returns if there is a tie between this player and the opponent. 
	 * @param opponent
	 * @return true if there is a tie.
	 */
	public boolean isTieWith(Player opponent) {
		if(score==opponent.getScore()){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns if this player has at least "forty" points.
	 * @return true if this player has at least "forty" points.
	 */
	public boolean hasAtLeastFortyPoints() {
		if(score>=3){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns if this player has less than "forty" points.
	 * @return true if this player has less than "forty" points.
	 */
	public boolean hasLessThanFortyPoints() {
		if(score<3){
			return true;
		}
		return false;	
	}
	
	/**
	 * Returns if this player has more than "forty" points.
	 * @return true if this player has more than "forty" points.
	 */
	public boolean hasMoreThanFourtyPoints() {
		if(score>3){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns if this player has one point of advantage on the opponent.
	 * @param opponent
	 * @return true if this player has one point of advantage.
	 */
	public boolean hasOnePointAdvantageOn(Player opponent) {
		if(score-opponent.getScore()==1){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns if this player has at least two points of advantage on the opponent.
	 * @param opponent
	 * @return true if this player has at least two points of advantage.
	 */
	public boolean hasAtLeastTwoPointsAdvantageOn(Player opponent) {
		if(score-opponent.getScore()>=2){
			return true;
		}
		return false;
	}

}
