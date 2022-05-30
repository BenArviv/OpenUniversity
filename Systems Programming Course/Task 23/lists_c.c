#include <stdio.h>
#include <stdlib.h>

#define LENGTH 25

typedef struct Lnode{
    char *string;
    struct Lnode *next;
} Node;

Node *nalloc(void);
void read_chars(Node *node, char *fname);
void print_chars(Node *node);
void free_list(Node *node);

int main(int argc, char *argv[]){
    Node *head;

    head = nalloc();

    if (argc < 2){
        printf("argc error: missing file name.\n");
        exit(0);
    }
    
    read_chars(head, argv[1]);
    print_chars(head);
    free_list(head);

    return 0;
}

/* free_list: gets the head node and frees the linked-list. */
void free_list(Node *node){
    Node *temp;

    while (node != NULL){
        temp = node -> next;
        free(node -> string);
        free(node);
        node = temp;
    }
}

/* print_chars: gets the head node and prints the character it holds, LENGTH characters at each line. 
                distinguishes between characters like '\t' and '\n', and the rest characters. */
void print_chars(Node *node){
    char c;
    int i = 0, count = 0;

    while (node != NULL){
        c = node -> string[i++];
        
        if (c == '\t')
            count += 8;
        else if (c == '\n')
            count = 0;
        else
            count++;

        putchar(c);

        if (i == LENGTH){
            i = 0;
            node = node -> next;
        }

        if (count == LENGTH){
            putchar('\n');
            count = 0;
        }
    }
}

/* read_chars: gets the head node and the file name, builds a linked-list starting from head
                and containing characters from the file, at most LENGTH characters. */
void read_chars(Node *node, char *fname){
    FILE *fptr;
    Node *temp;
    char c;
    int i = 0;

    fptr = fopen(fname, "r");
    if (fptr == NULL){
        printf("read_chars: error, file %s doesn't exist.\n", fname);
        exit(1);
    }

    while ((c = fgetc(fptr)) != EOF){
        node -> string[i++] = c;
        if (i == LENGTH){
            i = 0;
            temp = nalloc();
            node -> next = temp;
            node = temp;
        }
    }

    fclose(fptr);
}

/* nalloc: allocates memory for a Node variable and its string field */
Node *nalloc(void){
    Node *n = (Node *)malloc(sizeof(Node));
    if (!n){
        printf("nalloc: error, node malloc failed.\n");
        exit(2);
    }
    n -> string = (char *)malloc(LENGTH * sizeof(char));
    if (!(n -> string)){
        printf("nalloc: error, string malloc failed.\n");
        exit(2);
    }
    return n;
}
