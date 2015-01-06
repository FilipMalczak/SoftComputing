package com.github.fm_jm.softcomputing.impl.fun


class Var implements Serializable {
    private static Map<String, Var> vars = [:]

    final String name

    private static final long serialVersionUID = -1278776919563022478L;

    private Var(String name) {
        this.name = name
    }

    static Var forName(String name){
        if (!vars.containsKey(name))
            vars[name] = new Var(name)
        vars[name]
    }

    String toString(){
        return "$name"
    }
}
