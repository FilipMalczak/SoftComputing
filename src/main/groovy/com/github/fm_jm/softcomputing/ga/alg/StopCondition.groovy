package com.github.fm_jm.softcomputing.ga.alg

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.heuristics.Specimen


interface StopCondition<S extends Specimen> {
    boolean shouldStop(List<S> population, int generation, Context context)
}