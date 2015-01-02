package com.github.fm_jm.softcomputing.impl.operators

import com.github.fm_jm.softcomputing.impl.FunctionTree
import com.github.fm_jm.softcomputing.impl.fun.Function
import com.github.fm_jm.softcomputing.impl.fun.Node
import com.github.fm_jm.softcomputing.impl.fun.Var


class SubTreeInjectingCrossoverTest extends GroovyTestCase {
    SubTreeInjectingCrossover crossover

    void setUp(){
        crossover = new SubTreeInjectingCrossover()
    }

    void testCrossAtDepths() {

        assert crossover.crossAtDepths(
            //1 * cos(1 + a + cos(b) + b)) ; depth = 4
            new FunctionTree(
                1,
                new Node(
                    Function.COS, [
                        new Node( // cut here
                            Function.SUM,
                            [
                                1,
                                Var.forName("A"),
                                new Node(
                                    Function.COS,
                                    [
                                        Var.forName("B")
                                    ]
                                ),
                                Var.forName("B")
                            ]
                        )
                    ]
                )
            ),
            // 3* exp(a+b+c+sin(x+exp(y)) ; depth=6
            new FunctionTree(
                3,
                new Node(
                    Function.EXP,
                    [
                        new Node(
                            Function.SUM,
                            [
                                Var.forName("A"),
                                Var.forName("B"),
                                Var.forName("C"),
                                new Node( // cut here
                                    Function.SIN,
                                    [
                                        new Node(
                                            Function.SUM,
                                            [
                                                Var.forName("X"),
                                                new Node(
                                                    Function.EXP,
                                                    [
                                                        Var.forName("Y")
                                                    ]
                                                )
                                            ]
                                        )
                                    ]
                                )
                            ]
                        )
                    ]
                )
            ),
            2,
            3
        ) == [
            //1 * cos(sin(x+exp(y))) ; depth = 4
            new FunctionTree(
                1,
                new Node(
                    Function.COS, [
                    new Node( // cut here
                        Function.SIN,
                        [
                            new Node(
                                Function.SUM,
                                [
                                    Var.forName("X"),
                                    new Node(
                                        Function.EXP,
                                        [
                                            Var.forName("Y")
                                        ]
                                    )
                                ]
                            )
                        ]
                    )
                ]
                )
            ),
            // 3* exp(a+b+c+(1 + a + cos(b) + b)) ; depth=6
            new FunctionTree(
                3,
                new Node(
                    Function.EXP,
                    [
                        new Node(
                            Function.SUM,
                            [
                                Var.forName("A"),
                                Var.forName("B"),
                                Var.forName("C"),
                                new Node( // cut here
                                    Function.SUM,
                                    [
                                        1,
                                        Var.forName("A"),
                                        new Node(
                                            Function.COS,
                                            [
                                                Var.forName("B")
                                            ]
                                        ),
                                        Var.forName("B")
                                    ]
                                )
                            ]
                        )
                    ]
                )
            )
        ]
    }
}
