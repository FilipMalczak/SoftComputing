package com.github.fm_jm.softcomputing

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.impl.sa.BoltzmannAcceptance
import com.github.fm_jm.softcomputing.impl.sa.FastBoltzmannScheme
import com.github.fm_jm.softcomputing.sa.SimulatedAnnealing
import com.github.fm_jm.softcomputing.sa.operators.NeighbourSelector

def sa = new SimulatedAnnealing<Pair>(
        new FastBoltzmannScheme(),
        { Pair current, double temperature, Context context ->
            Pair neighbour = current.copy()
            if (Math.random() < 0.5)
                neighbour.x = (Math.random()-0.5)*4*neighbour.x
            else
                neighbour.y = (Math.random()-0.5)*4*neighbour.y
            return neighbour
        } as NeighbourSelector<Pair>,
        new BoltzmannAcceptance<Pair>()
)

Pair first = new Pair(5, 5)
Context context = new Context()
context.mutProb = 200
context.crossProb = 750
context.avgHistory = [5, 6, 7]
context.varianceHistory = [2, 1.5, 2.3]

def res = sa.doRun(first, context)

println res
println res[0].evaluate(context)
println res[1].evaluate(context)