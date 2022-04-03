#include <stdio.h>
#define MAXLIMIT 100

int scalar_product(int[], int[], int);
void set_array(int[], int);

int main(){
    int arr1[MAXLIMIT], arr2[MAXLIMIT];
    int size, result;

    printf("Enter the vectors' dimension: \n\n");
    scanf("%d", &size);

    set_array(arr1, size);
    set_array(arr2, size);

    result = scalar_product(arr1, arr2, size);
    printf("The scalar product is: %d \n", result);
    
    return 0;
}

/* set_array: creates a vector from the user's input */ 
void set_array(int a[], int size){
    int i;
    printf("Please enter a vector: \n");
    for (i = 0; i < size && i < MAXLIMIT - 1 && (scanf("%d", &a[i]) != EOF); i++);
}

/* scalar_product: receives two vectors (arrays) and returns their scalar product */
int scalar_product(int a1[], int a2[], int size){
    int product = 0, i;

    for (i = 0; i < size; i++){
        product += a1[i] * a2[i];
    }
    
    return product;
}
