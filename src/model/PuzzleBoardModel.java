package model;

import javafx.application.Platform;
import javafx.geometry.Pos;

import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by przemek on 20.11.16.
 */
public class PuzzleBoardModel extends Observable {
    public static final int BOARD_DIMENSION = 3;
    private Block [][] board;
    private Block emptyBlock;

    public PuzzleBoardModel() {
        board = generateBoard();
        emptyBlock = board[0][0];
        meshBoard();
    }

    private Block [][] generateBoard() {
        Block [][] board = new Block[BOARD_DIMENSION][BOARD_DIMENSION];
        for (int i = 0 ; i < BOARD_DIMENSION ; i++) {
            for (int j = 0 ; j < BOARD_DIMENSION ; j++) {
                int number = i + (j * 3);
                board[i][j] = new Block(new Position(i,j),number);
            }
        }
        return board;
    }

    public Block[][] getBoard() {
        return board;
    }

    public void changePlacesOnePosition(int number1, int number2) {
        Block block1 = findBlock(number1);
        Block block2 = findBlock(number2);
        if (block1 != null && block2 != null) {
            interchangeBlocks(block1,block2);
        }
    }

    public void interchangeBlocks(Block block1, Block block2) {
        if (block1.interchangeOnePosition(block2)) {
            Block tempBlock = board[block1.getPosition().getX()][block1.getPosition().getY()];
            board[block1.getPosition().getX()][block1.getPosition().getY()] =
                    board[block2.getPosition().getX()][block2.getPosition().getY()];
            board[block2.getPosition().getX()][block2.getPosition().getY()] = tempBlock;
            Platform.runLater(() -> {
                setChanged();
                notifyObservers();
            });
        }

    }

    public void interchangeEmptyBlock(Block block) {
        interchangeBlocks(emptyBlock,block);
    }

    public Block []  getEmptyBlockNeighbours() {
        return getNeighbouringBlocks(emptyBlock);
    }

    private Block [] getNeighbouringBlocks(Block block) {
        Position blockPosition = block.getPosition();
        Position [] positions = blockPosition.getNeighbouringPositions(BOARD_DIMENSION);
        Block [] blocks = new Block[positions.length];
        for (int i = 0 ; i < positions.length ; i++) {
            blocks[i] = new Block(board[positions[i].getX()][positions[i].getY()]);
        }
        return blocks;
    }

    private Block findBlock(int number) {
        for (int i = 0 ; i < BOARD_DIMENSION ; i++) {
            for (int j = 0 ; j < BOARD_DIMENSION ; j++) {
                if (board[i][j].getNumber() == number) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    private void meshBoard() {
        for (int j = 0 ; j < 100 ; j++) {
            int[] randomCoordinates = new int[4];
            for (int i = 0; i < randomCoordinates.length; i++) {
                randomCoordinates[i] = ThreadLocalRandom.current().nextInt(0, 3);
            }
            interchangeBlocks(board[randomCoordinates[0]][randomCoordinates[1]],
                    board[randomCoordinates[2]][randomCoordinates[3]]);
        }

    }

    public void printBoard() {
        for (int i = 0 ; i < BOARD_DIMENSION ; i++) {
            for (int j = 0 ; j < BOARD_DIMENSION ; j++) {
                System.out.print(board[j][i].getNumber());
            }
            System.out.println();
        }
        System.out.println();
    }

    public PuzzleBoardModel getCopy() {
        PuzzleBoardModel puzzleBoardModel = new PuzzleBoardModel();
        Block [][] copyBoard = new Block[BOARD_DIMENSION][BOARD_DIMENSION];
        for (int i = 0 ; i < BOARD_DIMENSION ; i++) {
            for (int j = 0 ; j < BOARD_DIMENSION ; j++) {
                copyBoard[i][j] = new Block(board[i][j]);
            }
        }
        puzzleBoardModel.board = copyBoard;
        puzzleBoardModel.emptyBlock = puzzleBoardModel.board[emptyBlock.getPosition().getX()][emptyBlock.getPosition().getY()];
        return puzzleBoardModel;
    }
}
