package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonPersistenceTest {

    private JsonPersistence jsonPersistence;
    private WortTrainer originalWortTrainer;
    private final String filePath = "C:\\Users\\jurij\\OneDrive\\Dokumente\\Jurij-tgm\\SEW\\SEW5-WiSe\\WortTrainer-Sew5\\src\\src\\test\\java\\model\\testJsonFile.json";

    @BeforeEach
    void setUp() {
        jsonPersistence = new JsonPersistence();
        originalWortTrainer = createSampleWortTrainer();
    }

    @Test
    void testSaveAndLoad() {
        // Save the originalWortTrainer to a JSON file
        System.out.println(originalWortTrainer.saveData(filePath));
        // Load the WortTrainer from the saved JSON file
        WortTrainer loadedWortTrainer = jsonPersistence.load(filePath);

        // Check if loadedWortTrainer is not null
        assertNotNull(loadedWortTrainer);

        // Check if loadedWortTrainer has the same attributes as originalWortTrainer
        assertWortTrainersEqual(originalWortTrainer, loadedWortTrainer);
    }

    @Test
    void testLoadNonExistentFile() {
        // Attempt to load from a non-existent file
        WortTrainer loadedWortTrainer = jsonPersistence.load("nonExistentFile.json");

        // Check that loadedWortTrainer is null in this case
        assertNull(loadedWortTrainer);
    }

    // Helper method to create a sample WortTrainer for testing
    private WortTrainer createSampleWortTrainer() {
        List<WortEintrag> testListe = new ArrayList<>();
        WortEintrag wortEintrag1 = new WortEintrag("Dog", "https://example.com/dog.jpg");
        WortEintrag wortEintrag2 = new WortEintrag("Cat", "https://example.com/cat.jpg");


        testListe.add(wortEintrag1);
        testListe.add(wortEintrag2);
        WortTrainer wortTrainer = new WortTrainer(testListe,jsonPersistence);

        return wortTrainer;
    }

    // Helper method to compare two WortTrainer instances for equality
    private void assertWortTrainersEqual(WortTrainer expected, WortTrainer actual) {
        assertEquals(expected.getWortListe().size(), actual.getWortListe().size());

        for (int i = 0; i < expected.getWortListe().size(); i++) {
            WortEintrag expectedEntry = expected.getWortListe().get(i);
            WortEintrag actualEntry = actual.getWortListe().get(i);
            assertEquals(expectedEntry.getWort(), actualEntry.getWort());
            assertEquals(expectedEntry.getUrl(), actualEntry.getUrl());
        }

        assertEquals(expected.getTrueGuess(), actual.getTrueGuess());
        assertEquals(expected.getFalseGuess(), actual.getFalseGuess());
    }

    // Cleanup after tests
    @Test
    void cleanUp() {
        // Delete the test JSON file after tests
        File file = new File(filePath);
        if (file.exists()) {
            boolean deleted = file.delete();
            assertTrue(deleted, "Failed to delete the test JSON file");
        }
    }
}