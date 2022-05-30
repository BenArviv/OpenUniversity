#include <stdio.h>
#include <stdarg.h>
#include <stdlib.h>
#include <time.h>

#define AMOUNT 9 /* a constant for the random array size */

/* the print routine macro */
#define print(a)\
        printf(#a ":\n");\
        printf("decimal: %d\n", a);\
        printf("hexadecimal: %x", a);\
        printf("\n---------------------\n");

int and_all(int amonut, ...);

int main(){
    int s1, s2, s3, s4, i;
    int arr[AMOUNT];

    srand(time(0));
    for (i = 0; i < AMOUNT; i++)
        arr[i] = rand();

    s1 = and_all(0);
    s2 = and_all(1, arr[0]);
    s3 = and_all(3, arr[1], arr[2], arr[3]);
    s4 = and_all(5, arr[4], arr[5], arr[6], arr[7], arr[8]);
    
    printf("s1 input: 0\n");
    print(s1)
    printf("s2 input: 1, %d\n", arr[0]);
    print(s2)
    printf("s3 input: 3, %d, %d, %d\n", arr[1], arr[2], arr[3]);
    print(s3)
    printf("s4 input: 5, %d, %d, %d ,%d, %d\n", arr[4], arr[5], arr[6], arr[7], arr[8]);
    print(s4)

    return 0;
}

/* and_all: a variable arguments function, with first argument as indicator for amount of arguments.
            gets variable amount of integers, returns the result of AND & operation on all of them */
int and_all(int amount, ...){
    va_list p; 
    int and = 0, i = 0, num;
    va_start(p, amount);
    if (amount){
        num = va_arg(p, int);
        and = num;
        i++;
    }
    else
        return -1;
    
    for (i; i < amount; i++){
        num = va_arg(p, int);
        and &= num;
    }
    va_end(p);
    return and;
}
