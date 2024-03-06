#include "std_lib_facilities.h"

double vector_magnitude(double a, double b)
{
    return sqrt(a * a + b * b);
}

double vector_magnitude(double a, double b, double c)
{
    return sqrt(a * a + b * b + c * c);
}

class Bad_input{};

int main()
{
    int dim;        /*dimensions*/
    double x, y, z; /*coordinates*/
    cout << "Please give the vector's dimensions: \n";
    cin >> dim;
    cout << "Please give the vector's coordinates: \n";
    if (dim == 2)
    {
        try
        {
            if (!(cin >> x >> y))
            {
                throw Bad_input();
            }
        }
        catch (Bad_input &x)
        {
            cerr << "Wrong variable type, please enter a double variable next time- ";
        }
        cout << vector_magnitude(x, y);
    }
    else if (dim == 3)
    {
        try
        {
            if (!(cin >> x >> y >> z))
            {
                throw Bad_input();
            }
        }
        catch (Bad_input &x)
        {
            cerr << "Wrong variable type, please enter a double variable next time- ";
        }
        cout << vector_magnitude(x, y, z);
    }
}
