package com.anura.view;

import com.anura.controller.Quest;
import com.anura.model.guientity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BottomPanel extends JPanel {
    static ArrayList<Quest> quests;
    JLabel questLog;
    static JTextArea questTextArea;
    static JPanel questListPanel;
    JScrollPane scrollPane;


    public BottomPanel() {
        // Set the layout, bg color and properties
        setLayout(new BorderLayout());
        setBackground(Color.orange);

        quests = new ArrayList<>();
        questLog = new JLabel("Quest Log", SwingConstants.CENTER);
        questLog.setFont(new Font("Arial", Font.BOLD, 20));
        add(questLog, BorderLayout.NORTH);

//        questListPanel = new JPanel();
//        questListPanel.setLayout(new BoxLayout(questListPanel, BoxLayout.Y_AXIS));
//        add(new JScrollPane(questListPanel), BorderLayout.CENTER);

        questTextArea = new JTextArea();
        questTextArea.setEditable(false);
        questTextArea.setLineWrap(true);
        questTextArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(questTextArea);

        add(scrollPane, BorderLayout.CENTER);
    }

    public static void addQuest(Quest quest){
        quests.add(quest);
        // update UI to show new quest
        updateQuestListUI();
    }
    public static void removeQuest(Quest quest){
        quests.remove(quest);
        // update UI to reflect removed quest
        updateQuestListUI();
    }
    public static void resetQuests(){
        quests.clear();
        updateQuestListUI();
    }


    private static void updateQuestListUI(){
        StringBuilder sb = new StringBuilder();

        for(Quest quest : quests) {
//            JLabel questLabel = new JLabel(quest.getTitle());
//            questListPanel.add(questLabel);
            sb.append(quest.getTitle()).append("\n");
        }

        // repaint the panel to reflect changes
//        questListPanel.revalidate();
//        questListPanel.repaint();
        questTextArea.setText(sb.toString());
    }
}
