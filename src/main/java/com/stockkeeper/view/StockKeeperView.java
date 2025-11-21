package com.stockkeeper.view;

import com.stockkeeper.components.StockKeeperComponent;
import com.stockkeeper.contracts.StockKeeperFrame;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class StockKeeperView extends StockKeeperComponent implements StockKeeperFrame {
    @Override
    public final void createAndShowUI() {
        JFrame frame = new JFrame("StockKeeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(new ImageIcon(StockKeeperView.class.getResource("/warehouse.png")).getImage());

        // Tambahkan komponen Swing di sini
        frame.add(this);
        frame.setVisible(true);
    }

}
