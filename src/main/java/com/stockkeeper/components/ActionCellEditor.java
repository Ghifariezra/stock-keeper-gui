package com.stockkeeper.components;

import com.stockkeeper.Barang;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

class ActionCellEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton editButton;
    private JButton deleteButton;

    public ActionCellEditor(JTable table, DefaultTableModel model) {
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        StockKeeperComponent.btnStyle("EDIT", editButton);
        StockKeeperComponent.btnStyle("DELETE", deleteButton);

        panel.add(editButton);
        panel.add(deleteButton);
        panel.setBorder(null);

        // Event EDIT
        editButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String nama = model.getValueAt(row, 1).toString();
                String jumlah = model.getValueAt(row, 2).toString();
                String harga = model.getValueAt(row, 3).toString();

                JTextField namaField = new JTextField(nama);
                JTextField jumlahField = new JTextField(jumlah);
                JTextField hargaField = new JTextField(harga);

                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new GridLayout(0, 1, 5, 5));
                inputPanel.add(new JLabel("Nama Barang:"));
                inputPanel.add(namaField);
                inputPanel.add(new JLabel("Jumlah Barang:"));
                inputPanel.add(jumlahField);
                inputPanel.add(new JLabel("Harga Barang:"));
                inputPanel.add(hargaField);

                int result = JOptionPane.showConfirmDialog(null, inputPanel, "Edit Data", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    String newNama = namaField.getText().trim();
                    String newJumlahStr = jumlahField.getText().trim();
                    String newHargaStr = hargaField.getText().trim();

                    if (newNama.isEmpty() || newJumlahStr.isEmpty() || newHargaStr.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Semua field harus diisi.");
                        return;
                    }

                    try {
                        int newJumlah = Integer.parseInt(newJumlahStr);
                        double newHarga = Double.parseDouble(newHargaStr);

                        if (newJumlah < 0 || newHarga < 0) {
                            JOptionPane.showMessageDialog(null, "Jumlah dan Harga harus bernilai positif.");
                            return;
                        }

                        // Update model dan list barang
                        model.setValueAt(newNama, row, 1);
                        model.setValueAt(newJumlah, row, 2);
                        model.setValueAt(newHarga, row, 3);

                        Barang b = Barang.daftarBarang.get(row);
                        b.setNamaBarang(newNama);
                        b.setJumlahBarang(newJumlah);
                        b.setHargaBarang(newHarga);

                        JOptionPane.showMessageDialog(null, "Data berhasil diupdate.");
                        fireEditingStopped();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Jumlah dan Harga harus angka valid.");
                    }
                }
            }
        });

        // Event DELETE
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                fireEditingStopped();

                model.removeRow(row);
                Barang.daftarBarang.remove(row);

                table.revalidate();
                table.repaint();
            }

        });
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column) {
        panel.setOpaque(true);
        panel.setBackground(table.getBackground());

        return panel;
    }
}
