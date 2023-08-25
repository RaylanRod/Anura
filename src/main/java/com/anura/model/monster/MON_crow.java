package com.anura.model.monster;

import com.anura.model.guientity.Entity;
import com.anura.view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class MON_crow extends Entity {

    public MON_crow(GamePanel gp) {
        super(gp);

        name = "crow";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        direction = "down";
        type = 2;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/crow.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/crow.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/crow.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/crow.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/crow.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/crow.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/crow.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/crow.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
