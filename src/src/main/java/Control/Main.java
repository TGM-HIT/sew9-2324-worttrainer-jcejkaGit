package Control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String[] options = {"Option 1", "Option 2", "Option 3"};
            String selectedOption = showMultiStringDialog("Select an option:", options);
            if (selectedOption != null) {
                JOptionPane.showMessageDialog(null, "You selected: " + selectedOption);
            }
        });
    }

    public static String showMultiStringDialog(String message, String[] options) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(message);
        JList<String> optionList = new JList<>(options);
        JScrollPane scrollPane = new JScrollPane(optionList);
        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Select an option",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String selectedOption = optionList.getSelectedValue();
            return selectedOption;
        } else {
            return null; // User canceled the dialog
        }
    }
}
