package com.github.fm_jm.softcomputing

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.impl.ContextLoader

File file = new File(this.class.classLoader.getResource("small_sqrt.csv").toURI())

Context loaded = ContextLoader.loadFromFile(file)

def ga = Utils.defaultGA(50, 10, 750, 10, 200, 10)

def pop = ga.doRun(loaded)

Utils.writeCsv(loaded, this.&println)