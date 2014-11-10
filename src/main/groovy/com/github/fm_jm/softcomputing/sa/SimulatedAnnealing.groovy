package com.github.fm_jm.softcomputing.sa

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.heuristics.Specimen
import com.github.fm_jm.softcomputing.impl.FunctionTree
import com.github.fm_jm.softcomputing.ga.operators.MutationOperator
import com.github.fm_jm.softcomputing.sa.operators.AcceptanceFunction
import com.github.fm_jm.softcomputing.sa.operators.CoolingScheme
import com.github.fm_jm.softcomputing.sa.operators.NeighbourSelector
import groovy.transform.Canonical

@Canonical
class SimulatedAnnealing<S extends Specimen> {

    CoolingScheme coolingScheme;
    NeighbourSelector<S> neighbourSelector;
    AcceptanceFunction<S> acceptanceFunction;

    List<S> doRun(S specimen, Context context) {
        double temperature = coolingScheme.initialTemperature(context)
        S current = specimen
        S best = current.copy()
        while (!coolingScheme.shouldStop(temperature)){
            S neighbour = neighbourSelector.findNeighbour(current, temperature, context)
            if (Math.random() < acceptanceFunction.jumpProbability(current, neighbour, temperature, context)) {
                current = neighbour
                if (current.evaluate(context) < best.evaluate(context)) {
                    best = current.copy()
                }
            }
            temperature = coolingScheme.decreaseTemperature(temperature, context)
        }

        return [current, best]
    }


}
