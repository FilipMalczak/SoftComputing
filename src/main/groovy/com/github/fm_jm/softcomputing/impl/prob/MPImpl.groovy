package com.github.fm_jm.softcomputing.impl.prob

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.ga.prob.MP
import com.github.fm_jm.softcomputing.impl.FunctionTree

/* TODO: implement me! */
class MPImpl implements MP<FunctionTree> {
    @Override
    int getMutationProbability(List<FunctionTree> population, int generation, Context context) {
        // gen==0/1 ? low
        //          : d avg(i) > d avg (i-1) ?
        //                      pm -- : pm ++
        // masz pm w context
        return 0
    }
}
