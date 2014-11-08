package com.github.whatever.softcomputing.impl


class Var {
    private static Map<String, Var> vars = [:]

    final String name

    private Var(String name) {
        this.name = name
    }

    static Var call(String name){
        if (!vars.containsKey(name))
            vars[name] = new Var(name)
        vars[name]
    }
}
