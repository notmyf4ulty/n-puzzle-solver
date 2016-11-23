package model.algorithm;

import model.Block;
import model.PuzzleBoardModel;

import java.util.Queue;

/**
 * Created by przemek on 21.11.16.
 */
public class AStar {

    Queue<PuzzleBoardModel> openList;
    Queue<PuzzleBoardModel> closedList;

    public AStar() {

    }

    public void printPuzzle(PuzzleBoardModel startPuzzleBoard) {
        for (PuzzleBoardModel puzzleBoardModel : generateDescendants(startPuzzleBoard)) {
            puzzleBoardModel.printBoard();
        }
    }


    private PuzzleBoardModel [] generateDescendants(PuzzleBoardModel parentPuzzleBoard) {
        Block[] blocks = parentPuzzleBoard.getEmptyBlockNeighbours();
        PuzzleBoardModel[] puzzleBoardModels = new PuzzleBoardModel[blocks.length];
        for (int i = 0 ; i < blocks.length ; i++) {
            PuzzleBoardModel descendant = parentPuzzleBoard.getCopy();
            descendant.interchangeEmptyBlock(blocks[i]);
            puzzleBoardModels[i] = descendant;
        }
        return puzzleBoardModels;
    }
}
