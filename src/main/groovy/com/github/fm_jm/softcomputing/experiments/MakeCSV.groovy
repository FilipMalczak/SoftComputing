package com.github.fm_jm.softcomputing.experiments

import com.github.fm_jm.softcomputing.experiments.impl.DirBasedExperimentSetup

new DirBasedExperimentSetup(
    new File("./results")
).toCSV(new File("./results.csv"))
