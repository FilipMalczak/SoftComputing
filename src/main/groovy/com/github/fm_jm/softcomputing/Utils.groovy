package com.github.fm_jm.softcomputing

import com.github.fm_jm.softcomputing.ga.GeneticAlgorithm
import com.github.fm_jm.softcomputing.impl.ContextHandlerImpl
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

class Utils {

    static GeneticAlgorithm<FunctionTree> defaultGA(int popSize, int maxGen, int initCP, int stepCP, int initMP, int stepMP){
        new GeneticAlgorithm<FunctionTree>(
                popSize,
                new ContextHandlerImpl(),
                new RandomFunctionsGenerator(),
                new StopCondImpl(maxGen),
                new SubTreeInjectingCrossover(),
                new AnnealingMutation(new SimulatedAnnealing<FunctionTree>(new FastBoltzmannScheme(), new BasicNeighbourSelector(), new BoltzmannAcceptance<FunctionTree>())),
                new SelectImpl(),
                new CPImpl(initCP, stepCP),
                new MPImpl(initMP, stepMP)
        )
    }

}
