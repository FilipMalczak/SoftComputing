package com.github.fm_jm.softcomputing

import com.github.fm_jm.softcomputing.ga.GeneticAlgorithm
import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.impl.ContextHandlerImpl
import com.github.fm_jm.softcomputing.impl.ContextLoader
import com.github.fm_jm.softcomputing.impl.FunctionTree
import com.github.fm_jm.softcomputing.impl.RandomFunctionsGenerator
import com.github.fm_jm.softcomputing.impl.StopCondImpl
import com.github.fm_jm.softcomputing.impl.operators.AnnealingMutation
import com.github.fm_jm.softcomputing.impl.operators.SubTreeInjectingCrossover
import com.github.fm_jm.softcomputing.impl.prob.CPImpl
import com.github.fm_jm.softcomputing.impl.prob.MPImpl
import com.github.fm_jm.softcomputing.impl.prob.SelectImpl
import com.github.fm_jm.softcomputing.impl.sa.BasicNeighbourSelector
import com.github.fm_jm.softcomputing.impl.sa.BoltzmannAcceptance
import com.github.fm_jm.softcomputing.impl.sa.FastBoltzmannScheme
import com.github.fm_jm.softcomputing.sa.SimulatedAnnealing


File file = new File(this.class.classLoader.getResource("ln+cos.csv").toURI())

Context loaded = ContextLoader.loadFromFile(file)

def ga = new GeneticAlgorithm<FunctionTree>(
        100,
        new ContextHandlerImpl(),
        new RandomFunctionsGenerator(),
        new StopCondImpl(50),
        new SubTreeInjectingCrossover(),
        new AnnealingMutation(new SimulatedAnnealing<FunctionTree>(new FastBoltzmannScheme(), new BasicNeighbourSelector(), new BoltzmannAcceptance<FunctionTree>())),
        new SelectImpl(),
        new CPImpl(750, 10),
        new MPImpl(200, 10)
)

def pop = ga.doRun(loaded)

println pop[0]
println pop[0].evaluate(loaded)