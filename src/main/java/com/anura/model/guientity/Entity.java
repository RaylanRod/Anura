package com.anura.model.guientity;

import com.anura.view.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Entity {

    GamePanel gp;
    public int worldX, worldY, speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public boolean collisionOn = false;
    public int solidAreaDefaultX, solidAreaDefaultY; //For item interactions
    public int actionLockCounter;
    String dialogues[] = new String[3];
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public boolean hiding;
    int dialogueIndex = 0;
    public String name;
    public int maxLife;
    public int life;
    public int type; //0 = player, 1 = npc, 2 = monster

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {
    }

    public void speak() {

        if (dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(dialogues.length);
        gp.ui.currentDialogue = dialogues[randomIndex];  // returns a random dialog index from 0-2
//        gp.ui.currentDialogue = dialogues[dialogueIndex];
//        dialogueIndex++;

        switch(gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update() {
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer){
            if (!gp.player.invincible) {
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
