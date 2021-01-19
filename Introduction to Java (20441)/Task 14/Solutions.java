public class Solutions{
      /**
     * This recursive method gets a number and returns the amount of solutions
     * for the equation: x1 + x2 + x3 = num.
     * @param num The number to check the equation for
     * @return The amount of solutions the number has, 0 for invalid value
     */
    public static int solutions(int num){
        if (num < 3 || num > 30)
            return 0;
        return solutions(num, 1, 1, 1);
    }
    
    /*
     * A private recursive method.
     * Gets a number to compare, and three other numbers to sum, and checks all
     * of the combinations available. 
     * Prints the correct combinations and add them to a counter, then returns
     * the counter.
     */
    private static int solutions(int num, int x1, int x2, int x3){
        int sum = x1 + x2 + x3, count = 0;
        if (x1 > 10 || x2 > 10 || x3 > 10) // if the numbers are bigger than 10
            return 0;
        if (sum == num){
            System.out.println(x1 + " + " + x2 + " + " + x3);
            count++;
        }
        if (x3 != 10)
            count += solutions(num, x1, x2, x3 + 1);
        else{ // gone through all the integers in the third place
            x3 = 1;
            if (x2 != 10)
                count += solutions(num, x1, x2 + 1, x3);
            else{ // gone through all the integers in the second place
                x2 = 1;
                count += solutions(num, x1 + 1, x2, x3);
            }
        }
        return count;
    }
}
