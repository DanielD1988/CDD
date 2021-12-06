/**
 *
 * @author Daniel Dinelli
 * @Date   2021-12-06
 * @Licence GNU GPL
 *
 * Create two threads and run them.
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#pragma omp parallel for
#define for_x for (int x = 0; x < w; x++)
#define for_y for (int y = 0; y < h; y++)
#define for_xy for_x for_y

void show(void *u, int w, int h)
{
	int (*univ)[w] = u;
	printf("\033[H");
	for_y {
		for_x printf(univ[y][x] ? "\033[07m  \033[m" : "  ");
		printf("\033[E");
	}
	fflush(stdout);//clear (or flush) the output buffer 
}
 
void evolve(void *u, int w, int h)
{
	unsigned (*univ)[w] = &u;
	unsigned new[h][w];
 
	for_y for_x {
		int n = 0;
		for (int y1 = y - 1; y1 <= y + 1; y1++)
			for (int x1 = x - 1; x1 <= x + 1; x1++)
				if (univ[(y1 + h) % h][(x1 + w) % w])
					n++;
 
		if (univ[y][x]) n--;
		new[y][x] = (n == 3 || (n == 2 && univ[y][x]));
	}
	for_y for_x univ[y][x] = new[y][x];
}
 
void game(int w, int h)
{
	unsigned univ[h][w]; //this is an unsigned int multi array
	for_xy univ[y][x] = rand() < RAND_MAX / 10 ? 1 : 0; //put random 1 or 0 into mult array
	while (1) {
		show(univ, w, h);
		evolve(univ, w, h);
		usleep(200000);
	}
}
 
int main(int c, char **v)
{
	int w = 0, h = 0;
	if (c > 1) w = atoi(v[1]);
	if (c > 2) h = atoi(v[2]);
	if (w <= 0) w = 30;
	if (h <= 0) h = 30;
	game(w, h);
}