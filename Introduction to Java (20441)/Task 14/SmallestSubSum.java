public class SmallestSubSum{
      /**
     * Question #2
     * This method gets an array and a number, and finds the smallest sub array 
     * that its sum is bigger than the received number.
     * Example:
     * array: {1, 2, 4, 3, 5}, number: 6
     * smallestSubSum: {3, 5}
     * Time complexity: O(n), Space complexity: O(1)
     * @param arr An array of integers
     * @param x The number to compare the sum with
     * @return The size of the sub array. -1 if such don't exist
     */
    public static int smallestSubSum(int arr[], int x){
        if (arr == null || arr.length == 0) // array is empty
            return -1;
        if (arr.length == 1){
            if (arr[0] > x)
                return 1;
            return -1;
        }
        int i = 0, j = 1, sum = arr[i], tempCount = 0, finalCount = arr.length; // space: O(1)
        while (i < arr.length){ // complexity: O(n)
            if (arr[i] > x) // enters if a number in the array is bigger then received value
                return 1;
            sum += arr[j];
            if (sum <= x && j + 1 != arr.length)
                j++;
            else if (sum > x && i + 2 < arr.length){
                tempCount = j - i;
                i++;
                j = i + 1;
                sum = arr[i];
                if (tempCount < finalCount) // chooses the minimal counter
                    finalCount = tempCount;
            }
            else if (i + 2 < arr.length){
                tempCount = 0;
                i++;
                j = i + 1;
                sum = arr[i];
            }
            else // if i = arr.length - 1
                i++;
        }
        if (finalCount == arr.length)
            return -1;
        return finalCount + 1;
    }
}
