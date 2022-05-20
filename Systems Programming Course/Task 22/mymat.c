#include "mymat.h"

/* read_mat: gets a matrix and a string with numbers seperated by ',', 
    assigns them to the matrix, if less numbers than matrix's size - fills with zeros */
void read_mat(mat *matrix, char *str){
    char *p, *q;
    char c;
    int count = 0, i = 0, j = 0, buff = 8, row = 0, col = 0;
    double num;

    p = (char *)calloc(buff, sizeof(char));

    while (count < (pow(DIM, 2))){
        c = *(str + i);
        if (c == EOF || c == '\0' || c == ','){
            num = atof(p);
            matrix -> matrix[row][col] = num;
            row++;
            if (row == DIM){
                row = 0;
                col++;
            }
            count++;
            i++;
            buff = 8;
            j = 0;
            free(p);
            if (c == EOF || c == '\0')
                break;
            p = (char *)calloc(buff, sizeof(char));
        }
        else if (isspace(c)){
            i++;
            continue;
        }
        else {
            if (!isdigit(c) && c != '.' && c != '-'){
                printf("read_mat: error - not a number.\n");
                return;
            }
            p[j] = c;
            i++;
            j++;
            
            if (j == buff - 1){
                buff *= 2;
                q = (char *)realloc(p, buff);
                if (!q){
                    printf("read_mat: memory allocation failed\n");
                    return;
                }
                p = q;
            }
            
        }
    }

    if (count < pow(DIM, 2)){
        while (count < pow(DIM, 2)){
            matrix -> matrix[row][col] = 0;
            row++;
            if (row == DIM){
                row = 0;
                col++;
            }
            count++;
        }
    }
}

/* print_mat: gets a matrix and prints it properly */
void print_mat(mat *matrix){
    int i;
    double *mat;
    mat = (double *)&(matrix -> matrix);

    for (i = 0; i < DIM; i++){
        printf("%7.2f\t\t%7.2f\t\t%7.2f\t\t%7.2f\n",
         *(mat + i), *(mat + i + 4), *(mat + i + 8), *(mat + i + 12));
    }
    printf("\n");
}

/* add_mat: gets two matrices, A and B, assigns the sum of them to matrix C */
void add_mat(mat *A, mat *B, mat *C){
    int i;
    int row = 0, col = 0;

    for (i = 0; i < pow(DIM, 2); i++){
        C -> matrix[row][col] = (A -> matrix[row][col]) + (B -> matrix[row][col]);
        col++;
        if (col == DIM){
            col = 0;
            row++;
        }
    }
}

/* sub_mat: gets two matrices, A and B, assigns the difference between them in matrix C */
void sub_mat(mat *A, mat *B, mat *C){
    int i;
    int row = 0, col = 0;

    for (i = 0; i < pow(DIM, 2); i++){
        C -> matrix[row][col] = (A -> matrix[row][col]) - (B -> matrix[row][col]);
        col++;
        if (col == DIM){
            col = 0;
            row++;
        }
    }
}

/* mul_mat: matrix multiplication between A and B, the result is being assigned to matrix C */
void mul_mat(mat *A, mat *B, mat *C){
    int row = 0, col = 0, i = 0;
    double sum = 0;

    while (row < DIM && col < DIM){
        if (i == DIM){
            C -> matrix[row][col] = sum;
            sum = 0;
            i = 0;
            row++;
            if (row == DIM){
                row = 0;
                col++;
            }
        }
        else {
            sum += (A -> matrix[i][col]) * (B -> matrix[row][i]);
            i++;
        }
    }
}

/* mul_scalar: scalar multiplication between A and given scalar, the result is being assigned 
    to matrix C */
void mul_scalar(mat *A, double scalar, mat *B){
    int i;
    int row = 0, col = 0;

    for (i = 0; i < pow(DIM, 2); i++){
        B -> matrix[row][col] = (A -> matrix[row][col]) * scalar;
        col++;
        if (col == DIM){
            col = 0;
            row++;
        }
    }
}

/* trans_mat: gets a matrix A, transposes it and assign the result to matrix B */\
void trans_mat(mat *A, mat *B){
    int i;
    int row = 0, col = 0;
    mat *tmat = (mat *)calloc(pow(DIM, 2), sizeof(double));

    if (!tmat){
        printf("trans_mat: calloc couldn't allocate memory.\n");
        return;
    }

    for (i = 0; i  < pow(DIM, 2); i++){
        tmat -> matrix[row][col] = A -> matrix[col][row];
        col++;
        if (col == DIM){
            col = 0;
            row++;
        }
    }

    row = 0;
    col = 0;
    for (i = 0; i  < pow(DIM, 2); i++){
        B -> matrix[row][col] = tmat -> matrix[row][col];
        col++;
        if (col == DIM){
            col = 0;
            row++;
        }
    }

    free(tmat);
}
