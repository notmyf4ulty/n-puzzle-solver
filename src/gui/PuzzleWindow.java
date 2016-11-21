package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.GameModel;
import model.PuzzleBoardModel;

public class PuzzleWindow {

    Scene scene;
    AnchorPane anchorPane;
    VBox vBox;
    HBox hBox;
    PuzzleBoardView puzzleBoardView;
    PuzzleBoardModel puzzleBoardModel;
    TextField textField1;
    TextField textField2;

    public PuzzleWindow() {
        vBox = new VBox();
        hBox = new HBox();
        anchorPane = new AnchorPane();
        puzzleBoardView = new PuzzleBoardView();
        anchorPane.getChildren().add(puzzleBoardView.getMainPane());
        vBox.getChildren().add(anchorPane);
        textField1 = new TextField();
        textField2 = new TextField();
        hBox.getChildren().add(textField1);
        hBox.getChildren().add(textField2);
        Button button = new Button("Click me");
        button.setOnAction(actionEvent ->
            puzzleBoardModel.changePlaces(
                    Integer.parseInt(textField1.getText()),
                    Integer.parseInt(textField2.getText())
            ));
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(button);
        scene = new Scene(vBox);
        puzzleBoardModel = GameModel.getInstance().getPuzzleBoardModel();
    }

    public Scene getScene() {
        return scene;
    }
}
