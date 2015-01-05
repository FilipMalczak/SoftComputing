package com.github.fm_jm.softcomputing.ga

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.ga.operators.CrossoverOperator
import com.github.fm_jm.softcomputing.ga.operators.MutationOperator
import com.github.fm_jm.softcomputing.ga.operators.SelectionOperator
import com.github.fm_jm.softcomputing.ga.prob.CP
import com.github.fm_jm.softcomputing.ga.prob.MP
import com.github.fm_jm.softcomputing.ga.alg.ContextHandler
import com.github.fm_jm.softcomputing.ga.alg.GeneratePopulation
import com.github.fm_jm.softcomputing.ga.alg.StopCondition
import com.github.fm_jm.softcomputing.heuristics.Specimen

import groovy.util.logging.Slf4j

import static com.github.fm_jm.softcomputing.impl.RandomUtils.*
import static com.github.fm_jm.softcomputing.experiments.Constants.*

import groovy.transform.Canonical

@Canonical
@Slf4j
class GeneticAlgorithm<S extends Specimen> {
    int populationSize

    ContextHandler<S> contextHandler
    GeneratePopulation<S> generatePopulation
    StopCondition<S> stop

    CrossoverOperator<S> crossover
    MutationOperator<S> mutation
    SelectionOperator<S> select

    CP<S> cp
    MP<S> mp



    /**
     * Run genetic algorithm parametrized with attributes of this instance.
     * @param callback Closure taking (List<S> population, int generation, Map context) called at the beginning of each
     * population (after context handling/update). Useful for tracking progress or generating CSV data.
     * @return Last population, sorted (ascending) by evaluation.
     */
    List<S> doRun(Context context) {
        log.debug("doRun")
        List<S> population = generatePopulation.generate(populationSize, context)
        int generation = 0

        contextHandler.start(context)

        while (!stop.shouldStop(population, generation, context)) {
            def mutProb = mp.getMutationProbability(population, generation, context)
            def crossProb = cp.getCrossoverProbability(population, generation, context)
            context.mutProb = mutProb
            context.crossProb = crossProb
            contextHandler.update(population, generation, context)

            List<S> tempPop = population//.collect { it.copy() } // is it worth it? (see next lines)
            // if we're gonna collect some specimens through the whole algortithm run, then it enforces immutability
            // between geenrations and it is worth it
            // if we're only gonna collect statistics, then this may be left out.
            log.debug("mixins")
            tempPop += generatePopulation.generate((int)Math.ceil(FREE_RADICALS_FACTOR*populationSize), context) // "wolne rodniki" / "free radicals"
            log.debug("mixins done")
            log.debug("crossing over")
            while (tempPop.size()<2*populationSize) {
                def s1 = random(population)
                def s2 = random(population)
                if (happens(crossProb))
                    tempPop += crossover.crossOver(s1, s2, context)
            }
            log.debug("crossing over done")
            log.debug("mutating")
            tempPop = tempPop.collect { S s ->
                happens(mutProb) ?
                    mutation.mutate(s, context)
                    : [s]
            }.flatten()
            log.debug("mutating done")
            log.debug("selecting")
            population = select.selectNewPopulation(populationSize, tempPop, generation, context)
            log.debug("selecting done")
            generation++
        }
        contextHandler.finish(context)
        return population
    }


}
