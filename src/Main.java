import gui.GuiRoot;
import gui.PuzzleWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        GuiRoot guiRoot = GuiRoot.getInstance();
        guiRoot.getMainStage().setTitle("N Puzzle Solver");
        guiRoot.getRootPane().getChildren().clear();
        guiRoot.getRootPane().getChildren().add(new PuzzleWindow());
        guiRoot.getMainStage().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
