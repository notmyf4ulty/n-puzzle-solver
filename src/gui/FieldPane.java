package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Pole reprezentujące jeden bloczek układanki.
 */
class FieldPane extends StackPane {
    /**
     * Rozmiar pola.
     */
    private static final int FIELD_DIMENSION = 60;

    /**
     * Etykieta do wyświetlania numeru na polu.
     */
    private Label numberLabel;

    /**
     * Domyślny konstruktor.
     */
    private FieldPane() {
        setMinSize(FIELD_DIMENSION, FIELD_DIMENSION);
        setStyle("-fx-border-color: black;");
        numberLabel = new Label();
        numberLabel.setStyle("-fx-font-size: 20");
        getChildren().add(numberLabel);
    }

    /**
     * Konstruktor oparty o liczbę. Będzie ona przypisana do etykiety numberLabel.
     * @param number Numer do przypisania.
     */
    FieldPane(int number) {
        this();
        setNumber(number);
    }

    /**
     * Setter etykiety z numerem.
     * @param number Nowy numer.
     */
    void setNumber(int number) {
        String textToSet = "";
        if (number != 0) {
            textToSet = Integer.toString(number);
        }
        numberLabel.setText(textToSet);
    }
}
