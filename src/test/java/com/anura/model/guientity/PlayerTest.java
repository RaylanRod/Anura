package com.anura.model.guientity;

import com.anura.controller.KeyHandler;
import com.anura.view.GamePanel;
import com.anura.view.TopPanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {

    @Test
    void testHidePlayer_WithLeafInInventory_ShouldHide() {
        // Arrange
        GamePanel gp = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gp);
        TopPanel topPanel = new TopPanel(gp.player);
        Player player = new Player(gp, keyHandler, topPanel);
        player.inventory.add("leaf"); // Simulate having a leaf in inventory

        // Act
        player.hidePlayer();

        // Assert
        assertTrue(player.hiding);
    }

    @Test
    void testHidePlayer_WithoutLeafInInventory_ShouldNotHide() {
        // Arrange

        GamePanel gp = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gp);
        TopPanel topPanel = new TopPanel(gp.player);
        Player player = new Player(gp, keyHandler, topPanel);
        player.inventory.clear(); // Simulate empty inventory

        // Act
        player.hidePlayer();

        // Assert
        assertFalse(player.hiding);
    }
}
