package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import model.game.GameModel;
import model.game.MeshType;
import model.game.SearchType;

/**
 * Created by przemek on 25.11.16.
 */
public class MeshTypeSettingsPane extends AnchorPane {
    GridPane meshTypeChoicePane;
    GridPane meshSettingsPane;
    GameModel gameModel;

    public MeshTypeSettingsPane() {
        meshTypeChoicePane = createMeshTypeChoicePane();
        getChildren().add(meshTypeChoicePane);
    }

    private GridPane createMeshTypeChoicePane() {
        GridPane meshTypeChoicePane = new GridPane();
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(10);
        rowConstraints.setPercentHeight(90);
        AnchorPane.setTopAnchor(meshTypeChoicePane,5.0);
        AnchorPane.setRightAnchor(meshTypeChoicePane,5.0);
        AnchorPane.setBottomAnchor(meshTypeChoicePane,5.0);
        AnchorPane.setLeftAnchor(meshTypeChoicePane,5.0);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        Label meshTypeLabel = new Label("Mesh type:");
        HBox meshTypeRadioGroupPane = createMeshTypeRadioGroupPane();
        vBox.getChildren().add(meshTypeLabel);
        vBox.getChildren().add(meshTypeRadioGroupPane);
        meshTypeChoicePane.add(vBox,0,0);
        return meshTypeChoicePane;
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
                            break;
                        case "Automatyczny":
                            gameModel.setMeshType(MeshType.AUTOMATIC);
                            break;
                    }
                }
            }
        });
        meshTypeRadioGroupPane.getChildren().add(manualChoice);
        meshTypeRadioGroupPane.getChildren().add(automaticChoice);

        return meshTypeRadioGroupPane;
    }


}
