package com.github.fm_jm.softcomputing

import com.github.fm_jm.softcomputing.experiments.impl.Utils
import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.impl.ContextLoader


class Main {

    public static void main(String[] args){
        assert (args.size() >= 1)
        String problemPath = args[0]
        String outPath = null
        if (args.size() > 1)
            outPath = args[1]

        Context problem = ContextLoader.loadFromFile(new File(problemPath))
        def ga = Utils.semiGA(200, 5, 750, 10, 200, 10)

        ga.doRun(problem)

        if (outPath) {
            File out = new File(outPath)
            out.text = ""
            Utils.writeCsv(problem, { out.append("$it\n") })
        }

        Utils.writeCsv(problem, {println it})

    }
}
