package com.github.fm_jm.softcomputing.impl.fun

import groovy.transform.Memoized

import static Function.*
import static java.lang.Math.log
import static java.lang.Math.sqrt

/**
 * Don't memoize LOGIC values - memoization happens at execute(Function, List<Double>)
 */
class FunctionsDefinitions {

    static final Map<Function, Closure<Double>> LOGIC = [
        (SUM): { List<Double> args -> args.sum() },
        (PRODUCT): { List<Double> args -> args.inject { acc, val -> acc * val } },
        (SQRT): { List<Double> args -> sqrt(args.head()) },
        (LN): { List<Double> args -> log(args.head()) },
        (LESS_THAN): { List<Double> args -> args[0]<args[1] ? 1.0 : 0.0 }
    ].asImmutable()

    @Memoized
    static double execute(Function foo, List<Double> args){
        assertArgCount(foo, args.size())
        LOGIC[foo](args)
    }

    /**
     * If value is greater than 0, then function must take exactly tat number of arguments. Else, it may take any
     * number of them.
     */
    static final Map<Function, Integer> ARGS_COUNTS = [
        (SUM): -1,
        (PRODUCT):-1 ,
        (SQRT): 1,
        (LN): 1,
        (LESS_THAN): 2
    ].asImmutable()

    static void assertArgCount(Function foo, int argsCount){
        if (ARGS_COUNTS[foo]>0)
            assert ARGS_COUNTS[foo] == argsCount
    }
}
