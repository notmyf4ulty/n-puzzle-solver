package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.game.GameModel;
import model.game.MeshLevel;
import model.game.MeshType;

class MeshTypeSettingsPane extends AnchorPane {
    private VBox automaticMeshSettingsPane;
    private VBox manualMeshSettingsPane;
    private GameModel gameModel;

    MeshTypeSettingsPane() {
        gameModel = GameModel.getInstance();
        GridPane meshTypePane = createMeshTypeChoicePane();
        getChildren().add(meshTypePane);
    }

    private GridPane createMeshTypeChoicePane() {
        GridPane meshTypePane = new GridPane();
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100);
        meshTypePane.getColumnConstraints().addAll(columnConstraints);
        RowConstraints firstRowConstraints = new RowConstraints();
        firstRowConstraints.setPercentHeight(30);
        RowConstraints secondRowConstraints = new RowConstraints();
        secondRowConstraints.setPercentHeight(70);
        meshTypePane.getRowConstraints().addAll(firstRowConstraints,secondRowConstraints);
        AnchorPane.setRightAnchor(meshTypePane,5.0);
        AnchorPane.setLeftAnchor(meshTypePane,5.0);

        GridPane meshSettingsPane = createMeshSettingsPane();
        VBox meshTypeChoicePane = new VBox();
        meshTypeChoicePane.setAlignment(Pos.CENTER);
        meshTypeChoicePane
                .setStyle("-fx-border-color: black; -fx-border-width: 1 1 0 1; -fx-border-style: solid;");
        Label meshTypeLabel = new Label("Rodzaj pomieszania:");
        GridPane meshTypeRadioGroupPane = createMeshTypeRadioGroupPane();
        meshTypeChoicePane.getChildren().add(meshTypeLabel);
        meshTypeChoicePane.getChildren().add(meshTypeRadioGroupPane);

        meshTypePane.add(meshTypeChoicePane,0,0);
        meshTypePane.add(meshSettingsPane,0,1);

