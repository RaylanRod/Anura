package com.anura.view;

import com.anura.UI;
import com.anura.controller.KeyHandler;
import com.anura.model.CollisionChecker;
import com.anura.model.guientity.Entity;
import com.anura.model.guientity.Player;
import com.anura.model.object.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    private final int originalTileSize = 16; // 16x16 tile
    private final int scale = 3;  // multiplier scale for tiles
    TopPanel topPanel;
    BottomPanel bottomPanel;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    // visible area on screen
    private final int maxScreenColumns = 16; // 16 default
    private final int maxScreenRows = 12;  // 12 default

    // Window mode
    public final int screenWidth = tileSize * maxScreenColumns; // 768 px
    public final int screenHeight = tileSize * maxScreenRows; // 576 px

    // FPS (adjust as needed)
    int FPS = 60;

    // WORLD SETTINGS
    public final int maxWorldColumns = 64;
    public final int maxWorldRows = 50;
    public final int worldWidth = tileSize * maxWorldColumns;
    public final int worldHeight = tileSize * maxWorldRows;

    // Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int helpState = 4;
    public final int soundState = 5;
    public final int winState = 6;

    // Tile manager
    public TileManager tileM = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    ExtendedGamePanel eGP;

    // GAME THREAD
    public Thread gameThread;


    //KEY HANDLER
    public KeyHandler keyH = new KeyHandler(this);

    //PLAYER instantiation
    public Player player = new Player(this, keyH, topPanel);

    //OBJECT Instantiation
    public SuperObject obj[] = new SuperObject[4];
    public AssetSetter setter = new AssetSetter(this);

    //NPC
    public Entity npc[] = new Entity[20];

    //MONSTER
    public Entity monster[] = new Entity[10];


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);   //improves the game rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    //UI
    public UI ui= new UI(this, topPanel, bottomPanel);

    public void setUpGame() {
        setter.setObject();
        setter.setNPC();
        gameState = titleState;
        setter.setMonster();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void resetGame(){
        GamePanel gp = setter.gp;
        // reset item locations
        gp.obj[0] = new OBJ_Backpack();
        setter.objectSettings(0, 18, 32);
        gp.obj[1] = new OBJ_Leaf();
        setter.objectSettings(1, 25, 27);
        gp.obj[2] = new OBJ_GlassBead();
        setter.objectSettings(2, 7, 29);
        gp.obj[3] = new OBJ_BottleCap();
        setter.objectSettings(3, 54, 25);
        // Clear player inventory and reset location
        gp.player.inventory.clear();
        gp.player.worldX = gp.tileSize * 18;
        gp.player.worldY = gp.tileSize * 14;
        gp.player.speed = 4;
        gp.player.direction = "down";
        // Reset entity locations
        setter.npcSettings(0, 18, 26);
        setter.npcSettings(1, 55, 4);
        setter.npcSettings(2, 60, 4);
        setter.npcSettings(3, 21, 16);
        setter.npcSettings(4, 41, 20);
        setter.npcSettings(5, 10, 26);
        setter.npcSettings(6, 21, 42);
        setter.npcSettings(7, 35, 38);
        setter.npcSettings(8, 50, 4);
        setter.npcSettings(9, 57, 26);
        // reset monsters
        setter.monsterSetting(0, 55, 7);
        setter.monsterSetting(1, 36, 27);
        setter.monsterSetting(2, 52, 28);


        // Reset inventory
        TopPanel.updateInventory(player.inventory);
        BottomPanel.resetQuests();
        BottomPanel.addQuest(player.findBackpack);
    }


    @Override
    public void run() {

        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        // create core game loop
        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
        }
    }

    public void update() {

        if (gameState == playState) {
            //PLAYER
            player.update();
            //NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    monster[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            // nothing for now
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Title Screen
        if (gameState == titleState) {
            ui.draw(g2);
        }
        // everything else
        else {
            //TILE
            tileM.draw(g2);  // make sure this is above player

            //OBJECT
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }
            //NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2);
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    monster[i].draw(g2);
                }
            }

            //PLAYER
            player.draw(g2, this);

            //UI
            ui.draw(g2);
        }

        g2.dispose();  // keeps from building up memory usage
    }

}
