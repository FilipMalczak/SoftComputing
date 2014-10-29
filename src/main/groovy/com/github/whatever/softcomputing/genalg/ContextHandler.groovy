package com.github.whatever.softcomputing.genalg


interface ContextHandler<S extends Specimen> {
    void update(List<S> population, int generation, Map context)
}
