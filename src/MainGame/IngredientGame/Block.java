package MainGame.IngredientGame;

import MainGame.DisplayGame.Screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle{

	private int groundID;
	private int airID;
	private int towerSquareSize = 64;
	private boolean shooting = false;
	private int shootEnemy = -1;
	private Rectangle towerSquare;
	private int[] shootTime = {0,0,1000};
	public Block(int x, int y, int width, int height, int groundID,int  airID ) {
		setBounds(x, y, width, height);
		this.setGroundID(groundID);
		this.setAirID(airID);
	}
	public void draw(Graphics g) {
		g.drawImage(Screen.tileset_ground[getGroundID()],x,y,width,height,null);
		if(getAirID()!= Value.airAir) {
			g.drawImage(Screen.tileset_air[getAirID()],x,y,width,height,null);
		}
	}
	private int shootFrame = 1000;
	private int[] towerID = {0,1,2};
	public void render() {
		if (shootEnemy!=-1 && towerSquare.intersects(Screen.enemy[shootEnemy])) {
			shooting = true;
		}else {
			shooting = false;
		}
		if (!shooting) {
			for (int id=0; id<towerID.length; id++)
				if (airID == towerID[id]) {
					towerSquare = new Rectangle(x - (towerSquareSize/2), y - (towerSquareSize/2), width + towerSquareSize, height + towerSquareSize);
					for (int i=0; i<Screen.enemy.length; i++) {
						if (Screen.enemy[i].isInGame() && towerSquare != null) {
							if (towerSquare.intersects(Screen.enemy[i])) {
								if (shootFrame>=shootTime[id]) {
									shooting = true;
									if (airID == Value.towerSlow) {
										Screen.enemy[i].setWalkSpeed(20);
									}
									shootEnemy = i;
									shootFrame = 0;
									break;
								}else shootFrame += 1;
							}
						}
					}
				}
		}

		if (shooting) {
			if (!Screen.enemy[shootEnemy].isInGame()) {
				shooting = false;
				shootEnemy = -1;
			}else {
				Screen.enemy[shootEnemy].loseEnemyHealth(Value.towerDam[airID]);
			}
		}
	}
	public void fight(Graphics g) {
		if (shooting) {
			g.setColor(new Color(255,255,0));
			g.drawLine(x+width/2, y+height/2, Screen.enemy[shootEnemy].x+Screen.enemy[shootEnemy].width/2, Screen.enemy[shootEnemy].y+Screen.enemy[shootEnemy].height/2);
		}

	}
	public int getGroundID() {
		return groundID;
	}
	public void setGroundID(int groundID) {
		this.groundID = groundID;
	}
	public int getAirID() {
		return airID;
	}
	public void setAirID(int airID) {
		this.airID = airID;
	}
}