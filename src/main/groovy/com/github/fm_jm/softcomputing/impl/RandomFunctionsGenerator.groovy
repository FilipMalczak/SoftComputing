package com.github.fm_jm.softcomputing.impl

import com.github.fm_jm.softcomputing.ga.alg.GeneratePopulation
import com.github.fm_jm.softcomputing.heuristics.Context
import com.github.fm_jm.softcomputing.impl.fun.Function
import com.github.fm_jm.softcomputing.impl.fun.Node
import com.github.fm_jm.softcomputing.impl.fun.FunctionsDefinitions
import com.github.fm_jm.softcomputing.impl.fun.Var
import static com.github.fm_jm.softcomputing.impl.RandomUtils.*


class RandomFunctionsGenerator implements GeneratePopulation<FunctionTree>{

    static final int MIN_DEPTH = 3
    static final int  MAX_INITIAL_WIDTH = 10
    static final int EARLY_LEAF_PROB = 400
    static final int CONST_PROB = 400
    static final List<Function> functions = [*Function.values()]
    static final double MAX_AVG_FOR_RESULT = 10000.0;

    @Override
    List<FunctionTree> generate(int size, Context context) {
        assert size>0
        List<FunctionTree> out
        while (out = generatePossiblyWrong(size, context))
            if (SimpleContextHandler.avgEval(out, context)<MAX_AVG_FOR_RESULT) // this should be elsewhere, but screw it
                return out
    }



    List<FunctionTree> generatePossiblyWrong(int size, Context context) {
        (1..size).collect { randomInstance(context) }
    }

    FunctionTree randomInstance(Context context){
        new FunctionTree(randomConst(), randomNode(context))
    }

    Node randomNode(Context context, int lvl=MIN_DEPTH-2){
        Function f = random(functions)
        int argc = FunctionsDefinitions.ARGS_COUNTS[f]
        if (argc<=0)
            argc = 1+r.nextInt(MAX_INITIAL_WIDTH-1)
        List args
        if (lvl>0) {
            List<Boolean> early_leaves = [false] +randomBools(argc-1, EARLY_LEAF_PROB)
            Collections.shuffle(early_leaves)
            args = early_leaves.collect { it ? randomLeaf(context) : randomNode(context, lvl-1) }
        } else
            args = (1..argc).collect { randomLeaf(context) }
        new Node(foo: f, args: args)
    }

    def randomLeaf(Context context){
        happens(CONST_PROB) ?
              randomConst()
            : randomVar(context)
    }

    Var randomVar(Context context){
        Var.forName(context.domainNames[r.nextInt(context.domainNames.size())])
    }

    double randomConst(){
        random(-1.0, 1.0)
    }

}
