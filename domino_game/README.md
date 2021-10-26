In this project, there are three packages. Package domino console contains Domino_Game class,
Package domino_gui contains gui versions of the class and package parent class contains boneyard, domino
and domino_Value class.

Package Parent_class:

* Class Domino_Value : This class contains domino value. I have stored the domino values in the form of
enum which returns respective integer value of each domino.

* Class Domino : This class gets the value of each domino value, creates a domino and gives its both value
also it gives the hBox value. This class also returns the left and right value of each domino and prints using
toString method.

* Class Boneyard : This class contains player, computer and boneyard dominos in the form of arraylist.
Methods : Generate boneyard - generated 28 domino and stores it in an array, likewise does the same for
player and computer domino. The way my computer plays is that, if there are multiple domino choice to play,
computer chooses the domino which have the highest value, so in that way wining against computer is little hard.
for this logic, i used the method sort deck inorder to follow this logic. I have used trayValue method to add the 
total number of domino points of each player and computer. Method Check domino value is where computer logic is implied,
computer places its value according to the rules given in this project. 

Package domino_console:

* Class Domino_Game : This is the main class where one can play via console.
In this class player interacts with the console and plays accordingly. In the main method,
the players plays the game, where it prompts asking user which domino to play where, after the turn ends
computer plays accordingly and the board is printed. After the boneyard is empty, the game calculates the winner
and based on the total value, either computer or player wins. 

Package domino_gui:
* Class DominoGUI: Domino class extends application. In this class, I have created Hbox, text field, label
and vbox in order to draw in the pane. I added player domino in hbox of player, computer domino in hbox of computer.
after that. With the help of mouse click, my domino is placed accordingly. If left button of mouse is pressed, domino gets added 
on the left side of the gameboard and if right key is pressed, domino gets added on the right side of the board.
after being added, computer playes its part, later it gets printed on the pane.

*Class GUI_Main : In this class, i have created individual dominos with the help of circles and rectangles. After that
i have added them on an arraylist of group. Later i iterate over the arraylist and get the guiboneyard which contains
28 dominos stored in each node of the Hbox. then this is passed to the dominoGUI class where its been executed.

JAR FILE:
There are two jar file present. Folder domino_console_jar has a executable jar which
runs for console version and folder domino_gui_jar contains jar which runs for the 
gui version of the program.

ERROR AND BUGS:
* My program does not check invalid move of the player. so if there is any invalid domino, it can be placed in the board.
* When the computer tray is empty, it does draws from boneyard, but it does not play it right away. error shows up.
* My GUI version is incomplete, it can only be played till a certain steps, when computer draws from boneyard, there is an error,
rotated domino doesnot get correctly placed in my gui version of the program. Draw from boneyard button doesnot work, and in the 
gui version game winner is not calculated.
* My board prints on a straight line.

Overall, if player plays without invalid inputs and move, my program works great on the console version. Above are the error,
I found while working on this project. Gui version of the program is incomplete and very buggy. Since this was my first, very large project
couldnt efficiently do error handling. Overall a nice project.