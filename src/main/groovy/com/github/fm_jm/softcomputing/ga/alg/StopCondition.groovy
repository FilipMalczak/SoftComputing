package com.github.fm_jm.softcomputing.ga.alg

import com.github.fm_jm.softcomputing.ga.Specimen


interface StopCondition<S extends Specimen> {
    boolean shouldStop(List<S> population, int generation, Map context)
}