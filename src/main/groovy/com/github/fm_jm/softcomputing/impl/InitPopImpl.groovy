package com.github.fm_jm.softcomputing.impl

import com.github.fm_jm.softcomputing.ga.alg.GeneratePopulation

/* TODO: implement me! */
class InitPopImpl implements GeneratePopulation<FunctionTree>{

    static final int MIN_DEPTH = 3

    @Override
    List<FunctionTree> generate(int size) { // dodać kontekst
        // równo każdą funkcje, losowe wartości liczbowe z [-100, 100]
        // równo każdą zmienną używać
        // depth >= 2, najlepiej koło 4 - static final int
        return null
    }


}
