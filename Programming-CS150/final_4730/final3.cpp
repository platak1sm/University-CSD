#include "std_lib_facilities.h"
#include "final3.h"
int main(){
    Vector3 v1(7, 6.1, 3);
    Vector3 v2(4.2 ,2, 9.4);
    Vector3 v3;
    Ray r1(v1, v2, 3.4 );
    v3=v1+v2;
    cout<<v3<<endl;
    v3=v1-v2;
    cout<<v3<<endl;
    v3=v1*2;
    cout<<v3<<endl;
    v3=v2/2;
    cout<<v3<<endl;
    cout<<v3.dot(v1,v2);
    cout<<r1<<endl;
    return 0;
}
