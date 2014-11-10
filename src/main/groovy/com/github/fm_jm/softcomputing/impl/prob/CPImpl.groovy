package com.github.fm_jm.softcomputing.impl.prob

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.ga.prob.CP
import com.github.fm_jm.softcomputing.impl.FunctionTree

import groovy.transform.Canonical

@Canonical
class CPImpl implements CP<FunctionTree>{
    int initialProb
    int step

    @Override
    int getCrossoverProbability(List<FunctionTree> population, int generation, Context context) {
        generation<2 ?
            initialProb :
            context.dVariance(0) > context.dVariance(1) ?
                context.crossProb - step :
                context.crossProb + step
    }
}
