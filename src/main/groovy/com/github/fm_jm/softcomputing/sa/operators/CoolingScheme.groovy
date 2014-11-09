package com.github.fm_jm.softcomputing.sa.operators


public interface CoolingScheme {

    double decreaseTemperature(double temp, Map context)

    double initialTemperature(Map context)

    boolean shouldStop(double temperature)






    //initial temp zalezny od prawdopodobienstwa krzyzowania

}