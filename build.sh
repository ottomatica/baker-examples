#!/bin/bash

for D in `find . -depth 1 -type d | grep -v .git`
do
    echo "Destroying $D";
    (cd $D && baker destroy)
done

for D in `find . -depth 1 -type d | grep -v .git`
do
    echo "Baking $D";
    (cd $D && baker bake)
done