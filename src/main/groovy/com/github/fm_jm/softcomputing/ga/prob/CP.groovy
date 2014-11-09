package com.github.fm_jm.softcomputing.ga.prob

import com.github.fm_jm.softcomputing.ga.Specimen

/**
 * Crossover probability.
 * @param < S > Specimen type (which will cross over with each other)
 */
interface CP<S extends Specimen> {
    int getCrossoverProbability(List<S> population, int generation, Map context)
}
