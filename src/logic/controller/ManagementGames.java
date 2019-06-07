package logic.controller;

import java.util.ArrayList;

import logic.entities.Game;
import logic.entities.Player;

/**
 * 
 * @author Martin
 *Clase GameMangement, donde se encuentran los atributos necesarios,
 *para generar el reporte de la simulación de 500 partidas de arqueros diferentes.
 */
public class ManagementGames {
	private ArrayList<Game> listOfGames = new ArrayList<>();
	private ArrayList<Game> listOfGamesWind = new ArrayList<>();
	private ArrayList<Game> listOfGamesRain = new ArrayList<>();
	private ArrayList<Game> listOfGamesPerfect = new ArrayList<>();
	
	/**
	 * Constructor de la clase managementGames.
	 */
	public ManagementGames() {
		startGames();
		sortListOfGames();
	}

	/**
	 * Método usado para iniciar la simulación de 500 partidas de arqueros diferentes.
	 */
	private void startGames() {
		for (int i = 0; i < 500; i++) {
			this.listOfGames.add(new Game());
		}
	}
	
	/**
	 * Método usado para ordenar las partidas de acuerdo 
	 * al parametro clima generado en cada juego.
	 */
	private void sortListOfGames() {
		for (Game game : this.listOfGames) {
			switch (game.getWeather()) {
			case 'V':
				this.listOfGamesWind.add(game);
				break;
			case 'L':
				this.listOfGamesRain.add(game);
				break;
			case 'P':
				this.listOfGamesPerfect.add(game);
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * Método usado para mostrar el promedio de suerte
	 * ganado en cada partida.
	 */
	public void averageLuckyByGame() {
		for (int i = 0; i < listOfGames.size(); i++) {
			System.out.println("El promedio de suerte ganado en el juego " + (i +1) + " fue: " + listOfGames.get(i).getAverageEarnedLucky());
		}
	}
	
	/**
	 * Método usado para mostrar el promedio de experiencia
	 * ganado en cada partida.
	 */
	public void averageExperienceByGame() {
		for (int i = 0; i < listOfGames.size(); i++) {
			System.out.println("El promedio de experiencia ganado en el juego " + (i +1) + " fue: " + listOfGames.get(i).getAverageEarnedEXperience());
		}
	}
	
	/**
	 * Método usado para mostrar a los jugadores con más suerte
	 * en cada escenario de juego.
	 */
	public void getLuckiestPlayer() {
		System.out.println("El jugador con más suerte en el escenario lluvioso: " + getMostLuckiestPlayerByEscenary(listOfGamesRain).getName());
		System.out.println("El jugador con más suerte en el escenario vientoso: " + getMostLuckiestPlayerByEscenary(listOfGamesWind).getName());
		System.out.println("El jugador con más suerte en el escenario perfecto: " + getMostLuckiestPlayerByEscenary(listOfGamesPerfect).getName());
	}
	
	/**
	 * Método usado para encontrar el jugador con más
	 * suerte por escenario, entre una lista de juegos simulados.
	 * @param listOfGames Lista de juegos simulados.
	 * @return Jugador con más suerte.
	 */
	private Player getMostLuckiestPlayerByEscenary(ArrayList<Game> listOfGames) {
		if(listOfGames.size() > 0) {			
			Player tmp = listOfGames.get(0).getLuckiestPlayer();
			for (Game game : listOfGames) {
				if(tmp.getLucky() < game.getLuckiestPlayer().getLucky());
					tmp = game.getLuckiestPlayer();
			}
			return tmp;
		}else
			return null;		
	}
	
	/**
	 * Método usado para mostrar los jugadores con más experiencia
	 * en cada escenario de juego.
	 */
	public void getMostExperiencedPlayer() {
		System.out.println("El jugador con más experiencia en el escenario lluvioso: " + getMostExperiencedPlayerByEscenary(listOfGamesRain).getName());
		System.out.println("El jugador con más experiencia en el escenario vientoso: " + getMostExperiencedPlayerByEscenary(listOfGamesWind).getName());
		System.out.println("El jugador con más experiencia en el escenario perfecto: " + getMostExperiencedPlayerByEscenary(listOfGamesPerfect).getName());
	}
	
	/**
	 * Método usado para encontrar el jugador con más
	 * experiencia por escenario, entre una lista de juegos simulados.
	 * @param listOfGames Lista de juegos simulados.
	 * @return Jugador con más experiencia.
	 */
	private Player getMostExperiencedPlayerByEscenary(ArrayList<Game> listOfGames) {
		if(listOfGames.size() > 0) {			
			Player tmp = listOfGames.get(0).getMostExperiencedPlayer();
			for (Game game : listOfGames) {
				if(tmp.getExperience() < game.getMostExperiencedPlayer().getExperience())
					tmp = game.getMostExperiencedPlayer();
			}
			return tmp;
		}else
			return null;		
	}
	
	/**
	 * Método usado para al equipo con más victorias
	 * en cada uno de los escenarios de juego.
	 */
	public void teamWithMostVictoriesByEscenary() {
		System.out.println("Equipo con más victorias totales en cada uno de los escenarios: ");
		System.out.println("El equipo con más victorias en el escenario lluvioso: " + counWinnerByScenary(listOfGamesRain));
		System.out.println("El equipo con más victorias en el escenario vientoso: " + counWinnerByScenary(listOfGamesWind));
		System.out.println("El equipo con más victorias en el escenario perfecto: " + counWinnerByScenary(listOfGamesPerfect));
	}
	
	/**
	 * Método usado para encontrar el equipo con más
	 * victorias por escenario, entre una lista de juegos simulados.
	 * @param listOfGames Lista de juegos simulados
	 * @return Equipo con más victorias.
	 */
	private String counWinnerByScenary(ArrayList<Game> listOfGames) {
		int countTeamOne = 0, countTeamTwo = 0;
		for (Game game : listOfGames) {
			if (game.getWinnerGame().equals("Equipo 1"))
				countTeamOne++;
			else
				countTeamTwo++;
				
		}
		if(countTeamOne >= countTeamTwo)
			return "Equipo 1";
		else
			return "Equipo 2";
					
	}
	
	/**
	 * Método usado para mostrar el equipo con más victorias
	 * totales entre los 500 juegos simulados.
	 */
	public void teamWithMostVictories() {
		int teamOneCount = 0, teamTwoCount = 0;
		for (Game game : listOfGames) {
			if (game.getWinnerGame().equals("Equipo 1"))
				teamOneCount++;
			else
				teamTwoCount++;
		}
		if (teamOneCount >= teamTwoCount)
			System.out.println("Equipo con más victorias totales: Equipo 1");
		else
			System.out.println("Equipo con más victorias totales: Equipo 2");
	}
	
	/**
	 * Método usado para mostrar el género con más
	 * victorias por escenario.
	 */
	public void getGenderWithMostWinsByEscenary() {
		System.out.println("El género con más victorias en el escenario lluvioso: " + countWinnerByGender(listOfGamesRain));
		System.out.println("El género con más victorias en el escenario vientoso: " + countWinnerByGender(listOfGamesWind));
		System.out.println("El género con más victorias en el escenario perfecto: " + countWinnerByGender(listOfGamesPerfect));
	}
	
	/**
	 * Método usado para contar las victorias por género a partir
	 * de una lista de juegos simulados.
	 * @param listOfGames Lista de juegos simulados
	 * @return Género con más victorias.
	 */
	private char countWinnerByGender(ArrayList<Game> listOfGames) {
		int tmpManCount = 0, tmpFemaleCount = 0;
		for (Game game : listOfGames) {
			if(game.getWinnerByGenderInGame() == 'M')
				tmpManCount++;
			else
				tmpFemaleCount++;
		}
		if(tmpManCount >= tmpFemaleCount)
			return 'M';
		else
			return 'F';
	}
	
	/**
	 * Método usado para mostrar el genéro con más victorias
	 * totales entre los 500 juegos simulados.
	 */
	public void getGenderWithMostWins() {
		int tmpManCount = 0, tmpFemaleCount = 0;
		for (Game game : listOfGames) {
			if(game.getWinnerByGenderInGame() == 'M')
				tmpManCount++;
			else
				tmpFemaleCount++;
		}
		if(tmpManCount >= tmpFemaleCount)
			System.out.println("Género con más victorias totales por juego: Hombres con: " + tmpManCount + " victorias.");
		else
			System.out.println("Género con más victorias totales por juego: Mujeres con: " + tmpFemaleCount + " victorias.");
	}
}
