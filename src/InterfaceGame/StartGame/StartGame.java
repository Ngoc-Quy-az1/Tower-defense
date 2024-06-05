package InterfaceGame.StartGame;

import InterfaceGame.LevelSelectionFrame.LevelSelectionFrame;
import InterfaceGame.MusicPlayer.MusicPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartGame extends JFrame {
    public static int ahihi ;
    public static String title = "Game thu thanh nhom 88";
    public static Dimension size = new Dimension(980, 800);
    public static MusicPlayer anhquy1 = new MusicPlayer("res/testnhac.wav");
    public StartGame() {
        setTitle(title);
        setSize(size);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    private void init() {

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(size);
        getContentPane().add(layeredPane);

        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("res/anhphongnen/anhmanhinhchinh.png");
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(0, 0, size.width, size.height);
        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // Làm cho panel trong suốt
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        buttonPanel.setBounds(0, 0, size.width, size.height);
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

        // Tạo nút "PLAY"
        JButton openSubFrameButton = createButton("res/anhbutton/PLAY.png", "res/anhbutton/play2.png");
        openSubFrameButton.setPreferredSize(new Dimension(252, 99));
        openSubFrameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                LevelSelectionFrame levelSelectionFrame = new LevelSelectionFrame();
                levelSelectionFrame.setVisible(true);
                dispose();
            }
        });

        // Thêm nút "PLAY" vào panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(openSubFrameButton, gbc);

        // Tạo nút "MENU"
        JButton gameStageButton = createButton("res/anhbutton/MENU.png", "res/anhbutton/menu2.png");
        gameStageButton.setPreferredSize(new Dimension(252, 99));
        gameStageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            new MenuClass();
                dispose();
            }
        });
        gbc.gridy = 1;
        buttonPanel.add(gameStageButton, gbc);

        // Tạo nút "HELP"
        JButton settingButton = createButton("res/anhbutton/HELP.png", "res/anhbutton/help2.png");
        settingButton.setPreferredSize(new Dimension(252, 99));
        settingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HelpClass();
                dispose();
            }
        });
        gbc.gridy = 2;
        buttonPanel.add(settingButton, gbc);

        // Tạo nút "EXIT"
        JButton exitGameButton = createButton("res/anhbutton/EXIT.png", "res/anhbutton/exit2.png");
        exitGameButton.setPreferredSize(new Dimension(252, 99));
        exitGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
        gbc.gridy = 3;
        buttonPanel.add(exitGameButton, gbc);

        // Hiển thị cửa sổ
        setVisible(true);
    }

    // Phương thức tạo nút với hình ảnh và hình ảnh khi hover
    private JButton createButton(String iconPath, String hoverIconPath) {
        final JButton button = new JButton();
        button.setBorder(BorderFactory.createEmptyBorder()); // Loại bỏ viền nút
        button.setContentAreaFilled(false); // Loại bỏ nền nút
        final ImageIcon icon = new ImageIcon(iconPath);
        final ImageIcon hoverIcon = new ImageIcon(hoverIconPath);
        button.setIcon(icon);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setIcon(hoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setIcon(icon);
            }
        });

        return button;
    }
}
