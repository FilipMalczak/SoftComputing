package com.github.fm_jm.softcomputing.experiments

import com.github.fm_jm.softcomputing.heuristics.Context

import groovy.transform.Memoized

@Singleton
class ResultStorage {
    /**
     * Keys may be long - they will be parameters joined with ";".
     * Easiest way to store context will probably be serializing it and storing as byte blob.
     */
    void store(String key, Context context){
        String path = pathForKey(key)
        if (exists(key))
            new File(path).delete()
        def out = new ObjectOutputStream(new FileOutputStream(path))
        out.writeObject(context)
        out.close()
    }

    /**
     * If there is more efficient way to do this, do it in this way.
     * We memoize at most 2 entries, so we're sure, that this implementation of exists(String) will keep loaded data,
     * so following load(String) will retrieve if from cache (instead of executing something twice).
     */
    boolean exists(String key){
        load(key)!=null
    }

    /**
     * If there is no result for such key, should return null.
     * Memoization is for the sake of exists
     */
    @Memoized(maxCacheSize = 2)
    Context load(String key){
        File file = new File(pathForKey(key))
        if (!file.exists())
            return null
        def inp = new ObjectInputStream(new FileInputStream(file))
        Context out = inp.readObject() as Context
        inp.close()
        return out
    }

    String pathForKey(String key){
          return "results${File.separator}${key}.obj"
//        return this.class.classLoader.getResource("results"+File.separator+key+".obj").toString()
    }
}
