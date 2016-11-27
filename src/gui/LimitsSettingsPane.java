package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.game.GameModel;
import model.game.MeshLevel;

class LimitsSettingsPane extends AnchorPane {
    private VBox timeLimitSettingsPane;
    private VBox nodeLimitSettingsPane;
    private GameModel gameModel;

    LimitsSettingsPane() {
        gameModel = GameModel.getInstance();
        GridPane meshTypePane = createMeshSettingsPane();
        getChildren().add(meshTypePane);
    }

    private GridPane createMeshSettingsPane() {
        GridPane meshSettingsPane = new GridPane();
        ColumnConstraints firstColumnConstraints = new ColumnConstraints();
        firstColumnConstraints.setPercentWidth(50);
        ColumnConstraints secondColumnConstraints = new ColumnConstraints();
        secondColumnConstraints.setPercentWidth(50);
        meshSettingsPane.getColumnConstraints().addAll(firstColumnConstraints, secondColumnConstraints);
        AnchorPane.setRightAnchor(meshSettingsPane,5.0);
        AnchorPane.setLeftAnchor(meshSettingsPane,5.0);
        timeLimitSettingsPane = createTimeLimitSettingPane();
        timeLimitSettingsPane
                .setStyle("-fx-border-color: black; -fx-border-width: 1 0 1 1; -fx-border-style: solid;");
        timeLimitSettingsPane.setPadding(new Insets(5,5,5,5));
        nodeLimitSettingsPane = createNodeLimitSettingPane();
        nodeLimitSettingsPane
                .setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid;");
        nodeLimitSettingsPane.setPadding(new Insets(5,5,5,5));
        meshSettingsPane.add(timeLimitSettingsPane,0,0);
        meshSettingsPane.add(nodeLimitSettingsPane,1,0);
        return meshSettingsPane;
    }

    private VBox createTimeLimitSettingPane() {
        VBox mainPane = new VBox();
        mainPane.setAlignment(Pos.CENTER);
        Label infoLabel = new Label("Maksymalny czas [s]:");
        TextField textField = new TextField(Integer.toString(gameModel.getTimeLimit()));

        textField.textProperty().addListener((observableValue, s, t1) -> {
            if (t1.matches("^[1-9]\\d*$")) {
                gameModel.setTimeLimit(Integer.parseInt(t1));
            } else {
                textField.setText(s);
            }
        });

        mainPane.getChildren().addAll(infoLabel,textField);
        return mainPane;
    }

    private VBox createNodeLimitSettingPane() {
        VBox mainPane = new VBox();
        mainPane.setAlignment(Pos.CENTER);
        Label infoLabel = new Label("Maksymalna ilość rozwijanych węzłów:");
        TextField textField = new TextField(Integer.toString(gameModel.getNodeLimit()));

        textField.textProperty().addListener((observableValue, s, t1) -> {
            if (t1.matches("^[1-9]\\d*$")) {
                gameModel.setNodeLimit(Integer.parseInt(t1));
            } else {
                textField.setText(s);
            }
        });

        mainPane.getChildren().addAll(infoLabel,textField);
        return mainPane;
    }
}
