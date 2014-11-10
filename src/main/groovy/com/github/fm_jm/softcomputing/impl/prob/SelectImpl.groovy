package com.github.fm_jm.softcomputing.impl.prob

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.ga.operators.SelectionOperator
import com.github.fm_jm.softcomputing.impl.FunctionTree

/* TODO: implement me! */
class SelectImpl implements SelectionOperator<FunctionTree>{
    @Override
    List<FunctionTree> selectNewPopulation(int populationSize, List<FunctionTree> population, int generation, Context context) {
        // 25% best
        // 1% worst just in fukken case
        // rest - tourney of size 2
        return null
    }
}
