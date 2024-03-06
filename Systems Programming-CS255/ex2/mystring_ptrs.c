#include "mystring.h"
#include <string.h>

/*
   @author Emmanouil Platakis 4730
   Assignment 2
   mystring_ptrs.c
*/

/*
  @return string s length (strlen)
  It is a checked runtime error for `s` to be NULL.
 */
size_t ms_length(const char *pcStr)
{
    const char *pcStrEnd = pcStr;
    assert(pcStr); /* Works because NULL and FALSE are identical. */
    while (*pcStrEnd)
        pcStrEnd++; /* Works because end-of-string and FALSE are identical. */
    return pcStrEnd - pcStr;
}

/* Copy string s to d. (strcpy)
   @return a pointer to the string d
   It is a checked runtime error for `s` to be NULL.
*/
char *ms_copy(char *d, const char *s)
{
    char *c = d;
    assert(s);

    while (*s != '\0')
    {
        *c = *s;
        s++;
        c++;
    }
    *c = '\0';
    return d;
}

/*  Copy n characters from s to d. If length < n d will be null (strncpy)
    @returns a pointer to the eventual string d
    It is a checked runtime error for `s` to be NULL or n=0.
*/
char *ms_ncopy(char *d, const char *s, size_t n)
{
    char *c = d;
    int i;
    assert(s);
    assert(n != 0);
    for (i = 0; i < n; i++)
    {
        if (*s == '\0')
            break;
        *c = *s;
        s++;
        c++;
    }
    *c = '\0';
    return d;
}

/*  Puts string s in the end of the string d. (strcat)
    @returns a pointer to the eventual string d
    It is a checked runtime error for `s` to be NULL.
*/
char *ms_concat(char *d, const char *s)
{
    char *c = d;
    assert(s);
    while (*c != '\0')
        c++;
    while (*s != '\0')
    {
        *c = *s;
        c++;
        s++;
    }
    *c = '\0';
    return d;
}

/*  Puts the first n chars of string s in the end of the string d. (strncat)
    @returns a pointer to the eventual string d
    It is a checked runtime error for `s` to be NULL or n=0.
 */
char *ms_nconcat(char *d, const char *s, size_t n)
{
    char *c = d;
    assert(s);
    assert(n != 0);
    while (*c != '\0')
        c++;
    ms_ncopy(c, s, n);
    return d;
}

/* Compares s1 with s2. (strcmp)
   @returns: -1 if s1 < s2.
              0 if s1 = s2.
              1 if s2 < s1.
It is a checked runtime error for 's1', 's2' to be NULL
*/
int ms_compare(const char *s1, const char *s2)
{
    assert(s1);
    assert(s2);
    for (; *s1 == *s2; s1++, s2++)
        if (*s1 == '\0' && *s2 == '\0')
            return 0;
    if (*s1 < *s2)
        return -1;
    else
        return 1;
}

/* Compares the first n chars of s1, s2 (strncmp)
   @returns: -1 if s1 < s2.
              0 if s1 = s2.
              1 if s2 < s1.
It is a checked runtime error for 's1', 's2' to be NULL or n=0

*/
int ms_ncompare(const char *s1, const char *s2, size_t n)
{
    int i;
    assert(s1);
    assert(s2);
    assert(n != 0);
    for (i = 0; (*s1 == *s2) && (i < n); s1++, s2++)
        if (*s1 == '\0' || i == n - 1)
            return 0;
    if (*s1 < *s2)
        return -1;
    else
        return 1;
}

/*
 Takes two strings s1 and s2 as an argument and finds the first occurrence
 of the sub-string s2 in the string s1. The process of matching does not
 include the terminating null-characters(‘\0’), but function stops there. (strstr)

@returns a pointer to the first occurrence in s1 or a null pointer
if the sequence is not present in s1.
It is a checked runtime error for 's1', 's2' to be NULL
*/
char *ms_search(const char *s1, const char *s2)
{
    const char *c1, *c2;
    assert(s1);
    assert(s2);
    while (*s1 != '\0')
    {
        if (*s1 == *s2)
        {
            c1 = s1;
            c2 = s2;
            for (; *c2 != '\0'; c1++, c2++)
                if (*c1 != *c2)
                    break;
            if (*c2 == '\0')
                return s1;
        }
        s1++;
    }
    return NULL;
}

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
