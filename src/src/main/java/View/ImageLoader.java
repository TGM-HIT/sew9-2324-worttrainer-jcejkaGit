package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class provides functionality for displaying images from a url in custom JOptionPaneMessageDialog
 * @author jurij
 * @version 18-10-2023
 */
public class ImageLoader {
    /**
     * Method to display imageUrl from The Web
     * @param imageUrl the url that is displayyed from the web
     * @param dialogTitle Title that is displayed while generating
     * @return
     */
    public static String loadImageAndDisplay(String imageUrl, String dialogTitle) {

        String guess = "";
        try {
            // Create a URL object
            URL url = new URL(imageUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check if the response code indicates success (HTTP 200)
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the image data from the URL
                BufferedImage image = ImageIO.read(url);

                // Create a custom image dialog with the image
                CustomImageDialog dialog = new CustomImageDialog(dialogTitle, image);

                // Display the custom dialog and wait for user confirmation
                guess = dialog.getUserGuess();

                // Check if the user confirmed by clicking "OK"
                if (dialog.isConfirmed()) {
                    // Process the user's guess (e.g., store it, compare it, etc.)
                    System.out.println("User's Guess: " + guess);
                }

                // Close the connection
                connection.disconnect();
            } else {
                // Handle HTTP error (e.g., display an error message)
                handleHttpError(responseCode);
            }
        } catch (IOException e) {
            // Handle any exceptions (e.g., display an error message)
            handleIoException(e);
        }
        return guess;
    }

    private static void handleHttpError(int responseCode) {
        JOptionPane.showMessageDialog(
                null,
                "Failed to load the image. HTTP response code: " + responseCode,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private static void handleIoException(IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(
                null,
                "Failed to load the image. Error: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    public static void displayMessage(String message,boolean success) {
        if(success){
            JOptionPane.showMessageDialog(
                    null,
                    message,
                    "Richtig",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }else{
            JOptionPane.showMessageDialog(
                    null,
                    message,
                    "Falsch",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }

}
