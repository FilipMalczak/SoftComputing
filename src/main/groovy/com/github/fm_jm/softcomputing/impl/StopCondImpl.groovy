package com.github.fm_jm.softcomputing.impl

import com.github.fm_jm.softcomputing.ga.alg.StopCondition


class StopCondImpl implements StopCondition<FunctionTree>{
    @Override
    boolean shouldStop(List<FunctionTree> population, int generation, Map context) {
        // maxPop
        return false
    }
}
