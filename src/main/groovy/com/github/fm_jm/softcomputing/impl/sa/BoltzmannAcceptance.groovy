package com.github.fm_jm.softcomputing.impl.sa

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.heuristics.Specimen
import com.github.fm_jm.softcomputing.sa.operators.AcceptanceFunction


class BoltzmannAcceptance<S extends Specimen> implements AcceptanceFunction {

    @Override
    double jumpProbability(Specimen from, Specimen to, double temperature, Context context) {
        double energyDiff = from.evaluate(context) - to.evaluate(context)
        if (energyDiff >= 0)
            return 1.0
        return Math.exp(energyDiff/temperature)
    }
}
