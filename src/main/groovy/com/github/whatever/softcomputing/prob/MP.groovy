package com.github.whatever.softcomputing.prob

import com.github.whatever.softcomputing.genalg.Specimen


/**
 * Mutation probability.
 * @param < S > Specimen type (which will be mutated)
 */
interface MP<S extends Specimen> {
    int getMutationProbability(List<S> population, int generation, Map context)
}
