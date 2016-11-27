package model.game;

import model.algorithm.Heuristic;
import model.algorithm.Node;
import java.util.ArrayList;
import java.util.List;

class PuzzleBoardNode extends Node {

    private static final int TRAVEL_COST = 1;
    private PuzzleBoardModel puzzleBoardModel;

    PuzzleBoardNode(PuzzleBoardModel puzzleBoardModel, int gCost, Node parent, Heuristic heuristic) {
        super(gCost, parent, heuristic);
        this.puzzleBoardModel = puzzleBoardModel;
        this.travelCost = TRAVEL_COST;
        this.gCost += travelCost;
        this.fCost = calculateFCost();
    }

    @Override
    public List<Node> generateDescendants() {
        List<Node> descendants = new ArrayList<>();
        for (PuzzleBoardModel puzzleBoardModel : generateDescendantPuzzleBoards(this.puzzleBoardModel)) {
            descendants.add(new PuzzleBoardNode(puzzleBoardModel, gCost, this, heuristic));
        }
        return descendants;
    }

    private PuzzleBoardModel [] generateDescendantPuzzleBoards(PuzzleBoardModel parentPuzzleBoard) {
        Block[] blocks = parentPuzzleBoard.getEmptyBlockNeighbours();
        PuzzleBoardModel[] puzzleBoardModels = new PuzzleBoardModel[blocks.length];
        for (int i = 0 ; i < blocks.length ; i++) {
            PuzzleBoardModel descendant = parentPuzzleBoard.getCopy();
            descendant.interchangeEmptyBlock(blocks[i]);
            puzzleBoardModels[i] = descendant;
        }
        return puzzleBoardModels;
    }

    @Override
    public int calculateFCost() {
        return gCost + heuristic.calculate(puzzleBoardModel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PuzzleBoardNode that = (PuzzleBoardNode) o;

        return puzzleBoardModel.equals(that.puzzleBoardModel);

    }

    @Override
    public int hashCode() {
        return puzzleBoardModel.hashCode();
    }

    PuzzleBoardModel getPuzzleBoardModel() {
        return puzzleBoardModel;
    }
}
