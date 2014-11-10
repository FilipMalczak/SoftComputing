package com.github.fm_jm.softcomputing.sa.operators

import com.github.fm_jm.softcomputing.heuristics.Context


public interface CoolingScheme {

    double decreaseTemperature(double temp, Context context)

    double initialTemperature(Context context)

    boolean shouldStop(double temperature)






    //initial temp zalezny od prawdopodobienstwa krzyzowania

}