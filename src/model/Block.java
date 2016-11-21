package model;

public class Block {
    private final Position position;
    private Position targetPosition;
    private int number;

    public Block(Position position, int number) {
        this.position = position;
        this.targetPosition = position;
        this.number = number;
    }

    public boolean interchangeOnePosition(Block block) {
        if (position.isOneUnitDistance(block.getPosition())) {
            interchange(block);
            return true;
        } else {
            return false;
        }
    }

    public void interchange(Block block) {
        int thisNumber = this.number;
        this.number = block.getNumber();
        block.setNumber(thisNumber);
    }

    public Position getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }


    public void setNumber(int number) {
        this.number = number;
    }
}
