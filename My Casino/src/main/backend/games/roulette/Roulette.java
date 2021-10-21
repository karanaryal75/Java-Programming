/*************************/
/** Karan Aryal          */
/**CS 251                */
/**Project 3             */
/**11/4/2020             */
/*************************/
package main.backend.games.roulette;
import main.backend.Utils;
import main.backend.games.Game;
import main.backend.player.Player;

import java.util.*;
import java.util.Scanner;

/**
 * Class to execute the roulette game
 */
public class Roulette extends Game{
    /**declaring variables*/
    public ArrayList<Integer> wheel;
    int betAmount, winningNum, min, max;

    public Roulette(Scanner scanner, Player player){
        super(scanner, player);
        /**Initializing variable*/
        wheel = new ArrayList<>();
        min = 1;
        max = 36;
        /** Generating random winning number*/
        winningNum = (int) (Math.random()*(max-min) + 1) + min;
    }

    /**
     * Creating the wheel for roulette and shuffling the numbers in the wheel.
     */
    public void createWheel(){
        int totalSpinNumber = 36;
        for (int i = 1; i<= totalSpinNumber ; i++){
            wheel.add(i);
        }
        Collections.shuffle(wheel);
    }


    /**
     * Method to straight Bet in roulette
     */
    public  void straightBet(){
        System.out.println("Enter your winning number: ");
        int betNum = scanner.nextInt();
        /**Asking user the wining number*/
        /**Condition to check if the number is between 1 to 36*/
        if (betNum > 0 && betNum <= 36) {
            if (betNum == winningNum) {
                System.out.println("Congratulation you just Won!");
                player.gainChips(betAmount + (betAmount * 35));
            } else {
                System.out.println("Sorry, You didn't win a straight bet");
                player.loseChips(betAmount);
            }
        }else{
            System.out.println("please enter a valid number.");
            straightBet();
        }
        play();
    }

    /**
     * Method for split bet in roulette.
     */
    public void splitBet(){
        System.out.println("Enter First Winning number: ");
        int splitChoice1 = scanner.nextInt();
        System.out.println("Enter Second Winning number: ");
        int splitChoice2 = scanner.nextInt();

        /**Checking diagonal value within the choice of the user*/
        if(splitChoice1 == (splitChoice2 - 1) || splitChoice1 == (splitChoice2 - 3)
        || splitChoice1 == (splitChoice2 + 1) || splitChoice1 ==(splitChoice2 + 3)){
            /**if condition true, player wins*/
            if(splitChoice1 == winningNum || splitChoice2 == winningNum) {
                System.out.println("You got split in " + winningNum);
                player.gainChips(betAmount + betAmount * 17);
            }
            else{
                System.out.println("You did not get split.");
                player.loseChips(betAmount);
            }
        }
        else{
            System.out.println("Not adjacent numbers");
        }
        play();

    }

    /**Method to create street bet in roulette game*/
    public void streetBet(){
        System.out.println("Select number from row 1 to 12");
        int numSelect = scanner.nextInt();
        int winningRow = 0;
        /** Creating two dimensional array of row to check where
         * the user enters the wining row
         */
        int[][] row = {{1,2,3},{4,5,6},{7,8,9},{10,11,12},{13,14,15},{16,17,18},
                {19,20,21},{22,23,24},{25,26,27},{28,29,30},{31,32,33},{34,35,36}};
        /** Looping through the array to check where the wining row is*/
        for(int i = 0; i<row.length; i++){
            for(int j : row[i]){
                if (j == winningNum){
                    winningRow = i;
                }
            }
        }
        /*Checking the wining row with the user input row number*/
        if(winningRow == numSelect){
            System.out.println("You got street of bet");
            player.gainChips(betAmount + betAmount * 17);
        }
        else {
            System.out.println("Sorry, you did not win the street");
            player.loseChips(betAmount);
        }
        play();

    }

