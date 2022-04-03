<b>About the project:</b><br><br>
This task compares the run-time comlplexity of different algorithms while finding the amount of different numbers in a given array. <br>
  A random array is generated with max number as set in <i>'UPPERBOUND'</i> and the size <i>N</i> as the user's input.<br><br>
  <b>The algorithms that are being tested are: <br></b>
  1. A unique algorithm that scans the array and creates a sub-array in the left part of the original array, containing the different numbers exist. <br>
  2. An algorithm that uses Insertion-Sort to sort the array, and then scans it for the different numbers. <br>
  3. An algorithm that uses an optimal O(nlogn) sorting algorithm - Merge-Sort in this case, and does the rest as in 2..
  4. An algorithm that is based on Counting-Sort's <i>C</i> array, that stores the amount of appearences each index (i = 0 to max key) has, and counts if it is bigger then 0.
  5. An algorithm that is based on Hash-Set data structure, that inserts the original array's elements to the table using unique double hash function, and raises the counter for each encounter with a different number.
  
