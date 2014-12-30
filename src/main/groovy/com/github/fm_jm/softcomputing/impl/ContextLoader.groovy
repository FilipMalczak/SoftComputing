package com.github.fm_jm.softcomputing.impl

import com.github.fm_jm.softcomputing.heuristics.Context

import com.opencsv.CSVReader

class ContextLoader {

    static Context loadFromFile(File file){
        Context out = new Context()
        CSVReader reader = new CSVReader(file.newReader(), ";" as char)
        String[] header = reader.readNext()
        out.valueSetName = header.last()
        out.domainNames = header[0..-2].toList()
        out.points = []
        String[] line = reader.readNext()
        while (line) {
            Map<String, Double> points = [:]
            line.eachWithIndex { String entry, int i ->
                points[header[i]] = entry.toDouble()
            }
            out.points.add(points)
            line = reader.readNext()
        }
        out
    }

}
