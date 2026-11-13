import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

/**
 * Electricity Bill Calculator
 * A beginner-friendly Java Swing mini project.
 *
 * Educational tariff used in this project (not an official electricity tariff):
 * 0-100 units: Rs. 3.00 per unit
 * 101-200 units: Rs. 5.00 per additional unit
 * Above 200 units: Rs. 7.00 per additional unit
 * Fixed charge: Rs. 50.00
 */
public class ElectricityBillCalculator extends JFrame {
    private final JTextField nameField = new JTextField();
    private final JTextField customerIdField = new JTextField();
    private final JTextField unitsField = new JTextField();
    private final JTextArea receiptArea = new JTextArea();
    private final DecimalFormat money = new DecimalFormat("0.00");

    public ElectricityBillCalculator() {
        setTitle("Electricity Bill Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620, 650);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout(14, 14));
        root.setBorder(new EmptyBorder(20, 24, 20, 24));
        root.setBackground(new Color(241, 247, 252));

        JLabel heading = new JLabel("⚡ Electricity Bill Calculator", SwingConstants.CENTER);
        heading.setFont(new Font("SansSerif", Font.BOLD, 25));
        heading.setForeground(new Color(20, 70, 120));
        root.add(heading, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(Color.WHITE);
        center.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 225, 238)),
                new EmptyBorder(18, 18, 18, 18)));
        center.setLayout(new BorderLayout(12, 12));

        JPanel form = new JPanel(new GridLayout(3, 2, 12, 14));
        form.setBackground(Color.WHITE);

        addFormRow(form, "Customer name:", nameField);
        addFormRow(form, "Customer ID:", customerIdField);
        addFormRow(form, "Units consumed:", unitsField);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 4));
        buttons.setBackground(Color.WHITE);

        JButton calculateButton = new JButton("Calculate Bill");
        calculateButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        calculateButton.setBackground(new Color(25, 105, 175));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.addActionListener(this::calculateBill);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        resetButton.addActionListener(e -> resetForm());

        buttons.add(calculateButton);
        buttons.add(resetButton);

        JPanel topContent = new JPanel(new BorderLayout(10, 16));
        topContent.setBackground(Color.WHITE);
        topContent.add(form, BorderLayout.CENTER);
        topContent.add(buttons, BorderLayout.SOUTH);

        receiptArea.setEditable(false);
        receiptArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        receiptArea.setBackground(new Color(250, 252, 254));
        receiptArea.setBorder(new EmptyBorder(12, 12, 12, 12));
        receiptArea.setText("Your electricity bill receipt will appear here.");

        JScrollPane receiptScroll = new JScrollPane(receiptArea);
        receiptScroll.setBorder(BorderFactory.createTitledBorder("Bill Receipt"));
        receiptScroll.setPreferredSize(new Dimension(520, 260));

        center.add(topContent, BorderLayout.NORTH);
        center.add(receiptScroll, BorderLayout.CENTER);

        JLabel footer = new JLabel(
                "Educational project • Sample tariff only • No database required",
                SwingConstants.CENTER);
        footer.setFont(new Font("SansSerif", Font.PLAIN, 11));
        footer.setForeground(Color.DARK_GRAY);

        root.add(center, BorderLayout.CENTER);
        root.add(footer, BorderLayout.SOUTH);
        setContentPane(root);
    }

    private void addFormRow(JPanel panel, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(260, 32));
        panel.add(label);
        panel.add(field);
    }

    private void calculateBill(ActionEvent event) {
        String customerName = nameField.getText().trim();
        String customerId = customerIdField.getText().trim();
        String unitsText = unitsField.getText().trim();

        if (customerName.isEmpty() || customerId.isEmpty() || unitsText.isEmpty()) {
            showError("Please fill in all three fields.");
            return;
        }

        int units;
        try {
            units = Integer.parseInt(unitsText);
        } catch (NumberFormatException ex) {
            showError("Units must be a whole number, for example 150.");
            return;
        }

        if (units < 0) {
            showError("Units consumed cannot be negative.");
            return;
        }

        double energyCharge = calculateEnergyCharge(units);
        double fixedCharge = 50.00;
        double total = energyCharge + fixedCharge;

        String receipt = """
                ==============================================
                         ELECTRICITY BILL RECEIPT
                ==============================================
                Customer Name : %s
                Customer ID   : %s
                Units Consumed: %d
                ----------------------------------------------
                Energy Charges: Rs. %s
                Fixed Charges : Rs. %s
                ----------------------------------------------
                TOTAL BILL    : Rs. %s
                ==============================================
                Thank you!

                Note: Sample educational tariff, not an
                official electricity-board bill.
                """.formatted(
                customerName,
                customerId,
                units,
                money.format(energyCharge),
                money.format(fixedCharge),
                money.format(total)
        );

        receiptArea.setText(receipt);
        receiptArea.setCaretPosition(0);
    }

    private double calculateEnergyCharge(int units) {
        if (units <= 100) {
            return units * 3.00;
        } else if (units <= 200) {
            return (100 * 3.00) + ((units - 100) * 5.00);
        } else {
            return (100 * 3.00) + (100 * 5.00) + ((units - 200) * 7.00);
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                this, message, "Invalid Input", JOptionPane.WARNING_MESSAGE);
    }

    private void resetForm() {
        nameField.setText("");
        customerIdField.setText("");
        unitsField.setText("");
        receiptArea.setText("Your electricity bill receipt will appear here.");
        nameField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ElectricityBillCalculator().setVisible(true));
    }
}
