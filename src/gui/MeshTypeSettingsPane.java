package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.game.GameModel;
import model.game.MeshLevel;
import model.game.MeshType;

/**
 * Created by przemek on 25.11.16.
 */
public class MeshTypeSettingsPane extends AnchorPane {
    GridPane meshTypePane;
    VBox meshTypeChoicePane;
    GridPane meshSettingsPane;
    VBox automaticMeshSettingsPane;
    VBox manualMeshSettingsPane;
    GameModel gameModel;

    public MeshTypeSettingsPane() {
        gameModel = GameModel.getInstance();
        meshTypePane = createMeshTypeChoicePane();
        getChildren().add(meshTypePane);
    }

    private GridPane createMeshTypeChoicePane() {
        GridPane meshTypePane = new GridPane();
        RowConstraints firstRowConstraints = new RowConstraints();
        firstRowConstraints.setPercentHeight(10);
        RowConstraints secondRowConstraints = new RowConstraints();
        secondRowConstraints.setPercentHeight(90);
        meshTypePane.getRowConstraints().addAll(firstRowConstraints,secondRowConstraints);
        AnchorPane.setTopAnchor(meshTypePane,5.0);
        AnchorPane.setRightAnchor(meshTypePane,5.0);
        AnchorPane.setBottomAnchor(meshTypePane,5.0);
        AnchorPane.setLeftAnchor(meshTypePane,5.0);

        meshTypeChoicePane = new VBox();
        meshTypeChoicePane.setAlignment(Pos.CENTER);
        Label meshTypeLabel = new Label("Rodzaj pomieszania:");
        HBox meshTypeRadioGroupPane = createMeshTypeRadioGroupPane();
        meshTypeChoicePane.getChildren().add(meshTypeLabel);
        meshTypeChoicePane.getChildren().add(meshTypeRadioGroupPane);

        meshSettingsPane = createMeshSettingsPane();

        meshTypePane.add(meshTypeChoicePane,0,0);
        meshTypePane.add(meshSettingsPane,0,1);
        return meshTypePane;
    }

    private HBox createMeshTypeRadioGroupPane() {
        HBox meshTypeRadioGroupPane = new HBox();
        final ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton manualChoice = new RadioButton("Ręczny");
        manualChoice.setToggleGroup(toggleGroup);
        manualChoice.setSelected(true);
        RadioButton automaticChoice = new RadioButton("Automatyczny");
        automaticChoice.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if (!toggle.equals(t1)) {
                    RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
                    switch (selectedRadioButton.getText()) {
                        case "Ręczny":
                            gameModel.setMeshType(MeshType.MANUAL);
                            manualMeshSettingsPane.setDisable(false);
                            automaticMeshSettingsPane.setDisable(true);
                            break;
                        case "Automatyczny":
                            gameModel.setMeshType(MeshType.AUTOMATIC);
                            manualMeshSettingsPane.setDisable(true);
                            automaticMeshSettingsPane.setDisable(false);
                            break;
                    }
                }
            }
        });
        meshTypeRadioGroupPane.getChildren().add(manualChoice);
        meshTypeRadioGroupPane.getChildren().add(automaticChoice);

        return meshTypeRadioGroupPane;
    }

    private GridPane createMeshSettingsPane() {
        GridPane meshSettingsPane = new GridPane();
        meshSettingsPane.setHgap(2);
        meshSettingsPane.setVgap(2);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(50);
        columnConstraints.setPercentWidth(50);
        AnchorPane.setTopAnchor(meshSettingsPane,5.0);
        AnchorPane.setRightAnchor(meshSettingsPane,5.0);
        AnchorPane.setBottomAnchor(meshSettingsPane,5.0);
        AnchorPane.setLeftAnchor(meshSettingsPane,5.0);
        automaticMeshSettingsPane = createMeshLevelRadioGroupPane();
        automaticMeshSettingsPane
                .setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid;");
        manualMeshSettingsPane = createManualMeshButtonsPane();
        manualMeshSettingsPane
                .setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid;");
        meshSettingsPane.add(automaticMeshSettingsPane,1,1);
        meshSettingsPane.add(manualMeshSettingsPane,0,1);
        return meshSettingsPane;
    }

    private VBox createManualMeshButtonsPane() {
        VBox mainPane = new VBox();

        Label manualMeshLabel = new Label("Przesuń pusty bloczek:");

        VBox buttonsPane = new VBox();
        Button upButton = new Button("Góra");
        upButton.setMaxWidth(Double.MAX_VALUE);
        GridPane middleButtonsPane = new GridPane();
        ColumnConstraints firstColumnConstraints = new ColumnConstraints();
        firstColumnConstraints.setPercentWidth(50);
        ColumnConstraints secondColumnConstraints = new ColumnConstraints();
        secondColumnConstraints.setPercentWidth(50);
        middleButtonsPane.getColumnConstraints().addAll(firstColumnConstraints,secondColumnConstraints);
        Button leftButton = new Button("Lewo");
        leftButton.setMaxWidth(Double.MAX_VALUE);
        Button rightButton = new Button("Prawo");
        rightButton.setMaxWidth(Double.MAX_VALUE);
        middleButtonsPane.add(leftButton,0,0);
        middleButtonsPane.add(rightButton,1,0);
        Button bottomButton = new Button("Dół");
        bottomButton.setMaxWidth(Double.MAX_VALUE);

        upButton.setOnAction(actionEvent -> gameModel.moveUp());
        rightButton.setOnAction(actionEvent -> gameModel.moveRight());
        bottomButton.setOnAction(actionEvent -> gameModel.moveBottom());
        leftButton.setOnAction(actionEvent -> gameModel.moveLeft());

        buttonsPane.getChildren().add(upButton);
        buttonsPane.getChildren().add(middleButtonsPane);
        buttonsPane.getChildren().add(bottomButton);

        mainPane.getChildren().add(manualMeshLabel);
        mainPane.getChildren().add(buttonsPane);

        return mainPane;
    }

    private VBox createMeshLevelRadioGroupPane() {
        VBox meshLevelRadioGroupPane = new VBox();
        meshLevelRadioGroupPane.setAlignment(Pos.TOP_LEFT);

        Label automaticMeshLevelLabel = new Label("Poziom pomieszania:");

        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton lowChoice = new RadioButton("Niski");
        lowChoice.setToggleGroup(toggleGroup);
        lowChoice.setSelected(true);
        RadioButton mediumChoice = new RadioButton("Średni");
        mediumChoice.setToggleGroup(toggleGroup);
        RadioButton highChoice = new RadioButton("Duży");
        highChoice.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
                switch (selectedRadioButton.getText()) {
                    case "Niski":
                        gameModel.setMeshLevel(MeshLevel.LOW);
                        break;
                    case "Średni":
                        gameModel.setMeshLevel(MeshLevel.MEDIUM);
                        break;
                    case "Duży":
                        gameModel.setMeshLevel(MeshLevel.HIGH);
                }
            }
        });
        meshLevelRadioGroupPane.getChildren().add(automaticMeshLevelLabel);
        meshLevelRadioGroupPane.getChildren().add(lowChoice);
        meshLevelRadioGroupPane.getChildren().add(mediumChoice);
        meshLevelRadioGroupPane.getChildren().add(highChoice);

        return meshLevelRadioGroupPane;
    }
}
