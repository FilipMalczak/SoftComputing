package com.github.fm_jm.softcomputing.ga.alg

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.heuristics.Specimen


interface ContextHandler<S extends Specimen> {
    void update(List<S> population, int generation, Context context)
    void start(Context context)
    void finish(Context context)
}
