package com.github.whatever.softcomputing.impl.sa

import com.github.whatever.softcomputing.impl.FunctionTree
import com.github.whatever.softcomputing.operators.MutationOperator
import com.github.whatever.softcomputing.operators.mutation.annealing.AcceptanceFunction
import com.github.whatever.softcomputing.operators.mutation.annealing.CoolingScheme
import com.github.whatever.softcomputing.operators.mutation.annealing.NeighbourSelector
import groovy.transform.Canonical

@Canonical
class SimulatedAnnealing implements MutationOperator<FunctionTree> {

    CoolingScheme coolingScheme;
    NeighbourSelector<FunctionTree> neighbourSelector;
    AcceptanceFunction<FunctionTree> acceptanceFunction;

    @Override
    List<FunctionTree> mutate(FunctionTree functionTree, Map context) {
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
