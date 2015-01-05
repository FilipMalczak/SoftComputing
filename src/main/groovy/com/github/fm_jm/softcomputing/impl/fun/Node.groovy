package com.github.fm_jm.softcomputing.impl.fun

import groovy.transform.Canonical

@Canonical
class Node implements Serializable {
    Function foo
    /**
     * List of Nodes, Vars or doubles.
     */
    List args

    double evaluate(Map<String, Double> varVals){
        def argsValues = args.collect {
            switch (it) {
                case Number: return it
                case Var: assert varVals.containsKey(it.name)
                    return varVals[it.name]
                case Node: return it.evaluate(varVals)
                default: assert "WTF is this?" && it && false
            }
        }
        FunctionsDefinitions.execute(foo, argsValues)
    }

    /**
     * TODO: implement something more useful
     * DO NOT USE THIS IN EVALUATION! THIS SHOULD BE USED BY FunctionTree!
     * It still may depend of args and their cost.
     */
    double getCost(){
        def argsCosts = args.collect {
            switch(it) {
                case Number: return 0
                case Var: return 0
                case Node: return it.cost
                default: assert "Unknown thing" && it && false
            }
        }
        return FunctionsDefinitions.FUN_COSTS[foo] + argsCosts.sum()
    }

    int getDepth(){
        def childNodes = args.findAll { it instanceof Node }
        return 1 + (childNodes ? childNodes.max { Node node -> node.depth }.depth : 0)
    }

    /**
     * The same contract as specified in Specimen.
     */
    Node copy(){
        new Node(
            foo,
            args.collect { it instanceof Node ? it.copy() : it }
        )
    }

    /**
     * Vector is gathered by searching tree depth-first.
     */
    List<Double> getConstantsVector(){
        def out = []
        args.each {
            if (it instanceof Number)
                out << it
            else if (it instanceof Node)
                out += it.constantsVector
        }
        return out
    }

    /**
     * WARNING! This method modified its argument!
     * FunctionTree delegates here, but only tail of vector used there, so it is new list, and it has no side effects.
     */
    void setConstantsVector(List<Double> vector){
        args.size().times { int idx ->
            if (args[idx] instanceof Number) {
                args[idx] = vector.head()
                vector.remove(0)
            } else if (args[idx] instanceof Node) {
                args[idx].constantsVector = vector
            }
        }
    }

    String toString(){
        "$foo(${args.collect {"$it"}.join(", ")})"
//        StringBuilder sb = new StringBuilder("$foo(")
//        args.each {
//            sb.append("$it, ")
//        }
//        sb.delete(sb.size()-2, sb.size())
//        sb.append(")")
//        return sb.toString()
    }
}
