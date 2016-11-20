package model;

import java.util.Observable;

/**
 * Created by przemek on 20.11.16.
 */
public class PuzzleBoardModel extends Observable {
    public static final int BOARD_DIMENSION = 3;
    private Block [][] board;

    public PuzzleBoardModel() {
        board = generateBoard();
    }

    private Block [][] generateBoard() {
        Block [][] board = new Block[BOARD_DIMENSION][BOARD_DIMENSION];
        for (int i = 0 ; i < BOARD_DIMENSION ; i++) {
            for (int j = 0 ; j < BOARD_DIMENSION ; j++) {
                int number = (i * 3) + j;
                board[i][j] =
                        new Block(new Position(i,j),number);
            }
        }
        return board;
    }

    public Block[][] getBoard() {
        return board;
    }

    public boolean changePlaces(int number1, int number2) {
        Block block1 = findBlock(number1);
        Block block2 = findBlock(number2);
        if (block1 != null && block2 != null && block1.interchange(block2)) {
            this.setChanged();
            this.notifyAll();
            return true;
        } else {
            return false;
        }
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

    public void printBoard() {
        for (Block [] blocks : board) {
            for (Block block : blocks) {
                System.out.print(block.getNumber() + " ");
            }
            System.out.println();
        }
    }
}
