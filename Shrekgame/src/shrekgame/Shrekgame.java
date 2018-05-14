package shrekgame;

import processing.core.PApplet;
import java.util.List;
import java.util.ArrayList;

public class Shrekgame extends PApplet {
	
	public int startTime;
	public int timer;
	
	public int score = 0;
	
	public List<Enemy> enemyList = new ArrayList<Enemy>();
	
	public float speed = 7;
	public float stoneSpeed = 14;
	
	public float x = 500;
	public float y = 500;
	
	public boolean moving = false;
	
	public boolean goLeft = false;
	public boolean goRight = false;
	
	public boolean shootStone = false;

	public void setup() {
		size(800, 700);
		startTime = millis();
	}

	public void draw() {
		timer = millis() - startTime;
		
		background(255, 100, 0);
		
		fill(0, 255, 0);
		rect(0, 600, 800, 200);
		
		textSize(32);
		fill(0, 102, 153);
		text("Score: " + score, 620, 650);
		
		fill(0, 102, 153);
		text("Protect the Swamp!", 10, 650);
		
		drawEnemy();
		drawShrek();
		drawStone();
		stoneMove();
		move();
		translate(x, y);
		
		if(timer > 500) {
			enemyList.add(new Enemy());
			startTime = millis();
			if(score < 100) {
			for(Enemy i : enemyList) {
				i.enemyY += 10;
			}
			}
			else if(score > 100) {
				for(Enemy i : enemyList) {
					i.enemyY += 12;
			}
			}
			else if(score > 300) {
				for(Enemy i : enemyList) {
					i.enemyY += 15;
			}
			}
		}
		
		for(Enemy i : enemyList) {
			 if(stoneX < i.enemyX + 40 && stoneY < i.enemyY + 40 && stoneX > i.enemyX && stoneY > i.enemyY) {
				 if(!(i.enemyY > 560) ) {
				 score++;
				i.enemyY = -1000000;   
				i.enemyX = -1000000;
				 }
			 }
		}
		for(Enemy i : enemyList) {
			if(i.enemyY > 700) {
				drawLose();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
			}
		}
		}
		
        
		
	
	
	public int shrekX = 400;
	public int shrekY = 650;
	
	public int stoneX = shrekX;
	public int stoneY = shrekY - 40;
	
	public void drawShrek() {
		fill(0, 100, 0);
		ellipse(shrekX, shrekY, 70, 70);
		fill(0, 100, 0);
		rect(shrekX + 22, shrekY - 45, 10, 30);
		fill(0, 100, 0);
		rect(shrekX - 30, shrekY - 45, 10, 30);
		fill(0, 0, 0);
		
		
	}
	
	public void drawEnemy() {
		for(Enemy i : enemyList) {
			fill(0, 0, 0);
			rect(i.enemyX, i.enemyY, 40, 40);
		}
	}
	
	public void drawStone() {
		fill(100, 100, 100);
		ellipse(stoneX, stoneY, 20, 20);
	}
	
	public void drawLose() {
		textSize(64);
		fill(0, 102, 153);
		text("The enemies got you! Check your score!", 400, 350);
	}
	
	public void keyPressed() 
    {
        if(key == 'd') 
        {
            goLeft = true;
        } 
        if(key == 'a') 
        {
            goRight = true;
        } 
     }
	public void keyReleased() 
    {                                                         
        if(key == 'd') 
        {
            goLeft = false;
        } 
        if(key == 'a') 
        {
            goRight = false;
        }
        if(key == ' ') {
        	shootStone = true;
        }
}
	public void move() {
		if(goLeft) {
		shrekX += speed;
		}
		if(goRight) {
			shrekX -= speed;
			}
	}
	
	public void stoneMove() {
		if(shootStone) {
			stoneY -= stoneSpeed;
		}
		else {
			stoneY = shrekY - 40;
			stoneX = shrekX + 20; 
		}
		if(stoneY < 0) {
			shootStone = false;
		}
		
	}
}
