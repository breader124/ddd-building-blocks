## What you can find here?
This repository contains implementation of tactical DDD building blocks. I decided to try to implement playing on the guitar, but not in standard, ordinary way 
starting from structure modelling. When you track commits history, you can see, that firstly I discovered (during Design Level Event Storming session) and grouped business rules, which need to always stay consistent together. They are called **invariants** and gathered create **aggregate** ('aggregate' word can be misleading for some people, it's crucial to remember that this block aggregates **invariants** on the first place, not any kind of data). 

## Problem I tried to model
Guitarist during concert, firstly warming up at the backstage following his standard warmup routine to finally play all songs from set list for all audience.

## Outcome of Design Level Event Storming (with legend attached)
![Design Level Event Storming diagram](https://github.com/breader124/ddd-building-blocks/blob/master/guitar_dles.drawio.png)

## Why did I try this way?
Maybe you're wondering what's wrong with standard approach? Answer is pretty simple this time: nothing. Presented solution is just another approach to the problem, but indeed it has great advantages. As so called **invariants** don't sound pretty clear when you see the name for the first time, let's try to express sense of grouping business rules other way. We don't want to listen to the song played on out-of-tune guitar, right? We also don't want to hear Master of Puppets played without distortion, right? And last thing, everyone, who had a pleasure to touch electric guitar, knows there's big difference between your performance as a guitar player whether you're warmed up or not. Whenever we're sure all 3 mentioned rules are satisfied, we can be also sure that guitar (and guitar player) is ready to shred. It's much more difficult to achieve the same goal if you start not by defining mentioned rules, but by implementing how guitar is built.

## Boundaries
Please also notice that Guitar aggregate doesn't contain actions like changing the strings or setting guitar up. Whoever plays guitar wouldn't say that shredding solo from The Trooper is the same category of action as replacing shabby saddle with another, brand new one. Trying to set correct boundaries is really challenging activity, but worth all your effort. This way we can keep our code clean and building blocks small, what on the other hand makes it simpler to stay in line with Single Responsibility Principle.

_Please note the project is being actively developed and might change from day to day. If you want to proceed on your own, just checkout to particular
commit and fork repo._

## What I've observed and learned
- Implementing optimistic concurrency in case of event sourcing is not a trivial thing to achieve and should be designed better than I did. For this project it's clearly visible it's not a thing, that received enough focus on right development stage and that's why it's done poorly taking design into consideration. On the other hand, it seems to be working solution, so it can be taken as starting point for further improvements
- It's challenging to enrich events properly, when I tried to keep domain events perfectly clear. That means that I wanted them to contain only their type and business related information and I managed to achieve it before the stage of implementing optimistic concurrency, when I was forced to introduce aggregate version to domain event structure. That's definitely the point to be improved when perfect design is a must (AND IT ALWAYS IS!).
- Replaying state of aggregate from events is really as handy thing as it's described in many articles somehow touching event sourcing topic. It's great way of persisting data, but...
- this project doesn't contain any kind of read endpoints. Whenever I'd want to expose them, then the problem of fast reads arises. It's a huge point on design roadmap.
- Actually implementing an idea of event sourcing showed me that even if I was concinced I understand it, then there are still many gaps to be filled in my knowledge, so I think I can close this sandbox side project marking it as a success -- I learned something and realized there's still a lot to learn :)
