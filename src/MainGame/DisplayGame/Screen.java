package MainGame.DisplayGame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import MainGame.IngredientGame.KeyHandel;
import MainGame.IngredientGame.Store;
import MainGame.IngredientGame.Enemy;

public class Screen extends JPanel implements Runnable{
	public Thread thread = new Thread(this);
	public static Image[] tileset_ground= new Image[100];
	public static Image[] tileset_air= new Image[100];
	public static Image[] tileset_res= new Image[100];
	public static boolean isFirst = true;
	public static int mywidth, myheight;
	public static Point mse = new Point(0,0);
	public static Room room;
	public static Save save;
	public static Store store;
	public static int money = 50;
	public static int enemyWave = 20;
	public static int health = 3;
	public static Enemy enemy[] = new Enemy[enemyWave];
	private JFrame parentFrame;
	public static Frame frame;
	public static int condition=0;
	public static Image gameover;
	public static Image win;
	public static boolean isLoose = false;
	public static boolean running;

	public Screen(Frame frame) {
		Screen.frame = frame;
		Screen.frame.addMouseListener(new KeyHandel());
		Screen.frame.addMouseMotionListener(new KeyHandel());
		running = true;
		thread.start();
	}

	public Screen(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}
	public void define() {
		room =new Room();
		save = new Save();
		store = new Store();

		for(int i=0;i<tileset_ground.length;i++) {
			tileset_ground[i] = new ImageIcon("res/map/tileset_ground.png").getImage();
			tileset_ground[i] = createImage(new FilteredImageSource(tileset_ground[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));
		}

		for(int i=0;i<tileset_air.length;i++) {
			tileset_air [i]= new ImageIcon("res/tower/tower.gif").getImage();
			tileset_air [i]= createImage(new FilteredImageSource(tileset_air[i].getSource(), new CropImageFilter(0, 32*i,32,32)));
		}

		tileset_res[0] = new ImageIcon("res/tower/cell.png").getImage();
		tileset_res[1] = new ImageIcon("res/tower/heart.png").getImage();
		tileset_res[2] = new ImageIcon("res/tower/coin.png").getImage();



		if(condition==1) {
			save.loadSave(new File("Save/man1.txt"));
		}else if(condition==2) {
			save.loadSave(new File("Save/man2.txt"));
		}else if(condition==3) {
			save.loadSave(new File("Save/man3.txt"));
		}else if(condition==4) {
			save.loadSave(new File("Save/man4.txt"));
		}
		for (int i=0; i<enemy.length; i++) {
			enemy[i] = new Enemy();
		}
	}

	public void paintComponent(Graphics g) {
		if(isFirst) {
			mywidth=getWidth();
			myheight=getHeight();
			define();
			isFirst=false;
		}

		g.setColor(new Color(70,70,70));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(0,0,0));
		g.drawLine(room.block[0][0].x-1, 0, room.block[0][0].x-1, room.block[room.worldHeight-1][0].y + room.blockSize);
		g.drawLine(room.block[0][room.worldWidth-1].x + room.blockSize, 0, room.block[0][room.worldWidth-1].x + room.blockSize, room.block[room.worldHeight-1][0].y + room.blockSize);
		g.drawLine(room.block[0][0].x, room.block[room.worldHeight-1][0].y + room.blockSize, room.block[0][room.worldWidth-1].x + room.blockSize, room.block[room.worldHeight-1][0].y + room.blockSize);
		room.draw(g);
		for (int i=0; i < enemy.length; i++) {
			if (enemy[i].isInGame())
				enemy[i].draw(g);
		}
		store.draw(g);
		if (health == 0) {
			gameover = new ImageIcon("res/anhphongnen/gameover.png").getImage();
			g.drawImage(gameover, 0, 0, mywidth, myheight, null);
			isLoose = true;
		}
		if (enemyWave == 0 && health!=0) {
			win = new ImageIcon("res/anhphongnen/win.png").getImage();
			g.drawImage(win, 0, 0, mywidth, myheight, null);
			isWin = true;
		}
	}

	public int spawnTime = 400, spawnFrame = 0;
	private boolean isWin = false;
	public void enemyspawner() {
		if (spawnFrame > spawnTime) {
			for (int i=0; i<enemy.length; i++) {
				if (!enemy[i].isInGame() && !enemy[i].isHasSpawn()) {
					Random random = new Random();
					enemy[i].spawnEnemies(random.nextInt(4));
					break;
				}
			}
			spawnFrame = 0;
		}else {
			spawnFrame +=1;
		}

	}
	public int winTime = 1000, winFrame = 0;
	public void run() {
		while(running) {
			if(!isFirst && health>0 && Store.isGameRunning && enemyWave > 0) {
				room.render();
				enemyspawner();
				for (int i=0; i<enemy.length; i++)
					if (enemy[i].isInGame()) {
						enemy[i].render();
					}
			}else if(isWin) {
				if (winFrame > winTime) {
					hasWin();
					Screen.condition +=1;
					define();
					isWin = false;
					winFrame = 0;
				}else {
					winFrame += 1;
				}
			}else if (isLoose) {
				hasLoose();
			}
			repaint();
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private void hasLoose() {
		if (winFrame > winTime) {
			System.exit(0);
		} else {
			winFrame += 1;
		}
	}


	private void hasWin() {
		Screen.money = 50;
		Screen.health = 3;
		Screen.enemyWave = 20;
		if (Screen.condition == 4) {
			System.exit(0);
		}
	}


}