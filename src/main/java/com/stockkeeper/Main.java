package com.stockkeeper;

import com.stockkeeper.view.StockKeeperView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StockKeeperView view = new StockKeeperView();
            view.createAndShowUI();
        });
    }
}
