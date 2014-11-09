package com.github.fm_jm.softcomputing.ga.operators

import com.github.fm_jm.softcomputing.ga.Specimen


interface CrossoverOperator<S extends Specimen> {
    List<S> crossOver(S s1, S s2, Map context)
}
