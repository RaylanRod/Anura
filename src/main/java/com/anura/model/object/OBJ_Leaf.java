package com.anura.model.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Leaf extends SuperObject {

    public OBJ_Leaf() {
        name = "leaf";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/leaf.png"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
