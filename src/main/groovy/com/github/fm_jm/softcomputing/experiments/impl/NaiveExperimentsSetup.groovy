package com.github.fm_jm.softcomputing.experiments.impl

import groovy.transform.Canonical
import groovy.util.logging.Slf4j

import static Constants.*

@Canonical
@Slf4j
class NaiveExperimentsSetup extends ExperimentSetup{

    Closure<Double> contextEvaluator//(Context ctx)
    /**
     * Do all experiments (with only one value changing amongst them) and set up some value given all values to be tested.
     * @param values parameter values
     * @param setValue for example { popSize = it }
     * @param c experiment/csv writing logic
     * @return
     */
    protected void doValues(List values, Closure<Void> setValue, Closure merge, Closure<Void> c){
        Map<String, List> contexts = [:]
        values.each { val ->
            setValue(val)
            dataSets.each { String dataSetName ->
                currentFile = new File(this.class.classLoader.getResource(dataSetName).toURI())
                currentDataSet = dataSetName
                timesPerConfig.times { int t ->
                    currentTime = t
                    log.warn("Current key: $currentKey")
                    c()
                    contexts[currentKey] = [ResultStorage.instance.load(currentKey), val]
                }
            }
        }
        setValue(contexts[contexts.keySet().min { contextEvaluator(contexts[it][0])}][1])
    }

    def mean(Set s, Closure key={it}){
        s.sum{key(it)/s.size()}
    }

    void eachConfig(Closure c){
        def mean = this.&mean
        def pairMean = { [mean(it, {it[0]}), mean(it, {it[1]})]}

        doValues(freeRadicalsFactors, {FREE_RADICALS_FACTOR = it}, mean, c)
        doValues(tourneySizes, {TOURNEY_SIZE = it}, mean, c)
        doValues(temperatures, {INITIAL_TEMP = it[0]; MINIMAL_TEMP = it[1]}, pairMean, c)
        doValues(minDepths, {MIN_DEPTH = it}, mean, c)
        doValues(initialWidths, {MIN_INITIAL_WIDTH = it[0]; MAX_INITIAL_WIDTH = it[1]}, pairMean, c)
        doValues(earlyLeavesProbs, {EARLY_LEAF_PROB = it}, mean, c)
        doValues(constProbs, {CONST_PROB = it}, mean, c)
        doValues(maxAvgs, {MAX_AVG_FOR_RESULT = it}, mean, c)
        doValues(popSizes, this.&setPopSize, mean, c)
        doValues(maxGens, this.&setMaxGen, mean, c)
        doValues(cps, {initCp = it[0]; stepCp = it[1]}, pairMean, c)
        doValues(mps, {initMp = it[0]; stepMp = it[1]}, pairMean, c)
    }
}
