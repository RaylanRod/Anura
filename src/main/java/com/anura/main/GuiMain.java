package com.anura.main;

import com.anura.view.ExtendedGamePanel;
import com.anura.view.GamePanel;

import javax.swing.*;

public class GuiMain {
    public static void main(String[] args) {

        // Load up the new extended game panel
        SwingUtilities.invokeLater(() -> new ExtendedGamePanel());

//        JFrame window = new JFrame();
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setResizable(false);
//        window.setTitle("Anura 2D game");
//
//        GamePanel gamePanel = new GamePanel();
//        window.add(gamePanel);
//        window.pack(); // enable the window settings to take on the subclass attributes for example width and height
//
//        window.setLocationRelativeTo(null);
//        window.setVisible(true);
//
//        gamePanel.setUpGame(); //load object into the game
//
//        gamePanel.startGameThread();
    }
}
