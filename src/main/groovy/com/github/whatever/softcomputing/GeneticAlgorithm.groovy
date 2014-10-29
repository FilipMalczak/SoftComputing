package com.github.whatever.softcomputing

import com.github.whatever.softcomputing.genalg.*
import com.github.whatever.softcomputing.operators.*
import com.github.whatever.softcomputing.prob.*
import static com.github.whatever.softcomputing.Casting.*


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
    List<S> doRun(Closure<Void> callback) {
        List<S> population = $(generatePopulation)(populationSize)
        int generation = 0
        Map context = [:]

        while ($(stop)(population, generation, context)) {
            $(contextHandler)(context)
            callback(population, generation, context)
            List<S> tempPop = population.collect { it.copy() } // fixme: is it worth it? (see next lines)
            // if we're gonna collect some specimens through the whole algortithm run, then it enforces immutability
            // between geenrations and it is worth it
            // if we're only gonna collect statistics, then this may be left out.
            while (tempPop.size()<2*populationSize) {
                def s1 = random(population)
                def s2 = random(population)
                if (happens($(cp)(population, generation, context)))
                    tempPop << crossover(s1, s2)
            }

            tempPop = tempPop.collect { S s ->
                happens($(mp)(population, generation, context)) ?
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
