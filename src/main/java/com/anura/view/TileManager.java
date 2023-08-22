package com.anura.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[20];
        mapTileNum = new int[gp.maxWorldColumns][gp.maxWorldRows];

        getTileImage();
        loadMap("/maps/world01.txt");
    }

    private void setTile(int index, String filepath, boolean collision) throws IOException {
        tile[index] = new Tile();
        tile[index].image = ImageIO.read(getClass().getResourceAsStream(filepath));
        tile[index].collision = collision;
    }

    public void getTileImage() {
        try {
            setTile(0, "/tiles/pgrass.png", false);
            setTile(1, "/tiles/tree.png", true);
            setTile(2, "/tiles/pwater.png", true);
            setTile(3, "/tiles/sgrass.png", false);
            setTile(4, "/tiles/trail.png", false);
            setTile(5, "/tiles/wetland.png", false);
            setTile(6, "/tiles/concrete.png", false);
            setTile(7, "/tiles/grassland.png", false);
            setTile(8, "/tiles/dock.png", false);
            setTile(9, "/tiles/log1.png", true);
            setTile(10, "/tiles/log_grass_bottom.png", false);
            setTile(11, "/tiles/log_trail_bottom.png", false);
            setTile(12, "/tiles/log_grass_top.png", true);
            setTile(13, "/tiles/log_trail_top.png", true);
            setTile(14, "/tiles/water2.png", true);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldColumns && row < gp.maxWorldRows) {
                String line = br.readLine();  // read a line and convert it to String
                while (col < gp.maxWorldColumns) {
                    String numbers[] = line.split(" "); // split the string into an array
                    int num = Integer.parseInt(numbers[col]);  // change the Strings to ints

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldColumns) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldColumns && worldRow < gp.maxWorldRows) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldColumns) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}