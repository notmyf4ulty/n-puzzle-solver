package gui;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.game.GameModel;

class SettingsPane extends GridPane {

    private GameModel gameModel;

    SettingsPane() {
        gameModel = GameModel.getInstance();
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100);
        getColumnConstraints().add(columnConstraints);
        SolveTypeSettingsPane solveTypeSettingsPane = new SolveTypeSettingsPane();
        solveTypeSettingsPane.setMaxWidth(Double.MAX_VALUE);
        GridPane.getHgrow(solveTypeSettingsPane);
        MeshTypeSettingsPane meshTypeSettingsPane = new MeshTypeSettingsPane();
        meshTypeSettingsPane.setMaxWidth(Double.MAX_VALUE);
        add(solveTypeSettingsPane,0,0);
        add(meshTypeSettingsPane,0,1);
    }
}
