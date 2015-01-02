package com.github.fm_jm.softcomputing.ga.alg

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.heuristics.Specimen


public interface GeneratePopulation<S extends Specimen> {
    List<S> generate(int size, Context context)
}