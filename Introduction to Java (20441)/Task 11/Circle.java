import java.util.Scanner;

/*
Class name: Circle
Author: Ben Arviv
Date: 02/10/2020
Description: An area and perimeter calculator for given rectangle's incircle and excircle 
*/
public class Circle
{
 public static void main (String [] args)
 {
    Scanner scan = new Scanner (System.in);
    System.out.println ("This program calculates the areas " +
    "and the perimeters of the excircle and the incircle " +
    "of a given rectangle.");
    System.out.print ("Please enter the two coordinates of the " +
    "left-upper point of the rectangle "); 

    int leftUpX = scan.nextInt();
    int leftUpY = scan.nextInt();

    System.out.print("Please enter the two coordinates of the " + 
    "right-lower point of the rectangle ");
    int rightDownX = scan.nextInt();
    int rightDownY = scan.nextInt();
    int diffX = leftUpX - rightDownX;
    int diffY = leftUpY - rightDownY;

    //Now we'll calculate for the circumscribed circle:
    double exDiameter = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
    double exRadius = exDiameter / 2;
    double exArea = Math.PI * Math.pow(exRadius, 2);
    double exPerimeter = exRadius * 2 * Math.PI;

    //Now we'll calculate for the inscribed circle:
    int inDiameter = leftUpY - rightDownY;
    double inRadius = inDiameter / 2;
    double inArea = Math.PI * Math.pow(inRadius, 2);
    double inPerimeter = 2 * Math.PI * inRadius;
    
    System.out.println("Inscribed circle: radius = " + inRadius +
    ", area = " + inArea + ", perimeter = " + inPerimeter);
    System.out.print("Circumscribed circle: radius = " + exRadius + 
    ", area = " + exArea + ", perimeter = " + exPerimeter);

 } //end of main method
} //end of class
