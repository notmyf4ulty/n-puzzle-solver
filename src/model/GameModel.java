package model;

/**
 * Created by przemek on 20.11.16.
 */
public class GameModel {
    private static GameModel instance = null;
    PuzzleBoardModel puzzleBoardModel;

    private GameModel() {
        puzzleBoardModel = new PuzzleBoardModel();
    }

    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    public PuzzleBoardModel getPuzzleBoardModel() {
        return puzzleBoardModel;
    }
}
