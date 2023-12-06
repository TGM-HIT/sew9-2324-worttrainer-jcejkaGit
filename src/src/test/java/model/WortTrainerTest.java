package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WortTrainerTest {

    private WortTrainer wortTrainer;
    private List<WortEintrag> testListe;

    @BeforeEach
    void setUp() {
        // Initialize a test list
        testListe = new ArrayList<>();
        testListe.add(new WortEintrag("Dog", "https://example.com/dog.jpg"));
        testListe.add(new WortEintrag("Cat", "https://example.com/cat.jpg"));

        // Initialize WortTrainer with the test list and a mock persistence strategy
        wortTrainer = new WortTrainer(testListe, new MockPersistence());
        wortTrainer.chooseWorteintrag(0);
    }

    @Test
    void testConstructor() {
        assertNotNull(wortTrainer);
        assertNotNull(wortTrainer.getWortListe());
        assertEquals(2, wortTrainer.getWortListe().size());
        assertEquals(0, wortTrainer.getTrueGuess());
        assertEquals(0, wortTrainer.getFalseGuess());
        assertNotNull(wortTrainer.getAktPaar());
    }

    @Test
    void testSaveData() {
        // Set a persistence strategy
        wortTrainer.setPersistenceStrategy(new MockPersistence());
        String path="testpath";

        // Test saving data
        String response = wortTrainer.saveData(path);
        assertEquals("file saved at this filepath:"+path, response);
    }

    @Test
    void testGuessWorteintrag() {
        // Test guessing a random word entry
        WortEintrag guessedEntry = wortTrainer.guessWorteintrag();
        assertTrue(testListe.contains(guessedEntry));
        assertEquals(guessedEntry, wortTrainer.getAktPaar());
    }

    @Test
    void testChooseWorteintrag() {
        // Test choosing a word entry by index
        WortEintrag chosenEntry = wortTrainer.chooseWorteintrag(1);
        assertEquals(testListe.get(1), chosenEntry);
        assertEquals(chosenEntry, wortTrainer.getAktPaar());
    }

    @Test
    void testCheck() {
        // Test checking a word entry
        assertTrue(wortTrainer.check("Dog"));
        assertEquals(1, wortTrainer.getTrueGuess());
        assertEquals(0, wortTrainer.getFalseGuess());
    }

    @Test
    void testCheckIgnoreCase() {
        // Test checking a word entry ignoring case
        assertTrue(wortTrainer.checkIgnoreCase("dOg"));
        assertEquals(1, wortTrainer.getTrueGuess());
        assertEquals(0, wortTrainer.getFalseGuess());
    }

    @Test
    void testPrintStatistik() {
        // Test printing statistics
        wortTrainer.check("Dog");
        wortTrainer.chooseWorteintrag(1);
        wortTrainer.check("Cat");
        assertEquals("Es wurde insgesamt 2 mal richtig und 0 mal falsch geraten!", wortTrainer.printStatistik());
    }
    // Mock Persistence class for testing
    static class MockPersistence implements Persistence {

        @Override
        public void save(WortTrainer wordTrainer, String filePath) {
        }

        @Override
        public WortTrainer load(String filePath) {
            return new WortTrainer();
        }
    }
}