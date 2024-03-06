#include "std_lib_facilities.h"
#include "askisi2_2.h"
class Bad_Input
{
};

void Rectangle::setLength(double l)
{
    try
    {
        if ((l <= 0) || (l > 30))
        {
            throw invalid_argument("Invalid input. We want length between 1-30");
        }
    }
    catch (const invalid_argument &l)
    {
        cout << "Error: " << l.what() << endl;
        exit(-1);
    }
    length = l;
}
void Rectangle::setWidth(double w)
{
    try
    {
        if ((w <= 0) || (w > 30))
        {
            throw invalid_argument("Invalid input. We want length between 1-30");
        }
    }
    catch (const invalid_argument &w)
    {
        cout << "Error: " << w.what() << endl;
        exit(-1);
    }
    width = w;
}

double Rectangle::getLength()
{
    return length;
}
double Rectangle::getWidth()
{
    return width;
}

double Rectangle::perimeter()
{
    return (2 * length + 2 * width);
}
double Rectangle::area()
{
    return length * width;
}

int main()
{
    double w1, w2, l1, l2;
    Rectangle rect; //beggining values
    cout << "The area of the first rectangle is: " << rect.area() << endl;
    //now we will create the valid rectangle
    cout << "The perimeter of the first rectangle is: " << rect.perimeter() << endl;
    cout << "Give the length and the width of the valid renctangle(1-30): " << endl;
    try
    {
        if (!(cin >> l1 >> w1))
        {
            throw Bad_Input();
        }
    }
    catch (Bad_Input &l1)
    {
        cerr << "Error. You gave a wrong variable type, you need a double.";
    }
    //valid values
    rect.setLength(l1);
    rect.setWidth(w1);
    cout << "The area of the second (valid) rectangle is: " << rect.area() << endl;
    cout << "The perimeter of the second (valid) rectangle is: " << rect.perimeter() << endl;
    //now we will create the invalid rectangle
    cout << "Give the length and the width of the invalid renctangle(<1 OR >30): " << endl;
    try
    {
        if (!(cin >> l2 >> w2))
        {
            throw Bad_Input();
        }
    }
    catch (Bad_Input &l2)
    {
        cerr << "Error. You gave a wrong variable type, you need a double.";
    }
    //invalid values
    rect.setLength(l2);
    rect.setWidth(w2);
    cout << "The area of the third (invalid) rectangle is: " << rect.area() << endl;
    cout << "The perimeter of the third (invalid) rectangle is: " << rect.perimeter() << endl;
    return 0;
}
