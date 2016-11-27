package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.game.GameModel;

/**
 * Pole przechowujące kontrolki umożliwające ustawienie granic dla obliczeń algorytmu.
 * Możliwe jest ograniczenie czasu wykonywania obliczeń oraz liczby rozwijanych węzłów.
 */
class LimitsSettingsPane extends AnchorPane {

    /**
     * Instancja modelu gry.
     */
    private GameModel gameModel;

    /**
     * Konstruktor domyślny.
     */
    LimitsSettingsPane() {
        gameModel = GameModel.getInstance();
        GridPane meshTypePane = createLimitsSettingsPane();
        getChildren().add(meshTypePane);
    }

    /**
     * Tworzy główne pole przechowujące kontrolki ograniczające.
     * @return Pole ze wszystkimi gotowymi kontrolkami.
     */
    private GridPane createLimitsSettingsPane() {
        GridPane limitsSettingsPane = new GridPane();

        ColumnConstraints firstColumnConstraints = new ColumnConstraints();
        firstColumnConstraints.setPercentWidth(50);
        ColumnConstraints secondColumnConstraints = new ColumnConstraints();
        secondColumnConstraints.setPercentWidth(50);
        limitsSettingsPane.getColumnConstraints().addAll(firstColumnConstraints, secondColumnConstraints);

        AnchorPane.setRightAnchor(limitsSettingsPane,5.0);
        AnchorPane.setLeftAnchor(limitsSettingsPane,5.0);

        VBox timeLimitSettingsPane = createTimeLimitSettingPane();
        timeLimitSettingsPane
                .setStyle("-fx-border-color: black; -fx-border-width: 1 0 1 1; -fx-border-style: solid;");
        timeLimitSettingsPane.setPadding(new Insets(5,5,5,5));

        VBox nodeLimitSettingsPane = createNodeLimitSettingPane();
        nodeLimitSettingsPane
                .setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid;");
        nodeLimitSettingsPane.setPadding(new Insets(5,5,5,5));

        limitsSettingsPane.add(timeLimitSettingsPane,0,0);
        limitsSettingsPane.add(nodeLimitSettingsPane,1,0);

        return limitsSettingsPane;
    }

    /**
     * Tworzy pole przechowujące kontrolki ograniczające czas obliczeń
     * @return Pole z gotowymi kontrolkami,
     */
    private VBox createTimeLimitSettingPane() {
        VBox mainPane = new VBox();
        mainPane.setAlignment(Pos.CENTER);

        Label infoLabel = new Label("Maksymalny czas [s]:");

        TextField textField = new TextField(Integer.toString(gameModel.getTimeLimit()));
        textField.textProperty().addListener((observableValue, s, t1) -> {
            if (t1.matches("^[1-9]\\d*$")) { // Pole tekstowe przyjmować może tylko liczby całkowite
                gameModel.setTimeLimit(Integer.parseInt(t1));
            } else {
                textField.setText(s);
            }
        });

        mainPane.getChildren().addAll(infoLabel,textField);

        return mainPane;
    }

    /**
     * Tworzy pole przechowujące kontrolki ograniczające liczbę rozwijanych węzłów.
     * @return Pole z gotowymi kontrolkami.
     */
    private VBox createNodeLimitSettingPane() {
        VBox mainPane = new VBox();
        mainPane.setAlignment(Pos.CENTER);

        Label infoLabel = new Label("Maksymalna ilość rozwijanych węzłów:");

        TextField textField = new TextField(Integer.toString(gameModel.getNodeLimit()));
        textField.textProperty().addListener((observableValue, s, t1) -> {
            if (t1.matches("^[1-9]\\d*$")) { // Pole tekstowe może przyjmować tylko liczby całkowite.
                gameModel.setNodeLimit(Integer.parseInt(t1));
            } else {
                textField.setText(s);
            }
        });

        mainPane.getChildren().addAll(infoLabel,textField);

        return mainPane;
    }
}
