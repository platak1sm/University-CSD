#include "std_lib_facilities.h"

//thema 1
void swap(double *a, double *b) // swap a element with b element
{
    double t; //temp
    t = *a;
    *a = *b;
    *b = t;
}

void selectionsort(double a[], int sizeofarr)
{
    int i, j, min;
    for (i = 0; i < sizeofarr - 1; i++)
    {
        min = i;
        for (j = i + 1; j < sizeofarr; j++)
        {
            if (a[min] > a[j])
                min = j;
        }
        swap(&a[i], &a[min]); // swapping the min element with the element in its position in the array
    }
}

int main (){
    int i, n;
    double x;
    cout<<"How many elements will you put in the array? "<<endl;
    while(!(cin >> n)){
            cout<<"Wrong value please insert an int type value: "<< endl;   
        }
    double *arr= new double[n];
    for(i=0;i<n;i++){
        cout<<"Please insert the element: "<<endl;
        while(!(cin >> x)){
            cout<<"Wrong value please insert a double type value: "<< endl;   
        }
        arr[i]=x;
    }
    selectionsort(arr, n);
    for(i=0;i<n;i++){
        cout<<"new array:(ascending order)"<<endl;
        cout<<arr[i]<<endl;
    }
    return 0;
}