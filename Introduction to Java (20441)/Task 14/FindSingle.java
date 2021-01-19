public class FindSingle{
/**
     * This method gets an array that includes couples of number and one single number,
     * and finds the single number.
     * Time complexity: O(log(n)), Space complexity: O(1)
     * @param a An array of integers
     * @return The number that shows in the array only one time
     */  
    public static int findSingle(int[] a){
        if (a.length == 1) // if the array has only one number
            return a[0];
        if (a[0] != a[1]) // if the single is the first number, complexity: O(1)
            return a[0];
        if (a[a.length - 1] != a[a.length - 2]) // if the single is the last number, complexity: O(1)
            return a[a.length - 1];
        return binarySearch(a, 0, a.length - 1); // checks the rest of the array, complexity: O(log(n))
    }
}
