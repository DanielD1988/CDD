**
 *
 * @author Daniel Dinelli
 * @Date   2022-13-01
 * @Licence GNU GPL
 *
 * This program times how long mandel function takes to run
 */

#include <iostream>
#include <stdlib.h>     /* srand, rand */
#include <complex>      /* complex number data type */
#include <time.h>       /* time */
#include <limits>
#include <omp.h>

using namespace std ;

const int ROW=1000;
const int COL=1000;
const int DEPTH=1000;

/**
  @class mandelbroit
  @fn calc
  @brief This function takes in i and k from the mandel function and gets the absolute value c in the parameters
  This method returns a count of how many times z is greater than 2.0
*/
int calc(complex<int> c, int depth){
    int count=0;
    complex<int> z=0;
    for(int i=0;i<depth;++i){
	     if (abs(z)>2.0){
            break;
	   }
	   z=z*z+c;
	   count++;
    }
    return count;
}

/**
  @class mandelbroit
  @fn mandel
  @brief This function takes in the array from the main function it has two loops that run in parallel
  and call the calc function to fill the passed in array
  
*/
void mandel( int p[ROW][COL], int depth){
  #pragma omp parallel for collapse(2)	  
  for(int i=0;i<ROW;++i){
        for(int k=0;k<COL;++k){
	  p[i][k]=calc(complex<int>(i,k),depth);
	}
    }
}
/**
  @class mandelbroit
  @fn main
  @brief This function is the point of entry 
*/
int main(void){

  
  int myArray[ROW][COL];
  /* initialize random seed: */
  srand (time(NULL));
  
  clock_t begin = clock();
  mandel(myArray,DEPTH);
  clock_t end = clock();
  double timeSec = (end - begin) / static_cast<double>( CLOCKS_PER_SEC );
  std::cout << timeSec << std::endl;
}


// 
// mandelbroit.cpp ends here
