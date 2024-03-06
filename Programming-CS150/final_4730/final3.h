#include "std_lib_facilities.h"

class Vector3
{
private:
    double x;
    double y;
    double z;

public:
    Vector3(double xx, double yy, double zz)
    {
        x = xx;
        y = yy;
        z = zz;
    }
    Vector3(); //default constructor
    Vector3 operator+(const Vector3 &obj)
    {
        Vector3 result;
        result.x += obj.x;
        result.y += obj.y;
        result.z += obj.z;
        return result;
    }
    Vector3 operator-(const Vector3 &obj)
    {
        Vector3 result;
        result.x -= obj.x;
        result.y -= obj.y;
        result.z -= obj.z;
        return result;
    }
    friend ostream &operator<<(ostream &outobj, const Vector3 &obj)
    {
        outobj << "(" << obj.x << ', ' << obj.y << ', ' << obj.z << ')';
        return outobj;
    }
    friend istream &operator>>(istream &inobj, Vector3 &obj)
    {
        cout << "Enter the 1st dim: " << endl;
        inobj >> obj.x;
        cout << "Enter the 2nd dim: " << endl;
        inobj >> obj.y;
        cout << "Enter the 3rd dim: " << endl;
        inobj >> obj.z;
        return inobj;
    };
    Vector3 operator*(int i)
    {
        Vector3 result;
        result.x *= i;
        result.y *= i;
        result.z *= i;
        return result;
    }
    Vector3 operator/(int i)
    {
        Vector3 result;
        result.x /= i;
        result.y /= i;
        result.z /= i;
        return result;
    }
    double dot(const Vector3 &v1, const Vector3 &v2)
    {
        double prod = v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
        return prod;
    }
};

class Ray
{
private:
    Vector3 o, d;
    float t;

public:
    Ray(); //default constructor
    Ray(Vector3 a1, Vector3 a2, float a3)
    {
        o = a1;
        t = a3;
        d = a2;
    } //constructor

    friend ostream &operator<<(ostream &outray, const Ray &ray1)
    {
        outray << "R= " << ray1.o << " + " << ray1.t << " * " << ray1.d;
        return outray;
    }
};
