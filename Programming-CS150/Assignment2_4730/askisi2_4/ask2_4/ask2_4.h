
#include <iostream>

using namespace std;

class operations
{
public:
    float add();  //addition
    float sub();  //substraction
    float mul();  //multiplication
    float div();  //division
    void setx(float);
    void sety(float);
    float getx();
    float gety();

private:
    float x;
    float y;
};