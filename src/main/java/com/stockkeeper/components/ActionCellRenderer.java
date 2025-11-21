package com.stockkeeper.components;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class ActionCellRenderer extends JPanel implements TableCellRenderer {
    private JButton editButton;
    private JButton deleteButton;

    public ActionCellRenderer() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        StockKeeperComponent.btnStyle("EDIT", editButton);
        StockKeeperComponent.btnStyle("DELETE", deleteButton);

        setBorder(null);

        add(editButton);
        add(deleteButton);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {

        setOpaque(true);
        setBackground(table.getBackground());
        setBorder(null);

        return this;
    }
}
