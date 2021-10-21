/*************************/
/** Karan Aryal          */
/**CS 251                */
/**Project 3             */
/**11/4/2020             */
/*************************/

package main.backend.games.slotmachine;
import main.backend.Utils;
import main.backend.games.Game;
import main.backend.player.Player;

import java.util.*;

/**
 * Main Class where slot machine game is executed.
 */
public class SlotMachine extends Game {
    public SlotMachine(Scanner scanner, Player player) {
        super(scanner, player);
    }

    /**
     * Enum method where symbol for the games are created.
     */
    public enum slotMachine_Symbol{
            DIAMONDS("diamond"),
            HEARTS("heart"),
            SPADE("spade"),
            HORSESHOE("horseshoe"),
            STAR("star"),
            LIBERTYBELL("libertybell");
        /**Creating list to store the symbol value*/
        private static final List<String> symbolList;

        slotMachine_Symbol(String enumVal) {
            this.enumVal = enumVal;
        }

        private final String enumVal;

        static {
            symbolList = new ArrayList<>();
            for (slotMachine_Symbol listIndex : slotMachine_Symbol.values()) {
                symbolList.add(listIndex.enumVal);
            }
        }

        /**Gets the value of list*/
        public static List<String> getValues() {

            return Collections.unmodifiableList(symbolList);
        }

    }

    /**
     * Method to shuffle the values in the list and storing it in an array
     * @return gameReel which has shuffled values of symbol
     */
    public  String[] pullTheLevel(){
        String[] gameReel = new String[3];
        Random rand = new Random();
        int symbolCount = 6;
        List<String> valueList = SlotMachine.slotMachine_Symbol.getValues();

        for (int i = 0; i < gameReel.length; i++){
            int randSymbol = rand.nextInt(symbolCount);
            gameReel[i] = valueList.get(randSymbol);
        }
        return  gameReel;
    }

    /**
     * Main method where the game is executed.
     */
    @Override
    protected void play() {
        /** Prompts the users if he/she wants to play the game*/
        System.out.println("What would you like to do?");
        System.out.println("[p] bet on Slot Machine");
        System.out.println("[q] walk away");
        /**Initializing payout value*/
        int payout;
        SlotMachine accessString = new SlotMachine(scanner, player);
        String[] slotElement = accessString.pullTheLevel();
        String choice  = scanner.next();
        switch (choice) {
            case "p":
                System.out.println("You require 5 chips to play: ");
                /**Prompt to ask the user to enter the chips*/
                int chips = scanner.nextInt();
                if (player.loseChips(chips)){
                    for(int i = 0; i < pullTheLevel().length; i++){
                        if(i == 0){
                            /**Creating game rule condition and printing the result
                             * of whether the player wins or not.
                             */
                            if(slotElement[i].equals("diamond") &&
                                    slotElement[i+1].equals("diamond")
                                    && slotElement[i+2].equals("diamond") ){
                                payout = 30;
                                System.out.print("Combination[" +  slotElement[i]);
                                System.out.print(" "+ slotElement[i + 1] );
                                System.out.println(" " + slotElement[i+2] + "]");
                                player.gainChips(payout);
                            }
                            else if(slotElement[i].equals("spade")
                                    && slotElement[i+1].equals("spade")
                                    && slotElement[i+2].equals("spade") ){
                                payout = 20;
                                System.out.print("Combination[" +  slotElement[i]);
                                System.out.print(" "+ slotElement[i + 1] );
                                System.out.println(" " + slotElement[i+2] + "]");
                                player.gainChips(payout);
                            }
                            else if(slotElement[i].equals("heart")
                                    && slotElement[i+1].equals("heart")
                                    && slotElement[i+2].equals("heart") ){
                                payout = 40;
                                System.out.print("Combination[" +  slotElement[i]);
                                System.out.print(" "+ slotElement[i + 1] );
                                System.out.println(" " + slotElement[i+2] + "]");
                                player.gainChips(payout);
                            }
                            else if(slotElement[i].equals("libertybell")
                                    && slotElement[i+1].equals("libertybell")
                                    && slotElement[i+2].equals("libertybell") ){
                                payout = 50;
                                System.out.print("Combination[" +  slotElement[i]);
                                System.out.print(" "+ slotElement[i + 1] );
                                System.out.println(" " + slotElement[i+2] + "]");
                                player.gainChips(payout);
                            }
                            else if((slotElement[i].equals("horseshoe")
                                    && slotElement[i+1].equals("horseshoe"))
                                   || (slotElement[i].equals("horseshoe") &&
                                    slotElement[i+2].equals("horseshoe"))
                                    || (slotElement[i+2].equals("horseshoe") &&
                                    slotElement[i+1].equals("horseshoe"))){
                                payout = 5;
                                System.out.print("Combination[" +  slotElement[i]);
                                System.out.print(" "+ slotElement[i + 1] );
                                System.out.println(" " + slotElement[i+2] + "]");
                                player.gainChips(payout);
                            }
                            else if((slotElement[i].equals("horseshoe") &&
                                    slotElement[i+1].equals("horseshoe") &&
                                    slotElement[i+2].equals("star"))
                                    || (slotElement[i].equals("star") &&
                                    slotElement[i+1].equals("horseshoe") &&
                                    slotElement[i+2].equals("horseshoe"))
                                    || (slotElement[i].equals("horseshoe") &&
                                    slotElement[i+1].equals("star") &&
                                    slotElement[i+2].equals("horseshoe"))){
                                payout = 10;
                                System.out.print("Combination[" +  slotElement[i]);
                                System.out.print(" "+ slotElement[i + 1] );
                                System.out.println(" " + slotElement[i+2] + "]");
                                player.gainChips(payout);
                            }
                            else {
                                System.out.println("Sorry, You didn't win any chips");
                                System.out.print("Combination[" +  slotElement[i]);
                                System.out.print(" "+ slotElement[i + 1] );
                                System.out.println(" " + slotElement[i+2] + "]");
                            }
                        }
                    }
                }
                startGame();
                break;
            /**Ending game*/
            case "q":
                System.out.println("Thanks for playing!");
                break;
            default:
                System.out.println(Utils.invalidChoice(choice));
                startGame();
                break;
        }
    }
}





