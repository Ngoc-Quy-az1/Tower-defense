package MainGame.IngredientGame;
import javax.swing.*;

import MainGame.DisplayGame.Screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import InterfaceGame.LevelSelectionFrame.LevelSelectionFrame;

public class Store {
	public static int shopWidth = 4;
	public static int buttonSize = 64;
	public static int cellSpace = 2;
	public static int awayFromRoom = 29;
	private Rectangle[] button = new Rectangle[shopWidth];
	private Rectangle buttonHealth;
	private Rectangle buttonCoin;
	public static int holdID = -1;
	public static int iconSize = 20;
	public static int iconSpace = 3;
	public static int iconTextY = 15;
	public static int itemIn = 10;
	public static int[] buttonID = {0, 1, 2, 3};
	private boolean holdTower = false;
	private boolean holdBin = false;
	public static int[] buttonPrice = {10, 20, 50};

	private Rectangle pauseButton;
	public static boolean isGameRunning = true; // Trạng thái của game

	public Store() {
		define();
	}

	public void click(int mouseButton) {
		if (mouseButton == 1) {
			for (int i = 0; i < button.length - 1; i++) {
				if (button[i].contains(Screen.mse)) {
					holdID = buttonID[i];
					holdTower = true;
					break;
				} else {
					if (holdTower) {
						if (Screen.money >= buttonPrice[holdID]) {
							for (int x = 0; x < Screen.room.block.length; x++) {
								for (int y = 0; y < Screen.room.block[0].length; y++) {
									if (Screen.room.block[x][y].contains(Screen.mse)) {
										if (Screen.room.block[x][y].getGroundID() == Value.groundGrass) {
											Screen.room.block[x][y].setAirID(holdID);
											Screen.money -= buttonPrice[holdID];
										}
									}
								}
							}
						}
					}
					holdTower = false;
				}
			}
			if (button[button.length - 1].contains(Screen.mse)) {
				holdID = buttonID[button.length - 1];
				holdBin = true;
			} else if (holdBin) {
				for (int x = 0; x < Screen.room.block.length; x++) {
					for (int y = 0; y < Screen.room.block[0].length; y++) {
						if (Screen.room.block[x][y].contains(Screen.mse)) {
							if (Screen.room.block[x][y].getGroundID() == Value.groundGrass && Screen.room.block[x][y].getAirID() != Value.airAir) {
								Screen.money += buttonPrice[Screen.room.block[x][y].getAirID()] / 2;
								Screen.room.block[x][y].setAirID(Value.airAir);
							}
						}
					}
				}
				holdBin = false;
			}
			if (pauseButton.contains(Screen.mse)) {
				pauseGame();
			}
		}
	}

	private void pauseGame() {
		isGameRunning = false;
		System.out.println("Game paused");
		new PauseGameFrame().setVisible(true);
		System.out.println("Game continued");
		// Logic để tiếp tục trò chơi có thể được thêm vào đây

	}

	public void define() {
		for (int i = 0; i < button.length; i++) {
			button[i] = new Rectangle((Screen.mywidth / 2) - ((shopWidth * (buttonSize + cellSpace)) / 2) + ((buttonSize + cellSpace) * i), (Screen.room.block[Screen.room.worldHeight - 1][0].y) + Screen.room.blockSize + awayFromRoom, buttonSize, buttonSize);
		}

		buttonHealth = new Rectangle(Screen.room.block[0][0].x + iconSize, button[0].y, iconSize, iconSize);
		buttonCoin = new Rectangle(Screen.room.block[0][0].x + iconSize, button[0].y + button[0].height - iconSize, iconSize, iconSize);

		// Define pause button
		int pauseButtonWidth = 64;
		int pauseButtonHeight = 64;
		int pauseButtonX = Screen.mywidth - pauseButtonWidth - 10; // Cách mép phải 10px
		int pauseButtonY = Screen.myheight - pauseButtonHeight - 10; // Cách mép dưới 10px
		pauseButton = new Rectangle(pauseButtonX, pauseButtonY, pauseButtonWidth, pauseButtonHeight);
	}

	public void draw(Graphics g) {
		for (int i = 0; i < button.length; i++) {
			g.drawImage(Screen.tileset_res[0], button[i].x, button[i].y, button[i].width, button[i].height, null);
			g.setFont(new Font("Courier New", Font.BOLD, 16));
			g.setColor(new Color(255, 255, 255));
			if (i != button.length - 1) {
				g.drawString("" + buttonPrice[i] + "$", button[i].x, button[i].y + 16);
			}
			g.drawImage(Screen.tileset_air[buttonID[i]], button[i].x + itemIn, button[i].y + 14, button[i].width - (itemIn * 2), button[i].height - (itemIn * 2), null);
			if (button[i].contains(Screen.mse)) {
				g.setColor(new Color(255, 255, 255, 150));
				g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
			}
		}
		g.drawImage(Screen.tileset_res[1], buttonHealth.x, buttonHealth.y, buttonHealth.width, buttonHealth.height, null);
		g.drawImage(Screen.tileset_res[2], buttonCoin.x, buttonCoin.y, buttonCoin.width, buttonCoin.height, null);
		g.setFont(new Font("Courier New", Font.BOLD, 16));
		g.setColor(new Color(255, 255, 255));
		g.drawString("" + Screen.health, buttonHealth.x + buttonHealth.width + iconSpace, buttonHealth.y + iconTextY);
		g.drawString("" + Screen.money + "$", buttonCoin.x + buttonCoin.width + iconSpace, buttonCoin.y + iconTextY);

		if (holdTower) {
			g.drawImage(Screen.tileset_air[holdID], Screen.mse.x - buttonSize / 4, Screen.mse.y - buttonSize / 4, buttonSize / 2, buttonSize / 2, null);
			g.setColor(new Color(0,0,0));
			g.drawRect(Screen.mse.x - buttonSize / 4-buttonSize/2, Screen.mse.y - buttonSize / 4-buttonSize/2, buttonSize*3/2, buttonSize*3/2);
			for (int x = 0; x < Screen.room.block.length; x++) {
				for (int y = 0; y < Screen.room.block[0].length; y++) {
					if (Screen.room.block[x][y].getGroundID() == Value.groundGrass) {
						g.setColor(new Color(255, 255, 255, 150));
						g.fillRect(y * 32 + 16, x * 32 + 16, 4, 4);
					}
				}
			}
		}
		if (holdBin) {
			g.drawImage(Screen.tileset_air[holdID], Screen.mse.x - buttonSize / 4, Screen.mse.y - buttonSize / 4, buttonSize / 2, buttonSize / 2, null);
		}

		// Draw pause button
		g.drawImage(new ImageIcon("res/anhbutton/MENU.png").getImage(), pauseButton.x, pauseButton.y, pauseButton.width, pauseButton.height, null);
	}
}

class PauseGameFrame extends JFrame {
	private JPanel pausePanel;

	public PauseGameFrame() {
		setTitle("Pause Game");
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);

		pausePanel = new JPanel();
		pausePanel.setLayout(new GridLayout(3, 1));

		JLabel pauseLabel = new JLabel("Game Paused", JLabel.CENTER);
		pausePanel.add(pauseLabel);

		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				continueGame();
			}
		});
		pausePanel.add(continueButton);

		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backToMenu();
			}
		});
		pausePanel.add(backButton);

		add(pausePanel);
	}

	private void continueGame() {
		dispose();
		Store.isGameRunning = true;
	}

	private void backToMenu() {
		Screen.isFirst = true;
		Store.isGameRunning = true;
		dispose();
		System.exit(0);
	}
}