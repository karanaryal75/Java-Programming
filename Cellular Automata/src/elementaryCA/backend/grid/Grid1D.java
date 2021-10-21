/**
 * Karan Aryal
 * CS 251
 * Project 4
 */
package elementaryCA.backend.grid;
import elementaryCA.backend.cell.Cell;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import java.util.List;

public class Grid1D {
    // Used by JavaFX to display the visualization
    private final GridPane GRID_PANE;
    // 8 bit string representing the behavior of the CA
    private final String BEHAVIOR;
    // Width/height of cell
    private final double CELL_SIZE;

    // Current generation of cells
    private final List<Cell> currentGen;
    // Current generation (row) being shown to the screen
    private int currentGenIndex;

    Cell[] nextGen;
    Cell left, currentState, right;


    public Grid1D(GridPane gridPane,
                  String behavior,
                  List<Cell> currentGen,
                  double cellSize) {
        this.GRID_PANE = gridPane;
        this.BEHAVIOR = behavior;
        this.currentGen = currentGen;
        this.CELL_SIZE = cellSize;
        this.currentGenIndex = 0;
        /**Creating Cell size**/
        this.nextGen = new Cell[currentGen.size()];
        // Show the initial generation to the screen
        show();
    }

    /**
     * Method to check the left and right cell from current cell
     * and update the board
     */
    public void generate(){
        for (int i = 0; i < currentGen.size(); i++){

            if (i == 0) {
                left = currentGen.get(currentGen.size() - 1);
            }
            else{
                left = currentGen.get(i - 1);
            }
            currentState = currentGen.get(i);
            if (i == (currentGen.size() - 1)){
                right = currentGen.get(0);
            }else{
                right = currentGen.get(i + 1);
            }
            nextGen[i] = gameRule(left,currentState,right);
        }
        /**Printing the board**/
        int z = 0;
        for (Cell cell : nextGen){
            currentGen.set(z, cell);
            z++;
        }
        System.out.println(currentGen);
    }

    /**
     * Method to execute game Rule.
     * @param x Left neighbour
     * @param y Current index
     * @param z Right neighbour
     * @return
     */
    public Cell gameRule(Cell x, Cell y, Cell z){
        if ( x == Cell.BLACK && y == Cell.BLACK && z == Cell.BLACK){
            return Cell.fromChar(BEHAVIOR.charAt(0));
        }
        else if (x == Cell.BLACK && y == Cell.BLACK && z == Cell.WHITE){
            return Cell.fromChar(BEHAVIOR.charAt(1));
        }
        else if (x == Cell.BLACK && y == Cell.WHITE && z == Cell.BLACK){
            return Cell.fromChar(BEHAVIOR.charAt(2));
        }
        else if (x == Cell.BLACK && y == Cell.WHITE && z == Cell.WHITE){
            return Cell.fromChar(BEHAVIOR.charAt(3));
        }
        else if (x == Cell.WHITE && y == Cell.BLACK && z == Cell.BLACK){
            return Cell.fromChar(BEHAVIOR.charAt(4));
        }
        else if (x == Cell.WHITE && y == Cell.BLACK && z == Cell.WHITE){
            return Cell.fromChar(BEHAVIOR.charAt(5));
        }
        else if (x == Cell.WHITE && y == Cell.WHITE && z == Cell.BLACK){
            return Cell.fromChar(BEHAVIOR.charAt(6));
        }
        else if (x == Cell.WHITE && y == Cell.WHITE && z == Cell.WHITE){
            return Cell.fromChar(BEHAVIOR.charAt(7));
        }
        return null;
    }

    /**
     * TODO: Fill in the logic below
     * I would suggest starting off by hard coding one of the rules,
     * such as rule 30, then generalize from there.
     * This function evolves the current generation to the next generation
     * using the current rule set given by the behavior string.
     */
    private void evolve() {
        generate();

    }

    /**
     * This function shows the current generation to the JavaFX window
     */
    private void show() {
        int colIndex = 0;
        // Create new rectangles to show for the current generation
        for (Cell cell : currentGen) {
            // Create a rectangle to represent the cell
            Rectangle rect = new Rectangle(CELL_SIZE, CELL_SIZE, cell.getColor());
            // Add it to the JavaFX graph
            GRID_PANE.add(rect, colIndex, currentGenIndex);
            // Tell it where to show it on the screen
            //GridPane.setConstraints(rect, );
            // Go to the next cell
            colIndex++;
        }
        currentGenIndex++;
    }

    /**
     * This function advances the state of the class to the next generation.
     * It then shows this new generation to the Java FX window.
     */
    public void nextGeneration() {
        evolve();
        show();
    }
}
