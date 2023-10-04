package model;

public interface Persistence {
    void save(WortTrainer wordTrainer, String filePath);
    WortTrainer load(String filePath);
}
