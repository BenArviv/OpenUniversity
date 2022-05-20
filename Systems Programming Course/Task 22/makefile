mainmat: main.o mymat.o mymat.h
	gcc -ansi -pedantic -Wall main.o mymat.o -o mainmat -g
mymat.o: mymat.h
	gcc -c -ansi -pedantic -Wall mymat.c -o mymat.o -g
main.o: mymat.h
	gcc -c -ansi -pedantic -Wall mainmat.c -o main.o -g
