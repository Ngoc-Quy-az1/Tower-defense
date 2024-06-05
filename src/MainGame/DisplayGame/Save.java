package MainGame.DisplayGame;

import java.io.File;
import java.util.Scanner;

public class Save {
	public void loadSave(File loadPath) {
		try {
			Scanner loadScanner = new Scanner(loadPath);

			while (loadScanner.hasNext()) {
				for(int y = 0; y< Screen.room.block.length; y++) {
					for(int x=0;x<Screen.room.block[0].length;x++) {
						Screen.room.block[y][x].setGroundID(loadScanner.nextInt());
					}
				}
				for(int y=0;y<Screen.room.block.length;y++) {
					for(int x=0;x<Screen.room.block[0].length;x++) {
						Screen.room.block[y][x].setAirID(loadScanner.nextInt());
					}
				}
			}
			loadScanner.close();
		}catch(Exception e) {
		}
	}
}
