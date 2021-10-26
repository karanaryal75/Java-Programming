/**
 * Karan Aryal
 * CS 351: Lab 2
 * 02/28/21
 */
package domino_console;
import parent_class.Boneyard;
import parent_class.Domino;

import java.util.ArrayList;
import java.util.Scanner;
import static parent_class.Boneyard.trayValue;
import static domino_gui.GUI_Main.gui_Domino;

/**
 * Class which plays the console version of the domino
 */
public class Domino_Game {
    public static Boneyard bone = new Boneyard();
    public static Scanner userInput = new Scanner(System.in);
    public static Domino domino;
    public static int choiceValue = 0;
    public static int track = 0;

    /**
     * Method where computer plays
     * @param list1 computer domino
     * @param list2 gameboard
     */
    public static void computerPlay(ArrayList<Domino> list1, ArrayList<Domino> list2) {
        System.out.println("Computer's turn");

        int i = 0;
        // Checks if the condition is true
        while (i < (list1.size()) || (list1.size() == 0)) {
            //if true, add the domino in the game board and prints the game board
            if (bone.checkDominoValue(i,list2,list1)) {
                System.out.println();
                System.out.println("BOARD: " + list2);
                list1.remove(i);
                break;
            }
            i++;
        }
    }

    /**
     * Method that checks which player is the winner
     * @param playerTray human
     * @param computerTray computer
     */
    public static void checkWinner(ArrayList<Domino> playerTray, ArrayList<Domino>  computerTray){
        // if the condition are true then prints the winner.
        if (trayValue(playerTray) >= trayValue(computerTray)){
            System.out.println("Computer wins");
        } else if(trayValue(playerTray) <= trayValue(computerTray)){
            System.out.println("Player wins");
        }
    }


    /**
     * Main method which executes the game
     * @param args player argument
     */
    public static void main(String[] args) {
        // creating boneyard, player and computer tray
        gui_Domino();
        bone.generateBoneyard();
        bone.get_player_domino();
        bone.get_computer_domino();
        // Sorting from highest to lowest domino
        bone.sortDeck(bone.computer_domino);
        System.out.println("Welcome to Domino Game");
        while (bone.boneyard.size() > 0) {
            System.out.println();
            System.out.println("Computer contains " + bone.computer_domino.size());
            System.out.println("Boneyard contains " + bone.countBoneyard() + " Domino.");
            System.out.println();
            System.out.println();
            System.out.println("Tray: " + bone.player_domino);
            System.out.println("Human's Turn");
            System.out.println("[p] Play Domino");
            System.out.println("[d] Draw from boneyard");
            System.out.println("[q] Quit");
            String choice = userInput.next();
            // Adds the player domino according to the choice he plays with
            if ("p".equals(choice) && (bone.player_domino.size() != 0)) {
                System.out.println("Which Domino?");
                choiceValue = userInput.nextInt();
                System.out.println();
                System.out.println("Left or Right?(l/r)");
                String choiceIndex = userInput.next();
                track = bone.gameBoard.size();

                if ("l".equalsIgnoreCase(choiceIndex)) {
                    System.out.println("Rotate first?(y/n)");
                    String rotateChoice = userInput.next();
                    if ("y".equalsIgnoreCase(rotateChoice)) {
                        domino = bone.player_domino.get(choiceValue);
                        domino.rotate();
                        bone.gameBoard.add(0,domino);
                        System.out.println("Playing " + bone.player_domino.get(choiceValue) + "at left");
                        System.out.println();

                    } else if ("n".equalsIgnoreCase(rotateChoice)) {
                        bone.gameBoard.add(0, bone.player_domino.get(choiceValue));
                        System.out.println("Playing " + bone.player_domino.get(choiceValue) + "at left");
                        System.out.println();

                    }
                } else if ("r".equalsIgnoreCase(choiceIndex)) {
                    System.out.println("Rotate first?(y/n)");
                    String rotateChoice = userInput.next();
                    if ("y".equalsIgnoreCase(rotateChoice)) {
                        domino = bone.player_domino.get(choiceValue);
                        domino.rotate();
                        bone.gameBoard.add(track, domino);
                        System.out.println("Playing " + bone.player_domino.get(choiceValue) + "at right");
                        System.out.println();

                    } else if ("n".equalsIgnoreCase(rotateChoice)) {
                        bone.gameBoard.add(track, bone.player_domino.get(choiceValue));
                        System.out.println("Playing " + bone.player_domino.get(choiceValue) + "at right");
                        System.out.println();


                    }
                }
                bone.player_domino.remove(choiceValue);
                computerPlay(bone.computer_domino, bone.gameBoard);
            }
            //if user inputs d, then it draws from the boneyard
            else if ("d".equals(choice)) {
                System.out.println("Drawing a domino from boneyard");
                bone.player_domino.add(bone.player_domino.size(), bone.boneyard.get(0));
                bone.withdraw_Domino();
            }
            // Quits the game
            else if ("q".equals(choice)) {
                    System.out.println("Thank you for playing");
                    System.exit(1);
            }
        }
        checkWinner(bone.player_domino, bone.computer_domino);
    }
}
