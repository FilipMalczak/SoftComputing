package com.github.fm_jm.softcomputing.impl.prob

import com.github.fm_jm.softcomputing.ga.operators.SelectionOperator
import com.github.fm_jm.softcomputing.impl.FunctionTree


class SelectImpl implements SelectionOperator<FunctionTree>{
    @Override
    List<FunctionTree> selectNewPopulation(int populationSize, List<FunctionTree> population, int generation, Map context) {
        // 25% best
        // 1% worst just in fukken case
        // rest - tourney of size 2
        return null
    }
}
