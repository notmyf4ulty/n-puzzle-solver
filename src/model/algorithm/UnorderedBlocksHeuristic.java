package model.algorithm;

import model.game.Block;
import model.game.PuzzleBoardModel;

/**
 * Implementacja algorytmu obliczającego funkcję heurystyki wg liczby nieuporzadkowanych bloczków.
 */
public class UnorderedBlocksHeuristic implements Heuristic {

    /**
     * Funkcja obliczająca heurystykę nieuporządkowanych bloczków dla danego ułożenia układanki.
     * Liczona jest suma nieuporządkowanych bloczków na układance.
     * @param puzzleBoardModel Układanka do obliczenia heurystyki.
     * @return Wartości funkcji heurystyki.
     */
    public int calculate(PuzzleBoardModel puzzleBoardModel) {
        int cost = 0;
        for (Block[] blocks : puzzleBoardModel.getBoard()) {
            for (Block block : blocks) {
                if (!block.isOnTargetPosition()) {
                    cost++;
                }
            }
        }
        return cost;
    }
}
