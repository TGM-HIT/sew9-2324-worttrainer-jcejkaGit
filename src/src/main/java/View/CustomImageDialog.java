package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class that is used in ImageLoader to create custom Dialog window from JOPtionPane
 */
public class CustomImageDialog extends JDialog {
    private JTextField textField;
    private boolean confirmed;

    private static final int IMAGE_WIDTH = 300; // Set the desired image width
    private static final int IMAGE_HEIGHT = 200; // Set the desired image height

    public CustomImageDialog(String title, BufferedImage image) {
        setTitle(title);
        setModal(true); // Ensure it's a modal dialog

        // Resize the image to a fixed width and height
        BufferedImage resizedImage = resizeImage(image, IMAGE_WIDTH, IMAGE_HEIGHT);

        // Create panels for the image and input field
        JPanel imagePanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new BorderLayout());

        // Add the resized image to the image panel
        JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Create the input field and "OK" button in the input panel
        textField = new JTextField();
        JButton okButton = new JButton("OK");

        // Add the input field and "OK" button to the input panel
        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(okButton, BorderLayout.EAST);

        // Add an ActionListener to the "OK" button
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the 'confirmed' flag to true and close the dialog
                confirmed = true;
                dispose();
            }
        });

        // Add the image panel and input panel to the main dialog
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(imagePanel, BorderLayout.CENTER);
        getContentPane().add(inputPanel, BorderLayout.SOUTH);

        // Set dialog properties
        pack();
        setLocationRelativeTo(null); // Center the dialog on the screen
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Close the dialog on "X" button
    }

    public String getUserGuess() {
        // Wait for user confirmation by blocking until the dialog is closed
        setVisible(true);

        // Return the user's guess when the dialog is closed
        return textField.getText();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }
}
