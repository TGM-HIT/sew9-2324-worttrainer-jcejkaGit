package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Saves and loads from a JSON File and persists current Wordtrainer data after the strategy pattern
 * @author jurij
 * @version 18-10-2023
 */
public class JsonPersistence implements Persistence {
    /**
     * Writes all Attributes with getter Methods in json file
     * @param wordTrainer Class that is given to write in json
     * @param filePath to jsonfile
     */
    public void save(WortTrainer wordTrainer, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(filePath), wordTrainer);
            System.out.println("Data saved to JSON file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving data to JSON file: " + filePath);
        }
    }

    /**
     * Reads from JSON file and turns it into Wordtrainer class
     * @param filePath to readable json
     * @return generated WortTrainer object
     */
    public WortTrainer load(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);

        if (file.exists()) {
            try {
                return objectMapper.readValue(file, WortTrainer.class);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error loading data from JSON file: " + filePath);
            }
        }

        return null; // Return a new instance if the file does not exist or there's an error
    }
}
