#include "std_lib_facilities.h"

int recursiveLinearSearch(vector<int> a, int key, int n)
{
    if (n > 75)
    {
        return -1;
    }
    if (a[n] == key)
    {
        return n;
    }
    return recursiveLinearSearch(a, key, n + 1);
}

int main()
{
    int key, i, pos, n;
    int j = 0;
    vector<int> a(76);
    for (i = 0; i <= 75; i++)
    {
        a[i] = 2 * i;
    }
    cout << "Give an even key-number from 0-150 only: " << endl;
    if (!(cin >> key))
    {
        cerr << "Error. You gave a different type of variable, the program needs an int." << endl;
        return -1;
    }
    if (key < 0)
    {
        cerr << "Bad Input. You gave a negative key number, the program needs an even positive number or 0.";
        return -1;
    }
    else if (key > 150)
    {
        cerr << "Bad Input. You gave a key number greater than 150, the program needs an even number between 0-150.";
        return -1;
    }
    else if (key % 2 != 0)
    {
        cerr << "Bad Input. You gave a non-even number, the programm needs an even number." << endl;
        return -1;
    }

    cout << "Give the position of the vector that you want to start the search from: " << endl;
    if (!(cin >> n))
    {
        cerr << "Error. You gave a different type of variable, the programm needs an int." << endl;
        return -1;
    }
    if ((n < 0) || (n > key / 2))
    {
        cerr << "Bad input. You either gave a number that was greater than the position of the key(key/2), or you gave a negative number.";
        return -1;
    }
    pos = recursiveLinearSearch(a, key, n);
    if ((pos == -1) && (key >= 0))
    {
        cout << "The number " << key << " was not found." << endl;
    }
    else
    {
        cout << "The number " << key << " was found at position " << pos << endl;
    }
    return 0;
}