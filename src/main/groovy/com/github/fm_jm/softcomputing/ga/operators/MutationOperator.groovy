package com.github.fm_jm.softcomputing.ga.operators

import com.github.fm_jm.softcomputing.heuristics.Specimen
import com.github.fm_jm.softcomputing.heuristics.Context


interface MutationOperator<S extends Specimen> {
    List<S> mutate(S s, Context context)
}