package com.github.fm_jm.softcomputing.ga.alg

import com.github.fm_jm.softcomputing.ga.Specimen


interface ContextHandler<S extends Specimen> {
    void update(List<S> population, int generation, Map context)
}