        return meshTypePane;
    }

    private GridPane createMeshTypeRadioGroupPane() {
        final String manualMeshType = "Ręczny";
        final String automaticMeshType = "Automatyczny";
        GridPane meshTypeRadioGroupPane = new GridPane();
        ColumnConstraints firstColumnConstraints = new ColumnConstraints();
        firstColumnConstraints.setPercentWidth(50);
        ColumnConstraints secondColumnConstraints = new ColumnConstraints();
        secondColumnConstraints.setPercentWidth(50);
        meshTypeRadioGroupPane.getColumnConstraints().addAll(firstColumnConstraints, secondColumnConstraints);
        final ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton manualChoice = new RadioButton(manualMeshType);
        manualChoice.setToggleGroup(toggleGroup);
        RadioButton automaticChoice = new RadioButton(automaticMeshType);
        automaticChoice.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                GameModel gameModel = GameModel.getInstance();
                RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
                switch (selectedRadioButton.getText()) {
                    case manualMeshType:
                        gameModel.setMeshType(MeshType.MANUAL);
                        manualMeshSettingsPane.setDisable(false);
                        automaticMeshSettingsPane.setDisable(true);
                        break;
                    case automaticMeshType:
                        gameModel.setMeshType(MeshType.AUTOMATIC);
                        manualMeshSettingsPane.setDisable(true);
                        automaticMeshSettingsPane.setDisable(false);
                        break;
                }
            }
        });
        meshTypeRadioGroupPane.add(manualChoice,0,0);
        GridPane.setHalignment(manualChoice, HPos.CENTER);
        meshTypeRadioGroupPane.add(automaticChoice,1,0);
        GridPane.setHalignment(automaticChoice, HPos.CENTER);
        manualChoice.setSelected(false);
        manualChoice.setSelected(true);

        return meshTypeRadioGroupPane;
    }

    private GridPane createMeshSettingsPane() {
        GridPane meshSettingsPane = new GridPane();
        ColumnConstraints firstColumnConstraints = new ColumnConstraints();
        firstColumnConstraints.setPercentWidth(50);
        ColumnConstraints secondColumnConstraints = new ColumnConstraints();
        secondColumnConstraints.setPercentWidth(50);
        meshSettingsPane.getColumnConstraints().addAll(firstColumnConstraints, secondColumnConstraints);
        AnchorPane.setTopAnchor(meshSettingsPane,5.0);
        AnchorPane.setRightAnchor(meshSettingsPane,5.0);
        AnchorPane.setBottomAnchor(meshSettingsPane,5.0);
        AnchorPane.setLeftAnchor(meshSettingsPane,5.0);
        manualMeshSettingsPane = createManualMeshButtonsPane();
        manualMeshSettingsPane
                .setStyle("-fx-border-color: black; -fx-border-width: 1 0 2 1; -fx-border-style: solid;");
        manualMeshSettingsPane.setPadding(new Insets(5,5,5,5));
        automaticMeshSettingsPane = createMeshLevelRadioGroupPane();
        automaticMeshSettingsPane
                .setStyle("-fx-border-color: black; -fx-border-width: 1 1 2 1; -fx-border-style: solid;");
        automaticMeshSettingsPane.setPadding(new Insets(5,5,5,5));
        meshSettingsPane.add(manualMeshSettingsPane,0,1);
        meshSettingsPane.add(automaticMeshSettingsPane,1,1);
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
        final String lowMeshLevel = "Niski (10 przesunięć)";
        final String mediumMeshLevel = "Średni (100 przesunięć)";
        final String highMeshLevel = "Wysoki (1000 przesunięć)";
        VBox meshLevelRadioGroupPane = new VBox();
        meshLevelRadioGroupPane.setAlignment(Pos.TOP_LEFT);
        Label automaticMeshLevelLabel = new Label("Poziom pomieszania:");
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton lowChoice = new RadioButton(lowMeshLevel);
        lowChoice.setToggleGroup(toggleGroup);
        RadioButton mediumChoice = new RadioButton(mediumMeshLevel);
        mediumChoice.setToggleGroup(toggleGroup);
        RadioButton highChoice = new RadioButton(highMeshLevel);
        highChoice.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            setMeshAccordingToModel((RadioButton) toggleGroup.getSelectedToggle());
        });

        Button meshButton = new Button("Mieszaj");
        meshButton.setOnAction(event -> {
            gameModel.meshPuzzleBoardModel();
        });
        meshButton.setMaxWidth(Double.MAX_VALUE);

        meshLevelRadioGroupPane.getChildren().add(automaticMeshLevelLabel);
        meshLevelRadioGroupPane.getChildren().add(lowChoice);
        meshLevelRadioGroupPane.getChildren().add(mediumChoice);
        meshLevelRadioGroupPane.getChildren().add(highChoice);
        meshLevelRadioGroupPane.getChildren().add(meshButton);
        lowChoice.setSelected(true);

        disableProperty().addListener((observableValue, aBoolean, t1) -> {
            if (aBoolean.equals(false)) {
                setMeshAccordingToModel((RadioButton) toggleGroup.getSelectedToggle());
            }
        });

        return meshLevelRadioGroupPane;
    }

    private void setMeshAccordingToModel(RadioButton radioButton) {
        final String lowMeshLevel = "Niski (10 przesunięć)";
        final String mediumMeshLevel = "Średni (100 przesunięć)";
        final String highMeshLevel = "Wysoki (1000 przesunięć)";
        GameModel gameModel = GameModel.getInstance();
        switch (radioButton.getText()) {
            case lowMeshLevel:
                gameModel.setMeshLevel(MeshLevel.LOW);
                break;
            case mediumMeshLevel:
                gameModel.setMeshLevel(MeshLevel.MEDIUM);
                break;
            case highMeshLevel:
                gameModel.setMeshLevel(MeshLevel.HIGH);
        }
    }
}
