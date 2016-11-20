package model;

public class Block {
    private final Position position;
    private int number;

    public Block(Position position, int number) {
        this.position = position;
        this.number = number;
    }

    public boolean interchange(Block block) {
        int thisNumber = this.number;
        if (position.isOneUnitDistance(block.getPosition())) {
            this.number = block.getNumber();
            block.setNumber(thisNumber);
            return true;
        } else {
            return false;
        }
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
