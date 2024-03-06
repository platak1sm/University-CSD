#include "std_lib_facilities.h"

//thema 2

int main()
{
    vector<string> names;
    string name, nameprev;
    vector<int> times;
    int i = 0, j, k; //metritis

    cout << "Give a name: (type end if you want to stop)" << endl;
    cin >> name;
    while (name != "end")
    {
        if (i == 0)
        {
            names[i] = name;
            times.push_back(1);
            i++;
        }
        else
        {
            j = 0;
            k = 0; //0 false 1 true
            while ((j <= times.size() - 1) && (k == 0))
            {
                if (names[j] == name)
                {
                    k = 1;
                    times[j]++;
                }
                j++;
            }
            if (k == 0)
            {
                names.push_back(name);
                times.push_back(1);
                i++;
            }
        }
        cout << "Give a name: (type end if you want to stop)" << endl;
        cin >> name;
    }
    int n = times.size();
    for (i = 0; i < n; i++)
    {
        cout << names[i] << "\t" << times[i];
    }
    return 0;
}

