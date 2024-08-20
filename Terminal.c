#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define MAX_LINE 1024  // Maximum command length
#define MAX_ARGS 100   // Maximum number of arguments

void parse_command(char *line, char **args) {
    int i = 0;
    args[i] = strtok(line, " \t\n");  // Split the line into tokens (arguments)
    while (args[i] != NULL) {
        i++;
        args[i] = strtok(NULL, " \t\n");  // Continue splitting
    }
}

void execute_command(char **args) {
    pid_t pid = fork();  // Fork a child process
    if (pid < 0) {
        perror("Fork failed");
        exit(EXIT_FAILURE);
    } else if (pid == 0) {  // Child process
        if (execvp(args[0], args) < 0) {  // Execute the command
            perror("Execution failed");
            exit(EXIT_FAILURE);
        }
    } else {  // Parent process
        int status;
        waitpid(pid, &status, 0);  // Wait for the child process to finish
    }
}

int main() {
    char line[MAX_LINE];  // Buffer to hold the command line
    char *args[MAX_ARGS];  // Array to hold the command arguments

    while (1) {
        printf("mysh> ");  // Display the prompt
        if (fgets(line, sizeof(line), stdin) == NULL) {
            perror("fgets failed");
            continue;
        }

        parse_command(line, args);  // Parse the input line into arguments

        if (args[0] == NULL) {  // Empty command
            continue;
        }

        if (strcmp(args[0], "exit") == 0) {  // Exit command
            break;
        }

        execute_command(args);  // Execute the command
    }

    return 0;
}
