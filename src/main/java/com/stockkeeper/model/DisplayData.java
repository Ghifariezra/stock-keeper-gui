package com.stockkeeper.model;

import java.text.NumberFormat;
import javax.swing.table.DefaultTableModel;

public class DisplayData {
    public static void fillDummyData(DefaultTableModel model, NumberFormat rupiahFormat) {
        model.addRow(new Object[] { 1, "Nasi Padang", 10, rupiahFormat.format(10000.00), null });
        model.addRow(new Object[] { 2, "Mie Goreng", 15, rupiahFormat.format(8000.00), null });
        model.addRow(new Object[] { 3, "Soto Ayam", 12, rupiahFormat.format(12000.00), null });
        model.addRow(new Object[] { 4, "Bakso", 20, rupiahFormat.format(15000.00), null });
        model.addRow(new Object[] { 5, "Ayam Goreng", 8, rupiahFormat.format(20000.00), null });
        model.addRow(new Object[] { 6, "Es Teh Manis", 25, rupiahFormat.format(5000.00), null });
        model.addRow(new Object[] { 7, "Kopi Hitam", 18, rupiahFormat.format(7000.00), null });
        model.addRow(new Object[] { 8, "Sate Ayam", 14, rupiahFormat.format(18000.00), null });
        model.addRow(new Object[] { 9, "Gado-Gado", 10, rupiahFormat.format(13000.00), null });
        model.addRow(new Object[] { 10, "Rendang", 5, rupiahFormat.format(25000.00), null });
        model.addRow(new Object[] { 11, "Tempe Goreng", 30, rupiahFormat.format(6000.00), null });
        model.addRow(new Object[] { 12, "Tahu Isi", 22, rupiahFormat.format(7000.00), null });
        model.addRow(new Object[] { 13, "Es Campur", 16, rupiahFormat.format(9000.00), null });
        model.addRow(new Object[] { 14, "Bakwan", 19, rupiahFormat.format(5000.00), null });
        model.addRow(new Object[] { 15, "Lontong Sayur", 7, rupiahFormat.format(15000.00), null });
    }

}
