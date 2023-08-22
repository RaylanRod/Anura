package com.anura.model.guientity;

import com.anura.view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class NPC_Karen extends Entity{

    public NPC_Karen(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 0;
        getImage();
        setDialogue();
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/entities/karen.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/entities/karen.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/entities/karen.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/entities/karen.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/entities/karen.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/entities/karen.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/entities/karen.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/entities/karen.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setAction () {}

    public void setDialogue () {

        dialogues[0] = "Hello, there good looking";
        dialogues[1] = "Frog, Pink and, Handsome";
        dialogues[2] = "Frog, Pink and, Handsome";
    }

    public void speak () {
        super.speak();
    }
}
