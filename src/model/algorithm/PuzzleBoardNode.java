package model.algorithm;

import model.game.Block;
import model.game.PuzzleBoardModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Model węzła w algorytmie przeszukiwania, oparty o układankę "N Puzzle."
 */
public class PuzzleBoardNode extends Node {

    /**
     * Koszt przejścia do następnego węzła.
     */
    private static final int TRAVEL_COST = 1;

    /**
     * Model układanki węzła.
     */
    private PuzzleBoardModel puzzleBoardModel;

    /**
     * Konstruktor inicjalizujacy początkowe wartosci.
     */
    public PuzzleBoardNode(PuzzleBoardModel puzzleBoardModel, int gCost, Node parent, Heuristic heuristic) {
        super(gCost, parent, heuristic);
        this.puzzleBoardModel = puzzleBoardModel;
        this.travelCost = TRAVEL_COST;
        this.gCost += travelCost;
        this.fCost = calculateFCost();
    }

    /**
     * Generuje listę następców dla węzła, tj. możliwe konfiguracje dla możliwych ruchów pustego bloczka na aktualnej
     * pozcji.
     * @return Lista następców węzła.
     */
    @Override
    public List<Node> generateDescendants() {
        List<Node> descendants = new ArrayList<>();
        for (PuzzleBoardModel puzzleBoardModel : generateDescendantPuzzleBoards(this.puzzleBoardModel)) {
            descendants.add(new PuzzleBoardNode(puzzleBoardModel, gCost, this, heuristic));
        }
        return descendants;
    }

    /**
     * Tworzy następców - w postaci możliwych konfiguracji po wszystkich możliwych ruchach pustego bloczka.
     * @param parentPuzzleBoard Model układanki, dla której liczone będą konfiguracje.
     * @return Tablica konfiguracji.
     */
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

    /**
     * Oblicza koszt dotarcia do danego węzła na podstawie zadanej heurystyki.
     * @return Koszt dotarcia funkcji f(n).
     */
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

    /**
     * Zwraca przechowywany przez węzeł model układanki.
     */
    public PuzzleBoardModel getPuzzleBoardModel() {
        return puzzleBoardModel;
    }

    /**
     * Ustawia przechowywany przez węzeł model układanki.
     */
    public void setPuzzleBoardModel(PuzzleBoardModel puzzleBoardModel) {
        this.puzzleBoardModel = puzzleBoardModel;
    }
}
