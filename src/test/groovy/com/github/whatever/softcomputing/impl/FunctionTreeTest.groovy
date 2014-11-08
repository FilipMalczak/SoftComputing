package com.github.whatever.softcomputing.impl

import org.junit.Before
import org.junit.Test


class FunctionTreeTest extends GroovyTestCase {
    FunctionTree tree
    FunctionTree modified

    @Before
    void setUp(){
        // 1  * (1 + x + 2y)
        tree = new FunctionTree(
            1.0,
            new Node(
                Function.SUM,
                [
                    1.0,
                    Var.forName("x"),
                    new Node(
                        Function.PRODUCT,
                        [
                            2.0,
                            Var.forName("y")
                        ]
                    )
                ]
            )
        )
        // 2  * (3 + x + 4y)
        modified = new FunctionTree(
            2.0,
            new Node(
                Function.SUM,
                [
                    3.0,
                    Var.forName("x"),
                    new Node(
                        Function.PRODUCT,
                        [
                            4.0,
                            Var.forName("y")
                        ]
                    )
                ]
            )
        )

    }

    @Test
    void testTree(){
        assert tree.depth == 3
        assert tree.evaluate([
            domainNames: [ "x", "y" ],
                 valueSetName: "z",
                 points: [
                     [ x: 1, y:1, z: 4 ],
                     [ x: 2, y:1, z: 5 ],
                     [ x: 1, y:5, z: 12 ],
                     [ x: 1, y:-1, z: 0 ]
                 ]
            ]) == 0
        assert tree.constantsVector == [1.0, 1.0, 2.0]
        tree.constantsVector = [2.0, 3.0, 4.0]
        assert tree == modified
    }
}
