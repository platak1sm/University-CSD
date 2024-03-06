/*  Emmanouil Platakis 4730
    csd4730@csd.uoc.gr     */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <signal.h>
#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h>

#define clear() printf("\033[H\033[J")

int mycommands(char **args); /* handle my commands (cd, exit)*/

void exec(char **args); /*execute default commands*/

void execpiped(char **args, char **argspiped); /*execute pipeline commands*/

char *read_line(); /*reading command line*/

int pipefinder(char *s, char **spiped); /*finds pipes and seperates them*/

void spacesplitting(char *s, char **args); /*separating command words*/

int inputsplit(char *s, char **args, char **argspiped); /*seperate the input, if it is one of my commands execute it, else return if commmands are piped or not*/