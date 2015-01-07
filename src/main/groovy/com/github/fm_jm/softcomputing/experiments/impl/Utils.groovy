package com.github.fm_jm.softcomputing.experiments.impl

import com.github.fm_jm.softcomputing.ga.GeneticAlgorithm
import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.impl.SimpleContextHandler
import com.github.fm_jm.softcomputing.impl.FunctionTree
import com.github.fm_jm.softcomputing.impl.RandomFunctionsGenerator
import com.github.fm_jm.softcomputing.impl.SimpleStopCondition
import com.github.fm_jm.softcomputing.impl.fun.FunctionsDefinitions
import com.github.fm_jm.softcomputing.impl.operators.AnnealingMutation
import com.github.fm_jm.softcomputing.impl.operators.SemiAnnealingMutation
import com.github.fm_jm.softcomputing.impl.operators.SubTreeInjectingCrossover
import com.github.fm_jm.softcomputing.impl.prob.CPImpl
import com.github.fm_jm.softcomputing.impl.prob.MPImpl
import com.github.fm_jm.softcomputing.impl.prob.SelectImpl
import com.github.fm_jm.softcomputing.impl.sa.BasicNeighbourSelector
import com.github.fm_jm.softcomputing.impl.sa.BoltzmannAcceptance
import com.github.fm_jm.softcomputing.impl.sa.FastBoltzmannScheme
import com.github.fm_jm.softcomputing.sa.SimulatedAnnealing
import groovy.time.TimeCategory

class Utils {

    static GeneticAlgorithm<FunctionTree> defaultGA(int popSize, int maxGen, int initCP, int stepCP, int initMP, int stepMP){
        new GeneticAlgorithm<FunctionTree>(
                popSize,
                new SimpleContextHandler(),
                new RandomFunctionsGenerator(),
                new SimpleStopCondition(maxGen),
                new SubTreeInjectingCrossover(),
                new AnnealingMutation(
                    new SimulatedAnnealing<FunctionTree>(
                        new FastBoltzmannScheme(),
                        new BasicNeighbourSelector(),
                        new BoltzmannAcceptance<FunctionTree>()
                    )
                ),
                new SelectImpl(),
                new CPImpl(initCP, stepCP),
                new MPImpl(initMP, stepMP)
        )
    }

    static GeneticAlgorithm<FunctionTree> semiGA(int popSize, int maxGen, int initCP, int stepCP, int initMP, int stepMP){
        new GeneticAlgorithm<FunctionTree>(
                popSize,
                new SimpleContextHandler(),
                new RandomFunctionsGenerator(),
                new SimpleStopCondition(maxGen),
                new SubTreeInjectingCrossover(),
                new SemiAnnealingMutation(
                        new SimulatedAnnealing<FunctionTree>(
                                new FastBoltzmannScheme(),
                                new BasicNeighbourSelector(),
                                new BoltzmannAcceptance<FunctionTree>()
                        )
                ),
                new SelectImpl(),
                new CPImpl(initCP, stepCP),
                new MPImpl(initMP, stepMP)
        )
    }

    /**
     * Works only with context that has everything filled by GA.
     */
    static void writeCsv(Context<FunctionTree> context, Closure<Void> println){
        println(context.globalBest) // row 1
        println(
            (context.domainNames+["${context.valueSetName}-expected", "${context.valueSetName}-calculated", "difference", "difference^2"]).join("\t")
        ) // row 2
        context.points.each { Map<String, Double> point ->
            def row = context.domainNames.collect { point[it] }
            row << point[context.valueSetName]
            def val = context.globalBest.value(point)
            row << val
            row << (Math.abs(val-point[context.valueSetName]))
            row << (Math.pow(Math.abs(val-point[context.valueSetName]), 2))
            println(row.collect {"$it".replaceAll("[.]", ",")}.join("\t"))
        } // rows 3-(points.size()+3)
        def diffColumn = (char)(((int)((char)'A'))+context.domainNames.size()+2)
        def sqrDiffColumn = (char)(((int)((char)'A'))+context.domainNames.size()+3)
        println("SUM"+"\t"*(context.domainNames.size()+2)+"=sum(${diffColumn}3;${diffColumn}${context.points.size()+2})\t=sum(${sqrDiffColumn}3;${sqrDiffColumn}${context.points.size()+2})")
        println("AVG"+"\t"*(context.domainNames.size()+2)+"=${diffColumn}${context.points.size() + 3}/${context.points.size()}\t=${sqrDiffColumn}${context.points.size() + 3}/${context.points.size()}")
        println("")
        println("")
        println( (["generation"]+((context.avgHistory.size()-1)..0).collect{"$it"}).join("\t") )
        println( (["best"]+context.bestHistory.collect{"$it".replaceAll("[.]", ",")}).join("\t") )
        println( (["avg"]+context.avgHistory.collect{"$it".replaceAll("[.]", ",")}).join("\t") )
        println( (["worst"]+context.worstHistory.collect{"$it".replaceAll("[.]", ",")}).join("\t") )
        println( (["variance"]+context.varianceHistory.collect{"$it".replaceAll("[.]", ",")}).join("\t") )
        println( (["CP"]+context.CPHistory.collect{"$it".replaceAll("[.]", ",")}).join("\t"))
        println( (["MP"]+context.MPHistory.collect{"$it".replaceAll("[.]", ",")}).join("\t"))
        println("")
        println("")
        println("Total execution time: ${TimeCategory.minus(context.endTime, context.startTime)}")
    }

}
