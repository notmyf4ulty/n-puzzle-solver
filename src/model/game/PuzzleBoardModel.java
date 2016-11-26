package model.game;

import javafx.application.Platform;

import java.util.Arrays;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by przemek on 20.11.16.
 */
public class PuzzleBoardModel extends Observable {
    private int boardDimension;
    private Block [][] board;
    private Block emptyBlock;

    PuzzleBoardModel(int boardDimension) {
        this.boardDimension = boardDimension;
        board = generateBoard();
        emptyBlock = board[0][0];
    }

    private Block [][] generateBoard() {
        Block [][] board = new Block[boardDimension][boardDimension];
        for (int i = 0; i < boardDimension; i++) {
            for (int j = 0; j < boardDimension; j++) {
                int number = i + (j * 3);
                board[i][j] = new Block(new Position(i,j),number);
            }
        }
        return board;
    }

    public Block[][] getBoard() {
        return board;
    }

    private void interchangeBlocks(Block block1, Block block2) {
        Block boardBlock1 = board[block1.getPosition().getX()][block1.getPosition().getY()];
        Block boardBlock2 = board[block2.getPosition().getX()][block2.getPosition().getY()];
        if (boardBlock1.interchangeOnePosition(boardBlock2)) {
            board[boardBlock1.getPosition().getX()][boardBlock1.getPosition().getY()]
                    = boardBlock1;
            board[boardBlock2.getPosition().getX()][boardBlock2.getPosition().getY()]
                    = boardBlock2;
        }

    }

    void moveEmptyBlockUp() {
        Position newPosition = new Position(emptyBlock.getPosition().getX(),emptyBlock.getPosition().getY() - 1);
        interchangEmptyBlockByPosition(newPosition);
    }

    void moveEmptyBlockRight() {
        Position newPosition = new Position(emptyBlock.getPosition().getX() + 1,emptyBlock.getPosition().getY());
        interchangEmptyBlockByPosition(newPosition);
    }

    void moveEmptyBlockBottom() {
        Position newPosition = new Position(emptyBlock.getPosition().getX(),emptyBlock.getPosition().getY() + 1);
        interchangEmptyBlockByPosition(newPosition);
    }

    void moveEmptyBlockLeft() {
        Position newPosition = new Position(emptyBlock.getPosition().getX() - 1,emptyBlock.getPosition().getY());
        interchangEmptyBlockByPosition(newPosition);
    }

    private void interchangEmptyBlockByPosition(Position position) {
        if (Position.validatePosition(position, boardDimension)) {
            interchangeEmptyBlock(board[position.getX()][position.getY()]);
            Platform.runLater(() -> {
                setChanged();
                notifyObservers();
            });
        }
    }

    void interchangeEmptyBlock(Block block) {
        interchangeBlocks(emptyBlock,block);
    }

    Block []  getEmptyBlockNeighbours() {
        return getNeighbouringBlocks(emptyBlock);
    }

    private Block [] getNeighbouringBlocks(Block block) {
        Position blockPosition = block.getPosition();
        Position [] positions = blockPosition.getNeighbouringPositions(boardDimension);
        Block [] blocks = new Block[positions.length];
        for (int i = 0 ; i < positions.length ; i++) {
            blocks[i] = new Block(board[positions[i].getX()][positions[i].getY()]);
        }
        return blocks;
    }

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

    public void changeDimension(int dimension) {

    }

    public void printBoard() {
        for (int i = 0; i < boardDimension; i++) {
            for (int j = 0; j < boardDimension; j++) {
                System.out.print(board[j][i].getNumber());
            }
            System.out.println();
        }
    }

    PuzzleBoardModel getCopy() {
        PuzzleBoardModel puzzleBoardModel = new PuzzleBoardModel(boardDimension);
        puzzleBoardModel.board = getCopyBoard();
        puzzleBoardModel.emptyBlock = puzzleBoardModel.board[emptyBlock.getPosition().getX()][emptyBlock.getPosition().getY()];
        return puzzleBoardModel;
    }

    Block[][] getCopyBoard() {
        Block [][] copyBoard = new Block[boardDimension][boardDimension];
        for (int i = 0; i < boardDimension; i++) {
            for (int j = 0; j < boardDimension; j++) {
                copyBoard[i][j] = new Block(board[i][j]);
            }
        }
        return copyBoard;
    }

    public Block getEmptyBlock() {
        return new Block(emptyBlock);
    }

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

        if (!Arrays.deepEquals(board, that.board)) return false;
        return emptyBlock.equals(that.emptyBlock);

    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(board);
        result = 31 * result + emptyBlock.hashCode();
        return result;
    }
}
