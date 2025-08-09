# ev3 clojure 

This project aims to leverage ev3-lang-java, but with the ability to 'drive' things from a repl - pun intended!

I am aware of [clj-ev3dev](https://github.com/annapawlicka/clj-ev3dev/tree/master), but I just
want a repl and to use java interop... simples.

## usage

* setup [ev3dev](https://www.ev3dev.org/docs/getting-started/)
* makefile cmd to create uber and copy it to device
* makefile cmd to connect

```shell
robot@ev3dev:~$ java -jar lib1-standalone.jar 
```
* this may take minutes to connect. you will see 'starting' printed eventually
* connect socket repl 192.168.2.2:7091
  * may take minutes before you see prompt
* see play ns. load it into repl. may take time...
* run cmds!
 
