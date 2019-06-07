package logic.controller;

import java.util.ArrayList;

import logic.entities.Game;
import logic.entities.Player;

/**
 * 
 * @author Martin
 *Clase GameMangement, donde se encuentran los atributos necesarios,
 *para generar el reporte de la simulaci�n de 500 partidas de arqueros diferentes.
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
	 * M�todo usado para iniciar la simulaci�n de 500 partidas de arqueros diferentes.
	 */
	private void startGames() {
		for (int i = 0; i < 500; i++) {
			this.listOfGames.add(new Game());
		}
	}
	
	/**
	 * M�todo usado para ordenar las partidas de acuerdo 
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
	 * M�todo usado para mostrar el promedio de suerte
	 * ganado en cada partida.
	 */
	public void averageLuckyByGame() {
		for (int i = 0; i < listOfGames.size(); i++) {
			System.out.println("El promedio de suerte ganado en el juego " + (i +1) + " fue: " + listOfGames.get(i).getAverageEarnedLucky());
		}
	}
	
	/**
	 * M�todo usado para mostrar el promedio de experiencia
	 * ganado en cada partida.
	 */
	public void averageExperienceByGame() {
		for (int i = 0; i < listOfGames.size(); i++) {
			System.out.println("El promedio de experiencia ganado en el juego " + (i +1) + " fue: " + listOfGames.get(i).getAverageEarnedEXperience());
		}
	}
	
	/**
	 * M�todo usado para mostrar a los jugadores con m�s suerte
	 * en cada escenario de juego.
	 */
	public void getLuckiestPlayer() {
		System.out.println("El jugador con m�s suerte en el escenario lluvioso: " + getMostLuckiestPlayerByEscenary(listOfGamesRain).getName());
		System.out.println("El jugador con m�s suerte en el escenario vientoso: " + getMostLuckiestPlayerByEscenary(listOfGamesWind).getName());
		System.out.println("El jugador con m�s suerte en el escenario perfecto: " + getMostLuckiestPlayerByEscenary(listOfGamesPerfect).getName());
	}
	
	/**
	 * M�todo usado para encontrar el jugador con m�s
	 * suerte por escenario, entre una lista de juegos simulados.
	 * @param listOfGames Lista de juegos simulados.
	 * @return Jugador con m�s suerte.
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
	 * M�todo usado para mostrar los jugadores con m�s experiencia
	 * en cada escenario de juego.
	 */
	public void getMostExperiencedPlayer() {
		System.out.println("El jugador con m�s experiencia en el escenario lluvioso: " + getMostExperiencedPlayerByEscenary(listOfGamesRain).getName());
		System.out.println("El jugador con m�s experiencia en el escenario vientoso: " + getMostExperiencedPlayerByEscenary(listOfGamesWind).getName());
		System.out.println("El jugador con m�s experiencia en el escenario perfecto: " + getMostExperiencedPlayerByEscenary(listOfGamesPerfect).getName());
	}
	
	/**
	 * M�todo usado para encontrar el jugador con m�s
	 * experiencia por escenario, entre una lista de juegos simulados.
	 * @param listOfGames Lista de juegos simulados.
	 * @return Jugador con m�s experiencia.
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
	 * M�todo usado para al equipo con m�s victorias
	 * en cada uno de los escenarios de juego.
	 */
	public void teamWithMostVictoriesByEscenary() {
		System.out.println("Equipo con m�s victorias totales en cada uno de los escenarios: ");
		System.out.println("El equipo con m�s victorias en el escenario lluvioso: " + counWinnerByScenary(listOfGamesRain));
		System.out.println("El equipo con m�s victorias en el escenario vientoso: " + counWinnerByScenary(listOfGamesWind));
		System.out.println("El equipo con m�s victorias en el escenario perfecto: " + counWinnerByScenary(listOfGamesPerfect));
	}
	
	/**
	 * M�todo usado para encontrar el equipo con m�s
	 * victorias por escenario, entre una lista de juegos simulados.
	 * @param listOfGames Lista de juegos simulados
	 * @return Equipo con m�s victorias.
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
	 * M�todo usado para mostrar el equipo con m�s victorias
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
			System.out.println("Equipo con m�s victorias totales: Equipo 1");
		else
			System.out.println("Equipo con m�s victorias totales: Equipo 2");
	}
	
	/**
	 * M�todo usado para mostrar el g�nero con m�s
	 * victorias por escenario.
	 */
	public void getGenderWithMostWinsByEscenary() {
		System.out.println("El g�nero con m�s victorias en el escenario lluvioso: " + countWinnerByGender(listOfGamesRain));
		System.out.println("El g�nero con m�s victorias en el escenario vientoso: " + countWinnerByGender(listOfGamesWind));
		System.out.println("El g�nero con m�s victorias en el escenario perfecto: " + countWinnerByGender(listOfGamesPerfect));
	}
	
	/**
	 * M�todo usado para contar las victorias por g�nero a partir
	 * de una lista de juegos simulados.
	 * @param listOfGames Lista de juegos simulados
	 * @return G�nero con m�s victorias.
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
	 * M�todo usado para mostrar el gen�ro con m�s victorias
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
			System.out.println("G�nero con m�s victorias totales por juego: Hombres con: " + tmpManCount + " victorias.");
		else
			System.out.println("G�nero con m�s victorias totales por juego: Mujeres con: " + tmpFemaleCount + " victorias.");
	}
}
