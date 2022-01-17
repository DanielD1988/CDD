// mandelbroit.cpp --- m
// 
// Filename: mandelbroit.cpp
// Description: 
// Author: Joseph
// Maintainer: 
// Created: Mon Feb  4 09:40:41 2019 (+0000)
// Version: 
// Package-Requires: ()
// Last-Updated: Mon Feb  4 10:08:24 2019 (+0000)
//           By: Joseph
//     Update #: 18
// URL: 
// Doc URL: 
// Keywords: 
// Compatibility: 
// 
// 

// Commentary: 
// 
// 
// 
// 

// Change Log:
// 
// 
// 
// 
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or (at
// your option) any later version.
// 
// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with GNU Emacs.  If not, see <http://www.gnu.org/licenses/>.
// 
// 

// Code:
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
