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

	unsigned int ism = 0;
	unsigned int *shmptr = &ism;
	int pids[10];

	printf("%d\n", *shmptr);


	for (int i = 0; i < 10; i++) {
		pids[i] = fork();
		if(pids[i] < 0) {
			printf("Fehler bei Fork");
			exit(0);
		} else if (pids[i] == 0) {
			for (int count = 1; count <= 10; count++) { //Dies führt zu einem Problemm siehe unter dem Code...
				int number = *shmptr;
				number++;
				*shmptr = number;
				printf("%dIn Schleife 2 %d\n", getpid(), ism);
			}
			exit(0);
		}
		printf("In Schleife 1 %d\n", ism);
	}
	for (int i = 0; i<10; i++) {
		wait(NULL);
	}
	printf("Das Ergebnis lautet: %d\n", ism);
	printf("Ende des Programms\n");

	return 0;
}

//Der Vater übergibt den Kindern zwar die Variable, welche sie hochzählen sollen, aber jedes Kind bekommt nur eine KOPIE davon.
//Das heißt, dass die Varibalen nur im Kindprozess angepasst werden und nicht auf den Vaterprozess übertragen werden. Deshalb
//wird der Vater Prozess immer "0" als Ergebnis zurückgeben, da ism mit "0" initilaisiert wurde.
