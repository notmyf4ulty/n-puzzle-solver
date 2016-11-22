package model;

public class Block {
    private Position position;
    private Position targetPosition;
    private final int number;

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
        Position thisPosition = new Position(this.position);
        this.position = new Position(block.getPosition());
        block.setPosition(thisPosition);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }
}
