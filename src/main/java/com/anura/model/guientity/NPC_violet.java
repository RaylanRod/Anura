package com.anura.model.guientity;

import com.anura.view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class NPC_violet extends Entity {

    public NPC_violet(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 0;
        getImage();
        setDialogue();
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/entities/violet.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/entities/violet.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/entities/violet.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/entities/violet.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/entities/violet.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/entities/violet.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/entities/violet.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/entities/violet.png"));

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
