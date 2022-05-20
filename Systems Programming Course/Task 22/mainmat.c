#include "mymat.h"

enum function {READ, PRINT, ADD, SUB, MUL_M, MUL_S, TRANS, STOP};
enum mat_name {A, B, C, D, E, F};

int func_analyzer(char *str);
int input_check(char *str);
double get_scalar(char *str);
char *lower_case(char *str);
char *read_input(void);
char *remove_white_space(char *str);
mat *create_matrix(void);
mat *mat_analyzer(char *str, mat *arr[]);

int main(){
    int func_identifier, i = 0, check;
    double scalar;
    char c[2];
    char *str, *func, *mat_name1, *mat_name2, *mat_name3;
    mat *MAT_A, *MAT_B, *MAT_C, *MAT_D, *MAT_E, *MAT_F, *temp_1, *temp_2, *temp_3;
    mat *mat_arr[6];

    MAT_A = create_matrix();
    MAT_B = create_matrix();
    MAT_C = create_matrix();
    MAT_D = create_matrix();
    MAT_E = create_matrix();
    MAT_F = create_matrix();

    mat_arr[0] = MAT_A;
    mat_arr[1] = MAT_B;
    mat_arr[2] = MAT_C;
    mat_arr[3] = MAT_D;
    mat_arr[4] = MAT_E;
    mat_arr[5] = MAT_F;

    do{
        printf("\nPlease type wanted command, or 'stop' to exit:\n");
        str = read_input();
        check = input_check(str);
        if (check == -1)
            continue;
        if (str == NULL)
            continue;
        str = remove_white_space(str);
        printf("\n");

        func = (char *)calloc(strlen(str) + 1, sizeof(str));
        strcpy(func, str);
        
        for (i; i < (int)strlen(str) + 1 && (isalpha(c[0] = str[i]) || c[0] == '_'); i++)
            ;
        i = 0;
        if (c[0] == ','){
            printf("error: invalid command.\n");
            continue;
        }
        strtok(func, c);
        func_identifier = func_analyzer(func);
        if (func_identifier == -1)
            continue;

        str += (int)strlen(func);
        str = remove_white_space(str);
    
        switch (func_identifier){
            case READ:
                mat_name1 = (char *)calloc(strlen(str) + 1, sizeof(str));
                strcpy(mat_name1, str);
                c[0] = mat_name1[5];
                mat_name1 = strtok(mat_name1, c);
                str += (int)strlen(mat_name1);
                str = remove_white_space(str); 
                temp_1 = mat_analyzer(mat_name1, mat_arr);

                if (temp_1 == NULL)
                    continue;
                if (!strcmp(str, "")){
                    printf("read_mat: error - missing arguments.\n");
                    continue;
                }
                read_mat(temp_1, str);
                free(mat_name1);
                break;
            case ADD:
                if (!strcmp(str, "")){
                    printf("read_mat: error - missing arguments.\n");
                    continue;
                }
                mat_name1 = (char *)calloc(strlen(str) + 1, sizeof(str));
                strcpy(mat_name1, str);
                mat_name1 = strtok(mat_name1, ",");
                str += (int)strlen(mat_name1);
                str = remove_white_space(str); /* gets the first matrix */
                temp_1 = mat_analyzer(mat_name1, mat_arr);

                if (!strcmp(str, "")){
                    printf("read_mat: error - missing arguments.\n");
                    continue;
                }
                mat_name2 = (char *)calloc(strlen(str) + 1, sizeof(str));
                strcpy(mat_name2, str);
                mat_name2 = strtok(mat_name2, ",");
                str += (int)strlen(mat_name2);
                str = remove_white_space(str); /* gets the second matrix */
                temp_2 = mat_analyzer(mat_name2, mat_arr);

                if (!strcmp(str, "")){
                    printf("read_mat: error - missing arguments.\n");
                    continue;
                }
                mat_name3 = (char *)calloc(strlen(str) + 1, sizeof(str));
                strcpy(mat_name3, str);
                c[0] = mat_name3[5];
                mat_name3 = strtok(mat_name3, c);
                str += (int)strlen(mat_name3);
                str = remove_white_space(str); /* gets the third matrix */
                temp_3 = mat_analyzer(mat_name3, mat_arr);

                if (temp_1 == NULL || temp_2 == NULL || temp_3 == NULL){
                    continue;
                }
                if (strlen(str) != 0){
                    printf("add_mat: error - extraneous text.\n");
                    continue;
                }
                add_mat(temp_1, temp_2, temp_3);  
                free(mat_name1);
                free(mat_name2);
                free(mat_name3);
                break;
            case PRINT:
                if (!strcmp(str, "")){
                    printf("read_mat: error - missing arguments.\n");
                    continue;
                }
                mat_name1 = (char *)calloc(strlen(str) + 1, sizeof(str));
                strcpy(mat_name1, str);
                c[0] = mat_name1[5];
                mat_name1 = strtok(mat_name1, c);
                str += (int)strlen(mat_name1);
                str = remove_white_space(str); /* gets the first matrix */
                temp_1 = mat_analyzer(mat_name1, mat_arr);

                if (temp_1 == NULL){
                    continue;
                }
                if (strlen(str) != 0){
                    printf("print_mat: error - extraneous text.\n");
                    continue;
                }
                print_mat(temp_1);
                free(mat_name1);
                break;
            case SUB:
                if (!strcmp(str, "")){
                    printf("read_mat: error - missing arguments.\n");
                    continue;
                }
                mat_name1 = (char *)calloc(strlen(str) + 1, sizeof(str));
                strcpy(mat_name1, str);
                mat_name1 = strtok(mat_name1, ",");
                str += (int)strlen(mat_name1);
                str = remove_white_space(str); /* gets the first matrix */
                temp_1 = mat_analyzer(mat_name1, mat_arr);

                if (!strcmp(str, "")){
                    printf("read_mat: error - missing arguments.\n");
                    continue;
                }
                mat_name2 = (char *)calloc(strlen(str) + 1, sizeof(str));
                strcpy(mat_name2, str);
                mat_name2 = strtok(mat_name2, ",");
                str += (int)strlen(mat_name2);
                str = remove_white_space(str); /* gets the second matrix */
                temp_2 = mat_analyzer(mat_name2, mat_arr);

                if (!strcmp(str, "")){
                    printf("read_mat: error - missing arguments.\n");
                    continue;
                }
                mat_name3 = (char *)calloc(strlen(str) + 1, sizeof(str));
                strcpy(mat_name3, str);
                c[0] = mat_name3[5];
                mat_name3 = strtok(mat_name3, c);
                str += (int)strlen(mat_name3);
                str = remove_white_space(str); /* gets the third matrix */
                temp_3 = mat_analyzer(mat_name3, mat_arr);

                if (temp_1 == NULL || temp_2 == NULL || temp_3 == NULL){
                    continue;
                }
                if (strlen(str) != 0){
                    printf("sub_mat: error - extraneous text.\n");
                    continue;
                }
                sub_mat(temp_1, temp_2, temp_3);  
                free(mat_name1);
                free(mat_name2);
                free(mat_name3);
                break;
            case MUL_M:
                if (!strcmp(str, "")){
                    printf("read_mat: error - missing arguments.\n");
                    continue;
                }
                mat_name1 = (char *)calloc(strlen(str) + 1, sizeof(str));
                strcpy(mat_name1, str);
                mat_name1 = strtok(mat_name1, ",");
                str += (int)strlen(mat_name1);
                str = remove_white_space(str); /* gets the first matrix */
                temp_1 = mat_analyzer(mat_name1, mat_arr);

                if (!strcmp(str, "")){
                    printf("read_mat: error - missing arguments.\n");
                    continue;
                }
                mat_name2 = (char *)calloc(strlen(str) + 1, sizeof(str));
                strcpy(mat_name2, str);
                mat_name2 = strtok(mat_name2, ",");
                str += (int)strlen(mat_name2);
                str = remove_white_space(str); /* gets the second matrix */
                temp_2 = mat_analyzer(mat_name2, mat_arr);

                if (!strcmp(str, "")){
                    printf("read_mat: error - missing arguments.\n");
                    continue;
                }
                mat_name3 = (char *)calloc(strlen(str) + 1, sizeof(str));
                strcpy(mat_name3, str);
                c[0] = mat_name3[5];
                mat_name3 = strtok(mat_name3, c);
                if (!strlen(str) == 0)
                    str += (int)strlen(mat_name3);
                str = remove_white_space(str); /* gets the third matrix */
                temp_3 = mat_analyzer(mat_name3, mat_arr);

                if (temp_1 == NULL || temp_2 == NULL || temp_3 == NULL){
                    continue;
                }
                if (strlen(str) != 0){
                    printf("mul_mat: error - extraneous text.\n");
                    continue;
                }
                mul_mat(temp_1, temp_2, temp_3);  
                free(mat_name1);
                free(mat_name2);
                free(mat_name3);
                break;
            case MUL_S:
                if (!strcmp(str, "")){
                    printf("read_mat: error - missing arguments.\n");
                    continue;
                }
                mat_name1 = (char *)calloc(strlen(str) + 1, sizeof(str));
                strcpy(mat_name1, str);
                mat_name1 = strtok(mat_name1, ",");
                str += (int)strlen(mat_name1);
                str = remove_white_space(str); /* gets the first matrix */
                temp_1 = mat_analyzer(mat_name1, mat_arr);

                scalar = get_scalar(str);
                if (scalar == -1)
                    continue;
                while ((c[0] = *(str + i)) != ',')
                    i++;
                str += i;
                i = 0;
                str = remove_white_space(str); /* gets the scalar */

                if (!strcmp(str, "")){
                    printf("read_mat: error - missing arguments.\n");
                    continue;
                }
                mat_name2 = (char *)calloc(strlen(str) + 1, sizeof(str));
                strcpy(mat_name2, str);
                c[0] = mat_name2[5];
                mat_name2 = strtok(mat_name2, c);
                str += (int)strlen(mat_name2);
                str = remove_white_space(str); /* gets the second matrix */
                temp_2 = mat_analyzer(mat_name2, mat_arr);

                if (temp_1 == NULL || temp_2 == NULL)
                    continue;
                if (strlen(str) != 0){
                    printf("mul_scalar: error - extraneous text.\n");
                    continue;
                }
                mul_scalar(temp_1, scalar, temp_2);
                free(mat_name1);
                free(mat_name2);
                break;
            case TRANS:
                if (!strcmp(str, "")){
                    printf("read_mat: error - missing arguments.\n");
                    continue;
                }
                mat_name1 = (char *)calloc(strlen(str) + 1, sizeof(str));
                strcpy(mat_name1, str);
                mat_name1 = strtok(mat_name1, ",");
                str += (int)strlen(mat_name1);
                str = remove_white_space(str); /* gets the first matrix */
                temp_1 = mat_analyzer(mat_name1, mat_arr);

                if (!strcmp(str, "")){
                    printf("read_mat: error - missing arguments.\n");
                    continue;
                }
                mat_name2 = (char *)calloc(strlen(str) + 1, sizeof(str));
                strcpy(mat_name2, str);
                c[0] = mat_name2[5];
                mat_name2 = strtok(mat_name2, c);
                str += (int)strlen(mat_name2);
                str = remove_white_space(str); /* gets the second matrix */
                temp_2 = mat_analyzer(mat_name2, mat_arr);

                if (temp_1 == NULL || temp_2 == NULL)
                    continue;
                if (strlen(str) != 0){
                    printf("trans_mat: error - extraneous text.\n");
                    continue;
                }
                trans_mat(temp_1, temp_2);
                free(mat_name1);
                free(mat_name2);
                break;
            case STOP:
                if (strlen(str) != 0){
                    printf("stop: error - extraneous text.\n");
                    continue;
                }
                exit(0);
        }
        printf("\n");

        free(func);

    } while (1);
    
    free(MAT_A);
    free(MAT_B);
    free(MAT_C);
    free(MAT_D);
    free(MAT_E);
    free(MAT_F);
    return 0;
}

