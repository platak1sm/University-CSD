
#include "ask2_4.h"

int main()
{
    float a, b;
    operations op;
    cout << "Give me two floats for an addition: " << endl;
    if (!(cin >> a >> b))
    {
        cerr << "Error, you gave wrong type of variables." << endl;
        exit(-1);
    }
    op.setx(a);
    op.sety(b);
    cout << "Addition: " << a << " + " << b << " = " << op.add() << endl;
    cout << "Give me two floats for a substraction: " << endl;
    if (!(cin >> a >> b))
    {
        cerr << "Error, you gave wrong type of variables." << endl;
        return -1;
    }
    op.setx(a);
    op.sety(b);
    cout << "Substraction: " << a << " - " << b << " = " << op.sub() << endl;
    cout << "Give me two floats for a multiplication: " << endl;
    if (!(cin >> a >> b))
    {
        cerr << "Error, you gave wrong type of variables." << endl;
        return -1;
    }
    op.setx(a);
    op.sety(b);
    cout << "Multiplication: " << a << " * " << b << " = " << op.mul() << endl;
    cout << "Give me two floats for a division: " << endl;
    if (!(cin >> a >> b))
    {
        cerr << "Error, you gave wrong type of variables." << endl;
        return -1;
    }
    op.setx(a);
    op.sety(b);
    cout << "Division: " << a << " / " << b << " = " << op.div() << endl;
    return 0;
}