public class CntTrueReg{
      /**
     * This recursive method gets a matrix and returns the amount of 'True' regions in the matrix
     * Example: {{true, false},
                {false, true}}
     * TrueRegions: 2
     * @param mat A matrix full of boolean variables
     * @return The amount of 'True' regions
     */
    public static int cntTrueReg(boolean[][] mat){
        if (mat.length == 0)
            return 0;
        if (mat.length == 1 && mat[0].length == 1){
            if (mat[0][0] == true)
                return 1;
            return 0;
        }
        return cntTrueReg(mat, 0, 0, 0);
    }
    
    /*
     * A private recursive method that gets a matrix, two index values, and a counter, and checks if the current cell is True.
     * If so, adds one to the counter and sends the matrix and the index values to deleteReg method to 'delete' the region.
     * Returns the total amount.
     */
    private static int cntTrueReg(boolean[][] mat, int i, int j, int count){
        if (!mat[i][j]){
            if (j == mat[i].length - 1){
                if (i == mat.length - 1)
                    return count; // end of matrix

                // end of if, else - i != mat.length - 1
                return cntTrueReg(mat, i + 1, 0, count);
            } // end of if, else - j != mat[i].length - 1
            return cntTrueReg(mat, i, j + 1, count);
        }
        else { // mat[i][j] == true\
            count++;
            deleteReg(mat, i, j);
            if (j == mat[i].length - 1){
                if (i == mat.length - 1)
                    return count; // end of matrix
                    
                // end of if, else - i != mat.length - 1
                return cntTrueReg(mat, i + 1, 0, count);
            } // end of if, else - j != mat[i].length - 1
            return cntTrueReg(mat, i, j + 1, count);
        }
    }

    /*
     * This private recursive method gets a starting cell of a 'True Region' and turns the region to
     * a false region.
     */
    private static void deleteReg(boolean[][] mat, int i, int j){
        mat[i][j] = false;
        if (j > 0 && mat[i][j - 1])
            deleteReg(mat, i, j - 1);
        if (j < mat[i].length - 1 && mat[i][j + 1])
            deleteReg(mat, i, j + 1);
        if (i > 0 && mat[i - 1][j])
            deleteReg(mat, i - 1, j);
        if (i < mat.length - 1 && mat[i + 1][j])
            deleteReg(mat, i + 1, j);
        return;
    }
}
}
