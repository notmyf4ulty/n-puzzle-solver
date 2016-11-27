package model.algorithm;

import model.game.PuzzleBoardModel;

/**
 * Interfejs heurystyki. Wymusza implementację metody obliczającej funkcję heurystyki dla modelu układanki.
 */
public interface Heuristic {
    int calculate(PuzzleBoardModel puzzleBoardModel);
}
