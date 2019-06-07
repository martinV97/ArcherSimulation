package logic.entities;

import java.util.Random;

/**
 * @author Martin
 * Clase Player, donde se encuentran los atributos
 * de un jugador en la simulación del juegos de arqueros.
 */
public class Player {
	private String name;
	private int age;
	private char gender;
	private int stamina = 0;
	private int roundStamina = 0;
	private int precision = 0;
	private int experience = 10;
	private float earnedLucky = 0;
	private float roundLucky = 0;
	private double roundDistance = 0;
	private Random random;
	
	/**
	 * Constructor de la clase Player
	 * @param name Nombre asignado al jugador
	 */
	public Player(String name) {
		this.name = name;
		this.random = new Random();
		selectGender();
		generateHabilities();
	}

	/**
	 * Método que asigna el genero al jugador de manera aleatoria
	 */
	private void selectGender() {
		if(this.random.nextInt(2) == 0)
			this.gender = 'M';
		else
			this.gender = 'F';
	}
	
	/**
	 * Método que genera aleatoriamente,
	 * el valor de las habilidades al
	 * jugador.
	 */
	private void generateHabilities() {
		setResistence();
		setPrecision();
		this.experience = this.random.nextInt(10) + 1;
		this.age = this.random.nextInt(10) + 18; 
	}

	/**
	 * Método que genera la resistencia del juagdor
	 * de manera aleatoria, dependiendo del genero.
	 */
	private void setResistence() {
		if(this.gender == 'M')
			this.stamina = 50 + this.random.nextInt(5);
		else
			this.stamina = 50 - this.random.nextInt(5);	
	}

	/**
	 * Método que genera la precision del jugador
	 * de manera aleatoria dependiendo del genero.
	 */
	private void setPrecision() {
		if(this.gender == 'F')
			this.precision = 50 + this.random.nextInt(5);
		else
			this.precision = 50 - this.random.nextInt(5);
	}
	
	/**
	 * Método usado para simular el proceso de disparo,
	 * hasta que finalice la resistencia del jugador.
	 * @param isLucky PArametro usado para generar un disparo 
	 * independiendte de la resistencia del jugaodr
	 * @param weather Parametro usado para influenciar la 
	 * distancia del disparo.
	 */
	public void shootArrow(boolean isLucky, char weather) {
		double shootDistance  = 0;
		if (isLucky) {
			shootDistance = (Math.pow(setArrowVelocity(), 2) * Math.sin(2 * setAngleShoot()) / 9.8);
			if((this.experience / 8) >= 1) {
				shootDistance += shootDistance * ((this.experience / 8) * 0.025);
			}
			shootDistance = weatherInShoot(weather, shootDistance);
			this.roundDistance += shootDistance;
		}
		else {
			while (this.roundStamina >= 4) {
				this.roundStamina -= 4;
				shootDistance = (Math.pow(setArrowVelocity(), 2) * Math.sin(Math.toRadians(2 * setAngleShoot())) / 9.8);
				if((this.experience / 8) >= 1) {
					shootDistance += shootDistance * ((this.experience / 8) * 0.025);
				}
				shootDistance = weatherInShoot(weather, shootDistance);
				this.roundDistance += shootDistance;
			}
		}
	}
	
	/**
	 *Método utilizado para generar la velocidad de la flecha en el disparo 
	 * @return Valor de velocidad generado aleatoriamente
	 */
	private double setArrowVelocity() {
		return (100 + this.random.nextInt(11));
	}
	
	/**
	 * Método utilizado para generar el ángulo de disparo de la flecha.
	 * @return Ángulo de disparo generado aleatoriamente
	 */
	private int setAngleShoot() {
		if (this.gender == 'F')
			return (30 + this.random.nextInt(5));
		else
			return (30 - this.random.nextInt(5));
	}
	
	/**
	 * Método usado para disminuir la distancia del disparo
	 * dependiendo de los parametros entrantes.
	 * @param weather Parametro usado para laterar la distancia del disparo.
	 * @param shootDistance Distancia del disparo sin alterar.
	 * @return Distancia del disparo alterada.
	 */
	private double weatherInShoot(char weather, double shootDistance) {
		switch (weather) {
		case 'V':
			shootDistance -= shootDistance * 0.04;
			break;
		case 'L':
			shootDistance -= shootDistance * 0.08;
			break;
		default:
			break;
		}
		return shootDistance;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}


	public char getGender() {
		return gender;
	}

	public int getStamina() {
		return stamina;
	}

	public void setRoundStamina(int roundStamina) {
		this.roundStamina = roundStamina;
	}
	
	public double getRoundDistance() {
		return roundDistance;
	}

	public void setRoundDistance(double roundDistance) {
		this.roundDistance = roundDistance;
	}

	public float getLucky() {
		return roundLucky + earnedLucky;
	}

	public void setRoundLucky() {
		this.roundLucky = this.random.nextFloat() + this.random.nextInt(5);	
	}

	public void setEarnedLucky(float earnedLucky) {
		this.earnedLucky += earnedLucky;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
}