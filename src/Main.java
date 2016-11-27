import gui.GuiRoot;
import gui.MainPane;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    /**
     * Generuje interfejs użytkownika
     */
    @Override
    public void start(Stage stage) throws Exception {
        GuiRoot guiRoot = GuiRoot.getInstance();
        guiRoot.getMainStage().setTitle("N Puzzle Solver");
        guiRoot.getRootPane().getChildren().clear();
        guiRoot.getRootPane().getChildren().add(new MainPane());
        guiRoot.getMainStage().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
