package com.github.whatever.softcomputing.impl.sa

import com.github.whatever.softcomputing.genalg.Specimen
import com.github.whatever.softcomputing.impl.FunctionTree
import com.github.whatever.softcomputing.operators.mutation.annealing.NeighbourSelector


class BasicNeighbourSelector implements NeighbourSelector<FunctionTree> {


    @Override
    FunctionTree findNeighbour(FunctionTree current, double temperature, Map context) {
        FunctionTree neighbour = current.copy()
        List<Double> constants = current.constantsVector
        int modifiedValueIndex = Math.round(Math.random()*constants.size())
        double delta = calculateDelta(context)*constants[modifiedValueIndex]
        constants[modifiedValueIndex] = Math.random()*(2*delta) + constants[modifiedValueIndex] - delta
        neighbour.setConstantsVector(constants)
        return neighbour
    }

    private double calculateDelta(Map context){
        return 0.5  //todo: calculate from CP. bigger CP = bigger delta
    }

}
