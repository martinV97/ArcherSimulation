package logic.entities;

import java.util.ArrayList;

public class Round {
	private String winnerRound;
	private ArrayList<Player> teamOne;
	private ArrayList<Player> teamTwo;
	private Player tmpPlayerTeamOne;
	private Player tmpPlayerTeamTwo;
	private Player tmpLuckyPlayerTeamOne;
	private Player tmpLuckyPlayerTeamTwo;
	private double teamOneDistance = 0;
	private double teamTwoDistance = 0;
	private int earnedExperience = 0;
	private float earnedLucky = 0;
	private int countLuckyStreakTeamOne = 1;
	private int countLuckyStreakTeamTwo = 1;
	
	public Round(ArrayList<Player> teamOne, ArrayList<Player> teamTwo ) {
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
		setTeamLucky();
		startShooting();
	}
	
	private void setTeamLucky() {
		for (int i = 0; i < 20; i++) {
			this.teamOne.get(i).setRoundLucky();
			this.teamTwo.get(i).setRoundLucky();
		}		
	}

	private void startShooting() {
		for (int i = 0; i < 20; i++) {
			this.teamOne.get(i).setRoundStamina(this.teamOne.get(i).getStamina());
			this.teamOne.get(i).shootArrow(false);
			this.teamTwo.get(i).setRoundStamina(this.teamTwo.get(i).getStamina());
			this.teamTwo.get(i).shootArrow(false);
		}
	}
	
	public void luckyShot() {
		findLuckiest();
		this.tmpPlayerTeamOne.shootArrow(true);
		this.tmpPlayerTeamOne.shootArrow(true);
		validateLuckyStreak();
		setNullTmpPlayers();
	}

	private void findLuckiest() {
		this.tmpPlayerTeamOne = this.teamOne.get(0);
		this.tmpPlayerTeamTwo = this.teamTwo.get(0);
		
		for (int i = 1; i < 20; i++) {
			if(this.tmpPlayerTeamOne.getLucky() < this.teamOne.get(i).getLucky())
				this.tmpPlayerTeamOne = this.teamOne.get(i);
			if(this.tmpPlayerTeamTwo.getLucky() < this.teamTwo.get(i).getLucky())
				this.tmpPlayerTeamTwo = this.teamTwo.get(i);
		}
	}
	
	private void validateLuckyStreak() {
		if(this.tmpLuckyPlayerTeamOne == null) {
			this.tmpLuckyPlayerTeamOne = this.tmpPlayerTeamOne;
			this.tmpLuckyPlayerTeamTwo = this.tmpPlayerTeamTwo;
		}else {
			luckyStreakTeamOne();
			luckyStreakTeamTwo();
			setPosibleLucky();
		}
	}

	private void luckyStreakTeamOne() {
		if(this.tmpLuckyPlayerTeamOne == this.tmpPlayerTeamOne)
			this.countLuckyStreakTeamOne ++;
		else {
			this.tmpLuckyPlayerTeamOne = this.tmpPlayerTeamOne;
			this.countLuckyStreakTeamTwo = 1;
		}
	}
	
	private void luckyStreakTeamTwo() {
		if(this.tmpLuckyPlayerTeamTwo == this.tmpPlayerTeamTwo)
			this.countLuckyStreakTeamOne ++;
		else {
			this.tmpLuckyPlayerTeamTwo = this.tmpPlayerTeamTwo;
			this.countLuckyStreakTeamTwo = 1;
		}
	}
	
	private void setPosibleLucky() {
		if(this.countLuckyStreakTeamOne == 3) {
			this.tmpLuckyPlayerTeamOne.setEarnedLucky((float) 0.05);
			this.tmpLuckyPlayerTeamOne = null;
			this.earnedLucky += 0.05;
		}
		if(this.countLuckyStreakTeamTwo == 3) {
			this.tmpLuckyPlayerTeamTwo.setEarnedLucky((float) 0.05);
			this.tmpLuckyPlayerTeamTwo = null;
			this.earnedLucky += 0.05;
		}
			
	}
	
