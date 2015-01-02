package com.github.fm_jm.softcomputing.impl.sa

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.heuristics.Specimen
import com.github.fm_jm.softcomputing.impl.FunctionTree
import com.github.fm_jm.softcomputing.impl.RandomUtils
import com.github.fm_jm.softcomputing.sa.operators.NeighbourSelector


class BasicNeighbourSelector implements NeighbourSelector<FunctionTree> {

    /**
     * Neighbour of a funtionTree is a functionTree with one of its constants having a different value.
     * The new value of constant C is chosen randomly from a range calculated according to the following formula:
     * Cmin = C - delta*C, Cmax = C + delta*C
     * @param current The FunctionTree which neighbour we want to be found
     * @param temperature Current temperature
     * @param context Context of current GA generation, used in calculating delta
     * @return Neighbour of the given FunctionTree
     */
    @Override
    FunctionTree findNeighbour(FunctionTree current, double temperature, Context context) {
        FunctionTree neighbour = current.copy()
        List<Double> constants = current.constantsVector
        int modifiedValueIndex = RandomUtils.random(constants.size())//Math.round(Math.random()*constants.size())
        double delta = calculateDelta(context)*constants[modifiedValueIndex]
        constants[modifiedValueIndex] = Math.random()*(2*delta) + constants[modifiedValueIndex] - delta
        neighbour.setConstantsVector(constants)
        return neighbour
    }

    /**
     *
     * @param context
     * @return
     */
    private double calculateDelta(Context context){
        return context.crossProb/500 * (context.dAvg(1)/context.dAvg(0))
    }


}
