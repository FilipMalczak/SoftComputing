package com.github.fm_jm.softcomputing.ga.operators

import com.github.fm_jm.softcomputing.ga.Specimen


interface SelectionOperator<S extends Specimen> {
    /**
     * Returned population should be sorted from best to worst kept specimen.
     */
    List<S> selectNewPopulation(int populationSize, List<S> population, int generation, Map context)
}
