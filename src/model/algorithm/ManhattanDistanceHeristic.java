package model.algorithm;

import model.Block;
import model.PuzzleBoardModel;

/**
 * Created by przemek on 24.11.16.
 */
public class ManhattanDistanceHeristic implements Heuristic {
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
