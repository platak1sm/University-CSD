#include "std_lib_facilities.h"

int main()
{
    int l1, l2, i, len1, len2, j;
    string s1, s2, s3;
    cout << "Give 1st string: \n";
    getline(cin, s1);
    cout << "Give 2nd string: \n";
    getline(cin, s2);
    l1 = s1.size();
    l2 = s2.size();
    i = 0;
    len1 = l1;
    len2 = l2;
    s3.clear();
    while (l1 != 0 && l2 != 0)
    {
        s3 = s3 + s1[i] + s2[i];
        ++i;
        --l1;
        --l2;
    }
    if (l1 == 0)
    {
        for (j = i; j <= len2; j++)
        {
            s3 = s3 + s2[j];
        }
    }
    else if (l2 == 0)
    {
        for (j = i; j <= len1; j++)
        {
            s3 = s3 + s1[j];
        }
    }
    cout << "The new string is: " << s3 << "\n";
    return 0;
}