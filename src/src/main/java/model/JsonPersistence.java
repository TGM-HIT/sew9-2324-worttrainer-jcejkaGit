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
}
