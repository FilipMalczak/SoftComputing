package com.github.fm_jm.softcomputing.impl.operators

import com.github.fm_jm.softcomputing.ga.operators.MutationOperator
import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.impl.FunctionTree
import com.github.fm_jm.softcomputing.sa.SimulatedAnnealing
import groovy.transform.Canonical

@Canonical
class AnnealingMutation implements MutationOperator<FunctionTree>{

    SimulatedAnnealing sa

    @Override
    List<FunctionTree> mutate(FunctionTree functionTree, Context context) {
        sa.doRun(functionTree, context)
    }
}
