package com.github.whatever.softcomputing.operators.mutation.annealing

import com.github.whatever.softcomputing.genalg.Specimen


public interface AcceptanceFunction<S extends Specimen> {

    double jumpProbability(S from, S to, double temperature, Map context)

}