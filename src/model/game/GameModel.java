package model.game;

import model.algorithm.*;

public class GameModel {
    private static GameModel instance = null;
    private PuzzleBoardModel puzzleBoardModel;
    private PuzzleBoardModel targetPuzzleBoardModel;
    private PuzzleBoardModel savedMeshModel;
    private HeuristicType heuristicType;
    private SearchType searchType;
    private MeshLevel meshLevel;
    private MeshType meshType;
    private SearchStat searchStat;
    private boolean lastComputationFailFlag;

    private GameModel() {
        puzzleBoardModel = new PuzzleBoardModel(3);
        targetPuzzleBoardModel = puzzleBoardModel.getCopy();
        savedMeshModel = puzzleBoardModel.getCopy();
        heuristicType = HeuristicType.UNORDERED_BLOCKS;
        searchType = SearchType.A_STAR;
        meshLevel = MeshLevel.LOW;
    }

    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    public void solve() {
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
            case IDA_STAR:
                informativeSearch = new IdaStar(rootNode,targetNode);
                break;
            default:
                informativeSearch = new AStar(rootNode,targetNode);
                break;
        }

        searchStat = informativeSearch.search();

        if (searchStat != null) {
            lastComputationFailFlag = false;
            PuzzleBoardNode informativeSearchResultNode = (PuzzleBoardNode) searchStat.getFinishNode();
            if (informativeSearchResultNode != null) {
                puzzleBoardModel.setBoard(informativeSearchResultNode.getPuzzleBoardModel().getCopyBoard());
            }
        } else {
            lastComputationFailFlag = true;
        }
    }

    public void meshPuzzleBoardModel() {
        int meshIterations;
        switch (meshLevel) {
            case LOW:
                meshIterations = 10;
                break;
            case MEDIUM:
                meshIterations = 100;
                break;
            case HIGH:
                meshIterations = 1000;
                break;
            default:
                meshIterations = 10;
                break;
        }
        puzzleBoardModel.meshBoard(meshIterations);
    }

    public void saveMesh() {
        savedMeshModel.setBoard(puzzleBoardModel.getCopyBoard());
    }

    public void loadMesh() {
        if (savedMeshModel != null) {
            puzzleBoardModel.setBoard(savedMeshModel.getCopyBoard());
        }
    }

    public void resetMesh() {
        puzzleBoardModel.setBoard(targetPuzzleBoardModel.getCopyBoard());
    }

    public void changeBoardDimension(BoardDimension boardDimension) {
        switch (boardDimension) {
            case X3_DIMENSION:
                puzzleBoardModel = new PuzzleBoardModel(3);
                break;
            case X4_DIMENSION:
                puzzleBoardModel = new PuzzleBoardModel(4);
                break;
            case X5_DIMENSION:
                puzzleBoardModel = new PuzzleBoardModel(5);
                break;
        }
        targetPuzzleBoardModel = puzzleBoardModel.getCopy();
        savedMeshModel = puzzleBoardModel.getCopy();
    }

    public void moveUp() {
        puzzleBoardModel.moveEmptyBlockUp();
    }

    public void moveRight() {
        puzzleBoardModel.moveEmptyBlockRight();
    }

    public void moveBottom() {
        puzzleBoardModel.moveEmptyBlockBottom();
    }

    public void moveLeft() {
        puzzleBoardModel.moveEmptyBlockLeft();
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

    public MeshLevel getMeshLevel() {
        return meshLevel;
    }

    public void setMeshLevel(MeshLevel meshLevel) {
        this.meshLevel = meshLevel;
    }

    public MeshType getMeshType() {
        return meshType;
    }

    public void setMeshType(MeshType meshType) {
        this.meshType = meshType;
    }

    public SearchStat getSearchStat() {
        return searchStat;
    }

    public boolean isLastComputationFailFlag() {
        return lastComputationFailFlag;
    }

    public void setLastComputationFailFlag(boolean lastComputationFailFlag) {
        this.lastComputationFailFlag = lastComputationFailFlag;
    }
}
