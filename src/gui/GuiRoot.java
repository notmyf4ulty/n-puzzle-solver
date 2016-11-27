package gui;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Pierwotny kontener, na którym dodawane będę pozostałe komponenty interfejsu użytkownika.
 * Klasa typu Singleton.
 */
public class GuiRoot {

    /**
     * instancja klasy.
     */
    private static GuiRoot instance = null;

    /**
     * Główna estrada. Na niej postawiona zostanie scena.
     */
    private Stage mainStage;

    /**
     * Pierwotne pole.
     */
    private AnchorPane rootPane;

    public static GuiRoot getInstance() {
        if (instance == null) {
            instance = new GuiRoot();
        }
        return instance;
    }

    /**
     * Domyślny konstruktor. POsiada modyfikator prywatny, jako jedno z założeń wzorca Singleton.
     */
    private GuiRoot() {
        mainStage = new Stage();
        rootPane = new AnchorPane();
        Scene mainScene = new Scene(rootPane);
        mainStage.setScene(mainScene);
    }

    /**
     * Getter głównej estrady.
     * @return Główna estrada.
     */
    public Stage getMainStage() {
        return mainStage;
    }

    /**
     * Getter pierwotnego pola.
     * @return Pierwotne pole.
     */
    public AnchorPane getRootPane() {
        return rootPane;
    }
}