/* input_check: gets input string and check if it is a valid input */
int input_check(char *str){
    enum status {COMMA, FUNC, ARG, PRESPACE};

    int length = (int)strlen(str);
    int state = FUNC;
    int i = 0;
    char c = *str, *err = "\ninput_check: error - invalid input.\n";
    if (!isalpha(c) && !isspace(c)){
        printf("%s", err);
        return -1;
    }

    while (i < length){
        c = *(str + i);
        switch (state){
            case FUNC:
                if (c == '_')
                    state = FUNC;
                else if (isspace(c))
                    state = ARG;
                else if (!isalpha(c)){
                    printf("%s", err);
                    return -1;
                }
                break;
            case ARG:
                if (c == ',')
                    state = COMMA;
                else if (isspace(c))
                    state = PRESPACE;
                break;
            case PRESPACE:
                if (c == ',')
                    state = COMMA;
                else if (!isspace(c)){
                    printf("%s", err);
                    return -1;
                }
                break;
            case COMMA:
                if (isalnum(c) || c == '-')
                    state = ARG;
                else if (!isspace(c)){
                    printf("%s", err);
                    return -1;
                }
        }
        i++;
    }

    if (state != FUNC && state != ARG && state != PRESPACE){
        printf("%s", err);
        return -1;
    }
    
    return i;
}

