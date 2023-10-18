package model;

/**
 * Persistance interface that enables interchangable persistence strategys for scalability
 */
public interface Persistence {
    void save(WortTrainer wordTrainer, String filePath);
    WortTrainer load(String filePath);
}
