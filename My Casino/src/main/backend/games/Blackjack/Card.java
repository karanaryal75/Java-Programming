/*************************/
/** Karan Aryal          */
/**CS 251                */
/**Project 3             */
/**11/4/2020             */
/*************************/
package main.backend.games.Blackjack;

/**Creating class for the cards in the deck*/

public class Card {
    Suit suit;
    Rank rank;
    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }

    /**
     * Method to determine the value of card
     * @return the value of card
     */
    @Override
    public String toString() {
        return "[" + suit + " " + rank +"] ";
    }
}
