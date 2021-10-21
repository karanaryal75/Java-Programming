/**Karan Aryal
 * CS 251
 * Project four
 */

package gameOfLife;
import elementaryCA.backend.cell.Cell;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Method to ask user to input manually or through files.
 */

public class GameOfLife extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Game of Life");
        System.out.println("Choose file[f] or Enter manually[m]");
        Scanner fileScan = new Scanner(System.in);
        String firstOption = fileScan.next();
        List<String> path;

        /**Case for passing file and reading **/
        if (firstOption.equalsIgnoreCase("f")) {
            System.out.println("Enter the file path ");
            String filepath = fileScan.next();
            path = Files.readAllLines(Paths.get(filepath));
            /**Storing the first line of the input file for row and col**/
            String firstRow = path.get(0);
            path.remove(0);
            String[] rowAndColumn = firstRow.split(" ");
            GridPane root2d = new GridPane();
            double width = 1200;
            double height = 1000;
            Scene scene = new Scene(root2d, width, height);
            primaryStage.setScene(scene);
            primaryStage.show();
            List<List<Cell>> board = new ArrayList<>();
            /**Adding the values to the list of list cell**/
            for (int i = 0; i < path.get(0).length(); i++) {
                List<Cell> row = new ArrayList<>();
                for (int j = 0; j < path.size(); j++) {
                    row.add(Cell.fromChar(path.get(i).charAt(j)));
                }
                board.add(row);
            }
            /**Passing the values to grid for calculation and generation**/
            Grid2D grid2d = new Grid2D(root2d, board, 50);
            Runner2D.run2d(grid2d);

            /**Case for manual entry**/
        }else if (firstOption.equalsIgnoreCase("m")) {
            System.out.println("Enter number of rows: ");
            int r = fileScan.nextInt();
            System.out.println("Enter number of columns: ");
            int c = fileScan.nextInt();
            System.out.println("Enter elements without spacing for the list: ");
            List<String> userVal = new ArrayList<>();
            Scanner scan = new Scanner(System.in);
            /**Adding the value to the list**/
            int count = -1;
            while(count < r-1){
                if (scan.hasNext()) {
                    userVal.add(scan.next());
                    count++;
                }
            }
            fileScan.close();
            GridPane root2d = new GridPane();
            double width = 1200;
            double height = 1000;
            Scene scene = new Scene(root2d, width, height);
            primaryStage.setScene(scene);
            primaryStage.show();
            List<List<Cell>> board = new ArrayList<>();
            /**Adding the values to the list of list cell**/
            for (int i = 0; i < userVal.get(0).length(); i++) {
                List<Cell> row = new ArrayList<>();
                for (int j = 0; j < userVal.size(); j++) {
                    row.add(Cell.fromChar(userVal.get(i).charAt(j)));
                }
                board.add(row);
            }
            /**Passing the values to grid for calculation and generation**/
            Grid2D grid2d = new Grid2D(root2d, board, 50);
            Runner2D.run2d(grid2d);
        }
    }

}
