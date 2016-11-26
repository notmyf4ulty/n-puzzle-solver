package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.algorithm.SearchStat;
import model.game.GameModel;
import model.game.SearchType;

import java.util.concurrent.*;

public class MainPane extends VBox {
    private GameModel gameModel;

    public MainPane() {
        gameModel = GameModel.getInstance();
        HBox puzzleSettingsPane = new HBox();
        puzzleSettingsPane.getChildren().add(new PuzzlePane());
        puzzleSettingsPane.getChildren().add(new SettingsPane());
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
            future.get(60, TimeUnit.SECONDS);
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
            showMessageDialog("Przekroczono czas obliczeń, t > 60s");
        }
    }

    private void showMessageDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wynik rozwiązania");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
