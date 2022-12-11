Posix : set of standards that specify how unix like operating systems should work, posix compliance ensures source code compatiblity across unix like systems.  

some posix standards include :  

## The Posix C library 
Extends the ansi c library with some additional features such as :
- file ops : `mkdir, dirname ..`.
- processes and threads : `pthread, fork,execl...`
- networking primitives : `socket...`
- memory management
- utilities

## cli utilities
ls, mkdir, echo, many of which are a front end to the apis in the c library

## Shell lang
Bash is a posix compliant shell lang for exmpl

## Env variables

## Program exit status
Posix adds a few env variables, such as 126 for `command found but not executable`...

## Regex
There are 2 types of regex, `BRE : basic regex` and `ERE : extended regex`, basic is deprecated but kept for backwards compatibilty,grep for examples works with BRE by default but can use ERE with the -E flag

## Directory structure and file names
using / as the delimiter, NUL cannot be used as a filename, . is cwd and .. is parent
