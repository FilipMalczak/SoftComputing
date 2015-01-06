package com.github.fm_jm.softcomputing.experiments.impl

import groovy.transform.Canonical
import groovy.util.logging.Slf4j

@Canonical
@Slf4j
class DirBasedExperimentSetup extends ExperimentSetup{
    File dir

    String currentKey

    @Override
    void eachConfig(Closure c) {
        dir.eachFileMatch({
            log.info("Matching $it with class ${it.class}")
            it!="CURRENT.obj"
        }, {
            log.info("it= $it of class ${it.class}")
            currentKey = it.name.replaceAll("[.]obj", "")
            c()
        })

    }
}
