package com.github.fm_jm.softcomputing.ga.prob

import com.github.fm_jm.softcomputing.heuristics.Specimen
import com.github.fm_jm.softcomputing.heuristics.Context


/**
 * Mutation probability.
 * @param < S > Specimen type (which will be mutated)
 */
interface MP<S extends Specimen> {
    int getMutationProbability(List<S> population, int generation, Context context)
}
