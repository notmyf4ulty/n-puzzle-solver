package model.game;

import model.PuzzleBoardModel;
import model.algorithm.*;

/**
 * Created by przemek on 20.11.16.
 */
public class GameModel {
    private static GameModel instance = null;
    PuzzleBoardModel puzzleBoardModel;
    PuzzleBoardModel targetPuzzleBoardModel;
    HeuristicType heuristicType;
    SearchType searchType;

    private GameModel() {
        puzzleBoardModel = new PuzzleBoardModel();
        targetPuzzleBoardModel = puzzleBoardModel.getCopy();
        puzzleBoardModel.meshBoard();
        heuristicType = HeuristicType.UNORDERED_BLOCKS;
        searchType = SearchType.A_STAR;
    }

    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    public void solvePuzzle() {
        Node rootNode;
        Node targetNode;
        InformativeSearch informativeSearch;

        switch (heuristicType) {
            case UNORDERED_BLOCKS:
                rootNode = new PuzzleBoardNode(puzzleBoardModel,0,null, new UnorderedBlocksHeuristic());
                targetNode = new PuzzleBoardNode(targetPuzzleBoardModel,0,null, new UnorderedBlocksHeuristic());
                break;
            case MANHATTAN_DISTANCE:
                rootNode = new PuzzleBoardNode(puzzleBoardModel,0,null,new ManhattanDistanceHeristic());
                targetNode = new PuzzleBoardNode(targetPuzzleBoardModel,0,null,new ManhattanDistanceHeristic());
                break;
            default:
                rootNode = new PuzzleBoardNode(puzzleBoardModel,0,null, new UnorderedBlocksHeuristic());
                targetNode = new PuzzleBoardNode(targetPuzzleBoardModel,0,null, new UnorderedBlocksHeuristic());
                break;
        }

        switch (searchType) {
            case A_STAR:
                informativeSearch = new AStar(rootNode,targetNode);
                break;
//            case IDA_STAR:
//                informativeSearch = new IdaStar(rootNode,targetNode);
//                break;
            default:
                informativeSearch = new AStar(rootNode,targetNode);
                break;
        }

        PuzzleBoardNode informativeSearchResultNode = (PuzzleBoardNode) informativeSearch.fullSearch();
        if (informativeSearchResultNode != null) {
            puzzleBoardModel.setBoard(informativeSearchResultNode.getPuzzleBoardModel().getCopyBoard());
        }
    }

    public PuzzleBoardModel getPuzzleBoardModel() {
        return puzzleBoardModel;
    }

    public PuzzleBoardModel getTargetPuzzleBoardModel() {
        return targetPuzzleBoardModel;
    }

    public HeuristicType getHeuristicType() {
        return heuristicType;
    }

    public void setHeuristicType(HeuristicType heuristicType) {
        this.heuristicType = heuristicType;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }
}
