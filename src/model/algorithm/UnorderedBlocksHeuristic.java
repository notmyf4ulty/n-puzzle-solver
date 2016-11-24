package model.algorithm;

import model.Block;
import model.PuzzleBoardModel;

/**
 * Created by przemek on 24.11.16.
 */
public class UnorderedBlocksHeuristic implements Heuristic{
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
