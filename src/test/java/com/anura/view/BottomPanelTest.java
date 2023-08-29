package com.anura.view;

import com.anura.controller.Quest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BottomPanelTest {
    BottomPanel bottomPanel = new BottomPanel();

    @Test
    void addQuestTest_ShouldReturnBothQuests() {
        // set quest1 and quest2 values
        Quest quest1 = new Quest("Quest Test 1");
        Quest quest2 = new Quest("Quest Test 2");

        // add both quests to the bottom panel
        bottomPanel.addQuest(quest1);
        bottomPanel.addQuest(quest2);

        // get the actual text from the text area
        String actualText = bottomPanel.questTextArea.getText();

        // expected text to confirm results
        String expectedText = quest1.getTitle() + "\n" + quest2.getTitle() + "\n";

        // assert if actual equals expected
        assertEquals(expectedText, actualText);
    }

    @Test
    void removeQuestTest_ShouldReturnOnlyQuest2() {
        // set quest1 and quest2 values
        Quest quest1 = new Quest("Quest Test 1");
        Quest quest2 = new Quest("Quest Test 2");

        // add both to the bottom panel
        bottomPanel.addQuest(quest1);
        bottomPanel.addQuest(quest2);

        // remove quest 1 from the panel
        bottomPanel.removeQuest(quest1);

        // get the actual text from the text area
        String actualText = bottomPanel.questTextArea.getText();

        // expected text to confirm results
        String expectedText = quest2.getTitle() + "\n";

        // assert if the actual equals the expected
        assertEquals(expectedText, actualText);
    }

    @Test
    void resetQuestsTest_ShouldRemoveAllQuests() {
        // set quest1 and quest2 values
        Quest quest1 = new Quest("Quest Test 1");
        Quest quest2 = new Quest("Quest Test 2");
        Quest quest3 = new Quest("Testing");
        Quest quest4 = new Quest("1");
        Quest quest5 = new Quest("2");
        Quest quest6 = new Quest("3");


        // add both to the bottom panel
        bottomPanel.addQuest(quest1);
        bottomPanel.addQuest(quest2);
        bottomPanel.addQuest(quest3);
        bottomPanel.addQuest(quest4);
        bottomPanel.addQuest(quest5);
        bottomPanel.addQuest(quest6);

        // run the reset method to remove all quests
        bottomPanel.resetQuests();

        // get the actual text from the text area
        String actualText = bottomPanel.questTextArea.getText();

        // expected text of blank
        String expectedText = "";

        // assert if the actual equals the expected
        assertEquals(expectedText, actualText);
    }
}