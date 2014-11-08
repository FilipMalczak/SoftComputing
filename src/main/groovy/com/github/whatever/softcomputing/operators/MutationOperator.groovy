package com.github.whatever.softcomputing.operators

import com.github.whatever.softcomputing.genalg.Specimen


interface MutationOperator<S extends Specimen> {
    List<S> mutate(S s, Map context)
}