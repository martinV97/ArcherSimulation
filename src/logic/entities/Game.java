package logic.entities;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	private ArrayList<Player> teamOne;
	private ArrayList<Player> teamTwo;
	private ArrayList<Round> roundList;
	private boolean isPlaying;
	private int victoryTeamOneCount = 0;
	private int victoryTeamTwoCount = 0;
	private char weather;
	private String winnerGame;
	private int earnedExperienceInGame = 0;
	private float earnedLuckyInGame = 0;
	private Player tmpLuckyPlayerTeamOne;
	private Player tmpLuckyPlayerTeamTwo;
	
	public Game() {
		this.isPlaying = true;
		setWeather();
		startTeams();
		startGame();
	}
	
	private void setWeather() {
		switch (new Random().nextInt(3)) {
		case 0:
			this.weather = 'V';
			break;
		case 1:
			this.weather = 'L';
			break;
		case 2:
			this.weather = 'P';
			break;
		default:
			break;
		}
	}
	
	private void startTeams() {
		this.teamOne = new ArrayList<>();
		this.teamTwo = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			this.teamOne.add(new Player("Jugador de equipo 1, número: " + (i + 1)));
			this.teamTwo.add(new Player("Jugador de equipo 2, número: " + (i + 1)));
		}
	}

	private void startGame() {
		this.roundList = new ArrayList<>();
		int count = 0;
		while (this.isPlaying) {
			this.roundList.add(new Round(this.teamOne, this.teamTwo));
			this.roundList.get(count).setTmpLuckyPlayerTeamOne(this.tmpLuckyPlayerTeamOne);
			this.roundList.get(count).setTmpLuckyPlayerTeamTwo(this.tmpLuckyPlayerTeamTwo);
			this.roundList.get(count).luckyShot();
			this.roundList.get(count).teamWinner();
			this.roundList.get(count).soloWinner();
			this.tmpLuckyPlayerTeamOne = this.roundList.get(count).getTmpLuckyPlayerTeamOne();
			this.tmpLuckyPlayerTeamTwo = this.roundList.get(count).getTmpLuckyPlayerTeamTwo();
			this.earnedExperienceInGame += this.roundList.get(count).getEarnedExperience();
			this.earnedLuckyInGame += this.roundList.get(count).getEarnedLucky();
			countRoundVictory(this.roundList.get(count));
			validateEndGame();
			count++;
		}
	}
	
	private void countRoundVictory(Round round) {
		if(round.getWinnerRound().equals("Equipo 1"))
			this.victoryTeamOneCount++;
		else
			this.victoryTeamTwoCount++;
	}

	private void validateEndGame() {
		if(this.victoryTeamOneCount == 10) {
			this.isPlaying = false;
			this.winnerGame = "Equipo 1";
		}
		if (this.victoryTeamTwoCount == 10) {
			this.isPlaying = false;
			this.winnerGame = "Equipo 2";
		}
	}
	
	public char getWinnerByGenderInGame() {
		int maleCount = 0, femaleCount = 0;
		for (Round round : roundList) {
			if(round.getWinnerRound().equals("Equipo 1"))
				if(round.getVictoriesByGender(this.teamOne) == 'M')
					maleCount++;
				else
					femaleCount++;
			else
				if(round.getVictoriesByGender(this.teamTwo) == 'M')
					maleCount++;
				else
					femaleCount++;
		}
		if(maleCount >= femaleCount)
			return 'M';
		else
			return 'F';
	}
	
	public Player getMostExperiencedPlayer() {
		Player tmp = roundList.get(0).getMostExperiencedPlayer();
		for (Round round : roundList) {
			if(tmp.getExperience() < round.getMostExperiencedPlayer().getExperience())
				tmp = round.getMostExperiencedPlayer();
		}
		return tmp;
	}
	
	public Player getLuckiestPlayer() {
		Player tmp = roundList.get(0).getLuckiestPlayer();
		for (Round round : roundList) {
			if(tmp.getLucky() < round.getLuckiestPlayer().getLucky())
				tmp = round.getLuckiestPlayer();
		}
		return tmp;
	}
	
	public float getAverageEarnedLucky() {
		return this.earnedLuckyInGame / this.roundList.size();
	}
	
	public double getAverageEarnedEXperience() {
		return this.earnedExperienceInGame / this.roundList.size();
	}
	
	public char getWeather() {
		return weather;
	}

	public String getWinnerGame() {
		return winnerGame;
	}	
	
}
