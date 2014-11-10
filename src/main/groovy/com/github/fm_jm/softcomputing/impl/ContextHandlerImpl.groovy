package com.github.fm_jm.softcomputing.impl

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.ga.alg.ContextHandler


class ContextHandlerImpl implements ContextHandler<FunctionTree>{

    @Override
    void update(List<FunctionTree> population, int generation, Context context) {
        // save copy of best
        if (generation==0)
            context.globalBest = findBest(population, context).copy()
        else
            context.globalBest = findBest([ context.globalBest, findBest(population, context).copy() ], context)

        def avg = avgEval(population, context)
        context.pushAvg(avg)

        context.pushVariance(variance(population, context, avg))
    }

    private FunctionTree findBest(List<FunctionTree> population, Context context){
        population.min { FunctionTree ft -> ft.evaluate(context) }
    }

    private double avgEval(List<FunctionTree> population, Context context){
        population.collect { FunctionTree ft -> ft.evaluate(context) }.sum() / population.size()
    }

    /**
     * TODO: check name!
     * Average absolute difference between evaluation and average evaluation.
     */
    private double variance(List<FunctionTree> population, Context context, double avg){
        population.collect { FunctionTree ft -> Math.abs(ft.evaluate(context) - avg) }.sum() / population.size()
    }
}
