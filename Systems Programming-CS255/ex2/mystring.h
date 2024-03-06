#include <stddef.h>
#include <stdio.h>
#include <assert.h>

/*
   @author Emmanouil Platakis 4730
   Assignment 2
*/

/*
  @return string s length (strlen)
  It is a checked runtime error for `s` to be NULL.
 */
size_t ms_length(const char *s);

/* Copy string s to d. (strcpy)
   @return a pointer to the string d
*/
char *ms_copy(char *d, const char *s);

/*  Copy n characters from s to d. If length < n d will be null (strncpy)
    @returns a pointer to the eventual string d
*/
char *ms_ncopy(char *d, const char *s, size_t n);

/*  Puts string s in the end of the string d. (strcat)
    @returns a pointer to the eventual string d
*/
char *ms_concat(char *d, const char *s);

/*  Puts the first n chars of string s in the end of the string d. (strncat)
 */
char *ms_nconcat(char *d, const char *s, size_t n);

/* Compares s1 with s2. (strcmp)
   @returns: -1 if s1 < s2.
              0 if s1 = s2.
              1 if s2 < s1.

*/
int ms_compare(const char *s1, const char *s2);

/* Compares the first n chars of s1, s2 (strncmp)
   @returns: -1 if s1 < s2.
              0 if s1 = s2.
              1 if s2 < s1.
*/
int ms_ncompare(const char *s1, const char *s2, size_t n);

/*
 Takes two strings s1 and s2 as an argument and finds the first occurrence
 of the sub-string s2 in the string s1. The process of matching does not
 include the terminating null-characters(‘\0’), but function stops there. (strstr)

@returns a pointer to the first occurrence in s1 of any
of the entire sequence of characters specified in s2, or a null pointer
if the sequence is not present in s1.
*/
char *ms_search(const char *s1, const char *s2);
