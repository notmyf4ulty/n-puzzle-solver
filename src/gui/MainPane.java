package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.algorithm.SearchStat;
import model.game.BoardDimension;
import model.game.GameModel;
import java.util.concurrent.*;

public class MainPane extends VBox {
    private GameModel gameModel;
    private PuzzlePane puzzlePane;
    private SettingsPane settingsPane;

    public MainPane() {
        gameModel = GameModel.getInstance();
        HBox puzzleSettingsPane = new HBox();
        puzzlePane = new PuzzlePane();
        settingsPane = new SettingsPane();
        prepareSettingsDimensionToggleGroup();
        puzzleSettingsPane.getChildren().add(puzzlePane);
        puzzleSettingsPane.getChildren().add(settingsPane);
        Button solveButton = createSolveButton();
        Button saveMeshButton = new Button("Zapamiętaj pomieszanie");
        saveMeshButton.setOnAction(event -> gameModel.saveMesh());
        saveMeshButton.setMaxWidth(Double.MAX_VALUE);
        Button loadMeshButton = new Button("Odtwórz ostatnie pomieszanie");
        loadMeshButton.setOnAction(event -> gameModel.loadMesh());
        loadMeshButton.setMaxWidth(Double.MAX_VALUE);
        Button resetMeshButton = new Button("Wyczyść pomieszanie");
        resetMeshButton.setOnAction(event -> gameModel.resetMesh());
        resetMeshButton.setMaxWidth(Double.MAX_VALUE);

        getChildren().addAll(puzzleSettingsPane,
                saveMeshButton,
                loadMeshButton,
                resetMeshButton,
                solveButton);
    }

    private void prepareSettingsDimensionToggleGroup() {
        final String x3Dimension = "3x3";
        final String x4Dimension = "4x4";
        final String x5Dimension = "5x5";
        final ToggleGroup toggleGroup = settingsPane.getToggleGroup();
        StackPane puzzleBoardPane = puzzlePane.getPuzzleBoardPane();
        toggleGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            GameModel gameModel = GameModel.getInstance();
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            switch (selectedRadioButton.getText()) {
                case x3Dimension:
                    gameModel.changeBoardDimension(BoardDimension.X3_DIMENSION);
                    break;
                case x4Dimension:
                    gameModel.changeBoardDimension(BoardDimension.X4_DIMENSION);
                    break;
                case x5Dimension:
                    gameModel.changeBoardDimension(BoardDimension.X5_DIMENSION);
                    break;
            }
            puzzleBoardPane.getChildren().clear();
            puzzleBoardPane.getChildren().add(new PuzzleBoardGrid().getMainPane());
        });
    }

    private Button createSolveButton() {
        Button button = new Button("Rozwiąż");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(actionEvent -> solveButtonClicked());
        return button;
    }

    private void solveButtonClicked() {
        Thread solveThread = new Thread() {
            @Override
            public void run() {
                gameModel.solve();
            }
        };

        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future future = executor.submit(solveThread);
        executor.shutdown();

        try {
            long solveStartTime = System.currentTimeMillis();
            future.get(gameModel.getTimeLimit(), TimeUnit.SECONDS);
            long solveFinishTime = System.currentTimeMillis();
            String resultMessage = "";
            if (!gameModel.isLastComputationFailFlag()) {
                SearchStat searchStat = gameModel.getSearchStat();
                resultMessage = "Rozwiązano zadanie\n";
                resultMessage += "Czas rozwiązania: ";
                resultMessage += (((double)(solveFinishTime - solveStartTime)) / 1000) + "s\n";
                resultMessage += "Długość ścieżki: " + searchStat.getPathDepth();
            } else {
                resultMessage = "Błąd obliczeń";
            }
            showMessageDialog(resultMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            showMessageDialog("Przekroczono czas obliczeń, t >" + gameModel.getTimeLimit() + "s");
        }
    }

    private void showMessageDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wynik rozwiązania");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public PuzzlePane getPuzzlePane() {
        return puzzlePane;
    }

    public SettingsPane getSettingsPane() {
        return settingsPane;
    }
}
