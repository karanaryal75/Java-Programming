/**
 * Karan Aryal
 * CS 351: Lab 2
 * 02/28/21
 */
package domino_gui;
import domino_console.Domino_Game;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import parent_class.Boneyard;
import parent_class.Domino;

import java.util.ArrayList;
import static domino_gui.GUI_Main.*;

/**
 * Class where gui  is displayed
 */
public class DominoGUI extends Application {
    public static ArrayList<HBox> guiBoneyard = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Setting title to the screen
        primaryStage.setTitle("Domino");
        gui_Domino();
        Boneyard b = new Boneyard();
        b.generateBoneyard();
        b.get_player_domino();
        b.get_computer_domino();
        HBox player_gui_board = new HBox(10);

        //HBox for the gui board
        HBox gameBoard_gui = new HBox(10);
        gameBoard_gui.setAlignment(Pos.CENTER);
        gameBoard_gui.setBackground(new Background(new BackgroundFill(Color.CORAL,null,null)));

        //Text box to display the boneyard and computer count.
        HBox hBox = new HBox();
        Text playerText = new Text("Boneyard contains: "+ b.boneyard.size() + " Dominoes");
        Text computerText = new Text("Computer contains: "+ b.computer_domino.size() + " Dominoes");
        playerText.setFont(Font.font(20));
        computerText.setFont(Font.font(20));

        //Label to show the title of the game
        Label title = new Label("Domino Game");
        title.setFont(Font.font(50));
        hBox.getChildren().add(title);
        hBox.setAlignment(Pos.CENTER);

        //Draw from boneyard button
        Button drawButton = new Button();
        drawButton.setText("Draw from Boneyard");
        HBox button_hBox = new HBox(drawButton);
        button_hBox.setAlignment(Pos.BOTTOM_RIGHT);

        //Adds in the player gui board
        for(int i = 0; i<b.player_domino.size(); i++){
            player_gui_board.getChildren().addAll(b.player_domino.get(i).get_hBox());
        }
        player_gui_board.setAlignment(Pos.CENTER);

        //Adds the domino from the player to the gui board
        for(int i = 0; i<player_gui_board.getChildren().size(); i++) {
            Domino dom = b.player_domino.get(i);
            HBox box = (HBox) player_gui_board.getChildren().get(i);

            //With left click, the domino adds to the left
            box.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    b.gameBoard.add(0,dom);
                    Domino_Game.computerPlay(b.computer_domino, b.gameBoard);
                    gameBoard_gui.getChildren().clear();
                    for (int j = 0; j<b.gameBoard.size(); j++){
                        //Displays the board in gui
                        gameBoard_gui.getChildren().add(b.gameBoard.get(j).get_hBox());
                    }
                    b.removeDomino(dom);
                }
                //With right click, domino adds to the right of the gui board
                else if (event.getButton() == MouseButton.SECONDARY) {
                    b.gameBoard.add(b.gameBoard.size(),dom);
                    Domino_Game.computerPlay(b.computer_domino, b.gameBoard);
                    gameBoard_gui.getChildren().clear();
                    for (int z = 0; z<b.gameBoard.size(); z++){
                        //Displays the board in gui
                        gameBoard_gui.getChildren().add(b.gameBoard.get(z).get_hBox());
                    }
                    b.removeDomino(dom);
                }
            });
        }

        //Adds the domino in vbox and displays in the screen
        VBox vBox = new VBox(50);
        vBox.getChildren().addAll(hBox, playerText,computerText,gameBoard_gui,player_gui_board,button_hBox);
        BorderPane pane = new BorderPane(vBox);
        primaryStage.setScene(new Scene(pane, 900, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}