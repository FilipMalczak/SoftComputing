package com.github.fm_jm.softcomputing.experiments.impl

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.impl.ContextLoader

import groovy.time.TimeCategory
import groovy.util.logging.Slf4j

import static Constants.*

@Slf4j
abstract class ExperimentSetup {
    int timesPerConfig

    List<String> dataSets

    List<Double> freeRadicalsFactors
    List<Integer> tourneySizes
    List<List<Double>> temperatures // [initial/max, min]

    List<Integer> minDepths
    List<List<Integer>> initialWidths // [min, max]

    List<Integer> earlyLeavesProbs
    List<Integer> constProbs

    List<Double> maxAvgs

    List<Integer> popSizes
    List<Integer> maxGens
    List<List<Integer>> cps // [initial, step]
    List<List<Integer>> mps // [initial, step]

    int currentTime
    String currentDataSet
    File currentFile
    int popSize = 100
    int maxGen = 50
    int initCp = 800
    int stepCp = 10
    int initMp = 300
    int stepMp = 10

    String getCurrentKey(){
        ([
            currentDataSet, "$currentTime",
            "$FREE_RADICALS_FACTOR",
            "$TOURNEY_SIZE",
            "$INITIAL_TEMP", "$MINIMAL_TEMP",
            "$MIN_DEPTH",
            "$MIN_INITIAL_WIDTH", "$MAX_INITIAL_WIDTH",
            "$EARLY_LEAF_PROB",
            "$CONST_PROB",
            "$MAX_AVG_FOR_RESULT",
            "$popSize",
            "$maxGen",
            "$initCp",
            "$stepCp",
            "$initMp",
            "$stepMp"
        ].join(";"))
    }

    static final String keyParts = ([
        "currentDataSet", "currentTime",
        "freeRadicalsFactor",
        "tourneySize",
        "initialTemp", "minTemp",
        "minDepth",
        "minInitialWidth", "maxInitialWidth",
        "earlyLeafProb",
        "constProb",
        "maxAvgForResult",
        "popSize",
        "maxGen",
        "initCp",
        "stepCp",
        "initMp",
        "stepMp"
    ].join("\t"))

    abstract void eachConfig(Closure c);

    ExperimentSetup calculate(){
        eachConfig {
            if (!ResultStorage.instance.exists(currentKey)) {
                log.warn("Calculating")
                Context context = ContextLoader.loadFromFile(currentFile);
                Utils.semiGA(
                    popSize,
                    maxGen,
                    initCp,
                    stepCp,
                    initMp,
                    stepMp
                ).doRun(context)
                ResultStorage.instance.store(currentKey, context)
            } else {
                log.warn("Already calculated, skipping")
            }
        }
        return this
    }

    ExperimentSetup toCSV(File target){
        target.text = "$keyParts\teval\tduration\tfoo"
        eachConfig {
            log.info(currentKey)
            def ctx = ResultStorage.instance.load(currentKey)
            if (ctx!=null)
                use (TimeCategory) {
                    target.append("${currentKey.replaceAll(";", "\t")}\t${ctx?.globalBest?.evaluate(ctx)}\t${ctx?.endTime?.minus(ctx?.startTime)}\t${ctx?.globalBest}\n")
                }
        }

        return this
    }
}
