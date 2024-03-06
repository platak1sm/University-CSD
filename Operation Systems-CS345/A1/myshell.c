/*  Emmanouil Platakis 4730
    csd4730@csd.uoc.gr  */

#include "myshell.h"

int mycommands(char **args)
{
    int i, cmd = 0;
    char *mycmds[3];

    mycmds[0] = "quit\0";
    mycmds[1] = "chdir\0";
    mycmds[2] = "pmlg\0";
    for (i = 0; i <= 2; i++)
    {
        if (strcmp(args[0], mycmds[i]) == 0)
        {
            cmd = i + 1;
            break;
        }
    }
    if (cmd == 1)
    {
        printf("\nExited Successfully.\n");
        exit(0);
    }
    else if (cmd == 2)
    {
        chdir(args[1]);
        return 1;
    }
    else if (cmd == 3)
    {
        printf("\nPaaaaame liigo me ton kalyterotero elladoos\n");
        exit(0);
    }
    return 0;
}

void exec(char **args)
{
    int status, pid, f1, f2, f3;

    pid = fork();
    if (pid == -1)
    {
        printf("\nUnsuccessful forking.");
        return;
    }
    else if (pid == 0)
    {
        f1 = -1;
        f2 = -1;
        f3 = -1;
        for (int i = 0; args[i] != NULL; i++) // ">"
        {
            if (strcmp(args[i], ">") == 0)
            {
                if (args[i + 1] != NULL)
                {
                    f1 = open(args[i + 1], O_WRONLY | O_CREAT | O_TRUNC, 0666);
                    if (f1 == -1)
                    {
                        perror("Failed to open file");
                    }
                    dup2(f1, 1);
                    close(f1);
                    args[i] = NULL;
                }
                else
                {
                    printf("Syntax error: Missing output file.\n");
                }
            }
        }
        for (int i = 0; args[i] != NULL; i++) // "<"
        {
            if (strcmp(args[i], "<") == 0)
            {
                if (args[i + 1] != NULL)
                {
                    f2 = open(args[i + 1], O_RDONLY);
                    if (f2 == -1)
                    {
                        perror("Failed to open file");
                        exit(1);
                    }
                    dup2(f2, 0);
                    close(f2);
                    args[i] = NULL;
                }
                else
                {
                    printf("Syntax error: Missing input file.\n");
                    exit(1);
                }
            }
        }

        for (int i = 0; args[i] != NULL; i++) // ">>"
        {
            if (strcmp(args[i], ">>") == 0)
            {
                if (args[i + 1] != NULL)
                {
                    f3 = open(args[i + 1], O_WRONLY | O_CREAT | O_APPEND, 0666);
                    if (f3 == -1)
                    {
                        perror("Failed to open file");
                        exit(1);
                    }
                    dup2(f3, 1);
                    close(f3);
                    args[i] = NULL;
                }
                else
                {
                    printf("Syntax error: Missing output file for append.\n");
                    exit(1);
                }
            }
        }
        if (execvp(args[0], args) < 0)
        {
            printf("\nCannot execute the command\n");
            exit(1);
        }
        exit(0);
    }
    else
    {
        waitpid(-1, &status, 0);
        return;
    }
}

void execpiped(char **args, char **argspiped)
{
    int p[2], status;
    pid_t proscess1, proscess2;

    if (pipe(p) < 0)
    {
        printf("\nPipe cannot be initialized");
        return;
    }

    proscess1 = fork();
    if (proscess1 < 0)
    {
        printf("\nUnsuccessful forking.");
        return;
    }
    else if (proscess1 == 0)
    {
        close(p[0]);
        dup2(p[1], 1);
        close(p[1]);

        if (execvp(args[0], args) < 0)
        {
            printf("\nCannot execute 1st command.\n");
            exit(0);
        }
    }
    else
    {
        close(p[1]);
        waitpid(proscess1, &status, 0); // Wait for the first child to complete
        proscess2 = fork();

        if (proscess2 < 0)
        {
            printf("\nCould not fork\n");
            return;
        }
        else if (proscess2 == 0)
        {
            dup2(p[0], 0);
            close(p[0]);

            if (execvp(argspiped[0], argspiped) < 0)
            {
                printf("\nCannot execute 2nd command.\n");
                exit(0);
            }
        }
        else
        {
            close(p[0]);
            waitpid(proscess2, &status, 0);
        }
    }
}

char *read_line()
{
    char *line = NULL;
    size_t buflen = 0;
    getline(&line, &buflen, stdin);
    int l = strlen(line);
    line[l - 1] = NULL;
    return line;
}

/*finds pipes and seperates them*/
int pipefinder(char *s, char **spiped)
{
    int i = 0;
    char *token = NULL;

    while (1)
    {
        token = strsep(&s, "|");
        if (token == NULL)
            break;

        if (strlen(token) > 0)
        { // Skip empty tokens
            spiped[i++] = token;
        }
    }

    if (i > 1)
    {
        return i; // Number of pipes
    }
    else
    {
        return 0; // No pipes
    }
}

/*separating command words*/
void spacesplitting(char *s, char **args)
{
    int i = 0;

    while (1) /*100 is a random max number for the cmds*/
    {
        args[i] = strsep(&s, " ");

        if (args[i] == NULL)
            break;
        if (strlen(args[i]) == 0) /* if args length =0 skip it*/
            i--;
        i++;
    }
}

int inputsplit(char *s, char **args, char **argspiped)
{
    char *spiped[2];
    int piped = pipefinder(s, spiped);

    if (piped)
    {
        spacesplitting(spiped[0], args);
        spacesplitting(spiped[1], argspiped);
        if (mycommands(args) || mycommands(argspiped))
        {
            return 0;
        }
        else
        {
            return 2;
        }
    }
    else
    {
        // No pipes found
        spacesplitting(s, args);

        // Check for my commands
        if (mycommands(args))
        {
            return 0; // found, do not execute external command
        }
        else
        {
            return 1;
        }
    }
}

int main()
{
    char input[1000], *str;
    char *args[100], *argspiped[100];
    char *prompt;
    int extype;
    char *shname = "csd4730-hy345sh";
    clear();
    do
    {
        /* get the current id and working directory*/
        char *cwd = getcwd(NULL, 0);
        char *id = getlogin();

        /* csd4730-hy345sh@id:/<dir> +3 as 2 for the "$ " and 1 for the terminating null character '\0'*/
        prompt = (char *)malloc((strlen(shname) + 1 + strlen(id) + 2 + strlen(cwd) + 3) * sizeof(char));
        strcpy(prompt, shname);
        strcat(prompt, "@");
        strcat(prompt, id);
        strcat(prompt, ":");
        strcat(prompt, cwd);
        strcat(prompt, "$ ");

        printf("%s", prompt);
        str = malloc(1000 * sizeof(char));
        str = read_line(); /*reading commands*/
        strcpy(input, str);
        char *comms[100];
        int comnum = 0;
        char *token = strtok(str, ";");
        while (token != NULL)
        {
            comms[comnum++] = token;
            token = strtok(NULL, ";");
        }

        for (int i = 0; i < comnum; i++)
        {
            extype = inputsplit(comms[i], args, argspiped);
            if (extype == 1) /* if there is no pipe */
                exec(args);

            if (extype > 1) /* if there is a pipe */
                execpiped(args, argspiped);
        }
        free(prompt);
    } while (1);

    return 0;
}