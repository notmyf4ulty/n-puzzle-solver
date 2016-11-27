package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.game.SearchStat;
import model.game.BoardDimension;
import model.game.GameModel;
import java.util.concurrent.*;

/**
 * Główne pole przechowujące wszystkie komponenty interfejsu użytkownika.
 */
public class MainPane extends VBox {

    /**
     * Instancja modelu gry.
     */
    private GameModel gameModel;

    /**
     * Pole z układanką.
     */
    private PuzzlePane puzzlePane;

    /**
     * Pole z ustawieniami.
     */
    private SettingsPane settingsPane;

    /**
     * Domyślny konstruktor tworzy wszystkie potrzebne pola i kontrolki.
     */
    public MainPane() {
        gameModel = GameModel.getInstance();
        HBox puzzleSettingsPane = new HBox();
        puzzlePane = new PuzzlePane();
        settingsPane = new SettingsPane();
        createSettingsDimensionToggleGroup();
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

    /**
     * Tworzy pole z ustawieniami dymiaru planszy.
     */
    private void createSettingsDimensionToggleGroup() {
        final String x3Dimension = "3x3";
        final String x4Dimension = "4x4";
        final String x5Dimension = "5x5";


        final ToggleGroup toggleGroup = settingsPane.getToggleGroup();
        toggleGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            GameModel gameModel = GameModel.getInstance();
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            // W zależności od wybranej kontrolki, ustawia odpowieni wymiar w modelu gry.
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

            // Przypisuje nowy wymiar układanki.
            StackPane puzzleBoardPane = puzzlePane.getPuzzleBoardPane();
            puzzleBoardPane.getChildren().clear();
            puzzleBoardPane.getChildren().add(new PuzzleBoardGrid().getMainPane());
        });
    }

    /**
     * Tworzy przycisk rozwiązujący układankę.
     * @return Gotowy przycisk.
     */
    private Button createSolveButton() {
        Button button = new Button("Rozwiąż");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(actionEvent -> solveButtonClicked());
        return button;
    }

    /**
     * Obsługuje rozwiązanie układanki z poziomu interfejsu użytkownika.
     */
    private void solveButtonClicked() {

        // Tworzony jest wątek, który następnie przekazany zostanie do specjalnego typu klasy ograniczającego
        // czas na obliczenia. W wątku przeprowadzone zostanie rozwiązanie układanki.
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
            // Pobiera czas w celu późniejszego wyświetlenia na ekranie.
            long solveStartTime = System.currentTimeMillis();
            // Wykonuje  obliczenia z ograniczeniem czasowym
            future.get(gameModel.getTimeLimit(), TimeUnit.SECONDS);
            long solveFinishTime = System.currentTimeMillis();

            String resultMessage;

            // Po wykonaniu obliczeń pobierana jest klasa ze statystykami rozwiązania zadania.
            SearchStat searchStat = gameModel.getSearchStat();
            // Jeżeli podczas obliczeń nie napotkano błędów, przypisz statystyki do wiadomości, która wyświetlona
            // zostanie na ekranie. W pozostałych ywpadkach przekażd opowiednie komunikaty.
            if (searchStat !=  null) {
                if (!searchStat.isNodesLimitError()) {
                    resultMessage = "Rozwiązano zadanie\n";
                    resultMessage += "Czas rozwiązania: ";
                    resultMessage += (((double) (solveFinishTime - solveStartTime)) / 1000) + "s\n";
                    resultMessage += "Liczba rozwiniętych węzłów: " + searchStat.getVisitedNodesNumber() + "\n";
                    resultMessage += "Długość ścieżki: " + searchStat.getPathDepth() +"\n";
                    resultMessage += "Rozwinięta ścieżka:\n";
                    resultMessage += searchStat.printPath();
                } else {
                    resultMessage = "Przekroczono limit rozwiniętych węzłów.";
                }
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

    /**
     * Tworzy okno z wiadomością dotyczącą wyniku rozwiązania.
     * @param message Wyświetlana wiadomośc.
     */
    private void showMessageDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wynik rozwiązania");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
