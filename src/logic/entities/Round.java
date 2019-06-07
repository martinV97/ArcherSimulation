package logic.entities;

import java.util.ArrayList;
/**
 * @author Martin
 * Clase Round, donde se encuentran los atributos
 * de una ronda de juego, en la simulación del juegos de arqueros.
 */
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
	private char weather;
	
	/**
	 * Constructor de la clase Round
	 * @param teamOne Equipo uno, participante en la ronda
	 * @param teamTwo Equipo dos, participante en la ronda
	 * @param weather Parametro de clima usado para influenciar la ronda
	 */
	public Round(ArrayList<Player> teamOne, ArrayList<Player> teamTwo, char weather ) {
		this.weather = weather;
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
		setTeamLucky();
		startShooting();
	}
	
	/**
	 * Método usado para generar la suerte por ronda, de los jugadores en 
	 * los equipos de manera aleatoria.
	 */
	private void setTeamLucky() {
		for (int i = 0; i < 20; i++) {
			this.teamOne.get(i).setRoundLucky();
			this.teamTwo.get(i).setRoundLucky();
		}		
	}

	/**
	 * Método usado para comenzar la simulación de disparo,
	 * para cada uno de los equipos.
	 */
	private void startShooting() {
		for (int i = 0; i < 20; i++) {
			this.teamOne.get(i).setRoundStamina(this.teamOne.get(i).getStamina());
			this.teamOne.get(i).shootArrow(false, this.weather);
			this.teamTwo.get(i).setRoundStamina(this.teamTwo.get(i).getStamina());
			this.teamTwo.get(i).shootArrow(false, this.weather);
		}
	}
	
	/**
	 * Método usado para realizar un disparo por equipo,
	 * a partir de la suerte de los jugadores.
	 */
	public void luckyShot() {
		findLuckiest();
		this.tmpPlayerTeamOne.shootArrow(true, this.weather);
		this.tmpPlayerTeamOne.shootArrow(true, this.weather);
		validateLuckyStreak();
		setNullTmpPlayers();
	}

	/**
	 * Método usado para encontrar el
	 * jugador con más suerte por equipo.
	 */
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
	
	/**
	 * Método usado para validar una racha de suerte de un jugador,
	 * por cada equipo.
	 */
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

	/**
	 * Método usado para validar la racha de suerte de un jugdor,
	 * en el equipo uno.
	 */
	private void luckyStreakTeamOne() {
		if(this.tmpLuckyPlayerTeamOne == this.tmpPlayerTeamOne)
			this.countLuckyStreakTeamOne ++;
		else {
			this.tmpLuckyPlayerTeamOne = this.tmpPlayerTeamOne;
			this.countLuckyStreakTeamTwo = 1;
		}
	}
	
	/**
	 * Método usado para validar la racha de suerte de un jugador,
	 * en el equipo dos.
	 */
	private void luckyStreakTeamTwo() {
		if(this.tmpLuckyPlayerTeamTwo == this.tmpPlayerTeamTwo)
			this.countLuckyStreakTeamOne ++;
		else {
			this.tmpLuckyPlayerTeamTwo = this.tmpPlayerTeamTwo;
			this.countLuckyStreakTeamTwo = 1;
		}
	}
	
	/**
	 * Método usado para aumentar la suerte de un jugador dependiendo de
	 * una racha de suerte.
	 */
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
	
	/**
	 * Método usado para encontrar el equipo ganador de la ronda.
	 */
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
	
	/**
	 * Método usado para calcular la distancia total
	 * lograda por los disparos de un equipo, en una ronda.
	 */
	private void calculateTeamDistance() {
		for (int i = 0; i < 20; i++) {
			this.teamOneDistance += this.teamOne.get(i).getRoundDistance();
			this.teamTwoDistance += this.teamTwo.get(i).getRoundDistance();
		}
	}

	/**
	 * Método usado para aumentar la experiencia de un equipo
	 * dependiendo del equipo ganador de la ronda.
	 * @param team Equipo al que se le aumentará la experiencia dependiendo de la victoria de la ronda.
	 */
	private void setTeamExperience(ArrayList<Player> team) {
		for (Player player : team) {
			player.setExperience(player.getExperience() + 2);
			this.earnedExperience += 2;
		}
	}

	/**
	 * Método usado para encontrar el jugador con mas distancia por ronda
	 * en cada equipo y aumentar su experiencia.
	 */
	public void soloWinner() {
		findLongestSoloDistance();
		this.tmpPlayerTeamOne.setExperience(this.tmpPlayerTeamOne.getExperience() + 2);
		this.tmpPlayerTeamTwo.setExperience(this.tmpPlayerTeamTwo.getExperience() + 2);
		this.earnedExperience += 4;
		setNullTmpPlayers();
	}

	/**
	 * Método usado para encontrar el jugador con mas distancia por ronda
	 * en cada equipo.
	 */
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

	/**
	 * Método creado para obtener el genero con mas victorias en la ronda.
	 * @param team Equipo que se analizará para obtener el genero con más victorias.
	 * @return
	 */
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
	
	/**
	 * Método usado para encontrar el jugador con más experiencia en la ronda.
	 * @return Jugador con más experiencia en la ronda.
	 */
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
	
	/**
	 * Método usado para encontrar el jugador con más suerte en la ronda.
	 * @return Jugador con más suerte en la ronda.
	 */
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
	
	/**
	 * Método usado para reiniciar la distancia total de 
	 * disparo para cada uno de los jugadores.
	 */
	public void resetRoundDistanceOfPlayers() {
		for (int i = 0; i < 20; i++) {
			this.teamOne.get(i).setRoundDistance(0);
			this.teamTwo.get(i).setRoundDistance(0);
		}
	}
	
	private void setNullTmpPlayers() {
		this.tmpPlayerTeamOne = null;
		this.tmpPlayerTeamTwo = null;
	}

	public String getWinnerRound() {
		return winnerRound;
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