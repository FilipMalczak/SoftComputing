package com.github.fm_jm.softcomputing.impl

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.ga.alg.StopCondition

import groovy.transform.Canonical
import groovy.util.logging.Slf4j

@Canonical
@Slf4j
class SimpleStopCondition implements StopCondition<FunctionTree>{
    int maxGenerations
    int gensPerTick=1

    @Override
    boolean shouldStop(List<FunctionTree> population, int generation, Context context) {
        if (generation % gensPerTick==0) {
            log.info("Generation $generation")
        }
        generation>maxGenerations
    }
}
