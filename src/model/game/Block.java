package model.game;

public class Block {
    private Position position;
    private Position targetPosition;
    private final int number;

    public Block(Position position, int number) {
        this.position = position;
        this.targetPosition = position;
        this.number = number;
    }

    public Block(Block block) {
        this.position = new Position(block.getPosition());
        this.targetPosition = new Position(block.getTargetPosition());
        this.number = block.getNumber();
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

    public boolean isOnTargetPosition() {
        return position.equals(targetPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        if (number != block.number) return false;
        if (!position.equals(block.position)) return false;
        return targetPosition.equals(block.targetPosition);

    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + targetPosition.hashCode();
        result = 31 * result + number;
        return result;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    public int getNumber() {
        return number;
    }
}
