package MainGame.IngredientGame;

import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

import javax.swing.ImageIcon;

import MainGame.DisplayGame.Screen;

public class Enemy extends Rectangle {
	private int up = 0, down = 1, right = 2, left = 3;
	private int enemyHealth;
	private int direction = right;
	private int xC,yC;
	private int enemySize = 32;
	private int enemyWalk = 0;
	private int enemyID = Value.enemyAir;
	private boolean inGame = false;
	private boolean hasSpawn = false;
	private boolean hasUp = false;
	private boolean hasDown = false;
	private boolean hasRight = false;
	private boolean hasLeft = false;
	public static Image[] tileset_enemies= new Image[5];
	public static Image[] tileset_enemies2= new Image[5];
	public static Image[] tileset_enemies3= new Image[5];
	public static Image[] tileset_enemies4= new Image[5];
	public Enemy() {

		if (Screen.condition == 1) {
			for (int i = 0; i < tileset_enemies.length; i++) {
				tileset_enemies[i] = new ImageIcon("res/enemy/enemy.gif").getImage();
				tileset_enemies[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));
				tileset_enemies2[i] = new ImageIcon("res/enemy/enemy1.gif").getImage();
				tileset_enemies2[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies2[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));
				tileset_enemies3[i] = new ImageIcon("res/enemy/enemy.gif").getImage();
				tileset_enemies3[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies3[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));
				tileset_enemies4[i] = new ImageIcon("res/enemy/enemy.gif").getImage();
				tileset_enemies4[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies4[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));

			}

		} else if (Screen.condition == 2) {
			for (int i = 0; i < tileset_enemies.length; i++) {
				tileset_enemies[i] = new ImageIcon("res/enemy/enemymap2phai.gif").getImage();
				tileset_enemies[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));
				tileset_enemies2[i] = new ImageIcon("res/enemy/enemymap2trai.gif").getImage();
				tileset_enemies2[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies2[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));
				tileset_enemies3[i] = new ImageIcon("res/enemy/enemymap2len.gif").getImage();
				tileset_enemies3[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies3[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));
				tileset_enemies4[i] = new ImageIcon("res/enemy/enemymap2xuong.gif").getImage();
				tileset_enemies4[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies4[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));

			}
		} else if (Screen.condition == 3) {
			for (int i = 0; i < tileset_enemies.length; i++) {
				tileset_enemies[i] = new ImageIcon("res/enemy/enemymap3phai.gif").getImage();
				tileset_enemies[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));
				tileset_enemies2[i] = new ImageIcon("res/enemy/enemymap3trai.gif").getImage();
				tileset_enemies2[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies2[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));
				tileset_enemies3[i] = new ImageIcon("res/enemy/enemymap3len.gif").getImage();
				tileset_enemies3[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies3[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));
				tileset_enemies4[i] = new ImageIcon("res/enemy/enemymap3xuong.gif").getImage();
				tileset_enemies4[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies4[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));
			}
		} else if (Screen.condition == 4) {
			for (int i = 0; i < tileset_enemies.length; i++) {
				tileset_enemies[i] = new ImageIcon("res/enemy/enemymap4phai.gif").getImage();
				tileset_enemies[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));
				tileset_enemies2[i] = new ImageIcon("res/enemy/enemymap4trai.gif").getImage();
				tileset_enemies2[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies2[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));
				tileset_enemies3[i] = new ImageIcon("res/enemy/enemymap4len.gif").getImage();
				tileset_enemies3[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies3[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));
				tileset_enemies4[i] = new ImageIcon("res/enemy/enemymap4xuong.gif").getImage();
				tileset_enemies4[i] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(tileset_enemies4[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));
			}
		}
	}

	public void spawnEnemies(int enemyID){
		for (int y = 0; y < Screen.room.block.length; y++)
			if (Screen.room.block[y][0].getGroundID() == Value.groundRoad) {
				setBounds(Screen.room.block[y][0].x, Screen.room.block[y][0].y, enemySize, enemySize);
				xC = 0;
				yC = y;
			}
		setInGame(true);
		this.enemyID = enemyID;
		this.setEnemyHealth(Value.enemyHealth[enemyID]);
		hasSpawn = true;
		direction = right;
		enemyWalk = 0;
		walkFrame = 0;
		walkSpeed = Value.enemySpeed[enemyID];
	}
	private int walkFrame, walkSpeed;


	public void render() {
		if (walkFrame>=getWalkSpeed()) {
			if (direction == right) {
				x+=1;
			}else {
				if (direction == up) {
					y-=1;
				}else {
					if (direction == down) {
						y+=1;
					}else {
						if (direction == left) {
							x-=1;
						}
					}
				}
			}
			enemyWalk += 1;
			if (enemyWalk == Screen.room.blockSize) {
				if (direction == right) {
					xC+=1;
					hasRight = true;
				}else {
					if (direction == up) {
						yC-=1;
						hasUp = true;
					}else {
						if (direction == down) {
							yC+=1;
							hasDown = true;
						}else {
							if (direction == left) {
								xC-=1;
								hasLeft = true;
							}
						}
					}
				}
				if (!hasUp) {
					try {
						if (Screen.room.block[yC+1][xC].getGroundID() == Value.groundRoad) {
							direction = down;
						}
					}catch(Exception e) {}
				}
				if (!hasDown) {
					try {
						if (Screen.room.block[yC-1][xC].getGroundID() == Value.groundRoad) {
							direction = up;
						}
					}catch(Exception e) {}
				}
				if (!hasRight) {
					try {
						if (Screen.room.block[yC][xC-1].getGroundID() == Value.groundRoad) {
							direction = left;
						}
					}catch(Exception e) {}
				}
				if (!hasLeft) {
					try {
						if (Screen.room.block[yC][xC+1].getGroundID() == Value.groundRoad) {
							direction = right;
						}
					}catch(Exception e) {}
				}
				if (Screen.room.block[yC][xC].getAirID() == Value.airEnd) {
					deleteEnemy();
					looseHealth();
				}
				hasUp = false;
				hasDown = false;
				hasLeft = false;
				hasRight = false;
				enemyWalk = 0;
			}
			walkFrame = 0;
		}else walkFrame+=1;

	}

	private void deleteEnemy() {
		Screen.enemyWave -= 1;
		inGame = false;
	}
	private void looseHealth() {
		Screen.health--;

	}
	public void draw (Graphics g) {
		if (isInGame()&&enemyHealth>0) {
			if(direction == left) {
				g.drawImage(tileset_enemies2[enemyID], x, y, width, height, null);
			}else if(direction == right) {
				g.drawImage(tileset_enemies[enemyID], x, y, width, height, null);
			}else if(direction== up){
				g.drawImage(tileset_enemies3[enemyID], x, y, width, height, null);
			}else{
				g.drawImage(tileset_enemies4[enemyID], x, y, width, height, null);
			}
			g.setColor(new Color(180,width,50));
			g.fillRect(x, y-9, 32, 6);
			g.setColor(new Color(50,180,50));
			g.fillRect(x, y-9, enemyHealth*32 /Value.enemyHealth[enemyID], 6);
			g.setColor(new Color(0,0,0));
			g.drawRect(x, y-9, 31, 5);
		}
	}
	public boolean isInGame() {
		return inGame;
	}
	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}
	public int getEnemyHealth() {
		return enemyHealth;
	}
	public void setEnemyHealth(int enemyHealth) {
		this.enemyHealth = enemyHealth;
	}
	public void loseEnemyHealth(int x) {
		enemyHealth -= x;
		checkDeath();
	}
	private void checkDeath() {
		if (enemyHealth <= 0) {
			deleteEnemy();
			Screen.money += Value.enemyPrice[enemyID];
		}

	}
	public int getWalkSpeed() {
		return walkSpeed;
	}
	public void setWalkSpeed(int walkSpeed) {
		this.walkSpeed = walkSpeed;
	}
	public boolean isHasSpawn() {

		return hasSpawn;
	}


}