package com.github.fm_jm.softcomputing.ga.operators

import com.github.fm_jm.softcomputing.heuristics.Specimen
import com.github.fm_jm.softcomputing.heuristics.Context


interface CrossoverOperator<S extends Specimen> {
    List<S> crossOver(S s1, S s2, Context context)
}
