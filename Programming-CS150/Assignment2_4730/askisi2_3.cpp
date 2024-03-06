#include "std_lib_facilities.h"
#include <fstream>
using namespace std;

double calculateCharges(int d)
{ //d=days
    if (d == 3)
        return 2;
    else if ((d > 3) && (d < 14))
        return 2 + (d - 3) * 1.50;
    else
        return 17.50;
}

int main()
{
    int i, days, sumd = 0;
    double sumc = 0;
    string name;
    vector<double> charge(5);
    vector<int> d(5);
    for (i = 0; i <= 4; i++)
    {
        cout << "For how many days did the customer borrow the book? Please enter: " << endl;
        if (!(cin >> days))
        {
            cerr << "Error. You gave a wrong type of variable instead of an integer." << endl;
            return -1;
        }
        if ((days < 3) || (days > 14))
        {
            cerr << "Error. You gave a wrong value for the variable. You need to give a value between 3-14." << endl;
            return -1;
        }
        charge[i] = calculateCharges(days);
        d[i] = days;   
    }
    for (i = 0; i <= 4; i++)
    {
        sumc += charge[i];
        sumd += d[i];
    }
    cout << "Please enter name of output file: ";
    cin >> name;
    ofstream ofs(name.c_str());

    if (!ofs)
        error("cannot open output file ", name);
    ofs << "---------------------------------------------------" << endl
        << "                  Table_of_Clients                 " << endl
        << "---------------------------------------------------" << endl
        << "              Charge            Days              " << endl
        << "Client_1      " << fixed << setprecision(2) << charge[0];
    if (charge[0] < 10)
        ofs << " ";
    ofs << "              " << setprecision(2) << d[0] << endl
        << "Client_2      " << fixed << setprecision(2) << charge[1];
    if (charge[1] < 10)
        ofs << " ";
    ofs << "              " << setprecision(2) << d[1] << endl
        << "Client_2      " << fixed << setprecision(2) << charge[2];
    if (charge[2] < 10)
        ofs << " ";
    ofs << "              " << setprecision(2) << d[2] << endl
        << "Client_3      " << fixed << setprecision(2) << charge[3];
    if (charge[3] < 10)
        ofs << " ";
    ofs << "              " << setprecision(2) << d[3] << endl
        << "Client_4      " << fixed << setprecision(2) << charge[4];
    if (charge[4] < 10)
        ofs << " ";
    ofs << "              " << setprecision(2) << d[4] << endl;
    ofs << "---------------------------------------------------" << endl
        << "Sum:          " << fixed << setprecision(2) << sumc
        << "              " << sumd << endl
        << "---------------------------------------------------" << endl;
    ofs.close();
    return 0;
}