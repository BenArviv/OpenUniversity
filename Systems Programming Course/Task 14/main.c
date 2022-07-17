
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_EXTENSION_LEN 5
#define ERROR 1

/* Types of files that indicate what is the desirable file extension */
enum filetypes {FILE_INPUT, FILE_OBJECT, FILE_ENTRY, FILE_EXTERN};

/* used for creating files and assigning required extensions to them */
char *createFileName(char *original, int type);

int main(int argc, char *argv[])
{
    int i;
    char *filename;
    FILE *fp;
    for (i = 1; i < argc; i++)
    {
        filename = createFileName(argv[i], FILE_INPUT);
        fp = fopen(filename, "r");
          if(fp != NULL) { /* If file exists */
           
        }
        else printf("CANNOT_OPEN_FILE\n");
        free(filename);
    }
    return 0;
}




char *createFileName(char *original, int type)
{
    char *newFileName = (char *) malloc(strlen(original) + MAX_EXTENSION_LEN);
    if(newFileName == NULL)
    {
        fprintf(stderr, "Dynamic allocation error.");
        exit(ERROR);
    }
        strcpy(newFileName, original); /* Copying original filename to the bigger string */

    /* Concatenating the required file extension */

    switch (type)
    {
        case FILE_INPUT:
            strcat(newFileName, ".as");
            break;

        case FILE_OBJECT:
            strcat(newFileName, ".ob");
            break;

        case FILE_ENTRY:
            strcat(newFileName, ".ent");
            break;

        case FILE_EXTERN:
            strcat(newFileName, ".ext");

    }
    return newFileName;
}


