**
 *
 * @author Daniel Dinelli
 * @Date   2022-13-01
 * @Licence GNU GPL
 *
 * Show a print statement outside the parallel block and one that is inside
 */
#include <omp.h>
#include <stdio.h>
/**
	@class hello
	@fn main
	@brief This function shows print statement that is not in the parallel block and one that is
*/
int main(int argc, char *argv[]){
  /* sequential code */
    printf("before\n");
#pragma omp parallel
{
  printf("I am a parallel region.\n");
}
/* sequential code */
 printf("after\n");
return 0;
}
