package com.github.fm_jm.softcomputing.ga.operators

import com.github.fm_jm.softcomputing.ga.Specimen


interface MutationOperator<S extends Specimen> {
    List<S> mutate(S s, Map context)
}