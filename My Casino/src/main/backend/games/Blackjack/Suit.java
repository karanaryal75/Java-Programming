/*************************/
/** Karan Aryal          */
/**CS 251                */
/**Project 3             */
/**11/4/2020             */
/*************************/
package main.backend.games.Blackjack;

/**
 * Method to create suit values.
 */
public enum Suit {
    SPADES("spades"),
    DIAMONDS("diamonds"),
    HEARTS("hearts"),
    CLUBS("clubs");

    private final String suitsValue;

    Suit(String suitsValue){
        this.suitsValue = suitsValue;
    }
    public String getSuitsValue(){return suitsValue;}
}
