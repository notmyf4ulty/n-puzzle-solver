package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.algorithm.*;
import model.game.GameModel;
import model.PuzzleBoardModel;
import model.game.PuzzleBoardNode;

public class PuzzleWindow {

    Scene scene;
    AnchorPane anchorPane;
    VBox vBox;
    HBox hBox;
    PuzzleBoardView puzzleBoardView;
    PuzzleBoardModel puzzleBoardModel;
    GameModel gameModel;
    TextField textField1;
    TextField textField2;

    public PuzzleWindow() {
        vBox = new VBox();
        hBox = new HBox();
        anchorPane = new AnchorPane();
        puzzleBoardView = new PuzzleBoardView();
        anchorPane.getChildren().add(puzzleBoardView.getMainPane());
        vBox.getChildren().add(anchorPane);
        textField1 = new TextField();
        textField2 = new TextField();
        hBox.getChildren().add(textField1);
        hBox.getChildren().add(textField2);
        Button button = new Button("Click me");
        button.setOnAction(actionEvent -> {
            Node rootNode = new PuzzleBoardNode(puzzleBoardModel,0,null,new ManhattanDistanceHeristic());
            Node targetNode =
                    new PuzzleBoardNode(gameModel.getTargetPuzzleBoardModel(),0,null,new ManhattanDistanceHeristic());
            InformativeSearch informativeSearch = new AStar(rootNode,targetNode);
            puzzleBoardModel.setBoard(((PuzzleBoardNode) informativeSearch.fullSearch()).getPuzzleBoardModel().getCopyBoard());
//            AStarSearch aStarSearch = new AStarSearch(puzzleBoardModel);
//            puzzleBoardModel.setBoard(aStarSearch.search().getCopyBoard());
//            aStarSearch.printPuzzle(puzzleBoardModel);
//            try {
//                puzzleBoardModel.changePlacesOnePosition(
//                        Integer.parseInt(textField1.getText()),
//                        Integer.parseInt(textField2.getText())
//                );
//            } catch (NumberFormatException e) {
//                System.out.println("Cannot convert field's text into number.");
//            }
        });
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(button);
        scene = new Scene(vBox);
        gameModel = GameModel.getInstance();
        puzzleBoardModel = gameModel.getPuzzleBoardModel();
    }

    public Scene getScene() {
        return scene;
    }
}
