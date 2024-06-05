	package MainGame.DisplayGame;
	
	import java.awt.Dimension;
	import java.awt.GridLayout;
	import javax.swing.JFrame;
	
	import InterfaceGame.StartGame.StartGame;
	
	public class Frame extends JFrame {
		public static String title = "Game thu thanh nhom 88";
		public static Dimension size = new Dimension(980, 800);
		public Frame() {
			setTitle(title);
			setSize(size);
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			init();
		}
	
		private void init() {
			setLayout(new GridLayout(1, 1, 0, 0));
			Screen screen = new Screen(this);
			add(screen);
			setVisible(true);
		}
		public  void closeFrame() {
			dispose();
		}
	
		public static void main(String args[]) {

			new StartGame();
		}
	}
