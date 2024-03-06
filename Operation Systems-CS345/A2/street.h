#include <stdio.h>
#include <stdlib.h>
#include <time.h>
//#include <pthread.h>
//#include <semaphore.h>
#define RED 0
#define BLUE 1

struct ped
{
    int id;
    char way;
    int col;
} typedef ped_T;

ped_T *street, *side;

//sem_t sem;

void initpeds(ped_T *ped, int i);

void initstreet(ped_T *peds, ped_T *al, int num);

void color(int c);

void red();

void blue();

void reset();

void *move(void *arg);

void update_street(ped_T *al, int num, int color, char dir, int count);
