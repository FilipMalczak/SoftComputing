package com.github.whatever.softcomputing.genalg


interface StopCondition<S extends Specimen> {
    boolean shouldStop(List<S> population, int generation, Map context)
}