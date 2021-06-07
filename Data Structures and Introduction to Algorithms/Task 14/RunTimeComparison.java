/**
 * Task 14 for course 20407 - a comparison between 5 different algorithms' run-time
 * @author Ben Arviv
 * @version 30/05/2021
 */

import java.util.Scanner;
import java.util.Random;
import java.math.*;

public class Task {
    public static int N, comps, places;
    public static BigInteger _m;
    public static final int UPPERBOUND = 100;

    public static void main (String[] args){
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Enter the amount of numbers:");
        N = scan.nextInt();
        int[] testArray = new int[N];

        for (int i = 0; i < N; i++){
            testArray[i] = rand.nextInt(UPPERBOUND);
        }

        System.out.println("\nFor N = " + N + ":\n");
        for (int i = 1; i < 7; i++){
            int[] A = testArray.clone();
            comps = 0;
            places = 0;

            switch (i){
                case 1:
                    System.out.println("Original algorithm: ");
                    originalAlg(A);
                    break;

                case 2:
                    System.out.println("Insertion-Sort based algorithm: ");
                    insertionAlg(A);
                    break;

                case 3:
                    System.out.println("Optimal sorting (Merge-Sort) based algorithm:");
                    mergeAlg(A);
                    break;

                case 4:
                    System.out.println("Counting-Sort based algorithm: ");
                    countingAlg(A);
                    break;

                case 5:
                    System.out.println("Hash-Table based algorithm: ");
                    hashAlg(A);
                    break;
            }

            System.out.println("=============================================");
        }
        System.out.println("End of testing.");
    }

    /**
     * Algorithm implementation for Q1b in task 11
     * Time complexity: Θ(n^2)
     * @param A a random array of integers
     */
    public static void originalAlg(int[] A){
        int diffCount = 1;
        boolean flag;

        for (int i = 1; i < A.length; i++){
            places++; // Line: 72
            comps++; // Line: 72
            flag = true;
                places++; // Line: 75
            for (int j = 0; j < diffCount; j++) {
                places++; // Line: 77
                comps += 2; // For the upcoming if (Line: 80), and Line: 77
                if (A[j] == A[i]) {
                    flag = false;
                    j = diffCount;
                    places += 2; // Lines: 81, 82
                }
            }
            comps++; // For the upcoming if (Line: 87)
            if (flag){
                diffCount++;
                A[diffCount - 1] = A[i];
                places += 2; // Lines: 88, 89
            }
        }
        System.out.println("committed " + comps + " comparisons and "
        + places + " placements.");
        System.out.println("The time complexity as calculated in Task 11 is Θ(n^2)");
        System.out.println("Output: " + diffCount);
    }

    /**
     * Implementation of the problem with Insertion-Sort algorithm.
     * The algorithm sorts the array, sends the sorted array to the method checkDiffAmount.
     * @param A a random array of integers
     */
    public static void insertionAlg(int[] A){
        insertionSort(A);
        int result = checkDiffAmount(A);
        System.out.println("committed " + comps + " comparisons and "
        + places + " placements.");
        System.out.println("The worst-case time complexity is Θ(n^2)");
        System.out.println("Output: " + result);
    }

    /**
     * A possible solution to the problem using optimal Merge-Sort algorithm.
     * The algorithm sorts the array, then sends the sorted array to the method checkDiffAmount.
     * @param A a random array of integers
     */
    public static void mergeAlg(int[] A){
        mergeSort(A, 0, A.length - 1);
        int result = checkDiffAmount(A);
        System.out.println("committed " + comps + " comparisons and "
                + places + " placements.");
        System.out.println("The worst-case time complexity is Θ(nlog(n))");
        System.out.println("Output: " + result);
    }

    /**
     * Implementation if the problem using Counting-Sort's 'C' array for checking the amount of different numbers.
     * @param A a random array of integers
     */
    public static void countingAlg(int[] A){
        int max = maximum(A);
        places++; // Line: 132

        int result = countingBasedDiffAmount(A, max);
        System.out.println("committed " + comps + " comparisons and "
                + places + " placements.");
        System.out.println("It is to mention that the comparisons have been committed in 'countingBasedDiffAmount' and "
                + "'maximum' methods, \nas the original algorithm doesn't require any of them.");
        System.out.println("The worst-case time complexity is Θ(n) (max is limited to be 100)");
        System.out.println("Output: " + result);
    }

    /**
     * A possible solution to the problem using Hash-Set.
     * Inside the "Insert" method, checks if a number is appears in the new table and raises the counter.
     * @param A a random array of integers
     */
    public static void hashAlg(int[] A){
        int m = (int)(UPPERBOUND * 1.3);
        _m = new BigInteger(String.valueOf(m));
        m = _m.nextProbablePrime().intValue(); // As there is a limited amount of values available, time complexity is Θ(1)
        Table table = new Table(m);
            places += 4; // Lines: 150-153

        for (int i = 0; i < A.length; i++) // A.length = N
            table.insert(A[i]);

        int result = table._count;
        double loadFactor = table.getLoadFactor();
        System.out.println("committed " + comps + " comparisons and "
                + places + " placements.");
        System.out.println("The average-case time complexity is Θ(n*(1+(1-α))) = Θ(n), whereas in this case: α = " + loadFactor + ".");
        System.out.println("Output: " + result);
    }

