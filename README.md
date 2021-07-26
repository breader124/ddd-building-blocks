# Description
This repository contains implementation of tactical DDD building blocks. I decided to try to implement playing on the guitar, but not in standard, ordinary way 
starting from structure modelling. When you track commits history, you can see, that firstly I created behavior skeleton. Those specified bahaviors are so called
**invariants**, which need to play together creating **aggregate**. We don't want to listen to the song played on out-of-tune guitar, right? Please also 
notice that Guitar aggregate doesn't contain actions like changing the strings or setting guitar up. Whoever plays guitar wouldn't say that shredding solo from The 
Trooper is the same category of action as replacing shabby saddle with another, brand new one.

_Please note the project is being actively developed and might change from one day to another. If you want to proceed on your own, just checkout to particular
commit and fork repo._
