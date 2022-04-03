#include <stdio.h>

int find_bits(unsigned long, unsigned long);
void printBin(unsigned long);

int main(){
    unsigned long x, y;
    int amount;

    printf("Please enter the value for x: \n");
    scanf("%lu", &x);
    printf("x = ");
    printBin(x);

    printf("Now enter the value for y: \n");
    scanf("%lu", &y);
    printf("y = ");
    printBin(y);

    amount = find_bits(x, y);
    printf("The number of bits on is %d\n", amount);

    return 0;
}

int max_val; /* a variable that contains the maximum value of mask */ 

/* printBin: gets an integer and prints its binary presentation */
void printBin(unsigned long x){
    unsigned long mask = 1;

    /* determines what should be the mask's value */
    while (mask < x){
        mask <<= 1; 
    }

    max_val = mask > max_val ? mask : max_val;

    for (; mask != 0; mask >>= 1){
        if ((x & mask) == 0)
            printf("0");
        else
            printf("1");
    }
    printf("\n");
}

/* find_bits: gets two integers, calculates the amount of common turned on bits in their 
binary presentation and prints the result */
int find_bits(unsigned long x, unsigned long y){
    unsigned long mask = 1;
    int count = 0;

    while (mask < max_val){
        if (x & mask && y & mask){
            count++;               
        }
        mask <<= 1; 
    }
    return count;
}
