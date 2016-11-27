package model.game;

import model.algorithm.*;

/**
 * Model gry - układanki "N Puzzle". Przechowuje obiekty reprezentujące abstrakcję układaki, a także
 * dostarcza mechanizmy umożliwające interakcję użytkownika z logiką aplikacji.
 */
public class GameModel {

    /**
     * Instancja klasy. Wymagana przez wzorzec Singleton.
     */
    private static GameModel instance = null;

    /**
     * Model układanki.
     */
    private PuzzleBoardModel puzzleBoardModel;

    /**
     * Model - cel układanki.
     */
    private PuzzleBoardModel targetPuzzleBoardModel;

    /**
     * Ustatni zachowany, pomieszany model ukłdanki.
     */
    private PuzzleBoardModel savedMeshModel;

    /**
     * Typ heurystyki wg której przeprowadzane będą obliczenia.
     */
    private HeuristicType heuristicType;

    /**
     * Typ przeszukiwania wg którego przeprowadzane będą obliczenia.
     */
    private SearchType searchType;

    /**
     * Poziom pomieszania modelu układanki.
     */
    private MeshLevel meshLevel;

    /**
     * Przechowuje statystyki ostatniego przeszukiwania.
     */
    private volatile SearchStat searchStat;

    /**
     * Limit czasowy wykonywanych obliczeń.
     */
    private int timeLimit;

    /**
     * Limit rozwijanych węzłów.
     */
    private int nodeLimit;

    /**
     * Konstruktor domyslny. Inicjalizyje wstępnie obiekty klasy.
     */
    private GameModel() {
        puzzleBoardModel = new PuzzleBoardModel(3);
        targetPuzzleBoardModel = puzzleBoardModel.getCopy();
        savedMeshModel = puzzleBoardModel.getCopy();
        heuristicType = HeuristicType.UNORDERED_BLOCKS;
        searchType = SearchType.A_STAR;
        meshLevel = MeshLevel.LOW;
        timeLimit = 60;
        nodeLimit = 10000;
    }

    /**
     * Zwraca instancję klasy. Wymagane przez wzorzec Singleton.
     * @return Instancja klasy.
     */
    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    /**
     * Rozwiązuje zadany problem układanki. W zależności od odpowiednio ustawionych typów heurystyki i rodzaju
     * przeszukiwania, tworzone są odpowiednie obiekty klasy przeszukującej, która następnie dokonuje obliczeń.
     * Jeżeli obliczenia zakończyły się sukcesem, do obiektu przechowującego statystyki rozwiązania zostaje przypisana
     * nowa wartość, zwrócona przez metodę 'search()'.
     */
    public void solve() {
        Node rootNode;
        Node targetNode;
        InformativeSearch informativeSearch;

        switch (heuristicType) {
            case UNORDERED_BLOCKS:
                rootNode = new PuzzleBoardNode(puzzleBoardModel, 0, null, new UnorderedBlocksHeuristic());
                targetNode = new PuzzleBoardNode(targetPuzzleBoardModel, 0, null, new UnorderedBlocksHeuristic());
                break;
            case MANHATTAN_DISTANCE:
                rootNode = new PuzzleBoardNode(puzzleBoardModel, 0, null,new ManhattanDistanceHeristic());
                targetNode = new PuzzleBoardNode(targetPuzzleBoardModel, 0, null,new ManhattanDistanceHeristic());
                break;
            default:
                rootNode = new PuzzleBoardNode(puzzleBoardModel, 0, null, new UnorderedBlocksHeuristic());
                targetNode = new PuzzleBoardNode(targetPuzzleBoardModel, 0, null, new UnorderedBlocksHeuristic());
                break;
        }

        switch (searchType) {
            case A_STAR:
                informativeSearch = new AStar(rootNode, targetNode, nodeLimit);
                break;
            case IDA_STAR:
                informativeSearch = new IdaStar(rootNode, targetNode, nodeLimit);
                break;
            default:
                informativeSearch = new AStar(rootNode, targetNode, nodeLimit);
                break;
        }

        searchStat = informativeSearch.search();

        if (searchStat != null && !searchStat.isNodesLimitError()) {
            PuzzleBoardNode informativeSearchResultNode = (PuzzleBoardNode) searchStat.getFinishNode();
            if (informativeSearchResultNode != null) {
                puzzleBoardModel.setBoard(informativeSearchResultNode.getPuzzleBoardModel().getCopyBoard());
            }
        }
    }

    /**
     * Miesza model układanki wg ustawionego poziomu pomieszania.
     */
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

    /**
     * Zapisuje aktualne pomieszanie układanki.
     */
    public void saveMesh() {
        savedMeshModel.setBoard(puzzleBoardModel.getCopyBoard());
    }

    /**
     * Wczytuje ostatnio zapisane pomieszanie układanki.
     */
    public void loadMesh() {
        if (savedMeshModel != null) {
            puzzleBoardModel.setBoard(savedMeshModel.getCopyBoard());
        }
    }

    /**
     * Ustawia układankę do konfiguracji początkowej.
     */
    public void resetMesh() {
        puzzleBoardModel.setBoard(targetPuzzleBoardModel.getCopyBoard());
    }

    /**
     * Modyfikuje rozmiar układanki wg zadanej wartości.
     * @param boardDimension Nowy wymiar układanki.
     */
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

    /**
     * Porusza pusty bloczek do góry.
     */
    public void moveUp() {
        puzzleBoardModel.moveEmptyBlockUp();
    }

    /**
     * Porusza pusty bloczek w prawo.
     */
    public void moveRight() {
        puzzleBoardModel.moveEmptyBlockRight();
    }

    /**
     * Porusza pusty bloczek do dołu.
     */
    public void moveBottom() {
        puzzleBoardModel.moveEmptyBlockBottom();
    }

    /**
     * Porusza pusty bloczek w lewo.
     */
    public void moveLeft() {
        puzzleBoardModel.moveEmptyBlockLeft();
    }

    /**
     * Zwraca aktualną układankę.
     */
    public PuzzleBoardModel getPuzzleBoardModel() {
        return puzzleBoardModel;
    }

    /**
     * Ustawia nowy typ heurystyki.
     */
    public void setHeuristicType(HeuristicType heuristicType) {
        this.heuristicType = heuristicType;
    }

    /**
     * Ustawia nowy typ przeszukiwania.
     */
    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

    /**
     * Ustawia nowy poziom pomieszania układanki.
     */
    public void setMeshLevel(MeshLevel meshLevel) {
        this.meshLevel = meshLevel;
    }

    /**
     * Zwraca statystyki ostatnio przeprowadzonego rozwiązania.
     */
    public SearchStat getSearchStat() {
        return searchStat;
    }

    /**
     * Zwraca limit czasowy na rozwiązanie.
     */
    public int getTimeLimit() {
        return timeLimit;
    }

    /**
     * Ustawia nowy limit czasowy na rozwiązanie.
     */
    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    /**
     * Zwraca aktualny limit rozwijanych węzłów.
     */
    public int getNodeLimit() {
        return nodeLimit;
    }

    /**
     * Ustawia nowy limit rozwijanych węzłów.
     */
    public void setNodeLimit(int nodeLimit) {
        this.nodeLimit = nodeLimit;
    }
}
