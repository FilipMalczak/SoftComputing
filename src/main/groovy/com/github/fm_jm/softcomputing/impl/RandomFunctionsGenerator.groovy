package com.github.fm_jm.softcomputing.impl

import com.github.fm_jm.softcomputing.ga.alg.GeneratePopulation
import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.impl.fun.Function
import com.github.fm_jm.softcomputing.impl.fun.Node
import com.github.fm_jm.softcomputing.impl.fun.FunctionsDefinitions
import com.github.fm_jm.softcomputing.impl.fun.Var

class RandomFunctionsGenerator implements GeneratePopulation<FunctionTree>{

    static final int MIN_DEPTH = 3
    static final int  MAX_INITIAL_WIDTH = 10
    static final double EARLY_LEAF_PROB = 0.4
    static final double CONST_PROB = 0.4
    static final Random r = new Random()
    static final List<Function> functions = [*Function.values()]


    @Override
    List<FunctionTree> generate(int size, Context context) {
        assert size>0
        (1..size).collect { randomInstance(context) }
    }

    FunctionTree randomInstance(Context context){
        new FunctionTree(randomConst(), randomNode(context))
    }

    Node randomNode(Context context, int lvl=MIN_DEPTH-2){
        Function f = functions[r.nextInt(functions.size())]
        int argc = FunctionsDefinitions.ARGS_COUNTS[f]
        if (argc<=0)
            argc = 1+r.nextInt(MAX_INITIAL_WIDTH-1)
        List args
        if (lvl>0) {
            List<Boolean> early_leafes = [false] + (2..argc).collect { r.nextDouble()<EARLY_LEAF_PROB }
            Collections.shuffle(early_leafes)
            args = early_leafes.collect { it ? randomLeaf(context) : randomNode(context, lvl-1) }
        } else
            args = (1..argc).collect { randomLeaf(context) }
        new Node(foo: f, args: args)
    }

    def randomLeaf(Context context){
        r.nextDouble()<CONST_PROB ?
              randomConst()
            : randomVar(context)
    }

    Var randomVar(Context context){
        Var.forName(context.domainNames[r.nextInt(context.domainNames.size())])
    }

    double randomConst(){
        r.nextDouble()*200 - 100
    }

}
