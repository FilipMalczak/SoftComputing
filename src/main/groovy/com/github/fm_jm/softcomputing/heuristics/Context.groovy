package com.github.fm_jm.softcomputing.heuristics

/**
 * It's not totally reusable, but will do.
 */
class Context<S extends Specimen> {
    // GA state
    int mutProb
    int crossProb

    //problem definition
    List<String> domainNames
    String valueSetName
    List<Map<String, Double>> points

    // history for analysis
    S globalBest = null

    List<Double> avgHistory = []
    List<Double> varianceHistory = []
    List<Double> bestHistory = []
    List<Double> worstHistory = []
//    static final int HISTORY_LENGTH = 3

    void pushAvg(double avg){
        avgHistory = [ avg ] + avgHistory//.subList(0, HISTORY_LENGTH-1)
    }

    void pushVariance(double var){
        varianceHistory = [ var ] + varianceHistory//.subList(0, HISTORY_LENGTH-1)
    }

    void pushBest(double best){
        bestHistory = [ best ] + bestHistory//.subList(0, HISTORY_LENGTH-1)
    }

    void pushWorst(double worst){
        worstHistory = [ worst ] + worstHistory//.subList(0, HISTORY_LENGTH-1)
    }



    /**
     * dAvg(dt) = avg(t-dt) - avg(t-dt-1)
     * No bounds checks are performed!
     */
    double dAvg(int dt){
        avgHistory.size()<2 ? (avgHistory ? avgHistory[0] : 0.0) : avgHistory[dt] - avgHistory[dt+1]
    }

    /**
     * dVariance(dt) = variance(t-dt) - variance(t-dt-1)
     * No bounds checks are performed!
     */
    double dVariance(int dt){
        varianceHistory.size()<2 ? (varianceHistory ? varianceHistory[0] : 1.0 ) : varianceHistory[dt] - varianceHistory[dt+1]
    }
}