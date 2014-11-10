package com.github.fm_jm.softcomputing.impl.prob

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.ga.prob.MP
import com.github.fm_jm.softcomputing.impl.FunctionTree

import groovy.transform.Canonical

@Canonical
class MPImpl implements MP<FunctionTree> {
    int initialProb
    int step

    @Override
    int getMutationProbability(List<FunctionTree> population, int generation, Context context) {
        generation<2 ?
            initialProb :
            context.dAvg(0) > context.dAvg(1) ?
                context.mutProb - step :
                context.mutProb + step
    }
}
