package com.github.fm_jm.softcomputing.impl.sa

import com.github.fm_jm.softcomputing.ga.Specimen
import com.github.fm_jm.softcomputing.sa.operators.AcceptanceFunction


class BoltzmannAcceptance implements AcceptanceFunction<Specimen> {

    @Override
    double jumpProbability(Specimen from, Specimen to, double temperature, Map context) {
        double energyDiff = from.evaluate(context) - to.evaluate(context)
        if (energyDiff <= 0)
            return 1.0
        return Math.exp(energyDiff/temperature)
    }
}
