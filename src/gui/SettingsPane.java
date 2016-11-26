package gui;

import javafx.scene.layout.VBox;
import model.game.GameModel;

class SettingsPane extends VBox {

    private GameModel gameModel;

    SettingsPane() {
        gameModel = GameModel.getInstance();
        SolveTypeSettingsPane solveTypeSettingsPane = new SolveTypeSettingsPane();
        MeshTypeSettingsPane meshTypeSettingsPane = new MeshTypeSettingsPane();
        getChildren().addAll(solveTypeSettingsPane, meshTypeSettingsPane);
    }
}
