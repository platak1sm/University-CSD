#include "mystring.h"
#include <string.h>

/*
   @author Emmanouil Platakis 4730
   Assignment 2
   mystring_ars.c
*/

/*
  @return string s length (strlen)
  It is a checked runtime error for `s` to be NULL.
 */
size_t ms_length(const char pcStr[])
{
    size_t uiLength = 0U;
    assert(pcStr != NULL);
    while (pcStr[uiLength] != '\0')
        uiLength++;
    return uiLength;
}

/* Copy string s to d. (strcpy)
   @return a pointer to the string d
    It is a checked runtime error for `s`, 'd' to be NULL.
*/
char *ms_copy(char d[], const char s[])
{
    int i;
    assert(s != NULL);
    assert(d != NULL);
    for (i = 0; s[i] != '\0'; i++)
    {
        d[i] = s[i];
    }
    d[i] = '\0';
    return d;
}

/*  Copy n characters from s to d. If length < n d will be null (strncpy)
    @returns a pointer to the eventual string d
    It is a checked runtime error for `s`, 'd' to be NULL or n=0.
*/
char *ms_ncopy(char d[], const char s[], size_t n)
{
    int i;
    assert(s != NULL);
    assert(d != NULL);
    assert(n != 0);
    if (n > ms_length(s))
        return NULL;
    for (i = 0; i < n; i++)
        d[i] = s[i];
    d[i] = '\0';
    return d;
}

/*  Puts string s in the end of the string d. (strcat)
    @returns a pointer to the eventual string d
    It is a checked runtime error for `s`, 'd' to be NULL.
*/
char *ms_concat(char d[], const char s[])
{
    int i = ms_length(d), j = 0;
    assert(s != NULL);
    assert(d != NULL);
    while (j != ms_length(s))
    {
        d[i] = s[j];
        i++;
        j++;
    }
    d[i] = '\0';
    return d;
}

/*Puts the first n chars of string s in the end of the string d. (strncat)
It is a checked runtime error for `s`, 'd' to be NULL or n=0.
 */
char *ms_nconcat(char d[], const char s[], size_t n)
{
    int i = ms_length(d), j = 0;
    assert(s != NULL);
    assert(d != NULL);
    assert(n != 0);
    while (j != n)
    {
        if (s[j] != '\0')
            d[i] = s[j];
        else
            d[i] = '\0';
        i++;
        j++;
    }
    d[i] = '\0';
    return d;
}

/* Compares s1 with s2. (strcmp)
   @returns: -1 if s1 < s2.
              0 if s1 = s2.
              1 if s2 < s1.
 It is a checked runtime error for 's1', 's2' to be NULL

*/
int ms_compare(const char s1[], const char s2[])
{
    int i;
    assert(s1 != NULL);
    assert(s2 != NULL);
    for (i = 0; s1[i] == s2[i]; i++)
    {
        if (i == ms_length(s1) || i == ms_length(s2))
        {
            return 0;
        }
    }
    if (s1[i] < s2[i])
        return -1;
    else
        return 1;
}

/* Compares the first n chars of s1, s2 (strncmp)
   @returns: -1 if s1 < s2.
              0 if s1 = s2.
              1 if s2 < s1.
 It is a checked runtime error for 's1', 's2' to be NULL or n=0.
*/
int ms_ncompare(const char s1[], const char s2[], size_t n)
{
    int i;
    assert(s1 != NULL);
    assert(s2 != NULL);
    assert(n != 0);
    for (i = 0; s1[i] == s2[i] && i != n; i++)
    {
        if (i == n - 1 || i == ms_length(s1))
        {
            return 0;
        }
    }
    if (s1[i] < s2[i])
        return -1;
    else
        return 1;
}

/* Takes two strings s1 and s2 as an argument and finds the first occurrence
 of the sub-string s2 in the string s1. The process of matching does not
 include the terminating null-characters(‘\0’), but function stops there. (strstr)

 @returns a pointer to the first occurrence in s1 of any
 of the entire sequence of characters specified in s2, or a null pointer
 if the sequence is not present in s1
 It is a checked runtime error for 's1', 's2' to be NULL.
*/
char *ms_search(const char s1[], const char s2[])
{
    int i, j, k;
    assert(s1 != NULL);
    assert(s2 != NULL);
    for (i = 0; i < ms_length(s1); i++)
    {
        if (s1[i] == s2[0])
        {
            k = i;
            j = 0;
            while (j < ms_length(s2))
            {
                if (s1[k] != s2[j])
                    break;
                j++;
                k++;
            }
            if (j == ms_length(s2))
            {
                return &(s1[i]); /*address*/
            }
        }
    }
    return NULL;
}

