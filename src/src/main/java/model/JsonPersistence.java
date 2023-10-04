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
            try {
                System.out.println("Data loaded from JSON file: " + filePath);
                return objectMapper.readValue(new File(filePath), WortTrainer.class);

            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error loading data from JSON file: " + filePath);
                return null; // Handle the error or return a default WordTrainer
            }
        }
}
