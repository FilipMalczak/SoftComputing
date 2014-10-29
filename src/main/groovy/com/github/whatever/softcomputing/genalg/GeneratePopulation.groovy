package com.github.whatever.softcomputing.genalg


public interface GeneratePopulation<S extends Specimen> {
    List<S> generate(int size)
}