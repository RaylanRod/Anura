package com.anura.model.guientity;

import com.anura.view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class NPC_Sting extends Entity{

    public NPC_Sting(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 0;
        getImage();
        setDialogue();
        name = "sting";
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/sting.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/sting.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/sting.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/sting.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/sting.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/sting.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/sting.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/sting.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setAction () {}

    public void setDialogue () {

        dialogues[0] = "Look, I'll be honest... \nI'm just here to eat smaller frogs.  \nThey're delicious.";
        dialogues[1] = "You're definitely going \nto have to jump over that \nlog to get to the stream.";
        dialogues[2] = "I feel like I should sting you, \nbut maybe I'm just hangry...";
    }

    public void speak () {
        super.speak();
    }

}