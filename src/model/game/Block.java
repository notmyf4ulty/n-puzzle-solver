package model.game;

/**
 * Model bloczku układanki.
 */
public class Block {

    /**
     * Zajmowana pozycja.
     */
    private Position position;

    /**
     * Docelowa pozycja.
     */
    private final Position targetPosition;

    /**
     * Przechowywana liczba.
     */
    private final int number;

    /**
     * Konstruktor inicjalizujący początkowe wartości pól.
     */
    Block(Position position, int number) {
        this.position = position;
        this.targetPosition = position;
        this.number = number;
    }

    /**
     * Konstruktor tworzący klasę na podstawie już istniejącego obiektu tej klasy.
     * @param block Istniejący obiekt klasy Block.
     */
    Block(Block block) {
        this.position = new Position(block.getPosition());
        this.targetPosition = new Position(block.getTargetPosition());
        this.number = block.getNumber();
    }

    /**
     * Zamienia miejscami aktualny bloczek z innym, istniejącym już bloczkiem.
     * Warunkiem jest, aby bloczki były obok siebie.
     * @param block Drugi element klasy Block.
     * @return true - zamiana miejscami przebiegła pomyślnie. false - nie udało się przestawić bloczków.
     */
    boolean interchangeOnePosition(Block block) {
        if (position.isOneUnitDistance(block.getPosition())) {
            interchange(block);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Zamienia miejscami aktualny bloczek z innym.
     * @param block Blozek do zamiany miejscami.
     */
    private void interchange(Block block) {
        Position thisPosition = new Position(this.position);
        this.position = new Position(block.getPosition());
        block.setPosition(thisPosition);
    }

    /**
     * Sprawdza, czy bloczek jest na pozycji końcowej.
     * @return true - jest. false - nie jest.
     */
    public boolean isOnTargetPosition() {
        return position.equals(targetPosition);
    }

    /**
     * Przeciążenie metody sprawdzającej równość obiektu typu Block.
     * @param o Drugi obiekt.
     * @return true - obiekty są sobie równe wg implementacji kryterium. false - nie są sobie równe.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        return number == block.number &&
                position.equals(block.position) &&
                targetPosition.equals(block.targetPosition);

    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + targetPosition.hashCode();
        result = 31 * result + number;
        return result;
    }

    /**
     * Zwraca pozycję bloczka.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Ustawia nową pozycję bloczka.
     */
    private void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Zwraca pozycję docelową.
     */
    public Position getTargetPosition() {
        return targetPosition;
    }

    /**
     * Zwraca przechowywaną liczbę.
     */
    public int getNumber() {
        return number;
    }
}
