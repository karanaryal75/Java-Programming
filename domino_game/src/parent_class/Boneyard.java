/**
 * Karan Aryal
 * CS 351: Lab 2
 * 02/28/21
 */
package parent_class;
import java.util.ArrayList;
import java.util.Collections;
import static domino_gui.DominoGUI.guiBoneyard;

/**
 * Boneyard class which contains player, computer, the main tray
 * and computer logic
 */
public class Boneyard {
    /**
     * Initializing arraylist for boneyard, player, computer
     * and game board
     */
    public ArrayList<Domino> boneyard;
    public ArrayList<Domino> player_domino;
    public ArrayList<Domino> computer_domino;
    public ArrayList<Domino> rotate;
    public ArrayList<Domino> gameBoard;
    public int numDomino = 7;
    public int count = 0;

    public Boneyard() {
        this.boneyard = new ArrayList<>();
        this.player_domino = new ArrayList<>();
        this.computer_domino = new ArrayList<>();
        this.rotate = new ArrayList<>();
        this.gameBoard = new ArrayList<>();
    }

    /**
     * Method to generate 28 domino elements and store it in the boneyard
     */
    public void generateBoneyard() {
        int count = 0;
        for (Domino_Value.Pips first : Domino_Value.Pips.values()) {
            for (Domino_Value.Pips second : Domino_Value.Pips.values()) {
                if (second.getValue() <= first.getValue()) {
                    boneyard.add(new Domino(first, second,guiBoneyard.get(count)));
                    count++;
                }
            }
        }
        Collections.shuffle(boneyard);
    }

    /**
     * Method to withdraw domino
     * @return first domino from the boneyard
     */
    public Domino withdraw_Domino() {
        return boneyard.remove(0);
    }

    /**
     * Counts the number of element in the boneyard
     * @return
     */
    public int countBoneyard() {
        return boneyard.size();
    }

    /**
     * Gets seven domino and stores it in an arraylist of player domino
     */
    public void get_player_domino() {
        for (int i = 0; i < numDomino; i++) {
            player_domino.add(withdraw_Domino());
        }
    }

    /**
     * Gets seven domino and stores it in an arraylist of computer domino
     */
    public void get_computer_domino() {
        for (int i = 0; i < numDomino; i++) {
            computer_domino.add(withdraw_Domino());
        }
    }


    /**
     * Method where computer plays its highest value first.
     * Game logic implemented where the computer tries to reduce its
     * domino count
     * @param list computer domino.
     */
    public void sortDeck(ArrayList<Domino> list){
        for(int j = 0; j<list.size(); j++) {
            for (int z = 0; z < list.size() - j - 1; z++) {
                if ((list.get(z).getFirst_value().getValue() + list.get(z).getSecond_value().getValue()) <
                        (list.get(z + 1).getFirst_value().getValue() + list.get(z + 1).getSecond_value().getValue())) {
                    Domino temp = list.get(z);
                    list.set(z, list.get(z + 1));
                    list.set(z + 1, temp);
                }
            }
        }
    }

