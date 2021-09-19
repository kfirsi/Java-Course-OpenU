
/**
 * Write a description of class Triangle here.
 *
 * @author (Kfir Sibirsky)
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class Triangle {

    public static void main(String[] args) {
        /**
         * This program calculates the area and the perimeter of a given triangle.
         * The user is required to enter the length of the triangle's sides one by one.
         */
        Scanner scan = new Scanner(System.in);//creating an object from the Scanner class in order to get data from the user

        System.out.println("This program calculates the area " + "and the perimeter of a given triangle.");
        System.out.println("Please enter the three lengths" + " of the triangles's sides");
        System.out.println("Please note: The length of each side of the triangle must be a natural number."+
            "\n(whole numbers that are bigger than 0)");

        int a = scan.nextInt();//getting the length of the first side
        if(a<1)//check validity of input for the first side
            System.out.println("Error! input not valid! " + a +" was entered and is not valid!");

        int b = scan.nextInt();//getting the length of the second side
        if(b<1)//check validity of input for the second side
            System.out.println("Error! input not valid! " + b +" was entered and is not valid!");

        int c = scan.nextInt();//getting the length of the third side
        if(c<1)//check validity of input for the third side
            System.out.println("Error! input not valid! " + c +" was entered and is not valid!");

        if (a+b > c && b+c > a && a+c > b)//check whether a triangle can be formed using the entered sides
        {
            double s = ((a+b+c)/2.0);//the perimeter is calculated, then divided by 2.0,then assigned to s
            System.out.println("perimeter = " + (a+b+c));//the perimeter is calculated and printed
            System.out.println("area = " + Math.sqrt(s*(s-a)*(s-b)*(s-c)));//the area is calculated using Heron's formula and then printed
        }
        else//in case a triangle can not be formed using the entered sides, a correspondent error will be printed 
            System.out.println("Error! sides of such length: " + a + "," + b + "," + c + " does not form A triangle!");

    }//end of method main
}//end of class Triangle

