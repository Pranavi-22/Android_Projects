// MovieCentralInterface.aidl
package com.example.common;

// Declare any non-default types here with import statements

interface MovieCentralInterface {
   Bundle getMovieInformation();
                               Bundle getMoviewithId(int id);
                               String getMovieURL(int id);
}