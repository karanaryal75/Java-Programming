/**
 * Karan Aryal
 * CS 351: Lab 2
 * 02/28/21
 */
package parent_class;

/**
 * Class which has each domino value known as pips.
 * which gives its integer value
 */
public class Domino_Value {

    public enum Pips {
        ZERO(0),
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6);

        private final int value;
        Pips(int firstVal){
            this.value = firstVal;
        }

        /**
         * Method to return the value of a domino
         * @return integer value
         */
        public int getValue(){
            return value;
        }
    }
}
