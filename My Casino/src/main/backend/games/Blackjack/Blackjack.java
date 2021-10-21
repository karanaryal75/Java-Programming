/*************************/
/** Karan Aryal          */
/**CS 251                */
/**Project 3             */
/**11/4/2020             */
/*************************/
package main.backend.games.Blackjack;
import main.backend.Utils;
import main.backend.player.Player;
import java.util.*;
import main.backend.games.Game;

/**
 * Class where we execute Blackjack game.
 */
public class Blackjack extends Game{

    /**Creating variable for the program*/
    public ArrayList<Card> cardList;
    public ArrayList<Card> playerHand;
    public ArrayList<Card> dealerHand;
    Deck deck;
    int playerCard_value;
    int player_deckValue;
    int dealerCard_value ;
    int dealerDeck_value;
    int betMoney;
    int playerHandValue = 0;
    int dealerHandValue = 0;


    public  Blackjack(Scanner scanner, Player player) {
        super(scanner, player);
        /**Initializing variable*/
        cardList  = new ArrayList<>();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        deck = new Deck();
        dealerCard_value = 0;

    }

    /**
     * Method to get the value of the player hand
     * @return value of player hand
     */
    public int getPlayerHandValue(){
        playerHandValue = 0;
        for(Card card : playerHand){
            playerHandValue += card.getRank().get_rankValue();
        }
        return  playerHandValue;
    }

    /**
     * Method to get the value of the dealer hand
     * @return value of the dealer hand
     */
    public int getDealerHandValue(){
        dealerHandValue = 0;
        for(Card card : dealerHand){
            dealerHandValue += card.getRank().get_rankValue();
        }
        return  dealerHandValue;
    }

    /**
     * Method executed when the user or dealer wants to use Hit as their option.
     */
    public void hit(){
        playerHand.add(deck.withdrawCard());
        getPlayerHandValue();
    }

    /**
     * Method executed when the user or dealer wants to use stand as their option.
     */
    public void stand(){
        for (Card card : playerHand) {
            player_deckValue = card.getRank().get_rankValue();
            playerCard_value = playerCard_value + player_deckValue;
        }
        System.out.println("Player Card Value" + playerCard_value);
        dealerVersusPlayer();
    }

    /**
     * Method executed when the user or dealer wants to use doubleDown as their option.
     */
    public void doubleDown(){
        betMoney += betMoney;
    }

    /**
     * Method executed when the user or dealer wants to use surrender as their option.
     */
    public void surrender(){
        player.loseChips(betMoney/2);
    }

    /**
     * Method to compare dealer and player hand
     */
    public void compareDealer(){
        System.out.println("Dealer second card " + dealerHand.get(1) );
        System.out.println("Dealer first and second card " + getDealerHandValue());
        int dealersTotal = getDealerHandValue();
        /**Condition true if dealer hand total value is less than 16*/
        if (dealersTotal <= 16){
            dealerHand.add(deck.withdrawCard());
            System.out.println("Dealer three cards value " + getDealerHandValue());
        }

        /**Condition true if dealer hand total value is more than 17*/
        else if (dealersTotal >= 17){
            for (Card card : dealerHand) {
                dealerDeck_value = card.getRank().get_rankValue();
                dealerCard_value = dealerCard_value + dealerDeck_value;
            }
            System.out.println("Dealer Card Value" + dealerCard_value);
        }
        /**Condition true if dealer hand total value is more than 21*/

        else if (dealersTotal > 21){
            System.out.println("Dealer lost the game");
            player.gainChips(betMoney * 2);
        }
    }

    /**
     * reseting all the values if the user wants to continue to play the game.
     */
    public void resetValues(){
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        playerHandValue = 0;
        dealerHandValue = 0;
        player_deckValue = 0;
        playerCard_value = 0;
        dealerCard_value = 0;
        dealerDeck_value = 0;
        deck = new Deck();

    }

