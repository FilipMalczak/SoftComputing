package com.github.fm_jm.softcomputing.utils

import com.github.fm_jm.softcomputing.ga.alg.ContextHandler
import com.github.fm_jm.softcomputing.ga.alg.GeneratePopulation
import com.github.fm_jm.softcomputing.ga.alg.StopCondition
import com.github.fm_jm.softcomputing.ga.Specimen
import com.github.fm_jm.softcomputing.ga.operators.CrossoverOperator
import com.github.fm_jm.softcomputing.ga.operators.MutationOperator
import com.github.fm_jm.softcomputing.ga.operators.SelectionOperator
import com.github.fm_jm.softcomputing.ga.prob.CP
import com.github.fm_jm.softcomputing.ga.prob.MP
import com.github.whatever.newstructure.ga.operators.*
import com.github.whatever.newstructure.ga.prob.*

/**
 * This is used, so we can write $(mutation)(x) instead of mutation.mutate(x). It's only a syntactic sugar, use it
 * if you will.
 */
class Casting {
    static <S extends Specimen> Closure $(ContextHandler<S> handler){
        return { List<S> population, int generation, Map context ->
            handler.update(population, generation, context)
        }
    }

    static <S extends Specimen> Closure $(GeneratePopulation<S> genPop){
        return { int size ->
            genPop.generate(size)
        }
    }

    static <S extends Specimen> Closure $(StopCondition<S> stop){
        return { List<S> population, int generation, Map context ->
            stop.shouldStop(population, generation, context)
        }
    }

    static <S extends Specimen> Closure $(CrossoverOperator<S> crossover){
        return { S s1, S s2 ->
            crossover.crossOver(s1, s1)
        }
    }

    static <S extends Specimen> Closure $(MutationOperator<S> mutation){
        return { S s ->
            mutation.mutate(s)
        }
    }

    static <S extends Specimen> Closure $(SelectionOperator<S> selection){
        return { int populationSize, List<S> population, int generation, Map context ->
            selection.selectNewPopulation(populationSize, population, generation, context)
        }
    }

    static <S extends Specimen> Closure $(CP<S> cp){
        return { List<S> population, int generation, Map context ->
            cp.getCrossoverProbability(population, generation, context)
        }
    }

    static <S extends Specimen> Closure $(MP<S> mp){
        return { List<S> population, int generation, Map context ->
            mp.getMutationProbability(population, generation, context)
        }
    }
}
