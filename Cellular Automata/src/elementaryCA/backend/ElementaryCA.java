/**Karan Aryal
 * CS 251
 * Project four
 */
package elementaryCA.backend;
import elementaryCA.backend.cell.Cell;
import elementaryCA.backend.grid.Grid1D;
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
 * Class where it asks user to read from file or manually
 * and passing to another class to compute Elementary CA in 1D.
 */
public class ElementaryCA extends Application {
    /**
     * Initializing variables
     */
    String  behaviour;
    String stateString;
    List<String> path;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("1D Cellular Automata");
        /**Asking user to operating manually or via opening a file**/
        System.out.println("Do you want to open manually [m] or choose a file[f]");
        Scanner options = new Scanner(System.in);
        String userOptions = options.next();
        /**Checking the entered user's option**/
        if(userOptions.equalsIgnoreCase("m")){

            System.out.println("Enter the initial state for a cell");
            Scanner scanState = new Scanner(System.in);
            stateString = scanState.next();


            System.out.println("Enter the behaviour from (0-255)");
            Scanner scanBehaviour = new Scanner(System.in);
            int value = scanBehaviour.nextInt();
            String binaryVal = Integer.toBinaryString(value);
            System.out.println(binaryVal);

            /**Storing behaviour*/
            behaviour = String.format("%8s", binaryVal).replace(" ", "0");
            List<Cell> currentGen = new ArrayList<>();

            /**Storing initial state in List of Cell*/
            for (char ch : stateString.toCharArray()){
                currentGen.add(Cell.fromChar(ch));
            }
            GridPane root = new GridPane();
            double width = 1200;
            double height = 1000;
            Scene scene = new Scene(root, width, height);

            primaryStage.setScene(scene);
            primaryStage.show();
            /**Passing behaviour and initial state to grid**/
            Grid1D grid = new Grid1D(root, behaviour, currentGen, 50);
            Runner.run(grid);


        }
        else if(userOptions.equalsIgnoreCase("f")){

            System.out.println("Enter the path location");
            Scanner scanFile = new Scanner(System.in);
            String files = scanFile.next();
            /**Getting path of file**/
            path = Files.readAllLines(Paths.get(files));

            /**storing behavior and first generation**/
            String fileBehaviour = path.get(0);
            String fileCurrentGen = path.get(1);

            List<Cell> currentGen = new ArrayList<>();
            for (char ch : fileCurrentGen.toCharArray()){
                currentGen.add(Cell.fromChar(ch));
            }
            GridPane root = new GridPane();
            double width = 1200;
            double height = 1000;
            Scene scene = new Scene(root, width, height);
            primaryStage.setScene(scene);
            primaryStage.show();

            /**Passing list and behaviour to grid for computation**/
            Grid1D grid = new Grid1D(root, fileBehaviour, currentGen, 50);
            Runner.run(grid);



        }



    }
}
