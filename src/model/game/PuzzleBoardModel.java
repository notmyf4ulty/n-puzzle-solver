package model.game;

import javafx.application.Platform;

import java.util.Arrays;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Model układanki. Implementuje część obserwowaną wzorca Obserwator.
 */
public class PuzzleBoardModel extends Observable {

    /**
     * Wymiar planszy.
     */
    private int boardDimension;

    /**
     * Plansza z bloczkami.
     */
    private Block [][] board;

    /**
     * Pusty (zerowy) bloczek.
     */
    private Block emptyBlock;

    /**
     * Konstruktor. Generuje planszę na podstawie zadanego wymiaru.
     * @param boardDimension Zadany wymiar planszy.
     */
    PuzzleBoardModel(int boardDimension) {
        this.boardDimension = boardDimension;
        board = generateBoard();
        emptyBlock = findBlock(0);
    }

    /**
     * Generuje planszę i wypełnia ją bloczkami o odpowiedniej wartości liczbowej.
     * @return Gotowa plansza.
     */
    private Block [][] generateBoard() {
        Block [][] board = new Block[boardDimension][boardDimension];
        for (int i = 0; i < boardDimension; i++) {
            for (int j = 0; j < boardDimension; j++) {
                int number = i + (j * boardDimension);
                board[i][j] = new Block(new Position(i,j),number);
            }
        }
        return board;
    }

    /**
     * Zwraca planszę.
     */
    public Block[][] getBoard() {
        return board;
    }

    /**
     * Zamienia miejscami dwa bloczki. Oprócz zmiany wewnętrznych wartości w bloczkach, zmieniane są też indeksy
     * tablicy-planszy.
     */
    private void interchangeBlocks(Block block1, Block block2) {
        Block boardBlock1 = board[block1.getPosition().getX()][block1.getPosition().getY()];
        Block boardBlock2 = board[block2.getPosition().getX()][block2.getPosition().getY()];
        if (boardBlock1.interchangeOnePosition(boardBlock2)) {
            board[boardBlock1.getPosition().getX()][boardBlock1.getPosition().getY()]
                    = boardBlock1;
            board[boardBlock2.getPosition().getX()][boardBlock2.getPosition().getY()]
                    = boardBlock2;
        }
        emptyBlock = findBlock(0);
    }

    /**
     * Porusza pustym bloczkiem do góry.
     */
    void moveEmptyBlockUp() {
        Position newPosition = new Position(emptyBlock.getPosition().getX(),emptyBlock.getPosition().getY() - 1);
        interchangEmptyBlockByPosition(newPosition);
    }

    /**
     * Porusza pustym bloczkiem w prawo.
     */
    void moveEmptyBlockRight() {
        Position newPosition = new Position(emptyBlock.getPosition().getX() + 1,emptyBlock.getPosition().getY());
        interchangEmptyBlockByPosition(newPosition);
    }

    /**
     * Porusza pustym bloczkiem w dół.
     */
    void moveEmptyBlockBottom() {
        Position newPosition = new Position(emptyBlock.getPosition().getX(),emptyBlock.getPosition().getY() + 1);
        interchangEmptyBlockByPosition(newPosition);
    }

    /**
     * Porusza pustym bloczkiem w lewo.
     */
    void moveEmptyBlockLeft() {
        Position newPosition = new Position(emptyBlock.getPosition().getX() - 1,emptyBlock.getPosition().getY());
        interchangEmptyBlockByPosition(newPosition);
    }

    /**
     * Porusza pustym bloczkiem na zadaną pozycję. Sprawdzany jest najpierw poprawność przesunięcia, tj. czy
     * pozycja znajduje się w odległości jednego bloczka od pustego bloczka.
     */
    private void interchangEmptyBlockByPosition(Position position) {
        if (Position.validatePosition(position, boardDimension)) {
            interchangeEmptyBlock(board[position.getX()][position.getY()]);
            Platform.runLater(() -> {
                setChanged();
                notifyObservers();
            });
        }
    }

