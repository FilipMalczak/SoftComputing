package com.github.whatever.softcomputing.prob

import com.github.whatever.softcomputing.genalg.Specimen

/**
 * Crossover probability.
 * @param < S > Specimen type (which will cross over with each other)
 */
interface CP<S extends Specimen> {
    int getCrossoverProbability(List<S> population, int generation, Map context)
}
