#include <stdio.h>
#include <stdlib.h> /* for memory allocation functions */
#include <ctype.h> /* for isalnum function */ 

#define LIM 20 /* the length limit of each line printed */

void readchars(char c, char *p, int *cnt, int *cntan, int *buff);
void printchars(char *p, int *cnt, int *cntan);

int main(){
    char c;
    char *p; /* pointer to characters dynamic array */
    int cnt = 0; /* characters counter */
    int cntan = 0; /* alphanumerical characters counter */
    int buff = 2; /* array size buffer */

    p = (char *) malloc(0);

    printf("Please enter a list of characters:\n");
    if ((c = getchar()) != EOF){
        cnt++;
        readchars(c, p, &cnt, &cntan, &buff);
    }

    printchars(p, &cnt, &cntan);
    free(p);
    return 0;
}

/* readchars: read input characters, while reallocating memory for each new character read. */
void readchars(char c, char *p, int *cnt, int *cntan, int *buff){
    char *q;

    do{
        q = (char *) realloc (p, *buff);
        if (!q){
            printf("Memory allocation failed.\n");
            return;
        }
        p = q;
        *(p + *cnt - 1) = c;
        (*cnt)++;
        if (isalnum(c))
            (*cntan)++;
        if (*cnt == *buff)
            *buff *= 2;
    } while ((c = getchar()) != EOF);

    q = (char *) realloc(p, *cnt);
    if (q){
        p = q;
    }
}

/* printchars: prints the array, LIM characters in each line. */
void printchars(char *p, int *cnt, int *cntan){
    int i, characters = 0;

    printf("\n");
    for (i = 0; i < *cnt; i ++){
        putchar(p[i]);
        characters++;
        if (characters == LIM || p[i] == '\n'){
            characters = 0;
            printf("\n");
        }
    }
    printf("\n");

    printf("Total characters read: %d\n", *cnt - 1);
    printf("Alphanumerical characters read: %d\n", *cntan);
}
