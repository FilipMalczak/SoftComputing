package com.github.fm_jm.softcomputing.experiments.impl

import groovy.util.logging.Slf4j
import groovy.transform.Canonical

@Canonical
@Slf4j
class BruteExperimentSetup extends ExperimentSetup{

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

}
