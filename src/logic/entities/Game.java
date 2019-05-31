package logic.entities;

import java.util.ArrayList;

public class Game {
	private ArrayList<Player> teamOne;
	private ArrayList<Player> teamTwo;
	private ArrayList<Round> roundList;
	private char weather;
	
	public Game() {
		setWeather();
		startTeams();
		startGame();
	}
	
	private void setWeather() {
		// TODO Auto-generated method stub
		
	}
	
	private void startTeams() {
		this.teamOne = new ArrayList<>();
		this.teamTwo = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			this.teamOne.add(new Player());
			this.teamTwo.add(new Player());
		}
	}

	private void startGame() {
		this.roundList = new ArrayList<>();
		for (int i = 0; i < 500; i++) {
			this.roundList.add(new Round(this.teamOne, this.teamTwo));
			this.roundList.get(i).luckyShot();
			this.roundList.get(i).teamWinner();
			System.out.println(this.roundList.get(i).getWinnerRound());
		}
	}

}