    /**
     * Method to create six line bet in roulette game
     */
    public void sixLine(){
        System.out.println("Select number from row 0 to 11");
        System.out.println("First row number: ");
        int rowSelect1 = scanner.nextInt();
        System.out.println("Second row number: ");
        int rowSelect2 = scanner.nextInt();
        /**Creating two dimensional array for numbers*/
        int[][] numbers = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {13, 14, 15}, {16, 17, 18},
                {19, 20, 21}, {22, 23, 24}, {25, 26, 27}, {28, 29, 30}, {31, 32, 33}, {34, 35, 36}};
        int winRow = 0;
        /**looping through the array*/
        for (int i = 0; i < numbers.length; i++) {
            for (int j : numbers[i]) {
                if (j == winningNum) {
                    winRow = i;
                }
            }

        }
        /**selecting the adjacent side of the wining number*/
        if((rowSelect1 - rowSelect2) == 1 || (rowSelect1 - rowSelect2) == -1){
            if (rowSelect1 == winRow || rowSelect2 == winRow){
                System.out.println("You hit Six Line: ");
                player.gainChips((betAmount + betAmount * 5));
            }
            else{
                System.out.println("You did not hit Six Line: ");
                player.loseChips(betAmount);
            }
        }
        play();

    }

    /**
     * Method to create red or black bet in roulette game
     */
    public void redOrBlack(){
        System.out.println("Enter [r] red");
        System.out.println("Enter [b] black");
        String cardChoice = scanner.next();
        String winner = "b";
        int[] redArray = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
        int[] blackArray = {2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35};
        /**looping through the array to determine if the wining number is red or black*/
        for(int i:redArray){
            if(i == winningNum){
                winner = "r";
            }
        }
        /**determining the winner*/
        if(cardChoice.equals(winner)){
            System.out.println("You hit red and black");
            player.gainChips(betAmount * 2);
        }
        else{
            System.out.println("You did not win red and black of the bet.");
            player.loseChips(betAmount);
        }
        play();

    }
    /**
     * Method to create odd or even bet in roulette game
     */
    public void oddEvenCheck(){
        System.out.println("Enter [o] Odd");
        System.out.println("Enter [e] Even");
        String choice3 = scanner.next();
        String gameWin = "o";
        /**checking if the winning number is odd or even*/
        if ((winningNum) % 2 == 0){
            gameWin = "e";
        }
        /**determining the result of wining number and comparing
         * with the number of player
         */
        if(choice3.equals(gameWin)){
            System.out.println("You hit odd and even bet");
            player.gainChips(betAmount * 2);
        }
        else{
            System.out.println("You did not win even and odd of the bet.");
            player.loseChips(betAmount);
        }
        play();
    }

    /**
     * Method to create high or low bet in roulette game
     */
    public void highOrLow(){
        System.out.println("Enter [h] High");
        System.out.println("Enter [l] Low");
        String choice4 = scanner.next();
        String gameWinner ="l";
        /**Checking if the number is high*/
        if (winningNum > 18){
            gameWinner = "h";
        }
        /**determining the result of wining number and comparing
         * with the number of player
         */
        if(choice4.equals(gameWinner)){
            System.out.println("You hit high or low bet");
            player.gainChips(betAmount * 2);
        }
        else{
            System.out.println("You did not win high or low of the bet.");
            player.loseChips(betAmount);
        }
        play();
    }

    /**
     * Method to create dozens bet in roulette game
     */
    public void dozens(){
        System.out.println("What do you want to bet on");
        System.out.println("[a] 1-12");
        System.out.println("[b] 13-24");
        System.out.println("[c] 25-36");
        String dozenChoice = scanner.next();
        String dozenWinner = "c";
        /**
         * Checking if the number is in the certain number ranging from 1 - 12
         */
        if (winningNum < 13){
            dozenWinner = "a";
        }
        /**
         * Checking if the number is in the certain number ranging from 13 - 24
         */
        else if (winningNum <24){
            dozenWinner = "b";
        }
        /**determining the winner*/
        if(dozenChoice.equals(dozenWinner)){
            System.out.println("You hit dozen");
            player.gainChips(betAmount += betAmount * 2);
        }
        else{
            System.out.println("You did not hit dozen.");
            player.loseChips(betAmount);
        }
        play();
    }

    /**
     * Method to create column bet in roulette game
     */
    public void columns(){
        System.out.println("Which column would you like to bet on?");
        System.out.println("[1] First Column");
        System.out.println("[2] Second Column");
        System.out.println("[3] Third Column");
        Integer columnChoice = scanner.nextInt();
        int columnWinner = 3;
        /**Creating columns of value in an array*/
        int [] col1={1,4,7,10,13,16,19,22,25,28,31,34};
        int[] col2={2,5,8,11,14,17,20,23,26,29,32,35};
        int [] col3= {3,6,9,12,15,18,21,24,27,30,33,36};
        /**Looping through the array to determine if the winning number is
         * in the certain column
         */
        for (int i : col1){
            if (winningNum == i){
                columnWinner = 1;
            }
        }
        for (int i : col2){
            if (winningNum == i){
                columnWinner = 2;
            }
        }
        /**Printing the winner column and payout rate*/
        if(columnChoice == columnWinner){
            System.out.println("You hit column on " + columnChoice);
            player.gainChips(betAmount += betAmount * 2);
        }
        else{
            System.out.println("You did not hit column.");
            player.loseChips(betAmount);
        }
        play();
    }


    /**
     * Main method to run the roulette game
     */
    @Override
    protected void play() {
        createWheel();
        /**Asking the user whether to play the game or not*/
        System.out.println("What would you like to do?");
        System.out.println("[p] bet on Roulette");
        System.out.println("[q] walk away");
        String choice1 = scanner.next();


        switch (choice1){
            case "p":
                System.out.println("How much do you want to bet?");
                betAmount = scanner.nextInt();
                player.loseChips(betAmount);
                /**Checking if the betamount is the multiple of 10 or not*/
                if ((betAmount % 10) == 0) {
                    System.out.println("Which bet would you like to choose?");
                    System.out.println("[S] Straight Bet");
                    System.out.println("[Sp] Split Bet");
                    System.out.println("[St] Street Bet");
                    System.out.println("[Si] Six Line Bet");
                    System.out.println("[Rb] Red or Black Bet");
                    System.out.println("[Oe] Odd or Even Bet");
                    System.out.println("[Hl] High or Low Bet");
                    System.out.println("[Do] Dozens Bet");
                    System.out.println("[C] Columns Bet");
                    String betChoice = scanner.next();

                    /**going through the method with the player indicated choice*/
                    switch (betChoice) {
                        case "S":
                            straightBet();
                        case "Sp":
                            splitBet();
                        case "St":
                            streetBet();
                        case "Si":
                            sixLine();
                        case "Rb":
                            redOrBlack();
                        case "Oe":
                            oddEvenCheck();
                        case "Hl":
                            highOrLow();
                        case "Do":
                            dozens();
                        case "C":
                            columns();
                    }
                }
                else{
                    System.out.println("Bet must be a multiple of 10.");
                    System.out.println("Please enter the bet amount again!");
                    play();
                }
                startGame();
                break;
            case "q":
                System.out.println("Thanks for playing");
                break;
            default:
                System.out.println(Utils.invalidChoice(choice1));
                startGame();
                break;
        }
    }
}
