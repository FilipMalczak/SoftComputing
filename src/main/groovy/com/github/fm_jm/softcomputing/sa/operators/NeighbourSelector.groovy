package com.github.fm_jm.softcomputing.sa.operators

import com.github.fm_jm.softcomputing.ga.Specimen


public interface NeighbourSelector<S extends Specimen> {

    S findNeighbour(S current, double temperature, Map context)

}