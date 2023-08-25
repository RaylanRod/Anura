package com.anura.model.guientity;

import com.anura.view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class NPC_violet extends Entity {

    public NPC_violet(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 0;
        getImage();
        setDialogue();
        name = "violet";
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/violet.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/violet.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/violet.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/violet.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/violet.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/violet.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/violet.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/violet.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {
    }

    public void setDialogue() {
        dialogues[0] = "I know this sounds crazy, \nbut don't you think we're a perfect match?  \nYou're a frog... I'm a frog...it makes sense!";
        dialogues[1] = "Were all the regular, \ngreen frogs mean to you too?";
        dialogues[2] = "Oh is your tongue PURPLE?  \nThat's so cool!!!";
    }

    public void speak() {
        super.speak();
    }
}
