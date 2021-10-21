/*************************/
/** Karan Aryal          */
/**CS 251                */
/**Project 3             */
/**11/4/2020             */
/*************************/
package main.backend.games.Blackjack;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Method to create deck of card and store it in a arraylist.
 */
public class Deck {

    public ArrayList<Card> deckList;

    public Deck(){
        deckList = new ArrayList<>();
    }

    /**
     * Generating the deck of cards and storing in arraylist
     */
    public void generateDeck(){
        for (Suit suits : Suit.values()){
            for(Rank rank : Rank.values()){
                deckList.add(new Card(suits,rank));
            }
        }
        Collections.shuffle(deckList);
    }


    /**
     * Method to withdraw card
     * @return withdraws the first card in the first index of arraylist.
     */
    public Card withdrawCard(){
        return deckList.remove(0);
    }

}
