package com.github.whatever.softcomputing.impl.sa

import com.github.whatever.softcomputing.genalg.Specimen
import com.github.whatever.softcomputing.operators.mutation.annealing.AcceptanceFunction


class BoltzmannAcceptance implements AcceptanceFunction<Specimen> {

    @Override
    double jumpProbability(Specimen from, Specimen to, double temperature, Map context) {
        double energyDiff = from.evaluate(context) - to.evaluate(context)
        if (energyDiff <= 0)
            return 1.0
        return Math.exp(energyDiff/temperature)
    }
}
