package com.github.fm_jm.softcomputing.impl

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.ga.alg.ContextHandler
import com.github.fm_jm.softcomputing.heuristics.Specimen


class SimpleContextHandler<S extends Specimen> implements ContextHandler<S>{

    @Override
    void update(List<S> population, int generation, Context context) {
        println "GENERATION $generation START"
        def popBest = findBest(population, context).copy()
        // save copy of best
        if (generation==0)
            context.globalBest = popBest
        else
            context.globalBest = findBest([ context.globalBest, popBest ], context)

        def avg = avgEval(population, context)
        context.pushAvg(avg)
        context.pushVariance(variance(population, context, avg))
        context.pushBest(popBest.evaluate(context))
        context.pushWorst(findWorst(population, context).evaluate(context))
        println "GENERATION $generation UPDATED"
    }

    static S findBest(List<S> population, Context context){
        population.min { S ft -> ft.evaluate(context) }
    }

    static S findWorst(List<S> population, Context context){
        population.max { S ft -> ft.evaluate(context) }
    }

    static double avgEval(List<S> population, Context context){
        def collection  = population.collect { S ft -> ft.evaluate(context)/ population.size() }
        collection.sum()
    }

    static double variance(List<S> population, Context context, double avg){
        def collection = population.collect { S ft -> Math.abs(ft.evaluate(context) - avg)/ population.size() }
        collection.sum()
    }
}
