
#include "ask2_4.h"

class Invalid{};
float operations::add() { return x + y; } //addition
float operations::sub() { return x - y; } //substraction
float operations::mul() { return x * y; } //multiplication
float operations::div()
{
    try
    {
        if (y == 0)
        {
            throw Invalid();
        }
    }
    catch (Invalid &y)
    {
        cerr<< "Error: 0 as the divisor"<<endl;
        return -1;
    }
    return x / y;
}

void operations:: setx(float x1){
    x=x1;
}

void operations:: sety(float y1){
    y=y1;
}

float operations:: getx(){return x;}
float operations:: gety(){return y;}