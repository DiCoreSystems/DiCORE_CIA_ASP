#const j = 8.
step(0..j).

fluent(inertial, 1).
holds(1,I+1) :- 
           fluent(inertial, 1), 
           holds(1,I),
           not -holds(1,I+1), step(I).
-holds(1,I+1) :- 
           fluent(inertial, 1), 
           -holds(1,I),
           not holds(1,I+1), step(I).

fluent(inertial, 2).
holds(2,I+1) :- 
           fluent(inertial, 2), 
           holds(2,I),
           not -holds(2,I+1), step(I).
-holds(2,I+1) :- 
           fluent(inertial, 2), 
           -holds(2,I),
           not holds(2,I+1), step(I).

fluent(inertial, 3).
holds(3,I+1) :- 
           fluent(inertial, 3), 
           holds(3,I),
           not -holds(3,I+1), step(I).
-holds(3,I+1) :- 
           fluent(inertial, 3), 
           -holds(3,I),
           not holds(3,I+1), step(I).

fluent(inertial, 4).
holds(4,I+1) :- 
           fluent(inertial, 4), 
           holds(4,I),
           not -holds(4,I+1), step(I).
-holds(4,I+1) :- 
           fluent(inertial, 4), 
           -holds(4,I),
           not holds(4,I+1), step(I).

fluent(inertial, 5).
holds(5,I+1) :- 
           fluent(inertial, 5), 
           holds(5,I),
           not -holds(5,I+1), step(I).
-holds(5,I+1) :- 
           fluent(inertial, 5), 
           -holds(5,I),
           not holds(5,I+1), step(I).

fluent(inertial, 6).
holds(6,I+1) :- 
           fluent(inertial, 6), 
           holds(6,I),
           not -holds(6,I+1), step(I).
-holds(6,I+1) :- 
           fluent(inertial, 6), 
           -holds(6,I),
           not holds(6,I+1), step(I).

fluent(inertial, 7).
holds(7,I+1) :- 
           fluent(inertial, 7), 
           holds(7,I),
           not -holds(7,I+1), step(I).
-holds(7,I+1) :- 
           fluent(inertial, 7), 
           -holds(7,I),
           not holds(7,I+1), step(I).

fluent(inertial, 8).
holds(8,I+1) :- 
           fluent(inertial, 8), 
           holds(8,I),
           not -holds(8,I+1), step(I).
-holds(8,I+1) :- 
           fluent(inertial, 8), 
           -holds(8,I),
           not holds(8,I+1), step(I).

action(do1).
-occurs(do1,I) :- not occurs(do1,I), step(I).

action(do2).
-occurs(do2,I) :- not occurs(do2,I), step(I).

action(do3).
-occurs(do3,I) :- not occurs(do3,I), step(I).

action(do4).
-occurs(do4,I) :- not occurs(do4,I), step(I).

action(do5).
-occurs(do5,I) :- not occurs(do5,I), step(I).

action(do6).
-occurs(do6,I) :- not occurs(do6,I), step(I).

action(do7).
-occurs(do7,I) :- not occurs(do7,I), step(I).

action(do8).
-occurs(do8,I) :- not occurs(do8,I), step(I).

action(do8).
-occurs(do8,I) :- not occurs(do8,I), step(I).

-holds(1,0).
-holds(2,0).
-holds(3,0).
-holds(4,0).
-holds(5,0).
-holds(6,0).
-holds(7,0).
-holds(8,0).

holds(1,T+1) :- 
          -holds(1,T),
           occurs(do1,T).

occurs(do1,0).

holds(2,T+1) :- 
           holds(1,T),
          -holds(2,T),
           occurs(do2,T).

occurs(do2,1).

holds(3,T+1) :- 
           holds(1,T),
          -holds(3,T),
           occurs(do3,T).

occurs(do3,1).

holds(4,T+1) :- 
           holds(2,T),
          -holds(4,T),
           occurs(do4,T).

occurs(do4,2).

holds(5,T+1) :- 
           holds(3,T),
          -holds(5,T),
           occurs(do5,T).

occurs(do5,2).

holds(6,T+1) :- 
           holds(4,T),
          -holds(6,T),
           occurs(do6,T).

occurs(do6,3).

holds(7,T+1) :- 
           holds(5,T),
          -holds(7,T),
           occurs(do7,T).

occurs(do7,3).

holds(8,T+1) :- 
           holds(6,T),
          -holds(8,T),
           occurs(do8,T).

holds(8,T+1) :- 
           holds(7,T),
          -holds(8,T),
           occurs(do8,T).

occurs(do8,4).

