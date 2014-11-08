package com.github.whatever.softcomputing.impl

import groovy.transform.Canonical

@Canonical
class Node {
    Function foo
    /**
     * List of Nodes, Vars or doubles.
     */
    List args

    double evaluate(Map<String, Double> varVals){
        def argsValues = args.collect {
            switch (it) {
                case Double: return it
                case Var: assert varVals.containsKey(it.name)
                    return varVals[it.name]
                case Node: return it.evaluate(varVals)
                default: assert "WTF is this?" && false
            }
        }
        FunctionsDefinitions.execute(foo, argsValues)
    }

    /**
     * TODO: implement something more useful
     * DO NOT USE THIS IN EVALUATION! THIS SHOULD BE USED BY FunctionTree!
     * It still may depend of args and their cost.
     */
    protected double getCost(){
        1
    }
}
