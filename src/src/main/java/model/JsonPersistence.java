package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonPersistence implements Persistence {
    @Override
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
