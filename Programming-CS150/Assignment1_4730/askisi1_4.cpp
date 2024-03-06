#include "std_lib_facilities.h"
int main()
{
    string s1;
    int no = 0, i, l1, nc = 0, firsto, firstc, lasto, lastc; /* no= number of opened parentheses, nc= number of closed, lasto= last opened, lastc= last closed */
    cout << "Give a string: ";
    getline(cin, s1);
    l1 = s1.size();
    for (i = 0; i <= l1; ++i)
    {
        if (s1[i] == '(')
        {
            ++no;
            lasto = i;
            if (no == 1)
                firsto = i;
        }
        else if (s1[i] == ')')
        {
            ++nc;
            lastc = i;
            if (nc == 1)
                firstc = i;
        }
    }
    if ((no == nc) && (firsto < firstc) && (lasto < lastc))
        cout << "Correct.";
    else
    {
        if (firstc < firsto)
            cout << "Wrong at position " << firstc << ".\n";
        else if (no > nc)
            cout << "Wrong at position " << lasto << ".\n";
        else if (no < nc)
            cout << "Wrong at position " << lastc << ".\n";
        else if (lastc < lasto)
            cout << "Wrong at position " << lastc << endl;
    }
    return 0;
}
//diorthoseis: otan empaine string xwris "(" h ")" crushare to programma 