/*************************/
/** Karan Aryal          */
/**CS 251                */
/**Project 3             */
/**11/4/2020             */
/*************************/

package main.backend.games.Blackjack;

/**
 * Method to create rank and store value of each individual rank.
 */
public enum Rank {

    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING( 10);
    private int rankValue;

    Rank(int rankValue){
        this.rankValue = rankValue;
    }

    public int get_rankValue() {return rankValue;}

}
