package InterfaceGame.StartGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HelpClass extends JFrame {

    private JPanel imagePanel;
    private JLabel imageLabel;
    private JButton backButton;
    private JButton leftButton;
    private JButton rightButton;
    private int currentImageIndex = 0;
    private ImageIcon[] images;

    public HelpClass() {
        setTitle("Image Viewer");
        setSize(980, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        images = new ImageIcon[]{
                new ImageIcon("res/anhphongnen/help1.jpg"),
                new ImageIcon("res/anhphongnen/help2.jpg"),
                new ImageIcon("res/anhphongnen/help3.jpg"),
                new ImageIcon("res/anhphongnen/help3.jpg"),
        };


        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        imagePanel = new JPanel(new BorderLayout());
        imageLabel = new JLabel(images[currentImageIndex]);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(imagePanel, BorderLayout.CENTER);

        backButton = createButtonWithImage("res/anhbutton/back.jpg", "res/anhbutton/back.jpg");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StartGame();
            }
        });
        mainPanel.add(backButton, BorderLayout.SOUTH);

        leftButton = createButtonWithImage("res/anhbutton/muitentrai1.jpg", "res/anhbutton/muitentrai.jpg");
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousImage();
            }
        });
        mainPanel.add(leftButton, BorderLayout.WEST);

        // Create right button with image
        rightButton = createButtonWithImage("res/anhbutton/muitenphai1.jpg", "res/anhbutton/muitenphai.jpg");
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextImage();
            }
        });
        mainPanel.add(rightButton, BorderLayout.EAST);

        setVisible(true);
    }

    private JButton createButtonWithImage(String imagePath, String hoverImagePath) {
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

        return button;
    }

    private void showPreviousImage() {
        if (currentImageIndex > 0) {
            currentImageIndex--;
            imageLabel.setIcon(images[currentImageIndex]);
        }
    }

    private void showNextImage() {
        if (currentImageIndex < images.length - 1) {
            currentImageIndex++;
            imageLabel.setIcon(images[currentImageIndex]);
        }
    }

}
