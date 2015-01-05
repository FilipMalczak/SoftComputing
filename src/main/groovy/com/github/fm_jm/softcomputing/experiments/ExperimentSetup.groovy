package com.github.fm_jm.softcomputing.experiments

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.impl.ContextLoader

import groovy.util.logging.Slf4j

import static com.github.fm_jm.softcomputing.experiments.Constants.*

import groovy.transform.Canonical

@Canonical
@Slf4j
class ExperimentSetup {
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
    int popSize
    int maxGen
    int initCp
    int stepCp
    int initMp
    int stepMp

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

    void eachConfig(Closure c){
        dataSets.each { String dataSetName ->
            currentFile = new File(this.class.classLoader.getResource(dataSetName).toURI())
            currentDataSet = dataSetName
            log.error("Data set: $currentDataSet @file: $currentFile")
            freeRadicalsFactors.each { double frf ->
                Constants.FREE_RADICALS_FACTOR = frf
                tourneySizes.each { int ts ->
                    Constants.TOURNEY_SIZE = ts
                    temperatures.each { List<Double> temps ->
                        Constants.INITIAL_TEMP = temps[0]
                        Constants.MINIMAL_TEMP = temps[1]
                        minDepths.each { int md ->
                            Constants.MIN_DEPTH = md
                            initialWidths.each { List<Integer> widths ->
                                Constants.MIN_INITIAL_WIDTH = widths[0]
                                Constants.MAX_INITIAL_WIDTH = widths[1]
                                earlyLeavesProbs.each { int elp ->
                                    Constants.EARLY_LEAF_PROB = elp
                                    constProbs.each { int const_p ->
                                        Constants.CONST_PROB = const_p
                                        maxAvgs.each { double ma ->
                                            Constants.MAX_AVG_FOR_RESULT = ma
                                            popSizes.each { int ps ->
                                                popSize = ps
                                                maxGens.each { int mg ->
                                                    maxGen = mg
                                                    cps.each { List<Integer> crossover_probs->
                                                        initCp = crossover_probs[0]
                                                        stepCp = crossover_probs[1]
                                                        mps.each { List<Integer> mut_probs->
                                                            initMp = mut_probs[0]
                                                            stepMp = mut_probs[1]
                                                            timesPerConfig.times { int t ->
                                                                currentTime = t
                                                                log.warn("Current: $currentKey")
                                                                c()
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    ExperimentSetup calculate(){
        eachConfig {
            if (!ResultStorage.instance.exists(currentKey)) {
                log.warn("Calculating")
                Context context = ContextLoader.loadFromFile(currentFile);
                Utils.defaultGA(
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
        target.text = ""
        eachConfig {
            def ctx = ResultStorage.instance.load(currentKey)
            target.append("$currentKey;${ctx?.globalBest.evaluate(ctx)};${ctx?.startTime};${ctx.endTime})\n")
        }
        return this
    }
}
