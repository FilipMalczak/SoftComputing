package com.github.fm_jm.softcomputing.heuristics

interface Specimen {
    /**
     * Evaluation function for minimalising task.
     * The lower evaluation function result, the better the specimen.
     *
     * <strong>MEMOIZE IT WHENEVER POSSIBLE!</strong>
     */
    double evaluate(Context context)

    /**
     * Cloneable has quite complicated contract and may have some weird behaviour in some cases. Use this to obtain
     * exact copy of this specimen (for example, if your operator wants to modify some specimen, instead of creating
     * new instance).
     *
     * <em>This should be called "clone", in spirit of "specimen" and "population", but it would be easily confused with
     * SDK clone, and may lead to some weird situations. Let's keep it ambiguous.</em>
     * @return As exact copy of this specimen as possible.
     */
    Specimen copy()
}
