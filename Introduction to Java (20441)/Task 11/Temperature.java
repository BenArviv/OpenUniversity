import java.util.Scanner;

/*
Class: Temperature
Description: A temperature converter for Celcius, Fahrenheit and Kelvin units
@Author: Ben Arviv
Date: 02/10/2020
*/
public class Temperature
{
    public static void main(String[] args) 
    {
        final double CONVERSION_FACTOR = (double) 5 / 9;
        final int F_WATER_FREEZING_POINT = 32;
        final double ABSULUTE_ZERO = 273.15;
        System.out.println("This program receives a temperature in one " +
        "of the units Celcius, Fahrenheit or Kelvin, converts it " +
        "to the other units, and prints the temperature in all of " + 
        "the written units.");
        System.out.println("Please enter a unit and a degree:");
        Scanner scan = new Scanner(System.in);
        String word = scan.next();
        double temperature = scan.nextDouble();
        char unit = word.charAt(0);
        double finalC, finalF, finalK;

        /*If the unit is C it calculates the rest of the units using the
        degree entered. */  
        if (unit == 'C')
        {
            finalC = temperature;
            finalF = finalC / CONVERSION_FACTOR + F_WATER_FREEZING_POINT;
            finalK = finalC + ABSULUTE_ZERO;
            System.out.println(finalC + " C");
            System.out.println(finalF + " F");
            System.out.print(finalK + " K");
        }

        /*If the unit is F it calculates the rest of the units using the
        degree entered. */
        else if (unit == 'F')
        {
            finalF = temperature;
            finalC = (finalF - F_WATER_FREEZING_POINT) * CONVERSION_FACTOR;
            finalK = finalC + ABSULUTE_ZERO;
            System.out.println(finalC + " C");
            System.out.println(finalF + " F");
            System.out.print(finalK + " K");
        }

        /*If the unit is K it calculates the rest of the units using the
        degree entered. */
        else if (unit == 'K')
        {
            finalK = temperature;
            finalC = finalK - ABSULUTE_ZERO;
            finalF = finalC / CONVERSION_FACTOR + F_WATER_FREEZING_POINT;
            System.out.println(finalC + " C");
            System.out.println(finalF + " F");
            System.out.print(finalK + " K");
        }
    }    
}
