package com.anura.model.guientity;

import com.anura.view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class NPC_Destiny extends Entity{

    public NPC_Destiny(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 0;
        getImage();
        setDialogue();
        name = "destiny";
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/destiny.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/destiny.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/destiny.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/destiny.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/destiny.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/destiny.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/destiny.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/destiny.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setAction () {}

    public void setDialogue () {

        dialogues[0] = "I don't think we'd be a good match.  \nWe're both aquarius, \nbut you're honestly gross.";
        dialogues[1] = "Pink is my least favorite color.  \nBy, like, a significant margin.";
        dialogues[2] = "Your tongue makes you look \nlike some gross kid's toy.";
    }

    public void speak () {
        super.speak();
    }

}