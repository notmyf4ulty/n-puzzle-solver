package model.game;

import model.PuzzleBoardModel;

/**
 * Created by przemek on 20.11.16.
 */
public class GameModel {
    private static GameModel instance = null;
    PuzzleBoardModel puzzleBoardModel;
    PuzzleBoardModel targetPuzzleBoardModel;
    HeuristicType heuristicType;
    SearchType searchType;

    private GameModel() {
        puzzleBoardModel = new PuzzleBoardModel();
        targetPuzzleBoardModel = puzzleBoardModel.getCopy();
        puzzleBoardModel.meshBoard();
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

    public PuzzleBoardModel getTargetPuzzleBoardModel() {
        return targetPuzzleBoardModel;
    }

    public HeuristicType getHeuristicType() {
        return heuristicType;
    }

    public void setHeuristicType(HeuristicType heuristicType) {
        this.heuristicType = heuristicType;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }
}
