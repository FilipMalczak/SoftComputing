package com.github.fm_jm.softcomputing.experiments

import com.github.fm_jm.softcomputing.experiments.impl.ResultStorage

import groovy.json.JsonBuilder

import static com.github.fm_jm.softcomputing.experiments.impl.Utils.fromCSVKey

class ContextPeeker implements Runnable{

//    String key = "exp.csv;0;0.05;2;100.0;1.0;3;2;10;400;400;10000.0;100;50;800;10;300;10"
    String key = "CURRENT"
//    String key = fromCSVKey "exp,csv\t0\t0,05\t2\t100,0\t1,0\t3\t2\t10\t400\t400\t10000,0\t100\t50\t800\t10\t300\t10"

    @Override
    void run() {
        def ctx = ResultStorage.instance.load(key)
        println ctx.globalBest.toString()
        println ctx.globalBest.evaluate(ctx)
        println new JsonBuilder( ctx ).toPrettyString()
    }
}
