package MainGame.DisplayGame;

import MainGame.IngredientGame.Block;
import MainGame.IngredientGame.Value;

import java.awt.Graphics;

public class Room {
	public int worldWidth=30;
	public int worldHeight=20;
	public int blockSize=32;
	public Block[][] block;


	public Room() {
		define();

	}
	private void define() {
		block=new Block[worldHeight][worldWidth];

		for(int x=0; x < block.length; x++) {
			for(int y=0; y < block[0].length; y++) {
				block[x][y] = new Block((Screen.mywidth/2) - ((worldWidth*blockSize)/2) + (y * blockSize), x * blockSize, blockSize, blockSize, Value.groundGrass, Value.airAir);
			}
		}

	}
	public void render() {
		for(int x=0; x < block.length; x++) {
			for(int y=0; y < block[0].length; y++) {
				block[x][y].render();
			}
		}
	}
	public void draw(Graphics g) {
		for(int x=0; x < block.length; x++) {
			for(int y=0; y < block[0].length; y++) {
				block[x][y].draw(g);
			}
		}
		for(int x=0; x < block.length; x++) {
			for(int y=0; y < block[0].length; y++) {
				block[x][y].fight(g);
			}
		}
	}
}
