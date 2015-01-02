package com.github.fm_jm.softcomputing

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.impl.ContextLoader

File file = new File(this.class.classLoader.getResource("ln+cos.csv").toURI())

Context loaded = ContextLoader.loadFromFile(file)

//def ga = new GeneticAlgorithm<FunctionTree>(
//        100,
//        new ContextHandlerImpl(),
//        new RandomFunctionsGenerator(),
//        new StopCondImpl(50),
//        new SubTreeInjectingCrossover(),
//        new AnnealingMutation(new SimulatedAnnealing<FunctionTree>(new FastBoltzmannScheme(), new BasicNeighbourSelector(), new BoltzmannAcceptance<FunctionTree>())),
//        new SelectImpl(),
//        new CPImpl(750, 10),
//        new MPImpl(200, 10)
//)

def ga = Utils.defaultGA(15, 10, 750, 10, 200, 10)

def pop = ga.doRun(loaded)

println pop[0]
println pop[0].evaluate(loaded)