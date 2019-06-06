package test;

import logic.controller.ManagmentGames;

public class TestGame {

	public static void main(String[] args) {
		ManagmentGames managmentGames = new ManagmentGames();
		System.out.println("------------------------------------------------------------------");
		managmentGames.getLuckiestPlayer();
		System.out.println("------------------------------------------------------------------");
		managmentGames.getMostExperiencedPlayer();
		System.out.println("------------------------------------------------------------------");
		managmentGames.teamWithMostVictoriesByEscenary();
		System.out.println("------------------------------------------------------------------");
		managmentGames.teamWithMostVictories();
		System.out.println("------------------------------------------------------------------");
		managmentGames.getGenderWithMostWinsByEscenary();
		System.out.println("------------------------------------------------------------------");
		managmentGames.getGenderWithMostWins();
	}

}
