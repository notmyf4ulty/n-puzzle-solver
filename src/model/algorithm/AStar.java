package model.algorithm;

import model.Block;
import model.PuzzleBoardModel;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by przemek on 21.11.16.
 */
public class AStar {

    LinkedList<PuzzleBoardModel> openList;
    LinkedList<PuzzleBoardModel> closedList;

    public AStar(PuzzleBoardModel startPuzzleBoard) {
        openList = new LinkedList<>();
        closedList = new LinkedList<>();
        openList.push(startPuzzleBoard);
    }

    public void printPuzzle(PuzzleBoardModel startPuzzleBoard) {
        for (PuzzleBoardModel puzzleBoardModel : generateDescendants(startPuzzleBoard)) {
            puzzleBoardModel.printBoard();
        }
    }

    private void search() {
        PuzzleBoardModel[] descendants = generateDescendants(openList.getFirst());
        closedList.push(openList.pop());
        for (PuzzleBoardModel descendant : descendants) {
            
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
