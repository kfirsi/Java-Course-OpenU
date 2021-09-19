
/**
 * Write a description of class Congruent here.
 *
 * @author (Kfir Sibirsky)
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class Congruent {

    public static void main(String[] args) {
        /**
         * This program checks whether or not two given triangles are congruent.
         * The user is required to enter the values of the triangle's points in the following order:
         * The first three pairs are the coordinates of the first triangle.
         * The second three pairs are the coordinates of the second triangle.
         * Each pair's values will be entered separately, first the x value and then the y value. 
         */
        Scanner scan = new Scanner(System.in);//creating an object from the Scanner class in order to get data from the user

        System.out.println("Please enter the x value of the first point of the first triangle:");
        double x11 = scan.nextDouble();//getting the value for the x value of the first point of the first triangle 
        System.out.println("Please enter the y value of the first point of the first triangle:");
        double y11 = scan.nextDouble();//getting the value for the y value of the first point of the first triangle 
        System.out.println("Please enter the x value of the second point of the first triangle:");
        double x12 = scan.nextDouble();//getting the value for the x value of the second point of the first triangle 
        System.out.println("Please enter the y value of the second point of the first triangle:");
        double y12 = scan.nextDouble();//getting the value for the y value of the second point of the first triangle 
        System.out.println("Please enter the x value of the third point of the first triangle:");
        double x13 = scan.nextDouble();//getting the value for the x value of the third point of the first triangle 
        System.out.println("Please enter the y value of the third point of the first triangle:");
        double y13 = scan.nextDouble();//getting the value for the y value of the third point of the first triangle 
        System.out.println("Please enter the x value of the first point of the second triangle:");
        double x21 = scan.nextDouble();//getting the value for the x value of the first point of the second triangle 
        System.out.println("Please enter the y value of the first point of the second triangle:");
        double y21 = scan.nextDouble();//getting the value for the y value of the first point of the second triangle 
        System.out.println("Please enter the x value of the second point of the second triangle:");
        double x22 = scan.nextDouble();//getting the value for the x value of the second point of the second triangle 
        System.out.println("Please enter the y value of the second point of the second triangle:");
        double y22 = scan.nextDouble();//getting the value for the y value of the second point of the second triangle 
        System.out.println("Please enter the x value of the third point of the second triangle:");
        double x23 = scan.nextDouble();//getting the value for the x value of the third point of the second triangle 
        System.out.println("Please enter the y value of the third point of the second triangle:");
        double y23 = scan.nextDouble();//getting the value for the y value of the third point of the second triangle

        double a1 =  Math.sqrt(Math.pow(x11-x12, 2)+Math.pow(y11-y12, 2));//the first side of the first triangle is calculated, then assigned to a1
        double b1 =  Math.sqrt(Math.pow(x11-x13, 2)+Math.pow(y11-y13, 2));//the second side of the first triangle is calculated, then assigned to b1
        double c1 =  Math.sqrt(Math.pow(x12-x13, 2)+Math.pow(y12-y13, 2));//the third side of the first triangle is calculated, then assigned to c1
        double a2 =  Math.sqrt(Math.pow(x21-x22, 2)+Math.pow(y21-y22, 2));//the first side of the second triangle is calculated, then assigned to a2
        double b2 =  Math.sqrt(Math.pow(x21-x23, 2)+Math.pow(y21-y23, 2));//the second side of the second triangle is calculated, then assigned to b2
        double c2 =  Math.sqrt(Math.pow(x22-x23, 2)+Math.pow(y22-y23, 2));//the third side of the second triangle is calculated, then assigned to c2

        //print the calculated measurements of the triangles 
        System.out.println("The first triangle is ("+x11+", "+y11+")"+" ("+x12+", "+y12+")"+" ("+x13+", "+y13+").");
        System.out.println("Its lengths are ("+a1+", "+b1+", "+c1+")");
        System.out.println("The second triangle is ("+x21+", "+y21+")"+" ("+x22+", "+y22+")"+" ("+x23+", "+y23+").");
        System.out.println("Its lengths are ("+a2+", "+b2+", "+c2+")");

        if((a1==a2||a1==b2||a1==c2)&&(b1==b2||b1==b2||b1==c2)&&(c1==a2||c1==b2||c1==c2))//checks whether the two triangles are congruent
            System.out.println("The triangles are congruent.");
        else//in case the they are not congruent, a correspondent message will be printed 
            System.out.println("The triangles are not congruent.");
    }//end of method main
}//end of class Triangle
