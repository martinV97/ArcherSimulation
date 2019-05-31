package logic.entities;

import java.util.Random;

public class Player {
	private char gender;
	private int stamina = 0;
	private int roundStamina = 0;
	private int precision = 0;
	private int experience = 10;
	private float lucky = 0;
	private double roundDistance = 0;
	private Random random;
	
	public Player() {
		this.random = new Random();
		selectGender();
		generateHabilities();
	}


	private void selectGender() {
		if(this.random.nextInt(2) == 0)
			this.gender = 'M';
		else
			this.gender = 'F';
	}
	
	private void generateHabilities() {
		setResistence();
		setPrecision();
		this.experience = this.random.nextInt(10) + 1;
		this.lucky = this.random.nextFloat() + this.random.nextInt(5);		
	}


	private void setResistence() {
		if(this.gender == 'M')
			this.stamina = 50 + this.random.nextInt(5);
		else
			this.stamina = 50 - this.random.nextInt(5);	
	}


	private void setPrecision() {
		if(this.gender == 'F')
			this.precision = 50 + this.random.nextInt(5);
		else
			this.precision = 50 - this.random.nextInt(5);
	}
	
	public void shootArrow(boolean isLucky) {
		double shootDistance  = 0;
		if (isLucky) {
			shootDistance = (Math.pow(setArrowVelocity(), 2) * Math.sin(2 * setAngleShoot()) / 9.8);
			if((this.experience / 8) >= 1) {
				shootDistance += shootDistance * ((this.experience / 8) * 0.025);
			}
			this.roundDistance += shootDistance;
		}
		else {
			while (this.roundStamina >= 4) {
				this.roundStamina -= 4;
				shootDistance = (Math.pow(setArrowVelocity(), 2) * Math.sin(2 * setAngleShoot()) / 9.8);
				if((this.experience / 8) >= 1) {
					shootDistance += shootDistance * ((this.experience / 8) * 0.025);
				}
				this.roundDistance += shootDistance;
			}
		}
	}

	private double setArrowVelocity() {
		return 100 + this.random.nextInt(11);
	}
	
	private int setAngleShoot() {
		if (this.gender == 'F')
			return 30 + this.random.nextInt(5);
		else
			return 30 - this.random.nextInt(5);
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
		return lucky;
	}

	public void setLucky(float lucky) {
		this.lucky = lucky;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
}