    /**
     * Przesuwa pusty bloczek.
     * @param block Bloczek, z którym pusty bloczek się zamieni.
     */
    public void interchangeEmptyBlock(Block block) {
        interchangeBlocks(emptyBlock,block);
        emptyBlock = findBlock(0);
    }

    /**
     * Zwraca sąsiadujące bloczki dla pustego bloczka. Zwracane są tylko poprawne obiekty.
     * @return Tablica sąsiadujących bloczków.
     */
    public Block []  getEmptyBlockNeighbours() {
        return getNeighbouringBlocks(emptyBlock);
    }

    /**
     * Zwraca sąsiadujące bloczki dla dnaego bloczka. Zwracane są tylko poprawne obiekty.
     * @param block Bloczek, którego sąsiadujące bloczki należy znaleźć.
     * @return Tablica sąsiadujących bloczków.
     */
    private Block [] getNeighbouringBlocks(Block block) {
        Position blockPosition = block.getPosition();
        Position [] positions = blockPosition.getNeighbouringPositions(boardDimension);
        Block [] blocks = new Block[positions.length];
        for (int i = 0 ; i < positions.length ; i++) {
            blocks[i] = new Block(board[positions[i].getX()][positions[i].getY()]);
        }
        return blocks;
    }

    /**
     * Wyszukuje bloczek o zadanej liczbie.
     * @param number Liczba, którą powinien zawierać bloczek.
     * @return Znaleziony bloczek.
     */
    private Block findBlock(int number) {
        for (int i = 0; i < boardDimension; i++) {
            for (int j = 0; j < boardDimension; j++) {
                if (board[i][j].getNumber() == number) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    /**
     * Miesza planszę zadą ilość razy. O tę ilość przesuwany jest pusty bloczek w losowym kierunku.
     * @param meshIterations Liczba przesunięć pustego bloczka.
     */
    void meshBoard(int meshIterations) {
        for (int j = 0 ; j < meshIterations ; j++) {
            Block[] neighbours = getEmptyBlockNeighbours();
            interchangeEmptyBlock(neighbours[ThreadLocalRandom.current().nextInt(0,neighbours.length)]);
        }
        Platform.runLater(() -> {
            setChanged();
            notifyObservers();
        });
    }

    /**
     * Zwraca kopię modelu ukłdanki.
     */
    public PuzzleBoardModel getCopy() {
        PuzzleBoardModel puzzleBoardModel = new PuzzleBoardModel(boardDimension);
        puzzleBoardModel.board = getCopyBoard();
        Position emptyBlockPosition = findBlock(0).getPosition();
        puzzleBoardModel.emptyBlock = puzzleBoardModel.board[emptyBlockPosition.getX()][emptyBlockPosition.getY()];
        return puzzleBoardModel;
    }

    /**
     * Zwraca kopię planszy.
     */
    Block[][] getCopyBoard() {
        Block [][] copyBoard = new Block[boardDimension][boardDimension];
        for (int i = 0; i < boardDimension; i++) {
            for (int j = 0; j < boardDimension; j++) {
                copyBoard[i][j] = new Block(board[i][j]);
            }
        }
        return copyBoard;
    }

    /**
     * Zwraca pusty bloczek.
     */
    Block getEmptyBlock() {
        return emptyBlock;
    }

    /**
     * Ustawia planszę do zadanej w argumencie konfiguracji.
     * @param board Zadana konfiguracja, które ma odpowiadać nowa plansza.
     */
    void setBoard(Block[][] board) {
        this.board = board;
        emptyBlock = findBlock(0);
        Platform.runLater(() -> {
            setChanged();
            notifyObservers();
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PuzzleBoardModel that = (PuzzleBoardModel) o;

        return Arrays.deepEquals(board, that.board)
                && emptyBlock.equals(that.emptyBlock);

    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(board);
        result = 31 * result + emptyBlock.hashCode();
        return result;
    }
}
