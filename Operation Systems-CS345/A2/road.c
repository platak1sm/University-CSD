#include "street.h"

void color(int c)
{
    if (c)
        blue();
    else
        red();
}

void red()
{
    printf("\033[1;31m");
}

void blue()
{
    printf("\033[0;34m");
}

void reset()
{
    printf("\033[0m");
}

void initpeds(ped_T *ped, int i)
{
    int j;
    int lower = 0, upper = 1;

    srand(time(0) + i + 5);
    int num = (rand() % (upper - lower + 1)) + lower;
    ped->id = i;
    if (num)
        ped->way = '<';
    else
        ped->way = '>';
    num = (rand() % (upper - lower + 1)) + lower;
    ped->col = num;
}

void initstreet(ped_T *peds, ped_T *al, int num)
{
    int j = 0, k = 0, i, m = 0;
    ped_T *west, ped;

    for (i = 0; i < num; i++)
    {
        if ((peds + i)->way == '<')
            j++;
    }
    west = (ped_T *)malloc(j * sizeof(ped_T));
    printf("||");
    for (i = 0; i < num; i++)
    {
        if ((peds + i)->way == '>')
        {
            color((peds + i)->col);
            printf("%02d %c", (peds + i)->id, (peds + i)->way);
            reset();
            printf("||");
            al[m] = peds[i];
            m++;
        }
        else
        {
            ped.id = (peds + i)->id;
            ped.col = (peds + i)->col;
            ped.way = (peds + i)->way;
            west[k] = ped;
            k++;
        }
    }

    int temp = num - j;
    for (i = j - 1; i >= 0; i--)
    {
        al[temp] = west[i];
        temp++;
        color(west[i].col);
        printf("%c %02d", (west + i)->way, (west + i)->id);
        reset();
        printf("||");
    }
    printf("\n ");

    for (i = 0; i < num; i++)
    {
        printf("------");
    }

    printf("\n||");

    for (i = 0; i < num; i++)
    {
        printf("    ||");
    }
    printf("\n\n");
    free(west);
}

void update_street(ped_T *al, int num, int color, char dir, int count)
{
    int j = 0, k = 0, i, c = 0;
    ped_T ped;
    if (count == 1)
    {
        for (i = 0; i < num; i++)
        {
            if ((al[i].col == color) && (al[i].way == dir))
            {

                street[i] = al[i];
                side[i].id = -1;
            }
            else
            {
                side[i] = al[i];
                street[i].id = -1;
            }
        }
        c = 1;
    }
    else if (count == 2)
    {
        for (i = 0; i < num; i++)
        {
            if (side[i].id > 0)
                c = 1;
            if ((side[i].col == color))
            {
                street[i] = side[i];
                side[i].id = -1;
            }
        }
    }
    else if (count == 3)
    {
        for (i = 0; i < num; i++)
        {
            if (side[i].id > 0)
                c = 1;
            if ((side[i].way == dir))
            {
                street[i] = side[i];
                side[i].id = -1;
            }
        }
    }
    else if (count == 4)
    {
        for (i = 0; i < num; i++)
        {
            if (side[i].id > 0)
                c = 1;
            street[i] = side[i];
            side[i].id = -1;
        }
    }

    if (!c)
        return;

    printf("||");
    for (i = 0; i < num; i++)
    {
        if (street[i].id != -1)
        {
            if (street[i].col)
                blue();
            else
                red();

            if (street[i].way == '>')
                printf("%02d %c", (street + i)->id, (street + i)->way);
            else
                printf("%c %02d", (street + i)->way, (street + i)->id);
            reset();
            printf("||");
        }
        else
        {
            printf("    ||");
        }
    }

    printf("\n");

    for (i = 0; i < num; i++)
    {
        printf("------");
    }
    printf("\n||");

    for (i = 0; i < num; i++)
    {
        if (side[i].id != -1)
        {
            if (side[i].col)
                blue();
            else
                red();

            if (side[i].way == '<')
                printf("%c %02d", (side + i)->way, (side + i)->id);
            else
                printf("%02d %c", (side + i)->id, (side + i)->way);

            reset();
            printf("||");
        }
        else
        {
            printf("    ||");
        }
    }
    printf("\n\n");
}

void *move(void *arg)
{
}

int main(int argc, char *argv[])
{
    int j = 5, i, rc;
    char *str = argv[1];
    j = atoi(str);
    ped_T peds[j], ped, al[j];
    int domcol, redc = 0, bluec = 0, right = 0, left = 0;
    char domdir;
    street = (ped_T *)malloc(j * sizeof(ped_T));
    side = (ped_T *)malloc(j * sizeof(ped_T));
    // pthread_t threads[j], t, street[j], side[j];

    for (i = 0; i < j; i++)
    {
        initpeds(&ped, i + 1);
        if (ped.col == 0)
            redc++;
        else
            bluec++;
        if (ped.way == '>')
            right++;
        else
            left++;
        peds[i] = ped;
        // threads[i] = t;
        // rc = pthread_create(&t, NULL, move, NULL);
    }

    initstreet(peds, &al, j);
    int lower = 0, upper = 1;
    srand(time(0) + 7);
    int num = (rand() % (upper - lower + 1)) + lower;
    if (redc > bluec)
        domcol = 0;
    else if (redc < bluec)
        domcol = 1;
    else
        domcol = num;

    num = (rand() % (upper - lower + 1)) + lower;

    if (right > left)
        domdir = '>';
    else if (right < left)
        domdir = '<';
    else
    {
        num = (rand() % (upper - lower + 1)) + lower;

        if (num)
            domdir = '>';
        else
            domdir = '<';
    }
    char *str2 = "blue";
    if (!domcol)
        str2 = "red";
    printf("domcolor=%s\ndomdir=%c\n", str2, domdir);

    update_street(al, j, domcol, domdir, 1); /*1os diaxwrismos me vash domcolor kai domdir*/
    update_street(al, j, domcol, domdir, 2); /*2os diaxwrismos me vash domcolor kai domdir*/
    update_street(al, j, domcol, domdir, 3); /*3os diaxwrismos me vash domcolor kai domdir*/
    update_street(al, j, domcol, domdir, 4); /*4os diaxwrismos me vash domcolor kai domdir*/

    // sem_init(&sem, 0, 0); /*Initialize semaphore with intraprocess scope*/

    // for (i = 0; i < j; i++)
    // {
    //     pthread_join(threads[j], NULL); /*Wait for the thread to finish*/
    // }

    free(side);
    free(street);
    return 0;
}
