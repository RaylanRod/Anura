package com.anura.view;

import com.anura.model.guientity.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TopPanel extends JPanel {

    private static JLabel inventoryLabel;
    private Player player;
    private static DefaultListModel<String> inventoryListModel;
    private static JList<String> inventoryList;

    static{
        inventoryLabel = new JLabel("Inventory");
        inventoryLabel.setVisible(false);
        //Add the inventory to a scroll pane
        inventoryListModel = new DefaultListModel<>();
        inventoryList = new JList<>(inventoryListModel);
    }

    public TopPanel(Player player){
        this.player = player;
        // Set the layout, bg color and properties
        setLayout(new BorderLayout());
        setBackground(Color.orange);
        add(inventoryLabel, BorderLayout.NORTH);

        // Create custom cell renderer
        inventoryList.setCellRenderer(new CustomListCellRenderer());

        setOpaque(true);
        inventoryList.setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(inventoryList);
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setOpaque(true);

        for(Component c : scrollPane.getViewport().getComponents()){
            if(c instanceof JComponent){
                ((JComponent) c).setOpaque(true);
            }
        }
    }

    public static void updateInventory(List<String> inventory){
        // clear current inventory
        inventoryLabel.setText("Inventory");
        inventoryListModel.clear();

        if(inventory.contains("backpack")){
            inventoryLabel.setVisible(true);
        }else if (inventory.isEmpty()){
            inventoryLabel.setVisible(false);
        }

        for(String item : inventory){
//            inventoryLabel.setText(inventoryLabel.getText() + ", " + item);
            inventoryListModel.addElement(item);
        }
    }

    public static JLabel getInventoryLabel() {
        return inventoryLabel;
    }

    public static void setInventoryLabel(JLabel inventoryLabel) {
        TopPanel.inventoryLabel = inventoryLabel;
    }

    public static DefaultListModel<String> getInventoryListModel() {
        return inventoryListModel;
    }

    public static void setInventoryListModel(DefaultListModel<String> inventoryListModel) {
        TopPanel.inventoryListModel = inventoryListModel;
    }

    public static JList<String> getInventoryList() {
        return inventoryList;
    }

    public static void setInventoryList(JList<String> inventoryList) {
        TopPanel.inventoryList = inventoryList;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

class CustomListCellRenderer extends DefaultListCellRenderer{
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus){
        Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        //Set foreground (text) color to white and background to black
        renderer.setForeground(Color.WHITE);
        renderer.setBackground(Color.BLACK);

        return renderer;
    }
}
