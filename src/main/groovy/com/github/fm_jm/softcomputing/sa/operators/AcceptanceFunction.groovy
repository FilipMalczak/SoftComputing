package com.github.fm_jm.softcomputing.sa.operators

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.heuristics.Specimen


public interface AcceptanceFunction<S extends Specimen> {

    double jumpProbability(S from, S to, double temperature, Context context)

}