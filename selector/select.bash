#!/bin/bash

### Some commands used to clean the original documentationOutput file after selecting only columns 2 and 4
# 
# sed -i 's/,/./g' classMethodNames.csv
# sed -i '/(/!d' classMethodNames.csv
# sed -i '/"/d' classMethodNames.csv
# sed -i 's/\[.*//' classMethodNames.csv

while read line
do

    cp level24/output/*\.$line level-24-selected

done < uniqueMethodNames.txt