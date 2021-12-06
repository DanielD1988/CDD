output: conwayGameOfLife.o
	gcc conwayGameOfLife.o -o output

conwayGameOfLife.o: conwayGameOfLife.c
	gcc -c conwayGameOfLife.c

clean:
	rm *.o output
	gcc -c conwayGameOfLife.c -fopenmp