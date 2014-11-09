package com.github.fm_jm.softcomputing.ga

import com.github.fm_jm.softcomputing.ga.operators.CrossoverOperator
import com.github.fm_jm.softcomputing.ga.operators.MutationOperator
import com.github.fm_jm.softcomputing.ga.operators.SelectionOperator
import com.github.fm_jm.softcomputing.ga.prob.CP
import com.github.fm_jm.softcomputing.ga.prob.MP
import com.github.fm_jm.softcomputing.ga.alg.ContextHandler
import com.github.fm_jm.softcomputing.ga.alg.GeneratePopulation
import com.github.fm_jm.softcomputing.ga.alg.StopCondition
import com.github.fm_jm.softcomputing.ga.operators.*
import com.github.fm_jm.softcomputing.ga.prob.*
import static com.github.fm_jm.softcomputing.utils.Casting.*


import groovy.transform.Canonical

@Canonical
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
    List<S> doRun(Map context) {
        List<S> population = $(generatePopulation)(populationSize)
        int generation = 0

        while ($(stop)(population, generation, context)) {
            def mutProb = $(mp)(population, generation, context)
            def crossProb = $(cp)(population, generation, context)
            context.mutProb = mutProb
            context.crossProb = crossProb
            $(contextHandler)(population, generation, context)

            List<S> tempPop = population.collect { it.copy() } // fixme: is it worth it? (see next lines)
            // if we're gonna collect some specimens through the whole algortithm run, then it enforces immutability
            // between geenrations and it is worth it
            // if we're only gonna collect statistics, then this may be left out.

            tempPop += $(generatePopulation)(0.15*populationSize) // "wolne rodniki"

            while (tempPop.size()<2*populationSize) {
                def s1 = random(population)
                def s2 = random(population)
                if (happens(crossProb))
                    tempPop << crossover(s1, s2)
            }

            tempPop = tempPop.collect { S s ->
                happens(mutProb) ?
                    $(mutation)(s)
                    : s
            }

            population = $(select)(populationSize, tempPop, generation, context)

            generation++
        }
        return population
    }

    private boolean happens(int probability) {
        Math.round(Math.random()*1000) < probability
    }

    private S random(List<S> population) {
        population[Math.round(Math.random()*population.size())]
    }
}
