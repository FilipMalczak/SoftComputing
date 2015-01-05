package com.github.fm_jm.softcomputing.impl.sa

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.sa.operators.CoolingScheme

import groovy.util.logging.Slf4j

@Slf4j
class FastBoltzmannScheme implements CoolingScheme {

    static double INITIAL_TEMP = 100        //10k
    static double MINIMAL_TEMP = 1           //10
                                            // needs to be tuned for expected range of energyDiffs


    @Override
    double decreaseTemperature(double temp, Context context) {
        def factor = calculateCoolingFactor(context)
        log.trace("Factor: $factor")
        return temp * (1 - factor)
    }

    @Override
    double initialTemperature(Context context) {
        return INITIAL_TEMP
    }

    @Override
    boolean shouldStop(double temperature) {
        return temperature <= MINIMAL_TEMP
    }

    double calculateCoolingFactor(Context context){
        1/context.mutProb //* Math.abs(context.dVariance(1)/ context.dVariance(0))
    }
}
