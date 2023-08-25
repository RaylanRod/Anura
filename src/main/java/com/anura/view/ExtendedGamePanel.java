package com.anura.view;

import com.anura.UI;
import com.anura.controller.KeyHandler;
import com.anura.model.guientity.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ExtendedGamePanel extends JFrame {

    private TopPanel topPanel;
    private BottomPanel bottomPanel;
    private Player player;

    public ExtendedGamePanel() {
        setTitle("Extended Game Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the main game panel
        GamePanel gamePanel = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gamePanel);

        // create top and bottom panels
        topPanel = new TopPanel(player);
        bottomPanel = new BottomPanel();

        // Create the additional side panel for the side display
        JPanel additionalPanel = new JPanel(new BorderLayout());
        additionalPanel.setPreferredSize(new Dimension(200, gamePanel.screenHeight));
        additionalPanel.setBackground(Color.BLACK);


        // Create top and bottom partitions
        topPanel.setPreferredSize(new Dimension(200, gamePanel.screenHeight/4));
        bottomPanel.setPreferredSize(new Dimension(200, (gamePanel.screenHeight/4)*3));

        // Add top and bottom panels to the additional panel
        additionalPanel.add(topPanel, BorderLayout.NORTH);
        additionalPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Use Border Layout to organize the main layout
        setLayout(new BorderLayout());
        add(gamePanel, BorderLayout.CENTER);
        add(additionalPanel, BorderLayout.EAST);

        // Create the player object
        Player player = new Player(gamePanel, keyHandler, topPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Start the game thread
        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }

    public TopPanel getTopPanel(){
        return topPanel;
    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }
}
