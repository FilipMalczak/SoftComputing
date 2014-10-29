package com.github.whatever.softcomputing.operators

import com.github.whatever.softcomputing.genalg.Specimen


interface CrossoverOperator<S extends Specimen> {
    List<S> crossOver(S s1, S s2)
}
