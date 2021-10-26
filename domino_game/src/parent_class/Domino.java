/**
 * Karan Aryal
 * CS 351: Lab 2
 * 02/28/21
 */
package parent_class;
import javafx.scene.layout.HBox;

/**
 * Class which returns, each domino first and second value
 * and its respective HBox id.
 */
public class Domino{
    Domino_Value.Pips first_value;
    Domino_Value.Pips second_value;
    HBox hBox;
    public Domino(Domino_Value.Pips value1, Domino_Value.Pips value2, HBox hBox1){
        this.first_value = value1;
        this.second_value = value2;
        this.hBox = hBox1;
    }

    /**
     * Method to rotate the domino
     */
    public void rotate(){
        Domino_Value.Pips swap;
        swap = this.first_value;
        this.first_value = this.second_value;
        this.second_value = swap;
    }

    /**
     * Method to get the first value of the domino
     * @return first Value
     */
    public Domino_Value.Pips getFirst_value(){
        return first_value;
    }

    /**
     * Method to get the second value of the domino
     * @return first Value
     */
    public Domino_Value.Pips getSecond_value(){
        return second_value;
    }

    /**
     * Method to get the HBox of the domino
     * @return HBox
     */
    public HBox get_hBox(){
        return hBox;
    }


    /**
     * toString method to print the domino on the console.
     * @return
     */
    public String toString(){
        return "[" + first_value + "|" + second_value +"] ";
    }

}