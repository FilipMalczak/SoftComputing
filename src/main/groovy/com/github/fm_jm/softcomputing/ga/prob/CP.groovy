package com.github.fm_jm.softcomputing.ga.prob

import com.github.fm_jm.softcomputing.heuristics.Specimen
import com.github.fm_jm.softcomputing.heuristics.Context

/**
 * Crossover probability.
 * @param < S > Specimen type (which will cross over with each other)
 */
interface CP<S extends Specimen> {
    int getCrossoverProbability(List<S> population, int generation, Context context)
}
