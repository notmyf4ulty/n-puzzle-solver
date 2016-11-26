package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

class FieldPane extends StackPane {
    private static final int FIELD_DIMENSION = 60;
    private Label numberLabel;

    private FieldPane() {
        setMinSize(FIELD_DIMENSION, FIELD_DIMENSION);
        setStyle("-fx-border-color: black;");
        numberLabel = new Label();
        numberLabel.setStyle("-fx-font-size: 20");
        getChildren().add(numberLabel);
    }

    FieldPane(int number) {
        this();
        setNumber(number);
    }

    void setNumber(int number) {
        numberLabel.setText(Integer.toString(number));
    }
}
