package com.github.whatever.softcomputing.operators.mutation.annealing

import com.github.whatever.softcomputing.genalg.Specimen


public interface NeighbourSelector<S extends Specimen> {

    S findNeighbour(S current, double temperature, Map context)

}