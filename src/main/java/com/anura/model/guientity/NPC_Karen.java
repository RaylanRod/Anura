package com.anura.model.guientity;

import com.anura.view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class NPC_Karen extends Entity{

    public NPC_Karen(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 0;
        getImage();
        setDialogue();
        name = "karen";
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/karen.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/karen.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/karen.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/karen.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/karen.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/karen.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/karen.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/karen.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setAction () {}

    public void setDialogue () {

        dialogues[0] = "This is unacceptable.  \nI want to see your manager.";
        dialogues[1] = "I need a frog that's pulling \nin at LEAST 1,000 insects a DAY!";
        dialogues[2] = "Other frogs just don't understand me.  \nIt's not me.  \nIt's everyone else that's the problem.";
    }

    public void speak () {
        super.speak();
    }
}
