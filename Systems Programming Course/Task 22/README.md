<b>Third C task - Matrix calculator </b>
<br><br>
This mini project is a matrix calculator implementation, includes the following:
* Defining the structure 'mat': an <i>DIMxDIM</i> double array, initialized with zeroes, where <i>DIM</i> is a constant number, declared in <i>mymat.h</i>.
* <b><i>read_mat</b></i> function: gets a matrix and a string of real numbers seperated by commas, assings each number read to a corresponding cell in the matrix.
* <b><i>add_mat</i></b> function: gets three matrices, sums the first two matrices and assigns the result to the third matrix.
* <i><b>sub_mat</b></i> function: gets three matrices, subtracts the second matrix from the first, and assigns the result to the third matrix.
* <i><b>mul_mat</i></b> function: gets three matrices, matrix-multiply the first two matrices and assigns the result to the third matrix.
* <i><b>mul_scalar</b></i> function: gets two matrices and a real number, scalar-multiply the first matrix and the scalar, and assigns the result to the second matrix.
* <i><b>trans_mat</i></b> function: gets two matrices, transposes the first matrix and assigns the result to the second matrix.
* <i><b>print_mat</b></i> function: gets a matrix and prints it properly.
<br><br>

The program initialy defines <i>six</i> new matrices, {MAT_A, ..., MAT_F}, and gets the commands via the standard input, according to the pattern: <br>
<i>func_name MAT_NAME, arguments... </i><br>
for example: <br>
<i>read_mat MAT_A, 1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8</i>
