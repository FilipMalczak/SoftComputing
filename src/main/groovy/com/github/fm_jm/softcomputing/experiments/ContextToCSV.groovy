package com.github.fm_jm.softcomputing.experiments

import com.github.fm_jm.softcomputing.experiments.impl.ResultStorage
import com.github.fm_jm.softcomputing.experiments.impl.Utils
import com.github.fm_jm.softcomputing.heuristics.Context

def fromCSVKey(String csvKey){
    csvKey.replaceAll(",", ".").replaceAll("\t", ";")
}

key = fromCSVKey "exp,csv\t0\t0,05\t2\t100,0\t1,0\t3\t2\t10\t400\t400\t10000,0\t100\t50\t800\t10\t300\t10"
out = new File("./singleResult.csv")
Context ctx = ResultStorage.instance.load(key)
if (ctx!=null) {
    out.text=""
    Utils.writeCsv(ctx, {out.append("$it\n")})
} else {
    println "No such context: $key"
}
