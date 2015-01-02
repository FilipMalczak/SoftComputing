package com.github.fm_jm.softcomputing.impl

import com.github.fm_jm.softcomputing.heuristics.Context


class RandomFunctionsGeneratorTest extends GroovyTestCase {
    Context context
    RandomFunctionsGenerator generator

    void setUp(){
        context = new Context()
        context.domainNames = [ "x", "y", "z" ]
        generator = new RandomFunctionsGenerator()
    }

    void testGenerate() {
        def result = generator.generate(10, context)
        assert result.size()==10
        result.each { FunctionTree tree ->
            assert tree.depth == generator.MIN_DEPTH
        }
    }
}
