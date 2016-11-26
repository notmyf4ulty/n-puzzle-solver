package model.algorithm;

import model.game.PuzzleBoardModel;

/**
 * Created by przemek on 24.11.16.
 */
public interface Heuristic {
    int calculate(PuzzleBoardModel puzzleBoardModel);
}
