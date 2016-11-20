package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Created by przemek on 21.11.16.
 */
public class FieldPane extends StackPane {
    private static final int FIELD_DIMENSION = 60;
    Label numberLabel;

    public FieldPane() {
        setMinSize(FIELD_DIMENSION, FIELD_DIMENSION);
        setStyle("-fx-border-color: black;");
        numberLabel = new Label();
        numberLabel.setStyle("-fx-font-size: 20");
        getChildren().add(numberLabel);
    }

    public void setNumber(int number) {
        numberLabel.setText(Integer.toString(number));
    }
}
