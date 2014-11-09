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
import com.github.fm_jm.softcomputing.ga.Specimen
import com.github.fm_jm.softcomputing.ga.operators.*
import com.github.fm_jm.softcomputing.ga.prob.*

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
    double evaluate(Map ctx) {
        (x+y)*3 - (x-y)*(x-y)*x
    }

    @Override
    Specimen copy() {
        new Pair(x, y)
    }
}

def ga = new GeneticAlgorithm<Pair>(
    100,
    {} as ContextHandler<Pair>,
    { int size ->
        (0..<size).collect {
            new Pair(Math.round(Math.random()*50), Math.round(Math.random()*50))
        }
    } as GeneratePopulation<Pair>,
    { List<Pair> population, int generation, Map context ->
        generation>200
    } as StopCondition<Pair>,
    { Pair p1, Pair p2, Map ctx ->
        [ new Pair(p1.x, p2.y), new Pair(p2.x, p1.y) ]
    } as CrossoverOperator<Pair>,
    { Pair p, Map ctx ->
        [ 2*p.x*(Math.random()-0.5), 2*p.y*(Math.random()-0.5) ]
    } as MutationOperator<Pair>,
    { int populationSize, List<Pair> population, int generation, Map context ->
        population.sort { it.evaluate() }.subList(0, populationSize) // sorts in ascending order
    } as SelectionOperator<Pair>,
    { List<Pair> population, int generation, Map context -> 0.25} as CP<Pair>,
    { List<Pair> population, int generation, Map context -> 0.25} as MP<Pair>
)

def pop = ga.doRun { p, g, c ->
    println "Generation $g"
}

println pop[0]
println pop[0].evaluate()