package com.github.fm_jm.softcomputing.sa.operators

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.heuristics.Specimen


public interface NeighbourSelector<S extends Specimen> {

    S findNeighbour(S current, double temperature, Context context)

}