package com.anura.model.guientity;

import com.anura.view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class NPC_Buckley extends Entity{

    public NPC_Buckley(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 0;
        getImage();
        setDialogue();
        name = "buckley";
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/buckley.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/buckley.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/buckley.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/buckley.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/buckley.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/buckley.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/buckley.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/buckley.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setAction () {}

    public void setDialogue () {

        dialogues[0] = "Oh thank goodness, \ncould you go get help for me?  \nI'm lost and I'm pretty sure you \ncouldn't get me out of this mud";
        dialogues[1] = "My antlers are coming \nin pretty well this year, \nbut they ITCH SO BADLY!";
        dialogues[2] = "If you see any doe, \ncould you tell them I'm over here?";
    }

    public void speak () {
        super.speak();
    }

}