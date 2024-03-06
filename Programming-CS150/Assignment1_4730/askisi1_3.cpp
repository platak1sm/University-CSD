#include "std_lib_facilities.h"

class Bad_input
{
};

int main()
{
    double g = 9.8, v0, h, t, x;
    cout << "Please give the initial velocity: \n";
    try
    {
        if ((!(cin >> v0)) && (v0 <= 0))
        {
            throw Bad_input();
        }
    }
    catch (Bad_input &v0)
    {
        cerr << "Wrong variable type, please enter a non-char variable next time- ";
        return -1;
    }
    cout << "Please give the height of the cliff: \n";
    try
    {
        if ((!(cin >> h)) && (h <= 0))
        {
            throw Bad_input();
        }
    }
    catch (Bad_input &h)
    {
        cerr << "Wrong variable type, please enter a non-char variable next time- ";
        return -1;
    }
    h = h * 1000;
    t = sqrt(2 * h / g); /* h=g*t^2/2 <=> t^2=2h/g , xronos pou einai ston aera*/
    x = v0 * t;          /* v0= x/t, velinekes */
    x = x / 1000;
    cout << "t= " << t << " seconds and x= " << x << " km";
    return 0;
}
//diorthoseis: line13: anti gia && eprepe na valw ||
