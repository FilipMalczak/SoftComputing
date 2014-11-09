package com.github.whatever.softcomputing.impl.sa

import com.github.whatever.softcomputing.operators.mutation.annealing.CoolingScheme


class FastBoltzmannScheme implements CoolingScheme {


    @Override
    double decreaseTemperature(double temp, Map context) {
        return temp * (1 - calculateCoolingFactor(context))
    }

    @Override
    double initialTemperature(Map context) {
        return 10000 //todo: calculate it from CP - the bigger the CP, the bigger the temp
    }

    @Override
    boolean shouldStop(double temperature) {
        return temperature <= 1
    }

    double calculateCoolingFactor(Map context){
        return 0.003 //todo: calculate it from MP. the lower the MP the faster the cooling
    }
}
