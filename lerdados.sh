#!/bin/bash
i=1
while read line
do
    name=$line
    if [ "$name" = "" ]
    then
      continue;
    fi
    if [ $i -eq 1 ]
    then
      num=$name
    fi
    if [ $i -eq 2 ]
    then
      nome=$name
    fi
    if [ $i -eq 3 ]
    then
      preco=$name
      echo "produtos.add(new Produto($num, \"$nome\", ${preco}f));"
      i=0
    fi
    i=$((i+1))
done < $1

