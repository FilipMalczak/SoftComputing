package com.github.fm_jm.softcomputing.impl.sa

import com.github.fm_jm.softcomputing.impl.FunctionTree
import com.github.fm_jm.softcomputing.sa.operators.NeighbourSelector


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
        // CP/1k * (davg(i-1)/davg(i))
        return 0.5  //todo: calculate from CP. bigger CP = bigger delta
    }

}
