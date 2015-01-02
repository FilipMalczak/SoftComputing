package com.github.fm_jm.softcomputing.impl.operators

import com.github.fm_jm.softcomputing.ga.operators.CrossoverOperator
import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.impl.FunctionTree
import com.github.fm_jm.softcomputing.impl.fun.Node
import static com.github.fm_jm.softcomputing.impl.RandomUtils.*

class SubTreeInjectingCrossover implements CrossoverOperator<FunctionTree> {
    @Override
    List<FunctionTree> crossOver(FunctionTree s1, FunctionTree s2, Context context) {
        int cutDepth1 = random(s1.root.depth-1)
        int cutDepth2 = random(s2.root.depth-1)
        crossAtDepths(s1, s2, cutDepth1, cutDepth2)
    }

    List<FunctionTree> crossAtDepths(FunctionTree f1, FunctionTree f2, int d1, int d2){
        FunctionTree out1 = f1.copy()
        FunctionTree out2 = f2.copy()
        Node n1 = out1.root
        Node n2 = out2.root
        Node parent1 = n1
        Node parent2 = n2


        (d1-1).times {
            parent1 = n1
            n1 = random(n1.args.findAll { it instanceof Node })
        }
        (d2-1).times {
            parent2 = n2
            n2 = random(n2.args.findAll { it instanceof Node })
        }
        parent1.args = parent1.args.collect { if (it.is(n1)) n2 else it }
        parent2.args = parent2.args.collect { if (it.is(n2)) n1 else it }
        [out1, out2]
    }
}
