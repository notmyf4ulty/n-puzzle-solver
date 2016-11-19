package gui;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GuiRoot {

    private static GuiRoot instance = null;

    private Stage mainStage;
    private AnchorPane rootPane;
    private Scene mainScene;

    public static GuiRoot getInstance() {
        if (instance == null) {
            instance = new GuiRoot();
        }
        return instance;
    }

    private GuiRoot() {
        mainStage = new Stage();
        rootPane = new AnchorPane();
        mainScene = new Scene(rootPane);
        mainStage.setScene(mainScene);
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public AnchorPane getRootPane() {
        return rootPane;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }
}
