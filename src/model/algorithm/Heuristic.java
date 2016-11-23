package model.algorithm;

import model.Block;
import model.PuzzleBoardModel;

public class Heuristic {
    public int unorderedBlocks(PuzzleBoardModel puzzleBoardModel) {
        int cost = 0;
        for (Block [] blocks : puzzleBoardModel.getBoard()) {
            for (Block block : blocks) {
                if (!block.getPosition().equals(block.getTargetPosition())) {
                    cost++;
                }
            }
        }
        return cost;
    }
}
