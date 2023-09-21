package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoader {

    public static void loadImageAndDisplay(String imageUrl, String dialogTitle) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Create a URL object
                URL url = new URL(imageUrl);

                // Open a connection to the URL
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // Check if the response code indicates success (HTTP 200)
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    // Read the image data from the URL
                    BufferedImage image = ImageIO.read(url);

                    // Create an ImageIcon from the loaded image
                    ImageIcon icon = new ImageIcon(image);

                    // Create a JLabel to display the image
                    JLabel label = new JLabel(icon);

                    // Create and display the JOptionPane dialog with the JLabel
                    JOptionPane.showMessageDialog(
                            null,         // Parent component (null for a simple dialog)
                            label,        // The JLabel containing the image
                            dialogTitle,  // Dialog title
                            JOptionPane.PLAIN_MESSAGE // Message type (PLAIN_MESSAGE displays the image without an icon)
                    );
                } else {
                    // Handle HTTP error (e.g., display an error message)
                    JOptionPane.showMessageDialog(
                            null,
                            "Failed to load the image. HTTP response code: " + connection.getResponseCode(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

                // Close the connection
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle any exceptions (e.g., display an error message)
                JOptionPane.showMessageDialog(
                        null,
                        "Failed to load the image. Error: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
