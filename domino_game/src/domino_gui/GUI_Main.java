/**
 * Karan Aryal
 * CS 351: Lab 2
 * 02/28/21
 */
package domino_gui;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import static domino_gui.DominoGUI.*;

/**
 * GUI class which creates domino and adds in the HBox
 */
public class GUI_Main {
    public static final float h = 40;
    public static final float w = 40;
    private static final int x = 10;
    private static final int y = 75;

    /**
     * Creating six dominoes in the form of group
     * @return each domino
     */
    public static Group domZero() {
        return new Group( new Rectangle(x ,y,w,h));
    }
    public static Group domOne() {
        Rectangle rectangle = new Rectangle(x, y, w, h);
        Circle circle1 = new Circle( x + w / 2, y+h/2, 3);
        circle1.setFill(Color.WHITE);
        return new Group(rectangle, circle1);
    }
    public static Group domTwo() {
        Rectangle rectangle = new Rectangle(x, y, w, h);
        rectangle.setFill(Color.BLACK);
        Circle circle1 = new Circle(x + 8, y + 8, 3);
        circle1.setFill(Color.WHITE);
        Circle circle2 = new Circle(x + w  - 8, y + h - 8, 3);
        circle2.setFill(Color.WHITE);
        return new Group(rectangle, circle1, circle2);
    }
    public static Group domThree() {
        float center_x = x + w / 2;
        float center_y = y + h / 2;
        Rectangle rectangle3 = new Rectangle(x, y, w, h);
        rectangle3.setFill(Color.BLACK);
        Circle circle1 = new Circle(x + 8, y + 8, 3);
        circle1.setFill(Color.WHITE);
        Circle circle2 = new Circle(x + w  - 8, y + h - 8, 3);
        circle2.setFill(Color.WHITE);
        Circle circle3 = new Circle(center_x, center_y, 3);
        circle3.setFill(Color.WHITE);
        return new Group(rectangle3, circle1, circle2, circle3);
    }
    public static Group domFour() {
        Rectangle rectangle = new Rectangle(x, y, w, h);
        rectangle.setFill(Color.BLACK);

        Circle circle1 = new Circle(x + 8, y + 8, 3);
        circle1.setFill(Color.WHITE);

        Circle circle2 = new Circle(x + w  - 8, y + 8, 3);
        circle2.setFill(Color.WHITE);

        Circle circle3 = new Circle(x + w-8, y + h - 8, 3);
        circle3.setFill(Color.WHITE);

        Circle circle4 = new Circle(x +  8, y + h - 8, 3);
        circle4.setFill(Color.WHITE);

        return new Group(rectangle, circle1,circle2,circle3,circle4);
    }
    public static Group domFive() {
        Rectangle rectangle3 = new Rectangle(x, y, w, h);
        rectangle3.setFill(Color.BLACK);


        Circle circle1 = new Circle(x + 8, y + 8, 3);
        circle1.setFill(Color.WHITE);

        Circle circle2 = new Circle(x + w  - 8, y + 8, 3);
        circle2.setFill(Color.WHITE);

        Circle circle3 = new Circle(x + w-8, y + h - 8, 3);
        circle3.setFill(Color.WHITE);

        Circle circle4 = new Circle(x +  8, y + h - 8, 3);
        circle4.setFill(Color.WHITE);
        Circle circle5 = new Circle( x + w / 2, y+h/2, 3);
        circle5.setFill(Color.WHITE);
        return new Group(rectangle3, circle1, circle2, circle3, circle4, circle5);
    }
    public static Group domSix() {
        Rectangle rectangle = new Rectangle(x, y, w, h);
        rectangle.setFill(Color.BLACK);

        Circle circle1 = new Circle(x + 8, y + 8, 3);
        circle1.setFill(Color.WHITE);

        Circle circle2 = new Circle(x + w  - 8, y + 8, 3);
        circle2.setFill(Color.WHITE);

        Circle circle3 = new Circle(x + w-8, y + h - 8, 3);
        circle3.setFill(Color.WHITE);

        Circle circle4 = new Circle(x +  8, y + h - 8, 3);
        circle4.setFill(Color.WHITE);

        Circle circle5 = new Circle(x + w / 2 , y +  8, 3);
        circle5.setFill(Color.WHITE);

        Circle circle6 = new Circle(x + w / 2, y + h -8, 3);
        circle6.setFill(Color.WHITE);

        return new Group(rectangle, circle1, circle2, circle3, circle4, circle5, circle6);
    }

    /**
     * Adds the group into the arraylist of group
     * @return arraylist
     */
    public static ArrayList<Group> arrayList(){
        ArrayList<Group> aList =new ArrayList<>();
        aList.add(domZero());
        aList.add(domOne());
        aList.add(domTwo());
        aList.add(domThree());
        aList.add(domFour());
        aList.add(domFive());
        aList.add(domSix());
        return aList;
    }

    /**
     * Method to create HBox and then adds the individual piece of domino,
     * into one single domino and stores in the gui boneyard.
     */
    public static void gui_Domino(){
        HBox box1= new HBox(10);

        int temp = 0;
        String string = "";
        for(int i = 0; i<arrayList().size(); i++){
            for(int j = 0; j<=i; j++){
                string = Integer.toString(i)+Integer.toString(j);
                box1.setId(string);
                //Adding in the guiBoneyard
                guiBoneyard.add(new HBox(1, arrayList().get(i), arrayList().get(j)));
                //Setting the id for individual domino
                guiBoneyard.get(temp).setId(string);
                temp = temp + 1;
            }
        }
    }
}
