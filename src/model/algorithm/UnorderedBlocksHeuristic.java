package model.algorithm;

import model.game.Block;
import model.game.PuzzleBoardModel;

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
