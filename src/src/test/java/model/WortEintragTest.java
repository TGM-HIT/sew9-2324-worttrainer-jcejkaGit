package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WortEintragTest {
    private WortEintrag testEintrag;
    @BeforeEach
    void setUp() {
        testEintrag=new WortEintrag("Cat", "https://example.com/cat.jpg");
    }

    @Test
    void testConstructor() {
        WortEintrag wortEintrag = new WortEintrag("Cat", "https://example.com/cat.jpg");

        assertEquals("Cat", wortEintrag.getWort());
        assertEquals("https://example.com/cat.jpg", wortEintrag.getUrl());
    }

    @Test
    void testDefaultConstructor() {
        WortEintrag wortEintrag = new WortEintrag();

        assertEquals("Dog", wortEintrag.getWort());
        assertEquals("https://img.freepik.com/free-vector/beagle-dog-cartoon-white-background_1308-75491.jpg?w=200", wortEintrag.getUrl());
    }

    @Test
    void testCheckURL() {
        assertTrue(WortEintrag.checkURL("https://example.com/image.jpg"));
        assertFalse(WortEintrag.checkURL("not_a_url"));
    }

    @Test
    void testSetWort() {
        WortEintrag wortEintrag = new WortEintrag();

        // Valid word
        wortEintrag.setWort("Elephant");
        assertEquals("Elephant", wortEintrag.getWort());

        // Invalid word (less than 2 letters)
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> wortEintrag.setWort("A"));
        assertEquals("geben sie ein wort mit mindestens 2 Buchstaben ein", exception.getMessage());
    }

    @Test
    void testCheckWort() {
        assertTrue(testEintrag.checkWort("Elephant"));
        assertFalse(testEintrag.checkWort("A"));
    }

    @Test
    void testSetUrl() {
        WortEintrag wortEintrag = new WortEintrag();

        // Valid URL
        wortEintrag.setUrl("https://example.com/image.jpg");
        assertEquals("https://example.com/image.jpg", wortEintrag.getUrl());

        // Invalid URL
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> wortEintrag.setUrl("not_a_url"));
        assertEquals("geben sie eine valide URL ein", exception.getMessage());
    }

    @Test
    void testToString() {
        WortEintrag wortEintrag = new WortEintrag("Bird", "https://example.com/bird.jpg");

        assertEquals("Bird;https://example.com/bird.jpg", wortEintrag.toString());
    }
}