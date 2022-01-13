/**
 *
 * @author Daniel Dinelli
 * @Date   2022-13-01
 * @Licence GNU GPL
 *
 * Times how long the saxpy function will take
 */
#include <iostream>
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */
#include <limits>
/**
  @class saxpy
  @fn saxpy
  @brief This function assigns the y array the a multpled by the array contents of x plus y array
*/
void saxpy(unsigned long n, float a,float y[], float x[])
{
  //#pragma omp parallel for
	for (unsigned long i=0; i < n; ++i){
		y[i]=a * x[i] + y[i];
	}//for
}//saxpy
/**
  @class saxpy
  @fn main
  @brief This main function is the point of entry this function sets up the x and y arrays
  it times how long it takes to run after passing the values to the saxpy function
*/
int main(void){
  const unsigned long size=1000000;
  const float A=1.234;
  float y[size];
  float x[size];
  /* initialize random seed: */
  srand (time(NULL));
  for(unsigned long long int i=0;i<size;++i){
    y[i]=i*i;
    /* generate secret number between 1 and 1000000: */
    x[i] = rand() % 1000000 + 1;
  }
  clock_t begin = clock();
  saxpy(size,A,y,x);
  clock_t end = clock();
  double timeSec = (end - begin) / static_cast<double>( CLOCKS_PER_SEC );
  std::cout << timeSec << std::endl;
}//main

// 
// saxpy.cpp ends here
