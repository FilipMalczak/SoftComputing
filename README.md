Oblicz. miękkie-metody i zast.
==============================

Kod programu na którym opiera się projekt.

Grupa zajęciowa
---------------

Rok akademicki 2014/2015 

Semestr zimowy 

Wt. 9:15

Autorzy
-------

* Jacek Miszczak (179158)
* Filip Malczak (179326)

> Following text should not be graded or taken under any consideration while grading. These are only developer notes.

Dev Notes
---------

*Following proposals are implemented in this version. Feel free to comment and not agree. Proposal: comments as lists under
each proposal; it would (hopefully) leave nice thought stack.*

**Proposal:** Where possible, use list literals (ArrayLists are the fastest non-array linear data structure). ~F

**Proposal:** Represent evaluations as double and probability as integer (attribute of value x would mean probability of x/1000) ~F
 
**Note:** Components using Evolution Context (see dictionary for all capitalized terms) are not forced to work together 
    by any type-safety techniques. You can create ContextManager that will not provide informations needed by your 
    implementation of CP, and that is your fault. The contract makes keeping it consistent your duty. ~F  

Dictionary
----------

* Specimen - object in searched space (function tree i some form, in case of our problem)
* Population - list of _Specimen_ s
* Evaluation - function from searched space to real numbers (represented as double), which should be minimised
* Generation - number of processed population; number of genetic algorithms iteration
* CP - crossover probability; may change with each generation
* MP - mutation probability; may change with each generation
* (Evolution) Context - namespace to keep all statistics on past and current populations, for parameter adaptation sake
* Context handler - piece of logic that takes care of filling context with statistics at the beginning of each GA iteration.

TODO
----

- Fix the nullpointer in context. After all, bounds checks should be performed, we've got problems during early generations
- Basically, make it work without throwing up. Run Example3.groovy and follow the exceptions, chosen one!
    - Oh it's bad luck to be you, chosen one of many is you!
- Figure out what to do with functions cost
- Make it find approximations more useful than MAX(x, x, x, x, x, x, 69, x, x, DIV(x,x))
- Implement some testing procedure that will save results in a nice CSV
- Perhaps some kind of experimentation procedure in order to find the best parameter values?