package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.game.GameModel;

public class MainPane extends VBox {
    private GameModel gameModel;
    ListView<String> messageListView;

    public MainPane() {
        gameModel = GameModel.getInstance();
        HBox puzzleSettingsPane = new HBox();
        puzzleSettingsPane.getChildren().add(new PuzzlePane());
        puzzleSettingsPane.getChildren().add(new SettingsPane());
        Button solveButton = createSolveButton();
        messageListView = new ListView<>();
        messageListView.setMaxWidth(Double.MAX_VALUE);
        messageListView.setMinHeight(200);
        messageListView.setMaxHeight(200);
        getChildren().addAll(puzzleSettingsPane,solveButton,messageListView);
        gameModel.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                messageListView.getItems().add(s);
            }
        });
    }

    private Button createSolveButton() {
        Button button = new Button("Rozwiąż");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(actionEvent -> gameModel.solveSinglePuzzle());
        return button;
    }
}
