package model;

import javafx.application.Platform;

import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by przemek on 20.11.16.
 */
public class PuzzleBoardModel extends Observable {
    public static final int BOARD_DIMENSION = 3;
    private Block [][] board;

    public PuzzleBoardModel() {
        board = generateBoard();
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
        if (block1 != null && block2 != null && block1.interchangeOnePosition(block2)) {
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

    public boolean changePlacesWithZero(int number1, int number2) {
        Block block1 = findBlock(number1);
        Block block2 = findBlock(number2);
        if (block1 != null && block2 != null) {
            block1.interchange(block2);
            return true;
        } else {
            return false;
        }
    }

//    private Block [] getNeighbouringBlocks(Block block) {
//
//    }

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
            board[randomCoordinates[0]][randomCoordinates[1]]
                    .interchange(board[randomCoordinates[2]][randomCoordinates[3]]);
        }

    }

    public void printBoard() {
        for (Block [] blocks : board) {
            for (Block block : blocks) {
                System.out.print(block.getNumber() + " ");
            }
            System.out.println();
        }
    }
}