    /**
     * Comparing the hand value of dealer and player
     * and determining the winner
     */
    public void dealerVersusPlayer(){
        /**Checks if the value of player hand is more than 21*/
        if(playerHandValue > 21){
            System.out.println("Sorry, you lost!");
            player.loseChips(betMoney);
        }
        /**Checks if the value of dealer hand is more than 21*/
        else if (dealerHandValue > 21){
            System.out.println("You win!");
            player.gainChips(betMoney * 2);
        }
        /**Checks if the valye of player and dealer is equal*/
        else if(playerHandValue == dealerHandValue){
            player.gainChips(betMoney);
        }
        /**Checks if the value of player hand is more than dealer hand*/
        else if (playerHandValue > dealerHandValue){
            System.out.println("You Win");
            player.gainChips(betMoney + betMoney * 2 );
        }
    }

    /**
     * Main method where the game Black Jack is exectuted.
     */
    @Override
    protected void play() {
        /**Prompts the user asking if he/she wants to play the game or not*/
        System.out.println("What would you like to do?");
        System.out.println("[p] bet on Blackjack");
        System.out.println("[q] walk away");

        String choice = scanner.next();
        int aceValue;

        switch (choice){
            case "p":
                resetValues();
                /**generating deck*/
                deck.generateDeck();
                System.out.println("How much would you like to bet?");
                betMoney = scanner.nextInt();
                player.loseChips(betMoney);

                /**condition for the user to enter the bet of multiple of 10*/
                if ((betMoney % 10) == 0) {
                    /**Withdrawing card from deck*/
                    playerHand.add(deck.withdrawCard());
                    System.out.println("Players first card " + playerHand.get(0));
                    /**Asking user to set the value of ACE card as 1 or 10*/
                    if (playerHand.get(0).getRank().equals(Rank.ACE)) {
                        System.out.println("Do you want to select the value of Ace as 1 or 10");
                        aceValue = scanner.nextInt();
                        if (aceValue == 10) {
                            playerHandValue = playerHandValue + 9 +
                                    playerHand.get(0).getRank().get_rankValue();
                        } else if (aceValue == 1) {
                            playerHandValue = playerHandValue +
                                    playerHand.get(0).getRank().get_rankValue();

                        }
                    }
                    playerHandValue += playerHand.get(0).getRank().get_rankValue();


                    playerHand.add(deck.withdrawCard());
                    System.out.println("Players second card" + playerHand.get(1));

                    /**Asking user to set the value of ACE card as 1 or 10*/
                    if (playerHand.get(1).getRank().equals(Rank.ACE)) {
                        System.out.println("Do you want to select the value of Ace as 1 or 10");
                        aceValue = scanner.nextInt();
                        if (aceValue == 10) {
                            playerHandValue = playerHandValue + 9 +
                                    playerHand.get(1).getRank().get_rankValue();
                        } else if (aceValue == 1) {
                            playerHandValue = playerHandValue +
                                    playerHand.get(1).getRank().get_rankValue();

                        }
                    }
                    playerHandValue += playerHand.get(1).getRank().get_rankValue();

                    /**Printing the value of two card deck*/
                    System.out.println("Player 1st and 2nd card value " + playerHandValue);

                    dealerHand.add(deck.withdrawCard());
                    if (dealerHand.get(0).getRank().equals(Rank.ACE)) {
                        if (dealerHandValue <= 16) {
                            dealerHandValue = dealerHandValue + 9 +
                                    dealerHand.get(0).getRank().get_rankValue();
                        } else{
                            dealerHandValue = dealerHandValue +
                                    dealerHand.get(0).getRank().get_rankValue();
                        }
                    }
                    /**Printing out the first card of dealer hand*/
                    System.out.println("Dealer's first card " + dealerHand.get(0));


                    dealerHand.add(deck.withdrawCard());
                    System.out.println("Dealer's second card is face down.");

                    /**check natural black jack for Player.*/
                    if (playerHand.get(0).getRank().equals(Rank.ACE) &&
                            playerHand.get(1).getRank().equals(Rank.KING)) {
                        System.out.println("Natural Blackjack");
                        player.gainChips(betMoney += betMoney * 3);
                        break;
                    } else if (playerHand.get(0).getRank().equals(Rank.ACE) &&
                            playerHand.get(1).getRank().equals(Rank.QUEEN)) {
                        System.out.println("Natural Blackjack");
                        player.gainChips(betMoney += betMoney * 3);
                        break;
                    } else if (playerHand.get(0).getRank().equals(Rank.ACE) &&
                            playerHand.get(1).getRank().equals(Rank.JACK)) {
                        System.out.println("Natural Blackjack");
                        player.gainChips(betMoney += betMoney * 3);
                        break;
                    } else if (playerHand.get(0).getRank().equals(Rank.KING) &&
                            playerHand.get(1).getRank().equals(Rank.ACE)) {
                        System.out.println("Natural Blackjack");
                        player.gainChips(betMoney += betMoney * 3);
                        break;
                    } else if (playerHand.get(0).getRank().equals(Rank.QUEEN) &&
                            playerHand.get(1).getRank().equals(Rank.ACE)) {
                        System.out.println("Natural Blackjack");
                        player.gainChips(betMoney += betMoney * 3);
                        break;
                    } else if (playerHand.get(0).getRank().equals(Rank.JACK) &&
                            playerHand.get(1).getRank().equals(Rank.ACE)) {
                        System.out.println("Natural Blackjack");
                        player.gainChips(betMoney += betMoney * 3);
                        break;
                    }

                    /**Checking Natural Blackjack for dealer*/
                    if (dealerHand.get(0).getRank().equals(Rank.ACE) &&
                            dealerHand.get(1).getRank().equals(Rank.KING)) {
                        System.out.println("Natural Blackjack");
                        player.gainChips(betMoney);
                        break;
                    } else if (dealerHand.get(0).getRank().equals(Rank.ACE) &&
                            dealerHand.get(1).getRank().equals(Rank.QUEEN)) {
                        System.out.println("Natural Blackjack");
                        player.gainChips(betMoney);
                        break;
                    } else if (dealerHand.get(0).getRank().equals(Rank.ACE) &&
                            dealerHand.get(1).getRank().equals(Rank.JACK)) {
                        System.out.println("Natural Blackjack");
                        player.gainChips(betMoney);
                        break;
                    } else if (dealerHand.get(0).getRank().equals(Rank.KING) &&
                            dealerHand.get(1).getRank().equals(Rank.ACE)) {
                        System.out.println("Natural Blackjack");
                        player.gainChips(betMoney);
                        break;
                    } else if (dealerHand.get(0).getRank().equals(Rank.QUEEN) &&
                            dealerHand.get(1).getRank().equals(Rank.ACE)) {
                        System.out.println("Natural Blackjack");
                        player.gainChips(betMoney);
                        break;
                    } else if (dealerHand.get(0).getRank().equals(Rank.JACK) &&
                            dealerHand.get(1).getRank().equals(Rank.ACE)) {
                        System.out.println("Natural Blackjack");
                        player.gainChips(betMoney);
                        break;
                    }

                    /**Asking the user with the option for hit,stand or double down*/
                    System.out.println("What do you want to do?");
                    System.out.println("[H] Hit");
                    System.out.println("[S] Stand");
                    System.out.println("[D] Double down");
                    System.out.println("[Su] Surrender");
                    String playChoice = scanner.next();
                    switch (playChoice) {
                        case "H":
                            hit();
                            if (getPlayerHandValue() < 21) {
                                hit();
                            }
                        case "S":
                            stand();
                            compareDealer();
                            dealerVersusPlayer();
                        case "D":
                            doubleDown();
                        case "Su":
                            surrender();
                            startGame();
                            break;
                    }
                }
                else{
                    /**Condition if the bet multiple is not of 10, so asking
                     * for the user to enter again
                     */
                    System.out.println("Bet must be a multiple of 10.");
                    System.out.println("Please enter the bet amount again!");
                    play();
                }

            case "q":
                System.out.println("Thanks for playing");
                break;
            default:
                System.out.println(Utils.invalidChoice(choice));
                startGame();
                break;
        }
    }
}

