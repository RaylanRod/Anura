package com.anura.model.guientity;

import com.anura.model.player.Player;
import com.anura.view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class NPC_Dawei extends Entity{

    public NPC_Dawei(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 0;
        getImage();
        setDialogue();
        name = "dawei";
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/dawei.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/dawei.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/dawei.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/dawei.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/dawei.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/dawei.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/dawei.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/dawei.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setAction () {}

    public void setDialogue () {
        dialogues[0] = "I don't know how you expect me \nto take you seriously \nwhen you're so pink.";
        dialogues[1] = "Ew is your tongue...purple?  \nAre you sick or something?";
        dialogues[2] = "Don't expect to find a mate \nlooking like a all sunburned like that.";
    }

    public void speak () {
        super.speak();
    }
}