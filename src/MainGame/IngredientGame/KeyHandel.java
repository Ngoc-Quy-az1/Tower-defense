package MainGame.IngredientGame;

import MainGame.DisplayGame.Screen;
import MainGame.DisplayGame.Frame;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



public class KeyHandel implements MouseMotionListener, MouseListener, KeyListener {

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

		Screen.store.click(e.getButton());
	}

	public void mouseReleased(MouseEvent e) {


	}

	public void mouseEntered(MouseEvent e) {

	}


	public void mouseDragged(MouseEvent e) {


	}

	public void mouseMoved(MouseEvent e) {
		Screen.mse = new Point((e.getX()) - ((Frame.size.width - Screen.mywidth)/2), (e.getY()) - ((Frame.size.height - (Screen.myheight)) - (Frame.size.width - Screen.mywidth)/2));

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}



}

