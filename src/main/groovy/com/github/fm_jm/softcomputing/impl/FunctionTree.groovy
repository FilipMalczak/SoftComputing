package com.github.fm_jm.softcomputing.impl

import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.heuristics.Specimen
import com.github.fm_jm.softcomputing.impl.fun.Node

import groovy.transform.Canonical

import static java.lang.Math.pow

@Canonical(includes = ["c", "root"])
class FunctionTree implements Specimen, Serializable{
    double c
    Node root

    /**
     * TODO: For now evaluation is just mean-square error multiplied by cost (which for now is 1, see Node.getCost())
     * In future we can introduce evaluation based on tree depth, cost, etc.
     */
    @Override
    double evaluate(Context context) {
//        double out = root.cost + context.points.collect { Map<String, Double> point ->
//            squaredError(point, context.valueSetName) / context.points.size()
//        }.sum()
        List<Double> errors = context.points.collect { Map<String, Double> point ->
            squaredError(point, context.valueSetName) / context.points.size()
        }
        double out = root.cost + errors.sum()
        if (Double.isInfinite(out)){
//            println "infinity $this"
//            println errors
            return Double.MAX_VALUE
        }
        if (Double.isNaN(out)){
            println "NaN $this"
            println errors
        }
        out
    }

    double value(Map<String, Double> point){
        double out = c*root.evaluate(point)
        if (Double.isNaN(out)){
//            println "NaN $this for point $point"
            return 0.0
        }
        if (Double.isInfinite(out)){
//            println "Infinity $this for point $point"
            if (out>0)
                return Double.MAX_VALUE
            return Double.MIN_VALUE
        }
        out
    }

    /**
     * See package info for interpretation of point and valueSetName.
     */
    private double squaredError(Map<String, Double> point, String valueSetName){
        pow( point[valueSetName] - value(point), 2)
    }

    @Override
    Specimen copy() {
        new FunctionTree(
            c,
            root.copy()
        )
    }

    /**
     * Vector is gathered by searching tree depth-first.
     */
    List<Double> getConstantsVector(){
        [c] + root.constantsVector
    }

    void setConstantsVector(List<Double> vector){
        c = vector.head()
        def tail = vector.tail()
        root.constantsVector = tail
        assert "Provided vector had too many elements!" && tail.empty
    }

    int getDepth(){
        1 + root.depth
    }

    String toString(){
        return "$c * $root"
    }

}
