#include "mystring.h"

int main()
{
    char d1[10], d2[10], *d3;
    printf("%d : %d\n", ms_length("that"), strlen("that"));
    printf("%d : %d\n", ms_compare("that", "this"), strcmp("this", "that"));
    strcpy(d1, "this");
    printf("%s : ", d1);
    ms_copy(d2, "that");
    printf("%s\n", d2);
    strcat(d1, "that");
    printf("%s : ", d1);
    ms_concat(d2, "this");
    printf("%s\n", d2);
    d3 = strstr("ManosPlatakis", "Plat");
    printf(d3);
    printf("\n");
    d3 = ms_search("ManosPlatakis", "nos");
    printf(d3);
    return 0;
}