import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PBO13 {
    private static ArrayList<Product> products = new ArrayList<>();
    private static DefaultTableModel tablemodel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Daftar Produk");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"Nama Produk", "Harga"};
        tablemodel = new DefaultTableModel(columnNames, 0); // Gunakan `tablemodel` yang dideklarasikan sebelumnya
        JTable table = new JTable(tablemodel);

        JTextField nameField = new JTextField(10);
        JTextField priceField = new JTextField(10);
        JButton addButton = new JButton("Tambah");
        
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
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Nama: "));
        panel.add(nameField);
        panel.add(new JLabel("Harga: "));
        panel.add(priceField);
        panel.add(addButton);

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
