package com.github.fm_jm.softcomputing.heuristics

import com.github.fm_jm.softcomputing.impl.FunctionTree

/**
 * It's not totally reusable, but will do.
 */
class Context {
    // GA state
    int mutProb
    int crossProb

    //problem definition
    List<String> domainNames
    String valueSetName
    List<Map<String, Double>> points

    // history for analysis
    FunctionTree globalBest = null
    List<Double> avgHistory = []
    List<Double> varianceHistory = []

    static final int HISTORY_LENGTH = 3

    void pushAvg(double avg){
        avgHistory = [ avg ] + avgHistory.subList(0, HISTORY_LENGTH-1)
    }

    void pushVariance(double var){
        varianceHistory = [ var ] + varianceHistory.subList(0, HISTORY_LENGTH-1)
    }

    /**
     * dAvg(dt) = avg(t-dt) - avg(t-dt-1)
     * No bounds checks are performed!
     */
    double dAvg(int dt){
        avgHistory[dt] - avgHistory[dt+1]
    }

    /**
     * dVariance(dt) = variance(t-dt) - variance(t-dt-1)
     * No bounds checks are performed!
     */
    double dVariance(int dt){
        varianceHistory[dt] - varianceHistory[dt+1]
    }
}