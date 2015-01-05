package com.github.fm_jm.softcomputing.experiments

new ExperimentSetup(
    timesPerConfig: 5,
    dataSets: [
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
    maxAvgs: [ // 1k, 5k, 100k
        10000.0
    ],
    popSizes: [
        50,
        100,
        200
    ],
    maxGens: [
        50,
        100,
        200
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
    ]
).calculate(
).toCSV(new File("./results.csv")
)