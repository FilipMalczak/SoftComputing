package com.github.fm_jm.softcomputing.heuristics

/**
 * It's not totally reusable, but will do.
 */
class Context<S extends Specimen> implements Serializable {
    // GA state
    int mutProb
    int crossProb

    //problem definition
    List<String> domainNames
    String valueSetName
    List<Map<String, Double>> points

    // fields for measuring time
    Date startTime = null
    Date endTime = null

    // history for analysis
    S globalBest = null

    List<Double> avgHistory = []
    List<Double> varianceHistory = []
    List<Double> bestHistory = []
    List<Double> worstHistory = []
    List<Double> CPHistory = []
    List<Double> MPHistory = []

    void pushAvg(double avg){
        avgHistory = [ avg ] + avgHistory
    }

    void pushVariance(double var){
        varianceHistory = [ var ] + varianceHistory
    }

    void pushBest(double best){
        bestHistory = [ best ] + bestHistory
    }

    void pushWorst(double worst){
        worstHistory = [ worst ] + worstHistory
    }

    void pushCP(double cp){
        CPHistory = [cp] + CPHistory
    }

    void pushMP(double mp){
        MPHistory = [mp] + MPHistory
    }



    /**
     * dAvg(dt) = avg(t-dt) - avg(t-dt-1)
     */
    double dAvg(int dt){
        dt+1<avgHistory.size() ?
            (avgHistory[dt] - avgHistory[dt + 1]) :
            (avgHistory.size()<2 ?
                (avgHistory.size() ? avgHistory[0] : 0.0) :
                avgHistory[-2] - avgHistory[-1] )
    }

    /**
     * dVariance(dt) = variance(t-dt) - variance(t-dt-1)
     */
    double dVariance(int dt){
        dt+1<varianceHistory.size() ?
            (varianceHistory[dt] - varianceHistory[dt + 1]) :
            (varianceHistory.size()<2 ?
                (varianceHistory.size() ? varianceHistory[0] : 1.0) :
                varianceHistory[-2] - varianceHistory[-1] )
    }
}