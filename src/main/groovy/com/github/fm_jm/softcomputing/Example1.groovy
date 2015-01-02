package com.github.fm_jm.softcomputing

import com.github.fm_jm.softcomputing.ga.operators.CrossoverOperator
import com.github.fm_jm.softcomputing.ga.operators.MutationOperator
import com.github.fm_jm.softcomputing.ga.operators.SelectionOperator
import com.github.fm_jm.softcomputing.ga.prob.CP
import com.github.fm_jm.softcomputing.ga.prob.MP
import com.github.fm_jm.softcomputing.ga.alg.ContextHandler
import com.github.fm_jm.softcomputing.ga.alg.GeneratePopulation
import com.github.fm_jm.softcomputing.ga.alg.StopCondition
import com.github.fm_jm.softcomputing.ga.GeneticAlgorithm
import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.heuristics.Specimen
import com.github.fm_jm.softcomputing.impl.ContextHandlerImpl

import groovy.transform.Canonical

/**
 * This script should find minimum of 3(x+y) + (x-y)^3 on [0, 50]^2.
 * It kinda sucks with results, but we can see that GA works, and we can rapidly experiment with logging,
 * filling context, etc here.
 */

@Canonical
class Pair implements Specimen{
    double x,y

    @Override
    double evaluate(Context ctx) {
        (x+y)*3 - (x-y)*(x-y)*x
    }

    @Override
    Specimen copy() {
        new Pair(x, y)
    }
}

def ga = new GeneticAlgorithm<Pair>(
    100,
//    {} as ContextHandler<Pair>,
    new ContextHandlerImpl<Pair>(),
    { int size, context ->
        (0..<size).collect {
            new Pair(Math.round(Math.random()*50), Math.round(Math.random()*50))
        }
    } as GeneratePopulation<Pair>,
    { List<Pair> population, int generation, Context context ->
        generation>200
    } as StopCondition<Pair>,
    { Pair p1, Pair p2, Context ctx ->
        [ new Pair(p1.x, p2.y), new Pair(p2.x, p1.y) ]
    } as CrossoverOperator<Pair>,
    { Pair p, Context ctx ->
        [ new Pair(2*p.x*(Math.random()-0.5), 2*p.y*(Math.random()-0.5)) ]
    } as MutationOperator<Pair>,
    { int populationSize, List<Pair> population, int generation, Context context ->
        population.sort { it.evaluate() }.subList(0, populationSize) // sorts in ascending order
    } as SelectionOperator<Pair>,
    { List<Pair> population, int generation, Context context -> 250} as CP<Pair>,
    { List<Pair> population, int generation, Context context -> 250} as MP<Pair>
)

Context c = new Context<Pair>()
def pop = ga.doRun(c)

println pop[0]
println pop[0].evaluate()

println c.globalBest
println c.globalBest.evaluate(c)
println c.bestHistory[0]
println c.avgHistory[0]
println c.varianceHistory[0]
println c.worstHistory[0]