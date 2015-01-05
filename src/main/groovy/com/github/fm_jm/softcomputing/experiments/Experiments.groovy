package com.github.fm_jm.softcomputing.experiments

new ExperimentSetup(
    timesPerConfig: 5,
    dataSets: [
        "small_sqrt.csv"
    ],
    freeRadicalsFactors: [
        0.15,
        0.5
    ],
    tourneySizes: [
        2
    ],
    temperatures: [
        [100, 1],
        [50, 1]
    ],
    minDepths: [
        3
    ],
    initialWidths: [
        [2, 10],
        [3, 5]
    ],
    earlyLeavesProbs: [
        400
    ],
    constProbs: [
        400
    ],
    maxAvgs: [
        10000.0
    ],
    popSizes: [
        10,
        20
    ],
    maxGens: [
        20
    ],
    cps: [
        [750, 10]
    ],
    mps: [
        [200, 10]
    ]
).calculate().toCSV(new File("./results.csv"))