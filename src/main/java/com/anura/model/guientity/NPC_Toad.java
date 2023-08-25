package com.anura.model.guientity;

import com.anura.view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class NPC_Toad extends Entity {

    public NPC_Toad(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 0;
        getImage();
        setDialogue();
        name = "toad";
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/toad.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/toad.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/toad.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/toad.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/toad.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/toad.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/toad.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/toad.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {}

    public void setDialogue() {

        dialogues[0] = "This backpack might be helpful \nif you find anything that you can't eat.";
        dialogues[1] = "You should try using the leaf.  \nI don't think any predators could \nsee you if you cover up with that bad boy.";
        dialogues[2] = "I got a gym membership, \nbut I think I'm just lying to myself...";
    }

    public void speak() {
        super.speak();
    }
}
