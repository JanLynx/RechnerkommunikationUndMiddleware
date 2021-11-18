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
#include <signal.h>

void handler(int signr) {
	if (signr == SIGUSR1) {
		printf("Signal SIGUSR1 erhalten\n");
	}else if (signr == SIGUSR2) {
		printf("\n");
	}else if (signr == SIGINT) {
		printf("Ich lasse mich nicht unterbrechen\n");
	}else if (signr == SIGALRM) {
		printf("ALARM\n");
	}else if (signr == SIGTERM) {
		printf("Ich lasse mich nicht terminieren\n");
	}else if (signr == SIGKILL) {
		printf("Ich lasse mich nicht terminieren\n");
	}else {
		printf("undefiniertes Signal\n");
	}
}

int main(void) {

	printf("PID: %d \n", getpid());

	signal(SIGUSR1, handler);
	signal(SIGUSR2, handler);
	signal(SIGINT, handler);
	signal(SIGALRM, handler);
	signal(SIGTERM, handler);
	signal(SIGKILL, handler);

	for (;;) {
		sleep(1000);
	}
	return 0;
}
