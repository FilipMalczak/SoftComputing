package com.github.fm_jm.softcomputing.sa

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.heuristics.Specimen
import com.github.fm_jm.softcomputing.impl.FunctionTree
import com.github.fm_jm.softcomputing.sa.operators.AcceptanceFunction
import com.github.fm_jm.softcomputing.sa.operators.CoolingScheme
import com.github.fm_jm.softcomputing.sa.operators.NeighbourSelector
import groovy.transform.Canonical


/**
 * TODO: inject this to mutation implementation
 * @param < S >
 */
@Canonical
class SimulatedAnnealing<S extends Specimen>{
    CoolingScheme coolingScheme;
    NeighbourSelector<S> neighbourSelector;
    AcceptanceFunction<S> acceptanceFunction;

    // I dunno, maybe this should return S instead of List<S>? ~F
    List<S> doRun(S functionTree, Context context) {
        double temperature = coolingScheme.initialTemperature(context)
        FunctionTree current = functionTree
        FunctionTree best = current
        while (!coolingScheme.shouldStop(temperature)){
            FunctionTree neighbour = neighbourSelector.findNeighbour(current, temperature, context)
            if (Math.random() < acceptanceFunction.jumpProbability(current, neighbour, temperature, context)) {
                current = neighbour
                if (current.evaluate(context) < best.evaluate(context))
                    best = current
            }
            temperature = coolingScheme.decreaseTemperature(temperature, context)
        }

        return [best]
    }


}
