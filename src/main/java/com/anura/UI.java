package com.anura;

import com.anura.model.object.OBJ_Backpack;
import com.anura.view.GamePanel;
import com.anura.view.Music;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    BufferedImage background;
    Font arial_40;
    BufferedImage backpackImage;
    public Boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue;
    public int menuNum = 0;
    public int pauseNum = 0;
    public int soundNum = 0;
    public boolean musicPlaying = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
//        OBJ_Backpack backpack = new OBJ_Backpack();
//        backpackImage = backpack.image;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState) {
            // do playState logic
            g2.drawString("Inventory: ", 10, 20);
             g2.drawImage(backpackImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        if(gp.gameState == gp.helpState){
            drawHelpScreen();
        }
        if(gp.gameState == gp.soundState){
            drawSoundScreen();
        }
    }

    public void drawTitleScreen(){

        try{
            background = ImageIO.read(getClass().getResourceAsStream("/GuiTitleScreen.png"));
            g2.drawImage(background, 0, 0, null);
        }catch(IOException e){
            e.printStackTrace();
        }
        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
        String text = "NEW GAME";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2 + (gp.tileSize * 2);
        g2.drawString(text, x, y);
        if(menuNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "LOAD GAME(under development)";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(menuNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }
        text = "Console Game(under development)";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(menuNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(menuNum == 3){
            g2.drawString(">", x-gp.tileSize, y);
        }
        // Menu Instructions
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        String text1 = "Use W/S to change Selection";
        String text2 = "then Enter to make your choice";
        x = 10;
        y += gp.tileSize;
        g2.drawString(text1, x, y);
        g2.drawString(text2, x, y + gp.tileSize/2);
    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 2;
        g2.drawString(text, x, y);

        // Menu options
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
        text = "HELP";
        x = getXForCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if(pauseNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "Sound Controls";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(pauseNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "SAVE GAME(under development)";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(pauseNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(pauseNum == 3){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }
    public void drawSoundScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
        String text = "SOUND OPTIONS";
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 2;
        g2.drawString(text, x, y);

        // Sound Options
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
        text = "Music off";
        x = getXForCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if(soundNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "Music on";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(soundNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "Volume Up";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(soundNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "Volume Down";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(soundNum == 3){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    // helper method to get center of screen x to display text centered
    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    private void drawDialogueScreen() {
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        x += gp.tileSize;
        y += gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26F));

        for (String line : currentDialogue.split("\n")){
        g2.drawString(currentDialogue, x, y);
        y += 40;
        }
    }

    private void drawHelpScreen() {
        int windowX = gp.tileSize * 2;
        int windowY = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 10;
        drawSubWindow(windowX, windowY, width, height);
        String line1 = "Use WSAD to move";
        String line2 = "Hit Enter to speak to NPCs";
        String line3 = "If it doesn't work, try again while walking toward the NPC";
        String line4 = "Hit Escape to pause and for options";
        String line5 = "Instructions:";
        String line6 = "You're born into this dangerous world as a tiny pink tadpole.\n" +
                "You need to eat, evolve, and survive long enough to find a mate.\n" +
                "Navigate around friends and foes to find someone to love!";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26F));
        int x = getXForCenteredText(line1);
        int y = windowY += gp.tileSize;
        g2.drawString(line1, x, y);

        x = getXForCenteredText(line2);
        y += gp.tileSize;
        g2.drawString(line2, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18F));
        x = getXForCenteredText(line3);
        y += 30;
        g2.drawString(line3, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26F));
        x = getXForCenteredText(line4);
        y += gp.tileSize;
        g2.drawString(line4, x, y);

        x = getXForCenteredText(line5);
        y += gp.tileSize * 3;
        g2.drawString(line5, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18F));
        x = windowX += gp.tileSize ;
        y +=gp.tileSize;
        for(String line : line6.split("\n")){  // split the text at \n to get the line break
            g2.drawString(line, x, y);
            y += 40;  // after splitting the line, increase the Y so the next line is down 40 px
        }
//        g2.drawString(line6, x, y);

    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.white);
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 35, 35);
    }
}


