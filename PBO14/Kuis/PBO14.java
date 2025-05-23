import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class PBO14 {
    private static ArrayList<Product> products = new ArrayList<>();
    private static DefaultTableModel tablemodel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Daftar Produk");
        frame.setSize(750, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"Nama Produk", "Harga"};
        tablemodel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tablemodel);

        JTextField nameField = new JTextField(10);
        JTextField priceField = new JTextField(10);
        JButton addButton = new JButton("Tambah");
        JButton deleteButton = new JButton("Hapus");
        JButton editButton = new JButton("Ubah");

        // untuk semua tombol tidak aktif dulu ketika belum ada input
        addButton.setEnabled(false);
        deleteButton.setEnabled(false);
        editButton.setEnabled(false);

        // listener untuk aktifkan tombol jika ada inputan
        DocumentListener inputListener = new DocumentListener() {
            void updateButtons() {
                boolean filled = !nameField.getText().trim().isEmpty() && !priceField.getText().trim().isEmpty();
                addButton.setEnabled(filled);
                editButton.setEnabled(filled && table.getSelectedRow() != -1);
            }
            public void insertUpdate(DocumentEvent e) { updateButtons();}
            public void removeUpdate(DocumentEvent e) { updateButtons();}
            public void changedUpdate(DocumentEvent e) { updateButtons();}
        };

        nameField.getDocument().addDocumentListener(inputListener);
        priceField.getDocument().addDocumentListener(inputListener);

        // action untuk tombol tambah
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            if (name.isEmpty() || priceField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Isi semua kolom!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double price = Double.parseDouble(priceField.getText());
                Product product = new Product(name, price);
                products.add(product);
                tablemodel.addRow(new Object[]{name, price});
                nameField.setText("");
                priceField.setText("");
                addButton.setEnabled(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // action untuk tombol hapus
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                products.remove(selectedRow);
                tablemodel.removeRow(selectedRow);
                nameField.setText("");
                priceField.setText("");
                deleteButton.setEnabled(false);
                editButton.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(frame, "Tidak ada baris yang dipilih!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });

        // action untuk tombol edit
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Pilih produk yang ingin diubah!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String name = nameField.getText();
            String priceText = priceField.getText();

            if (name.isEmpty() || priceText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Isi semua kolom!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double price = Double.parseDouble(priceText);
                Product product = products.get(selectedRow);
                product.setName(name);
                product.setPrice(price);
                tablemodel.setValueAt(name, selectedRow, 0);
                tablemodel.setValueAt(price, selectedRow, 1);
                
                nameField.setText("");
                priceField.setText("");
                table.clearSelection(); // untuk hapus seleksi setelah edit
                editButton.setEnabled(false);
                deleteButton.setEnabled(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        table.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = table.getSelectedRow();
            boolean selected = selectedRow != -1;
            deleteButton.setEnabled(selected);
            if (selected) {
                nameField.setText(products.get(selectedRow).getName());
                priceField.setText(String.valueOf(products.get(selectedRow).getPrice()));
                editButton.setEnabled(!nameField.getText().trim().isEmpty() && !priceField.getText().trim().isEmpty());
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Nama: "));
        panel.add(nameField);
        panel.add(new JLabel("Harga: "));
        panel.add(priceField);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(editButton);

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
