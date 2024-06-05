package InterfaceGame.StartGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuClass extends JFrame {

    private JButton volumeButton;
    private JButton exitButton;
    private boolean isVolumeOn = true;

    public MenuClass() {
        setTitle("Menu");
        setSize(980, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("res/anhphongnen/anhnen4.jpg");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        add(mainPanel);

        volumeButton = createButtonWithImage("res/anhbutton/amluongmo.jpg", "res/anhbutton/amluongtat.jpg", true);
        volumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleVolume();
            }
        });

        // Create exit button with image
        exitButton = createButtonWithImage("res/anhbutton/exit.png", "res/anhbutton/exit2.png", false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StartGame();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(volumeButton, gbc);

        gbc.gridy = 1;
        mainPanel.add(exitButton, gbc);

        setVisible(true);
    }

    private JButton createButtonWithImage(String imagePath, String hoverImagePath, boolean isVolumeButton) {
        JButton button = new JButton(new ImageIcon(imagePath));
        button.setBorder(BorderFactory.createEmptyBorder()); // Remove button border
        button.setContentAreaFilled(false); // Remove button background

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setIcon(new ImageIcon(hoverImagePath));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setIcon(new ImageIcon(imagePath));
            }
        });

        if (isVolumeButton) {
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setIcon(new ImageIcon(isVolumeOn ? "res/anhbutton/amluongtat.jpg" : "res/anhbutton/amluongmo.jpg"));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setIcon(new ImageIcon(isVolumeOn ? "res/anhbutton/amluongmo.jpg" : "res/anhbutton/amluongtat.jpg"));
                }
            });
        }

        return button;
    }

    private void toggleVolume() {
        if (isVolumeOn) {
            StartGame.anhquy1.stop();
        } else {
            StartGame.anhquy1.play();
        }
        isVolumeOn = !isVolumeOn;
        updateVolumeButtonImage();
    }

    private void updateVolumeButtonImage() {
        if (isVolumeOn) {
            volumeButton.setIcon(new ImageIcon("res/anhbutton/amluongmo.jpg"));
        } else {
            volumeButton.setIcon(new ImageIcon("res/anhbutton/amluongtat.jpg"));
        }
    }

}
