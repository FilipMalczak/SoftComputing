/**
 * We have list of points in some real space.
 * Those points are represented as list under "points" key in algorithm context.
 * Each element of this list is map from Strings (dimension names - x, y, z, etc) to doubles (values in each dimension).
 *
 * Context also have field "domainNames" with list of domain dimension names (their order makes no difference, but
 * they will be loaded from file, probably CSV, so it would be nice to keep their order, to format result).
 *
 * Last important part of context is "valueSetName" which holds name of result dimension.
 *
 * For example, if we want to model function z = f(x, y) = 2x + 3y minimal context for some example samples:
 *
 * context = [
 *     domainNames: [ "x", "y" ],
 *     valueSetName: "z",
 *     points: [
 *         [ x: 1, y:1, z: 5 ],
 *         [ x: 2, y:1, z: 7 ],
 *         [ x: 1, y:5, z: 17 ],
 *         [ x: 1, y:-1, z: -1 ]
 *     ]
 * ]
 */
package com.github.whatever.softcomputing.impl;