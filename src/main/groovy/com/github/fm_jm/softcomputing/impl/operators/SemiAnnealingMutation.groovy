package com.github.fm_jm.softcomputing.impl.operators

import com.github.fm_jm.softcomputing.ga.operators.MutationOperator
import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.impl.FunctionTree
import com.github.fm_jm.softcomputing.impl.fun.Function
import com.github.fm_jm.softcomputing.impl.fun.FunctionsDefinitions
import com.github.fm_jm.softcomputing.impl.fun.Node
import com.github.fm_jm.softcomputing.sa.SimulatedAnnealing
import groovy.transform.Canonical

import static com.github.fm_jm.softcomputing.impl.RandomUtils.*
import static com.github.fm_jm.softcomputing.experiments.Constants.*


@Canonical
class SemiAnnealingMutation implements MutationOperator<FunctionTree> {

    SimulatedAnnealing sa

    @Override
    List<FunctionTree> mutate(FunctionTree functionTree, Context context) {
        if(happens(ANNEALING_CHANCE))
            return anneal(functionTree, context)
        return swap(functionTree)
    }

    List<FunctionTree> anneal(FunctionTree functionTree, Context context) {
        sa.doRun(functionTree, context)
    }

    List<FunctionTree> swap(FunctionTree functionTree) {
        FunctionTree out = (FunctionTree)functionTree.copy()
        Node changed = randomFooNode(out)
        int argc = FunctionsDefinitions.ARGS_COUNTS[changed.foo]
        int children = changed.args.size()
        Function newFoo = random(FunctionsDefinitions.ARGS_COUNTS.findAll {k, v ->
            (v == argc) /*|| (v == -1) || (v == children)*/
        }.keySet().toList())
        changed.foo = newFoo
        [out]
    }
}
