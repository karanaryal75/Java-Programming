/**Karan Aryal
 * CS 251
 * Project four
 */
package elementaryCA.backend.cell;
import javafx.scene.paint.Color;

/**
 * Enum class which contains color and value of Cell
 */
public enum Cell {
    /**
     * Initializing cells with its value.
     */
    WHITE(false, 0),
    BLACK(true, 1);

    private final int VALUE;
    private final boolean STATE;

    Cell(boolean state, int value) {

        this.STATE = state;
        this.VALUE = value;
    }

    /**
     * Method that returns the integer value of cell
     * @param cell present in a certain index on the board
     * @return integer value
     */
    public static int valueOfCell(Cell cell){
        if (cell == Cell.WHITE){
            return WHITE.VALUE;
        }
        else if (cell == Cell.BLACK) {
            return BLACK.VALUE;
        }
        return -1;

    }

    /**
     * Checking the state
     * @return state
     */
    public boolean isOn() {
        return STATE;
    }

    /**
     * Method to get the color of the element present in the board
     * @return color
     */
    public Color getColor() {
        if (this == WHITE) {
            return Color.WHITE;
        }
        else {
            return Color.BLACK;
        }
    }

    /**
     * Method that checks if the board contains 0 or 1's
     * throws exception if the board contains other value that 0 and 1.
     * @param c character value at a certain index
     * @return Cell
     * @throws IllegalArgumentException
     */
    public static Cell fromChar(char c) throws IllegalArgumentException {
        if (c == '0') {
            return WHITE;
        }
        else if (c == '1') {
            return BLACK;
        }
        else {
            throw new IllegalArgumentException("Input char must be either 0 or 1.");
        }
    }
}
