#!/bin/sh

filename=$1

tsc "$filename.ts"
node "$filename.js"