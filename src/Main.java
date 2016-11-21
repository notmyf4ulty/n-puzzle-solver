import gui.GuiRoot;
import gui.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        GuiRoot guiRoot = GuiRoot.getInstance();
        guiRoot.getMainStage().setTitle("N Puzzle Solver");
        MainMenu mainMenu = new MainMenu();
        guiRoot.getMainStage().setScene(mainMenu.getScene());
        guiRoot.getMainStage().show();
    }

    public static void main(String[] args) {
//        PuzzleBoardModel puzzleBoardModel = new PuzzleBoardModel();
//        puzzleBoardModel.printBoard();
//        System.out.println();
//        puzzleBoardModel.changePlaces(2,5);
//        puzzleBoardModel.printBoard();
        launch(args);
    }
}
