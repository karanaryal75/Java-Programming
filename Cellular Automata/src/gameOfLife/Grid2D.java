/**Karan Aryal
 * CS 251
 * Project four
 */

package gameOfLife;
import elementaryCA.backend.cell.Cell;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Method for generating board, computing the logic and running the animation
 */

public class Grid2D {
    // Used by JavaFX to display the visualization
    private final GridPane GRID_PANE;
    // Width/height of cell
    private final double CELL_SIZE;
    // Current generation of cells
    public List<List<Cell>> board;
    /**Initializing row and col of board**/
    int row,col;

    public Grid2D(GridPane gridPane,
                  List<List<Cell>> board,
                  double cellSize) {
        this.GRID_PANE = gridPane;
        this.board = board;
        this.CELL_SIZE = cellSize;
        // Show the initial generation to the screen
        show();
        row = 10;
        col = 10;
    }

    /**Method for generating new board**/
    private void nextGenBoard(){
        List<List <Cell>> newBoard = new ArrayList<>();
        for(int i = 0; i< board.size(); i++){
            List<Cell> row = board.get(i);
            List<Cell> newRow = new ArrayList<>();
            for (int j = 0; j<row.size(); j++){
                newRow.add(gameRule(i,j));
            }
            newBoard.add(newRow);
        }
        board = newBoard;
    }

    /**
     * Method to call nextGenBoard.
     */
    private void evolve() {
        nextGenBoard();
    }

    /**
     * Method to calculate neighbour value
     * @param row of the board
     * @param col of the board
     * @return int value of neighbour.
     */
    private  int neighbourValue(int row, int col){
        int rowCount = board.size();
        List<Cell> listRow = board.get(row);
        int colCount = listRow.size();
        int neighbour = 0;
        /**Looking the surrounding neighbour**/
        for(int x = 0; x < 3; x++){
            List<Cell> currentRow = board.get((((row + rowCount - 1) % rowCount) + x) % rowCount);
            for (int y = 0; y < 3; y++){
                int indexColumn = (((col + colCount - 1) %colCount) + y) % colCount;
                /**Adding the value to the neighbour**/
                neighbour += Cell.valueOfCell(currentRow.get(indexColumn));
            }
        }
        return neighbour - Cell.valueOfCell(listRow.get(col));
    }


    /**
     * Method for game rule
     * @param row of the board
     * @param col of the board
     * @return Cell
     */
    private Cell gameRule(int row, int col){
        Cell current = board.get(row).get(col);
        int neighbour = neighbourValue(row, col);
        if (current.isOn()){
            /**Implementing rule**/
            if (neighbour < 2 || neighbour > 3){
                return Cell.WHITE;
            }
        }
        else if (neighbour == 3){
            return Cell.BLACK;
        }
        return current;

    }

    /**
     * This function shows the current generation to the JavaFX window
     */
    private void show() {
        GRID_PANE.getChildren().clear();
        int row = 0;
        for(List<Cell> Row : board){
            int col = 0;
            for (Cell cell : Row){
                /**Creating rectangles in the grid and filling it up with color**/
                Rectangle rect = new Rectangle(CELL_SIZE, CELL_SIZE, cell.getColor());
                GRID_PANE.getChildren().add(rect);
                GridPane.setConstraints(rect, col, row);
                col++;
            }
            row++;
        }
    }


    /**
     * This function advances the state of the class to the next generation.
     * It then shows this new generation to the Java FX window.
     */
    public void nextGeneration() {
        show();
        evolve();
    }
}
