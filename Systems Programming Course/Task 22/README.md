<b>Third C task - Matrix calculator </b>
<br><br>
This mini project is a matrix calculator implementation, includes the following:
* Defining the structure `mat`: an `DIM X DIM` double array, initialized with zeroes, where `DIM` is a constant number, declared in `mymat.h`.
* `read_mat` function: gets a <b>matrix</b> and a <b>string</b> of real numbers seperated by commas, assings each number read to a corresponding cell in the matrix.
* `add_mat` function: gets <b>three matrices</b>, <i>sums</i> the first two matrices and assigns the result to the third matrix.
* `sub_mat` function: gets <b>three matrices</b>, <i>subtracts</i> the second matrix from the first, and assigns the result to the third matrix.
* `mul_mat` function: gets <b>three matrices</b>, <i>matrix-multiply</i> the first two matrices and assigns the result to the third matrix.
* `mul_scalar` function: gets <b>two matrices</b> and a <b>real number</b>, <i>scalar-multiply</i> the first matrix and the scalar, and assigns the result to the second matrix.
* `trans_mat` function: gets <b>two matrices</b>, <i>transposes</i> the first matrix and assigns the result to the second matrix.
* `print_mat` function: gets a <b>matrix</b> and <i>prints</i> it properly.
<br><br>

The program initialy defines `six` new matrices, `{MAT_A, ..., MAT_F}`, and gets the commands via the <i>standard input</i>, according to the pattern: <br>
`func_name MAT_NAME, arguments...`<br>
for example: <br>
`read_mat MAT_A, 1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8`