	public void teamWinner() {
		calculateTeamDistance();
		if(this.teamOneDistance >= this.teamTwoDistance) {
			this.winnerRound = "Equipo 1";
			setTeamExperience(this.teamOne);
		}
		else {
			this.winnerRound = "Equipo 2";
			setTeamExperience(this.teamTwo);
		}
	}

	private void setTeamExperience(ArrayList<Player> team) {
		for (Player player : team) {
			player.setExperience(player.getExperience() + 2);
			this.earnedExperience += 2;
		}
	}

	private void calculateTeamDistance() {
		for (int i = 0; i < 20; i++) {
			this.teamOneDistance += this.teamOne.get(i).getRoundDistance();
			this.teamTwoDistance += this.teamTwo.get(i).getRoundDistance();
		}
	}
	
	public void soloWinner() {
		findLongestSoloDistance();
		this.tmpPlayerTeamOne.setExperience(this.tmpPlayerTeamOne.getExperience() + 2);
		this.tmpPlayerTeamTwo.setExperience(this.tmpPlayerTeamTwo.getExperience() + 2);
		this.earnedExperience += 4;
		setNullTmpPlayers();
	}

	private void findLongestSoloDistance() {
		this.tmpPlayerTeamOne = this.teamOne.get(0);
		this.tmpPlayerTeamTwo = this.teamTwo.get(0);
		for (int i = 0; i < 20; i++) {
			if(this.tmpPlayerTeamOne.getRoundDistance() < this.teamOne.get(i).getRoundDistance())
				this.tmpPlayerTeamOne = this.teamOne.get(i);
			if(this.tmpPlayerTeamTwo.getRoundDistance() < this.teamTwo.get(i).getRoundDistance())
				this.tmpPlayerTeamTwo = this.teamTwo.get(i);
		}
	}
	

	private void setNullTmpPlayers() {
		this.tmpPlayerTeamOne = null;
		this.tmpPlayerTeamTwo = null;
	}

	public char getVictoriesByGender(ArrayList<Player> team) {
		int maleCount = 0, femaleCount = 0;
		for (int i = 0; i < 20; i++) {
			if(team.get(i).getGender() == 'M')
				maleCount++;
			else
				femaleCount++;
				
		}
		if(maleCount >= femaleCount)
			return 'M';
		else
			return 'F';
	}
	
	public String getWinnerRound() {
		return winnerRound;
	}
	
	public Player getMostExperiencedPlayer() {
		Player tmp = this.teamOne.get(0);
		for (int i = 0; i < 20; i++) {
			if(tmp.getExperience() < this.teamOne.get(i).getExperience())
				tmp = this.teamOne.get(i);
			if(tmp.getExperience() < this.teamTwo.get(i).getExperience())
				tmp = this.teamTwo.get(i);
		}
		return tmp;
	}
	
	public Player getLuckiestPlayer() {
		Player tmp = this.teamOne.get(0);
		for (int i = 0; i < 20; i++) {
			if(tmp.getLucky() < this.teamOne.get(i).getLucky())
				tmp = this.teamOne.get(i);
			if(tmp.getLucky() < this.teamTwo.get(i).getLucky())
				tmp = this.teamTwo.get(i);
		}
		return tmp;
	}


	public void setWinnerRound(String winnerRound) {
		this.winnerRound = winnerRound;
	}

	public float getEarnedLucky() {
		return earnedLucky;
	}

	public int getEarnedExperience() {
		return earnedExperience;
	}

	public Player getTmpLuckyPlayerTeamOne() {
		return tmpLuckyPlayerTeamOne;
	}

	public void setTmpLuckyPlayerTeamOne(Player tmpLuckyPlayerTeamOne) {
		this.tmpLuckyPlayerTeamOne = tmpLuckyPlayerTeamOne;
	}

	public Player getTmpLuckyPlayerTeamTwo() {
		return tmpLuckyPlayerTeamTwo;
	}

	public void setTmpLuckyPlayerTeamTwo(Player tmpLuckyPlayerTeamTwo) {
		this.tmpLuckyPlayerTeamTwo = tmpLuckyPlayerTeamTwo;
	}
}