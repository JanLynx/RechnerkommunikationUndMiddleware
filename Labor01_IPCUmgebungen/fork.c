/*
 ============================================================================
 Name        : IPC.c
 Author      : Jan Schönitz 26336
 Date        : 08.10.2021
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main(void) {
	printf("Programm Start\n");

	int pid = fork();
	if (pid < 0) {
		perror("Error: Fork"); //Fehler abfangen
	} else if (pid == 0) {
		printf("Ich bin das Kind mit PID= %d und habe den Vater mit PID= %d\n",
				(int) getpid(), (int) getppid());
		kill(getpid(),SIGTERM);
		//exit(0);
	}
	printf("Ich bin der Vater mit PID= %d  und habe das Kind mit PID= %d\n",
			(int) getpid(), (int) pid);
	wait(NULL);
	printf("Ende des Programms\n");

	return 0;
}
