package logic.controller;

import java.util.ArrayList;

import logic.entities.Game;
import logic.entities.Player;

public class ManagmentGames {
	private ArrayList<Game> listOfGames = new ArrayList<>();
	private ArrayList<Game> listOfGamesWind = new ArrayList<>();
	private ArrayList<Game> listOfGamesRain = new ArrayList<>();
	private ArrayList<Game> listOfGamesPerfect = new ArrayList<>();
	
	public ManagmentGames() {
		startGames();
		sortListOfGames();
	}

	private void startGames() {
		for (int i = 0; i < 500; i++) {
			this.listOfGames.add(new Game());
		}
	}
	
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
	
	public void averageLuckyByGame() {
		for (int i = 0; i < listOfGames.size(); i++) {
			System.out.println("El promedio de suerte ganado en el juego " + (i +1) + " fue: " + listOfGames.get(i).getAverageEarnedLucky());
		}
	}
	
	public void averageExperienceByGame() {
		for (int i = 0; i < listOfGames.size(); i++) {
			System.out.println("El promedio de experiencia ganado en el juego " + (i +1) + " fue: " + listOfGames.get(i).getAverageEarnedEXperience());
		}
	}
	
	public void getLuckiestPlayer() {
		System.out.println("El jugador con más suerte en el escenario lluvioso: " + getMostLuckiestPlayerByEscenary(listOfGamesRain).getName());
		System.out.println("El jugador con más suerte en el escenario vientoso: " + getMostLuckiestPlayerByEscenary(listOfGamesWind).getName());
		System.out.println("El jugador con más suerte en el escenario perfecto: " + getMostLuckiestPlayerByEscenary(listOfGamesPerfect).getName());
	}
	
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
	
	public void getMostExperiencedPlayer() {
		System.out.println("El jugador con más experiencia en el escenario lluvioso: " + getMostExperiencedPlayerByEscenary(listOfGamesRain).getName());
		System.out.println("El jugador con más experiencia en el escenario vientoso: " + getMostExperiencedPlayerByEscenary(listOfGamesWind).getName());
		System.out.println("El jugador con más experiencia en el escenario perfecto: " + getMostExperiencedPlayerByEscenary(listOfGamesPerfect).getName());
	}
	
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
	
	public void teamWithMostVictoriesByEscenary() {
		System.out.println("Equipo con más victorias totales en cada uno de los escenarios: ");
		System.out.println("El equipo con más victorias en el escenario lluvioso: " + counWinnerByScenary(listOfGamesRain));
		System.out.println("El equipo con más victorias en el escenario vientoso: " + counWinnerByScenary(listOfGamesWind));
		System.out.println("El equipo con más victorias en el escenario perfecto: " + counWinnerByScenary(listOfGamesPerfect));
	}
	
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
	
	public void getGenderWithMostWinsByEscenary() {
		System.out.println("El genero con más victorias en el escenario lluvioso: " + countWinnerByGender(listOfGamesRain));
		System.out.println("El genero con más victorias en el escenario vientoso: " + countWinnerByGender(listOfGamesWind));
		System.out.println("El genero con más victorias en el escenario perfecto: " + countWinnerByGender(listOfGamesPerfect));
	}
	
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
	
	public void getGenderWithMostWins() {
		int tmpManCount = 0, tmpFemaleCount = 0;
		for (Game game : listOfGames) {
			if(game.getWinnerByGenderInGame() == 'M')
				tmpManCount++;
			else
				tmpFemaleCount++;
		}
		if(tmpManCount >= tmpFemaleCount)
			System.out.println("Genero con más victorias totales por juego: Hombres con: " + tmpManCount + " victorias.");
		else
			System.out.println("Genero con más victorias totales por juego: Mujeres con: " + tmpFemaleCount + " victorias.");
	}
}
