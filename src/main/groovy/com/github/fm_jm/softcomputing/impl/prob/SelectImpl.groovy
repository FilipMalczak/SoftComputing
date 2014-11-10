package com.github.fm_jm.softcomputing.impl.prob

import com.github.fm_jm.softcomputing.ga.GeneticAlgorithm
import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.ga.operators.SelectionOperator
import com.github.fm_jm.softcomputing.impl.FunctionTree

import groovy.transform.Canonical

@Canonical
class SelectImpl implements SelectionOperator<FunctionTree>{
    double bestTaken
    double worstTaken

    static final int TOURNEY_SIZE = 2

    @Override
    List<FunctionTree> selectNewPopulation(int populationSize, List<FunctionTree> population, int generation, Context context) {
        def sorted = population.sort { FunctionTree ft -> ft.evaluate(context) }
        def out = sorted.subList(0, Math.ceil(bestTaken * populationSize) as int)
        out += sorted.subList(Math.floor((1.0-worstTaken) * populationSize) as int, populationSize)
        def forTourneys = population.subList(
            Math.ceil(bestTaken * populationSize) as int,
            Math.floor((1.0-worstTaken) * populationSize) as int
        )
        while (out.size()<populationSize)
            out.add(tourney(forTourneys, context))
        return out.sort { FunctionTree ft -> ft.evaluate(context) }
    }

    private FunctionTree tourney(List<FunctionTree> pop, Context context){
        def pool = []
        TOURNEY_SIZE.times {
            pool.add(GeneticAlgorithm.random(pop))
        }
        return pool.min { FunctionTree ft -> ft.evaluate(context) }
    }


}

