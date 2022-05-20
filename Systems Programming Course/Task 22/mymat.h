#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <ctype.h>

#define DIM 4 /* the matrix's dimension */

typedef struct mat {
    double matrix[DIM][DIM];
} mat;

void read_mat(mat *matrix, char *str); 
void add_mat(mat *A, mat *B, mat *C);
void sub_mat(mat *A, mat *B, mat *C);
void mul_mat(mat *A, mat *B, mat *C);
void mul_scalar(mat *A, double scalar, mat *B);
void trans_mat(mat *A, mat *B);
void print_mat(mat *matrix); 


