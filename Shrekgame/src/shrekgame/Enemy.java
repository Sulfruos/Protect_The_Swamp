package shrekgame;

import java.util.Random;

public class Enemy {
	
	Random generator = new Random();
	
	public Enemy() {
		enemyX = generator.nextInt(780) + 1;
		enemyY = 0;
	}
	
	public int enemyX;
	public int enemyY;
	

}
