package com.github.fm_jm.softcomputing.impl.sa

import com.github.fm_jm.softcomputing.sa.operators.CoolingScheme


class FastBoltzmannScheme implements CoolingScheme {


    // 10k - 10

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
        // 1/MP * (dvar(i-1)/dvar(i))   <- **1 not 1k!**
        return 0.03 //todo: calculate it from MP. the lower the MP the faster the cooling  = bigger cooling factor
        // 0.05 is long; 0.1 seems legit
    }
}
