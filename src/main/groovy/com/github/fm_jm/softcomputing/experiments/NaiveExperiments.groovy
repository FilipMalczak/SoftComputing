package com.github.fm_jm.softcomputing.experiments

import com.github.fm_jm.softcomputing.experiments.impl.NaiveExperimentsSetup
import com.github.fm_jm.softcomputing.heuristics.Context

new NaiveExperimentsSetup(
    timesPerConfig: 2,
    dataSets: [
        "exp.csv",      // Jacek
        "ln+cos.csv",   // Jacek
        "sqrt.csv",     // Filip
        "x2+x.csv"      // Filip
    ],
    freeRadicalsFactors: [
        0.05,
        0.15,
        0.5,
        1.0
    ],
    tourneySizes: [
        2,
        3,
        5
    ],
    temperatures: [
        [100, 1],
        [50, 1],
        [1000, 10],
        [500, 10]
    ],
    minDepths: [
        3,
        5,
//        10
    ],
    initialWidths: [
        [2, 10],
        [3, 5],
        [5, 10]
    ],
    earlyLeavesProbs: [
        0,
        200,
        400,
        600
    ],
    constProbs: [
        200,
        400,
        700
    ],
    maxAvgs: [ // 1k, 5k, 100k
//        100000.0
1e40
    ],
    popSizes: [
        50,
        100,
        200
    ],
    maxGens: [
        50//,
//        100,
//        200
    ],
    cps: [
        [800, 10],
        [600, 10],
        [800, 50],
        [600, 50]
    ],
    mps: [
        [300, 10],
        [100, 10],
        [300, 50],
        [100, 50]
    ],
    contextEvaluator: { Context ctx -> ctx.globalBest.evaluate(ctx) }
).calculate(
).toCSV(new File("./results.csv")
)
/*
 * 4*3*4*3*3*4*3*3*4*4 = 248832 - that was number of experiments (each time timesPerConfig) in brute version for each dataset
 * 4+3+4+3+3+4+3+3+4+4 = 35 - this is the same number with naive version
 * In the end, we would have done ~2 millions of runs (assuming timesPerConfig=2) with brute approach, and 280 with this
 * 'naive' one.
 */

