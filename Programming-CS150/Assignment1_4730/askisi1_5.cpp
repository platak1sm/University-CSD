#include "std_lib_facilities.h"

int main()
{
    vector<int> maxco(3);
    int c1, c2, c3, bill, b, c11, c22, c33, i, j, temp; /*coins*/
    double cost, change;
    cout << "Please insert the available currency coins: " << endl;
    if (!(cin >> c1 >> c2 >> c3))
    {
        cerr << "Error. Please insert a valid type of variable (int) " << endl;
        exit(-1);
    }
    if ((c1==c2) || (c2==c3) || (c1==c3)){
        cerr << "Error. Please insert variables with different content. " << endl;
        exit(-1);
    }
    cout << "Please insert the cost of the product: " << endl;
    if (!(cin >> cost))
    {
        cerr << "Error. Please insert a valid type of variable (int) " << endl;
        exit(-1);
    }
    maxco[0] = c1;
    maxco[1] = c2;
    maxco[2] = c3;
    for (i = 1; i <= 2; i++)
    {
        for (j = 2; j >= i; j--)
        {
            if (maxco[j - 1] > maxco[j])
            {
                temp = maxco[j - 1];
                maxco[j - 1] = maxco[j];
                maxco[j] = temp;
            }
        }
    }
    c1 = maxco[0];
    c2 = maxco[1];
    c3 = maxco[2];
    while (cost != -1)
    {
        cost = cost * 100;
        c11 = 0;
        c22 = 0;
        c33 = 0;
        cout << "Please pay using a 200, 500 or 1000 bill: " << endl;
        cin >> bill;
        while (bill < cost)
        {
            cout << "Please pay using a 200, 500 or 1000 bill: " << endl;
            cin >> b;
            bill += b;
        }
        change = bill - cost;
        if (change == 0)
        {
            cout << "Your change is " << c3 << "x0, " << c2 << "x0, " << c1 << "x0" << endl;
        }
        else
        {
            while (c1 <= change)
            {
                if (c3 <= change)
                {
                    ++c33;
                    change -= c3;
                }
                else if (c2 <= change)
                {
                    ++c22;
                    change -= c2;
                }
                else if (c1 <= change)
                {
                    ++c11;
                    change -= c1;
                }
            }
            if (change == 0)
            {
                cout << "Your change is " << c3 << "x" << c33 << ", " << c2 << "x" << c22 << ", " << c1 << "x" << c11 << endl;
            }
            else
            {
                cout << "Your change is " << c3 << "x" << c33 << ", " << c2 << "x" << c22 << ", " << c1 << "x" << c11 << endl;
                cout << "The vending machine cannot give you the following change " << change << endl;
            }
        }
        cout << "Please insert the cost of the product: " << endl;
        cin >> cost;
    }
}

//diorthoseis: den elegxa to bill na einai 200,500,1000,   eixa if c1=c2 anti gia c1==c2 kai panta evgaze error