/* get_scalar: gets a string, identifies the number part of it and assings it to variable 'scalar' */
double get_scalar(char *str){
    char *p, c;
    int i = 0;
    int flag = 0;
    double scalar;

    p = (char *)calloc(strlen(str) + 1, sizeof(str));
    while ((c = *(str + i)) != ','){
        if (!isdigit(c)){
            if (c == '.' && flag == 0){
                flag = 1;
            }
            else {
                printf("get_scalar: error - not a real number.\n");
                return -1;
            }
        }
        *(p + i) = c;
        i++;
    }
    scalar = atof(p);
    free(p);
    return scalar;
}

/* remove_white_space: gets a string and skips each white space until encounters other character */
char *remove_white_space(char *str){
    unsigned int i = 0, length = strlen(str);
    char c;

    c = *str;
    while ((c == ' ' || c == '\t' || c == '\n' || c == ',') && i++ < length)
        c = *(++str);
    return str;
}

/* read_input: reads the input string into a dynamic array, allocates more memory if needed */
char *read_input(void){
    int buff = 2, i = 0;
    char c, *temp;
    char *str = (char *)calloc(1, sizeof(char));
    if (!str){
        printf("read_input: malloc failed.\n");
        return NULL;
    }

    while ((c = getchar()) != EOF){
        *(str + i++) = c;
        if (i + 1 == buff){
            buff *= 2;
            temp = (char *)realloc(str, buff);
            if (!temp){
                printf("read_input: realloc failed.\n");
                return NULL;
            }
            str = temp;
        }
    }

    *(str + i) = '\0';
    return str;
}

