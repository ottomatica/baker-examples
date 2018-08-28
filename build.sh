#!/bin/bash

for D in `find . -depth 1 -type d | grep -v .git`
do
    echo "Destroying $D";
    (cd $D && baker destroy)
    echo "Baking $D";
    # provide input for hibernate-spring prompt
    (cd $D && echo 326 | baker bake)
    echo "Destroying $D";
    (cd $D && baker destroy)
done