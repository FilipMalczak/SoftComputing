package com.github.fm_jm.softcomputing.impl

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.ga.alg.StopCondition

import groovy.transform.Canonical

@Canonical
class StopCondImpl implements StopCondition<FunctionTree>{
    int maxGenerations

    @Override
    boolean shouldStop(List<FunctionTree> population, int generation, Context context) {
        generation>maxGenerations
    }
}
