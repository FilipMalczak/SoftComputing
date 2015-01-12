package com.github.fm_jm.softcomputing.experiments

import com.github.fm_jm.softcomputing.experiments.impl.ResultStorage
import com.github.fm_jm.softcomputing.experiments.impl.Utils
import com.github.fm_jm.softcomputing.heuristics.Context

import static com.github.fm_jm.softcomputing.experiments.impl.Utils.fromCSVKey

key = "x2+x.csv;0;0.15;5;100.0;1.0;3;2;10;400;400;10000.0;100;50;800;10;300;10"
out = new File("./best_x2_x.csv")
Context ctx = ResultStorage.instance.load(key)
if (ctx!=null) {
    out.text=""
    Utils.writeCsv(ctx, {out.append("$it\n")})
} else {
    println "No such context: $key"
}
