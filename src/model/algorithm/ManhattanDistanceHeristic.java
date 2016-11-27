package model.algorithm;

import model.game.Block;
import model.game.PuzzleBoardModel;

/**
 * Implementacja algorytmu obliczającego funkcję heurystyki wg dystansu Manhattan.
 */
public class ManhattanDistanceHeristic implements Heuristic {

    /**
     * Funkcja obliczająca heurystykę Manhattan dla danego ułożenia układanki.
     * Liczona jest suma odległości po współrzędnych x i y dla każdego z bloczków.
     * @param puzzleBoardModel Układanka do obliczenia heurystyki.
     * @return Wartości funkcji heurystyki.
     */
    public int calculate(PuzzleBoardModel puzzleBoardModel) {
        int cost = 0;
        for (Block[] blocks : puzzleBoardModel.getBoard()) {
            for (Block block : blocks) {
                if (!block.isOnTargetPosition()) {
                    int x = block.getPosition().getX();
                    int targetX = block.getTargetPosition().getX();
                    int y = block.getPosition().getY();
                    int targetY = block.getTargetPosition().getY();
                    cost += Math.abs(x - targetX) + Math.abs(y - targetY);
                }
            }
        }
        return cost;
    }
}