    // sub-methods bellow

    /**
     * Insertion-Sort sorting algorithm implementation.
     * @param A an unsorted array of integers
     */
    private static void insertionSort(int[] A){
        int key, j;

        for (int i = 0; i < A.length; i++){
            comps++; // Line: 177
            key = A[i];
            j = i - 1;
            places += 3; // Lines: 177, 178-179

            while (j >= 0 && A[j] > key){
                comps += 2; // Line: 182
                A[j + 1] = A[j];
                j--;
                    places += 2; // Lines: 184-185
            }

            A[j + 1] = key;
                places++; // Line: 189
        }
    }

    /**
     * This method runs over the array and raises the counter if an element of the array is different the its
     * predecessor.
     * @param A a sorted array with random integers
     * @return the amount of different elements in the array
     */
    public static int checkDiffAmount(int[] A){
        int temp = A[0];
        int diffCounter = 1;

        for (int i = 1; i < A.length; i++){
            places++; // Line: 20
            comps += 2; // Lines: 204, 207
            if (A[i] != temp){
                diffCounter++;
                temp = A[i];
                    places += 2; // Lines: 208-209
            }
        }

        return diffCounter;
    }

    /**
     * Merge-Sort sorting algorithm implementation.
     * @param A an unsorted array of integers
     * @param low lower border of the recursive call
     * @param high higher border of the recursive call
     */
    private static void mergeSort(int[] A, int low, int high){
        comps++; // Line: 225
        if (low < high) {
            int mid = (low + high) / 2;
                places++; // Line: 227
            mergeSort(A, low, mid);
            mergeSort(A, mid + 1, high);
            merge(A, low, mid, high);
        }
    }

    /**
     * Merge method to merge two sorted sub-arrays.
     * @param array an array of integers
     * @param low first index of the array
     * @param mid middle index of the array
     * @param high last index of the array
     */
    private static void merge(int[] array, int low, int mid, int high) {
        // Creating temporary subarrays
        int[] leftArray = new int[mid - low + 1];
        int[] rightArray = new int[high - mid];

        // Copying our subarrays into temporaries
        for (int i = 0; i < leftArray.length; i++) {
            leftArray[i] = array[low + i];
            comps++; // Line: 247
            places += 2; // Lines: 247-248
        }
        for (int i = 0; i < rightArray.length; i++) {
            rightArray[i] = array[mid + i + 1];
            comps++; // Line: 252
            places += 2; // Lines: 252-253
        }

        // Iterators containing current index of temp subarrays
        int leftIndex = 0;
        int rightIndex = 0;

        // Copying from leftArray and rightArray back into array
        for (int i = low; i < high + 1; i++) {
            comps += 3; // Lines: 263, 267
            places++; // Line: 263
            // If there are still uncopied elements in R and L, copy minimum of the two
            if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < leftArray.length) {
                comps++; // Line: 275
                // If all elements have been copied from rightArray, copy rest of leftArray
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (rightIndex < rightArray.length) {
                comps++; // Line: 280
                // If all elements have been copied from leftArray, copy rest of rightArray
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
            places += 2; // Lines: 269-270/272-273/278-279/283-284
        }
    }

    /**
     * This method creates the "C" array of Counting-Sort, and counts the amount of different numbers
     * @param A the original array
     * @param k maximal value of the keys
     * @return the amount of different numbers in the array
     */
    private static int countingBasedDiffAmount(int[] A, int k){
        int[] tempArr = new int[k + 1];
        int count = 0;

        for (int i = 0; i < k; i++) {
            tempArr[i] = 0;
                places += 2; // Lines: 300-301
        }

        for (int j = 0; j < A.length; j++) {
            tempArr[A[j]]++;
                places += 2; // Lines: 305-306
                comps++; // Lines: 306, 308
            if (tempArr[A[j]] == 1) {
                count++; // checks if this is the first encounter with a number, and if it is - raised by 1
                places++; // Line: 310
            }
        }

        return count;
    }

    /**
     * This method gets an array of integers and returns the maximal key
     * @param A an array of integers
     * @return maximal key in the array
     */
    private static int maximum(int[] A){
        int k = A[0];
            places++; // Line: 324
        for (int i = 1; i < A.length; i++){
            places++; // Line: 326
            comps++; // Line: 329
            if (A[i] > k) {
                k = A[i];
                    places++; // Line: 330
            }
        }
        return k;
    }

}
