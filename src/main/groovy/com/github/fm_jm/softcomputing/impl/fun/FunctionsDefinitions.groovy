package com.github.fm_jm.softcomputing.impl.fun

import groovy.transform.Memoized

import static Function.*
import static java.lang.Math.abs
import static java.lang.Math.cos
import static java.lang.Math.exp
import static java.lang.Math.log
import static java.lang.Math.signum
import static java.lang.Math.sin
import static java.lang.Math.sqrt
import static java.lang.Math.tanh

/**
 * Don't memoize LOGIC values - memoization happens at execute(Function, List<Double>)
 */
class FunctionsDefinitions {

    static final Map<Function, Closure<Double>> LOGIC = [
        (SUM): { List<Double> args -> args.sum() },
        (PRODUCT): { List<Double> args -> args.inject { acc, val -> acc * val } },
        (SQRT): { List<Double> args -> args.head() > 0 ? sqrt(args.head()) : 0.0 },
        (LN): { List<Double> args -> args.head() > 0 ? log(args.head()) : 0.0 },
        (LESS_THAN): { List<Double> args -> args[0]<args[1] ? 1.0 : 0.0 },
        (COS): { List<Double> args -> cos(args.head())},
        (SIN): {List<Double> args -> sin(args.head())},
        (DIV): {List<Double> args -> args[1] != 0 ? args[0]/args[1] : 0.0 },
        (SUBTRACT): {List<Double> args -> args.inject { acc, val -> acc - val}},
        (EXP): {List<Double> args -> exp(args.head())},
        (ABS): {List<Double> args -> abs(args.head())},
        (MAX): {List<Double> args -> args.max()},
        (MIN): {List<Double> args -> args.min() },
        (SIGN): {List<Double> args -> signum(args.head())},
        (TANH): {List<Double> args -> tanh(args.head())}

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
        (LESS_THAN): 2,
        (COS): 1,
        (SIN): 1,
        (DIV): 2,
        (SUBTRACT): -1,
        (EXP): 1,
        (ABS): 1,
        (MAX): -1,
        (MIN): -1,
        (SIGN): 1,
        (TANH): 1
    ].asImmutable()

    /**
     * Costs of primitive funtions, as calculated by Streeter in 'AUTOMATED DISCOVERY OF NUMERICAL APPROXIMATION FORMULAE
     * VIA GENETIC PROGRAMMING'
     */
    static final Map<Function, Double> FUN_COSTS = [
            (SUM): 0.1,
            (PRODUCT): 1.0,
            (SQRT): 10.0,
            (LN): 10.0,
            (LESS_THAN): 0.1,
            (COS): 10.0,
            (SIN): 10.0,
            (DIV): 1.0,
            (SUBTRACT): 0.1,
            (EXP): 10.0,
            (ABS): 0.1,
            (MAX): 0.1,
            (MIN): 0.1,
            (SIGN): 0.1,
            (TANH): 10.0
    ].asImmutable()

    static void assertArgCount(Function foo, int argsCount){
        if (ARGS_COUNTS[foo]>0)
            assert ARGS_COUNTS[foo] == argsCount
    }
}
