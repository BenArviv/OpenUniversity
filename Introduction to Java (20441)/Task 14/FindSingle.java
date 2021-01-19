public class FindSingle{
/**
     * This method gets an array that includes couples of number and one single number,
     * and finds the single number.
     * Example:
     * array: {1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6}
     * single: 3
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

    /*
     * A binary search implementation that finds the single number in the array.
     * Time complexity: O(log(n))
     * Space complexity: O(1)
     */
    private static int binarySearch(int[] a, int first, int last){
        int mid = (first + last) / 2;
        while (first <= last){
            if (mid % 2 == 1){ // if the middle index is odd
                if (a[mid] == a[mid - 1])
                    first = mid + 1;
                else if (a[mid] == a[mid + 1])
                    last = mid - 1;
                else
                    return a[mid];
                    mid = (first + last) / 2;
            }
            else {
                if (a[mid] == a[mid + 1])
                    first = mid + 1;
                else if (a[mid] == a[mid - 1])
                    last = mid - 1;
                else
                    return a[mid];
                mid = (first + last) / 2;
            }
        }
        return a[mid];
    }
}
