package com.github.fm_jm.softcomputing.ga.alg

import com.github.fm_jm.softcomputing.ga.Specimen


public interface GeneratePopulation<S extends Specimen> {
    List<S> generate(int size)
}