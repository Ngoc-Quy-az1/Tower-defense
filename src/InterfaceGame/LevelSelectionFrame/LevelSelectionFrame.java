package InterfaceGame.LevelSelectionFrame;
import javax.swing.*;

import InterfaceGame.StartGame.StartGame;
import MainGame.DisplayGame.Frame;
import MainGame.DisplayGame.Screen;

import java.awt.*;
import java.awt.event.*;

public class LevelSelectionFrame extends JFrame {
    public LevelSelectionFrame() {
        setTitle("Chọn màn chơi");
        setSize(980, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tạo JLayeredPane để chứa ảnh nền và các nút
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(980, 800));
        getContentPane().add(layeredPane);

        // Tạo label để chứa ảnh nền
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("res/anhphongnen/anhmanhinhchinh.png"); // Đường dẫn đến ảnh nền của bạn
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(0, 0, 980, 800);
        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);

        // Tạo JPanel để chứa các nút
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // Làm cho panel trong suốt
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Đặt kích thước và vị trí của button panel
        buttonPanel.setBounds(0, 0, 980, 800);
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

        // Tạo các nút chọn màn chơi với hình ảnh
        JButton level1Button = createButton("res/anhbutton/map11.png", "res/anhbutton/map12.png");
        level1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Screen.condition=1;
                StartGame.ahihi=1;
                Frame frame = new Frame();
                dispose(); // Đóng cửa sổ LevelSelectionFrame khi chọn màn chơi
            }
        });

        JButton level2Button = createButton("res/anhbutton/map21.png", "res/anhbutton/map22.png");
        level2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Screen.condition=2;
            	Frame frame = new Frame();
                dispose(); // Đóng cửa sổ LevelSelectionFrame khi chọn màn chơi
            }
        });

        JButton level3Button = createButton("res/anhbutton/map31.png", "res/anhbutton/map32.png");
        level3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Screen.condition=3;
            	Frame frame = new Frame();
                dispose(); // Đóng cửa sổ LevelSelectionFrame khi chọn màn chơi
            }
        });

        JButton level4Button = createButton("res/anhbutton/map41.png", "res/anhbutton/map42.png");
        level4Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Screen.condition=4;
            	Frame frame = new Frame();
                dispose();
            }
        });

        // Thêm các nút vào panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(level1Button, gbc);
        gbc.gridx = 1;
        buttonPanel.add(level2Button, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(level3Button, gbc);
        gbc.gridx = 1;
        buttonPanel.add(level4Button, gbc);

        // Thêm nút "Back"
        JButton backButton = createButton("res/anhbutton/EXIT.png", "res/anhbutton/exit2.png");
        backButton.setPreferredSize(new Dimension(252, 99));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new StartGame();// Đóng cửa sổ hiện tại khi quay lại màn hình chính
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        buttonPanel.add(backButton, gbc);

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
        button.setPreferredSize(new Dimension(300, 300)); // Kích thước của nút

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