/* mat_analyzer: gets a matrix name string and determines which matrix should be used */
mat *mat_analyzer(char *str, mat *arr[]){
    if (!strcmp(str, "MAT_A"))
        return arr[A];
    else if (!strcmp(str, "MAT_B"))
        return arr[B];
    else if (!strcmp(str, "MAT_C"))
        return arr[C];
    else if (!strcmp(str, "MAT_D"))
        return arr[D];
    else if (!strcmp(str, "MAT_E"))
        return arr[E];
    else if (!strcmp(str, "MAT_F"))
        return arr[F];
    else if (*str == EOF){
        printf("mat_analyzer: error - EOF encountered.\n");
        exit(0);
    }
    else {
        printf("mat_analyzer: undefined matrix name.\n");
        return NULL;
    }
}

/* func_analyzer: gets a function string and determines which function should be called */
int func_analyzer(char *str){
    if (!(strcmp(str, "read_mat")))
        return READ;
    else if (!(strcmp(str, "print_mat")))
        return PRINT;
    else if (!(strcmp(str, "add_mat")))
        return ADD;
    else if (!(strcmp(str, "sub_mat")))
        return SUB;
    else if (!(strcmp(str, "mul_mat")))
        return MUL_M;
    else if (!(strcmp(str, "mul_scalar")))
        return MUL_S;
    else if (!(strcmp(str, "trans_mat")))
        return TRANS;
    else if (!(strcmp(str, "stop")))
        return STOP;
    else if (*str == EOF){
        printf("func_analyzer: error - EOF encountered.\n");
        exit(0);
    }
    else {
        printf("func_analyzer: undefined command.\n");
        return -1;
    }
}

/* create_matrix: create a new variable of type mat *, initialized to 0 */
mat *create_matrix(void){
    mat *matrix;
    matrix = (mat *)calloc(pow(DIM, 2), sizeof(double));
    if (!matrix){
        printf("create_matrix: calloc couldn't allocate memory.\n");
        exit(1);
    }
    return matrix;
}

