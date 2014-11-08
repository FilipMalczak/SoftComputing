package com.github.whatever.softcomputing.impl

import com.github.whatever.softcomputing.genalg.Specimen

import groovy.transform.Canonical

import static java.lang.Math.pow

@Canonical
class FunctionTree implements Specimen{
    double c
    Node root

    /**
     * TODO: For now evaluation is just mean-square error multiplied by cost (which for now is 1, see Node.getCost())
     * In future we can introduce evaluation based on tree depth, cost, etc.
     */
    @Override
    double evaluate(Map context) {
        root.cost * context.points.collect { Map<String, Double> point ->
            squaredError(point, context.valueSetName)
        }.sum() / context.points.size()
    }

    /**
     * See package info for interpretation of point and valueSetName.
     */
    private double squaredError(Map<String, Double> point, String valueSetName){
        pow( point[valueSetName] - c*root.evaluate(point), 2)
    }

    @Override
    Specimen copy() {
        return null
    }
}
