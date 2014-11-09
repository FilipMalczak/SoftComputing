package com.github.fm_jm.softcomputing.impl

import com.github.fm_jm.softcomputing.ga.alg.ContextHandler


class ContextHandlerImpl implements ContextHandler<FunctionTree>{
    @Override
    void update(List<FunctionTree> population, int generation, Map context) {
        // save copy of best
        // save avg & variance of gen(i), gen(i-1), gen(i-2)
        // variance - srednia odchylenia od sredniej

    }
}
