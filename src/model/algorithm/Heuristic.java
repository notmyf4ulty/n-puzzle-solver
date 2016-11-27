package model.algorithm;

import model.game.PuzzleBoardModel;

public interface Heuristic {
    int calculate(PuzzleBoardModel puzzleBoardModel);
}
