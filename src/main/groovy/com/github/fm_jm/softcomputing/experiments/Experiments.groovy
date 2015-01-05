package com.github.fm_jm.softcomputing.experiments

new ExperimentSetup(
    timesPerConfig: 5,
    dataSets: [
        "small_sqrt.csv",
        "exp.csv",
        "ln+cos.csv",
        "sqrt.csv",
        "x2+x.csv"
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
        10
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
    maxAvgs: [
        1000.0,
        5000.0,
        10000.0,
        100000.0
    ],
    popSizes: [
        100,
        200,
        500
    ],
    maxGens: [
        200,
        300,
        500
    ],
    cps: [
        [750, 10],
        [750, 50],
        [250, 10],
        [250, 50]
    ],
    mps: [
        [750, 10],
        [750, 50],
        [250, 10],
        [250, 50]
    ]
).calculate(
).toCSV(new File("./results.csv")
)