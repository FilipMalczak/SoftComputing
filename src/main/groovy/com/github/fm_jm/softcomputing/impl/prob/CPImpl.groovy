package com.github.fm_jm.softcomputing.impl.prob

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.ga.prob.CP
import com.github.fm_jm.softcomputing.impl.FunctionTree

/* TODO: implement me! */
class CPImpl implements CP<FunctionTree>{
    @Override
    int getCrossoverProbability(List<FunctionTree> population, int generation, Context context) {
        // gen==0/1 ? low
        //          : d var(i) > d var (i-1) ?
        //                      pc -- : pc ++
        // masz pc w context
        return 0
    }
}
