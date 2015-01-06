package com.github.fm_jm.softcomputing.experiments

import com.github.fm_jm.softcomputing.experiments.impl.ResultStorage

import groovy.json.JsonBuilder


class ContextPeeker implements Runnable{

    String key = "exp.csv;0;0.05;2;100.0;1.0;3;2;10;400;400;10000.0;100;50;800;10;300;10"
//    String key = "CURRENT"

    @Override
    void run() {
        println new JsonBuilder( ResultStorage.instance.load(key) ).toPrettyString()
    }
}
