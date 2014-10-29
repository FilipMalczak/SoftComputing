package com.github.whatever.softcomputing.operators

import com.github.whatever.softcomputing.genalg.Specimen


interface SelectionOperator<S extends Specimen> {
    /**
     * Returned population should be sorted from best to worst kept specimen.
     */
    List<S> selectNewPopulation(int populationSize, List<S> population, int generation, Map context)
}