    /**
     * Method which implements computer logic.
     * @param i current index
     * @param listBoard arraylist of game board
     * @param listComp arraylist of computer dominoes
     * @return true if the computer plays a valid move.
     */
    public boolean checkDominoValue(int i, ArrayList<Domino> listBoard, ArrayList<Domino> listComp) {
        boolean temp = false;
        int gameBoard_size = listBoard.size() - 1;
        if(listComp.size() != 0) {
            // Checks the first and last domino and compares with the computer selected domino
            // and adds accordingly if the value matches
            if ((listBoard.get(0).getFirst_value()).equals(listComp.get(i).getFirst_value()) ||
                    (listBoard.get(0).getFirst_value()).equals(listComp.get(i).getSecond_value()) ||
                    (listBoard.get(gameBoard_size).getSecond_value().equals(listComp.get(i).getFirst_value())) ||
                    (listBoard.get(gameBoard_size).getSecond_value().equals((listComp.get(i).getSecond_value())))) {
                // Adds the value to the left, if it matches with the left domino of the board
                if (listBoard.get(0).getFirst_value().equals(listComp.get(i).getSecond_value())) {
                    listBoard.add(0, listComp.get(i));
                    System.out.println("Computer plays " + listComp.get(i) + " at left");
                    temp = true;
                }
                // Adds the value to the left and rotates if required
                else if (listBoard.get(0).getFirst_value().equals(listComp.get(i).getFirst_value())) {
                    listComp.get(i).rotate();
                    listBoard.add(0, listComp.get(i));
                    System.out.println("Computer plays " + listComp.get(i) + " at left");

                    temp = true;
                }
                // Adds the value to the right, if it matches with the right domino of the board
                else if (listBoard.get(gameBoard_size).getSecond_value().equals(listComp.get(i).getFirst_value())) {
                    listBoard.add(listBoard.size(), listComp.get(i));
                    System.out.println("Computer plays " + listComp.get(i) + " at right");

                    temp = true;

                }
                // Adds the value to the right and rotates if required
                else if (listBoard.get(gameBoard_size).getSecond_value().equals(listComp.get(i).getSecond_value())) {
                    listComp.get(i).rotate();
                    listBoard.add(listBoard.size(), listComp.get(i));
                    System.out.println("Computer plays " + listComp.get(i) + " at right");
                    temp = true;
                }
            }
            // Wildcard if the edges of domino contains zero
            else if (listComp.get(i).getFirst_value().equals(Domino_Value.Pips.ZERO)
                    || listComp.get(i).getSecond_value().equals(Domino_Value.Pips.ZERO)) {
                if (listComp.get(i).getFirst_value().equals(Domino_Value.Pips.ZERO)) {
                    listBoard.add(listBoard.size(), listComp.get(i));
                    System.out.println("Computer plays " + listComp.get(i) + " at right");

                    temp = true;
                } else if (listComp.get(i).getSecond_value().equals(Domino_Value.Pips.ZERO)) {
                    listBoard.add(0, listComp.get(i));
                    System.out.println("Computer plays " + listComp.get(i) + " at left");

                    temp = true;
                }
            } else if (listBoard.get(0).getFirst_value().equals(Domino_Value.Pips.ZERO) ||
                    listBoard.get(gameBoard_size).getSecond_value().equals(Domino_Value.Pips.ZERO)) {
                if (listBoard.get(0).getFirst_value().equals(Domino_Value.Pips.ZERO)) {
                    listBoard.add(0, listComp.get(i));
                    System.out.println("Computer plays " + listComp.get(i) + " at left");

                    temp = true;
                } else if (listBoard.get(gameBoard_size).getSecond_value().equals(Domino_Value.Pips.ZERO)) {
                    listBoard.add(listBoard.size(), listComp.get(i));
                    System.out.println("Computer plays " + listComp.get(i) + " at right");

                    temp = true;
                }
            } else {
                // if the computer doesnot have a domino, it draws from the boneyard
                count++;
                if (i == listComp.size() - 1) {
                    System.out.println("Computer Draws from boneyard");
                    listComp.add(listComp.size(), boneyard.get(0));
                    withdraw_Domino();
                } else if (listComp.size() == 0){
                    System.out.println("Computer boneyard");
                    listComp.add(boneyard.get(0));
                    System.out.println(listComp);
                    withdraw_Domino();
                }
            }
        } else {
            System.out.println("Computer boneyard");
            listComp.add(boneyard.get(0));
            System.out.println(listComp);
            withdraw_Domino();
        }
        return temp;
    }

    /**
     * To sting method to display the game board
     * @return
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Domino domino : boneyard) {
            result.append("[").append(domino.getFirst_value()).append("|").append(domino.getSecond_value()).append("] ");
        }
        return result.toString();
    }

    /**
     * Method that Removes domino from the arraylist.
     * @param dom domino
     */
    public  void removeDomino(Domino dom){
        for(int i = 0; i<player_domino.size(); i++){
            if ((dom.getFirst_value().getValue() == player_domino.get(i).getFirst_value().getValue())
                    && (dom.getSecond_value().getValue() == player_domino.get(i).getSecond_value().getValue())){
                player_domino.remove(i);
            }
        }
    }

    /**
     * Method to compare the total value of dominos in the end
     * and calculate the winner
     * @param boneyard arraylist of domino
     * @return total value present in the hand of the player or computer.
     */
    public static int trayValue(ArrayList<Domino> boneyard){
        int val = 0;
        for (int i = 0; i<boneyard.size(); i++){
            val +=(boneyard.get(i).getFirst_value().getValue() + boneyard.get(i).getSecond_value().getValue());
        }
        return val;
    }



}
