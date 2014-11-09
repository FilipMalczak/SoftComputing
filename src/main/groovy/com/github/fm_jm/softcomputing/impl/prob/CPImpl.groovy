package com.github.fm_jm.softcomputing.impl.prob

import com.github.fm_jm.softcomputing.ga.prob.CP
import com.github.fm_jm.softcomputing.impl.FunctionTree


class CPImpl implements CP<FunctionTree>{
    @Override
    int getCrossoverProbability(List<FunctionTree> population, int generation, Map context) {
        // gen==0/1 ? low
        //          : d var(i) > d var (i-1) ?
        //                      pc -- : pc ++
        // masz pc w context
        return 0
    }
}
