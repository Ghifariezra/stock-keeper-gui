package com.stockkeeper.components;

import com.stockkeeper.Barang;
import com.stockkeeper.model.DisplayData;

import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class StockKeeperComponent extends JPanel {
    private NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    private DefaultTableModel model;
    private JTable table;
    private final String[] cols = { "Nama Barang", "Jumlah Barang", "Harga Barang" };
    private final JLabel[] labels;
    private final JTextField[] fields;
    private final JButton addButton;

    public StockKeeperComponent() {
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Inisialisasi array
        labels = new JLabel[cols.length];
        fields = new JTextField[cols.length];
        addButton = new JButton("Add");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Form Input Barang");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24f));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 2, 0);
        add(titleLabel, gbc);

        setupFormFields(gbc);
        setupTable(gbc);
    }

    private final void setupFormFields(GridBagConstraints gbc) {
        for (int i = 0; i < cols.length; i++) {
            // Label
            labels[i] = new JLabel(cols[i] + ":");
            labels[i].setFont(labels[i].getFont().deriveFont(Font.PLAIN, 14f));
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(10, 0, 4, 0);
            gbc.anchor = GridBagConstraints.WEST;
            add(labels[i], gbc);

            // TextField
            fields[i] = new JTextField(20);
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(0, 0, 2, 0);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            add(fields[i], gbc);
        }

        // Add Button
        btnStyle("ADD", addButton);
        addButton.addActionListener(e -> {
            if (inputEmpty())
                return;
            try {
                int jumlah = Integer.parseInt(getFieldValue(1));
                double harga = Double.parseDouble(getFieldValue(2));

                checkInputNumber(jumlah, harga);

                Barang b = new Barang(getFieldValue(0), jumlah, harga);
                Barang.daftarBarang.add(b);

                displayTable(model);

                JOptionPane.showMessageDialog(this, "Barang berhasil ditambahkan.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Jumlah dan Harga harus berupa angka.");
            } finally {
                clearFields();
            }
        });

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(12, 0, 0, 0);

        add(addButton, gbc);
    }

    private final void setupTable(GridBagConstraints gbc) {
        String[] tableCols = {
                "No",
                "Nama Barang",
                "Jumlah Barang",
                "Harga Barang",
                "Action"
        };

        model = new DefaultTableModel(tableCols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };

        displayTable(model);
        styleTable(model, new DefaultTableCellRenderer());

        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(350, 100));

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scroll, gbc);
    }

    private final void displayTable(DefaultTableModel model) {
        model.setRowCount(0);

        if (Barang.daftarBarang.isEmpty()) {
            DisplayData.fillDummyData(model, rupiahFormat);
        } else {
            for (Barang b : Barang.daftarBarang) {
                int no = model.getRowCount() + 1;
                model.addRow(new Object[] {
                        no,
                        b.getNamaBarang(),
                        b.getJumlahBarang(),
                        rupiahFormat.format(b.getHargaBarang()),
                        null
                });
            }
        }
    }

    private final void styleTable(DefaultTableModel model, DefaultTableCellRenderer centerRenderer) {
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setSelectionBackground(getBackground());
        table.setBackground(Color.WHITE);
        table.setRowSorter(new TableRowSorter<>(model));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setFont(table.getTableHeader().getFont().deriveFont(Font.BOLD, 14f));
        table.setFont(table.getFont().deriveFont(Font.PLAIN, 14f));
        table.setRowHeight(35);

        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        table.getColumn("Action").setCellRenderer(new ActionCellRenderer());
        table.getColumn("Action").setCellEditor(new ActionCellEditor(table, model));
        table.setEnabled(true);

        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i != 4) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
    }

    public static void btnStyle(String type, JButton button) {
        switch (type) {
            case "ADD":
                button.setForeground(Color.WHITE);
                button.setFont(button.getFont().deriveFont(Font.BOLD, 14f));
                button.setBackground(Color.green.darker());
                button.setBorderPainted(false);
                break;
            case "EDIT":
                button.setForeground(Color.WHITE);
                button.setBackground(Color.blue.darker());
                button.setBorderPainted(false);
                break;
            case "DELETE":
                button.setForeground(Color.WHITE);
                button.setBackground(Color.red.darker());
                button.setBorderPainted(false);
                break;
        }
    }

    private final boolean inputEmpty() {
        if (getFieldValue(0).isEmpty() || getFieldValue(1).isEmpty() || getFieldValue(2).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi.");
            return true;
        }

        return false;
    }

    private final void checkInputNumber(int jml, double hrg) {
        if (jml < 0 || hrg < 0) {
            JOptionPane.showMessageDialog(this, "Jumlah dan Harga harus bernilai positif.");
            return;
        }
    }

    private final String getFieldValue(int index) {
        return fields[index].getText().trim();
    }

    private final void clearFields() {
        for (JTextField field : fields) {
            field.setText("");
        }
    }
}
