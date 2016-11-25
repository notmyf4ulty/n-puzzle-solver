package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class MainMenu extends AnchorPane {

    private static final int SCENE_WIDTH = 300;
    private static final int SCENE_HEIGHT = 300;

    private GridPane buttonsPane;
    private Button startButton;
    private Button settingsButton;
    private Button exitButton;

    public MainMenu() {
        buttonsPane = new GridPane();
        startButton = new Button("Start");
        settingsButton = new Button("Settings");
        exitButton = new Button("Exit");

        buttonsPane.add(startButton,0,0);
        buttonsPane.add(settingsButton,0,1);
        buttonsPane.add(exitButton,0,2);
        getChildren().add(buttonsPane);

        AnchorPane.setTopAnchor(buttonsPane,10.0);
        AnchorPane.setRightAnchor(buttonsPane,10.0);
        AnchorPane.setBottomAnchor(buttonsPane,10.0);
        AnchorPane.setLeftAnchor(buttonsPane,10.0);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100);
        buttonsPane.getColumnConstraints().add(0,columnConstraints);
        startButton.setMaxWidth(Double.MAX_VALUE);
        settingsButton.setMaxWidth(Double.MAX_VALUE);
        exitButton.setMaxWidth(Double.MAX_VALUE);

        startButton.setOnAction(actionEvent -> {
            GuiRoot.getInstance().getRootPane().getChildren().clear();
            GuiRoot.getInstance().getRootPane().getChildren().add(new PuzzleWindow());
            GuiRoot.getInstance().getMainStage().sizeToScene();
        });
    }


    public GridPane getButtonsPane() {
        return buttonsPane;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public Button getExitButton() {
        return exitButton;
    }
}
