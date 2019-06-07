package ArcherSimulation;

import logic.controller.ManagementGames;

/**
 * Clase principal donde se ejecutan la simulación
 * y se muestran los reportes de dicha simulación.
 * @author Martin
 *
 */
public class ArcherSimulation {

	public static void main(String[] args) {
		ManagementGames managmentGames = new ManagementGames();
		System.out.println("------------------------------------------------------------------");
		managmentGames.averageLuckyByGame();
		System.out.println("------------------------------------------------------------------");
		managmentGames.averageExperienceByGame();
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
