package com.github.fm_jm.softcomputing.sa

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.heuristics.Specimen
import com.github.fm_jm.softcomputing.sa.operators.AcceptanceFunction
import com.github.fm_jm.softcomputing.sa.operators.CoolingScheme
import com.github.fm_jm.softcomputing.sa.operators.NeighbourSelector
import groovy.transform.Canonical
import groovy.util.logging.Slf4j

@Canonical
@Slf4j
class SimulatedAnnealing<S extends Specimen> {

    CoolingScheme coolingScheme;
    NeighbourSelector<S> neighbourSelector;
    AcceptanceFunction<S> acceptanceFunction;

    List<S> doRun(S specimen, Context context) {
        double temperature = coolingScheme.initialTemperature(context)
        log.trace("initital temp: $temperature")
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
        log.trace("Result: [$current, $best]")
        return [current, best]
    }


}
