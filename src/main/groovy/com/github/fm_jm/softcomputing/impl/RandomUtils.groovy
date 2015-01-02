package com.github.fm_jm.softcomputing.impl


class RandomUtils {
    static Random r = new Random()

    static boolean happens(int probability) {
        Math.round(r.nextDouble()*1000) < probability
    }

    static <S> S random(List<S> population) {
        population[r.nextInt(population.size())]
    }

    static double random(double from, double to){
        r.nextDouble()*(to-from)+from
    }

    static List<Boolean> randomBools(int size, int prob){
        (1..size).collect { happens(prob) }
    }
}
