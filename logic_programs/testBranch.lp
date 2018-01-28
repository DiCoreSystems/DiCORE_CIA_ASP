#const h = 11.
step(0..h).

_n_fluent(inertial, 1).
_n_holds(1,I+1) :- 
           _n_fluent(inertial, 1), 
           _n_holds(1,I),
           not -_n_holds(1,I+1), step(I).
-_n_holds(1,I+1) :- 
           _n_fluent(inertial, 1), 
           -_n_holds(1,I),
           not _n_holds(1,I+1), step(I).

_n_fluent(inertial, 2).
_n_holds(2,I+1) :- 
           _n_fluent(inertial, 2), 
           _n_holds(2,I),
           not -_n_holds(2,I+1), step(I).
-_n_holds(2,I+1) :- 
           _n_fluent(inertial, 2), 
           -_n_holds(2,I),
           not _n_holds(2,I+1), step(I).

_n_fluent(inertial, 3).
_n_holds(3,I+1) :- 
           _n_fluent(inertial, 3), 
           _n_holds(3,I),
           not -_n_holds(3,I+1), step(I).
-_n_holds(3,I+1) :- 
           _n_fluent(inertial, 3), 
           -_n_holds(3,I),
           not _n_holds(3,I+1), step(I).

_n_fluent(inertial, 4).
_n_holds(4,I+1) :- 
           _n_fluent(inertial, 4), 
           _n_holds(4,I),
           not -_n_holds(4,I+1), step(I).
-_n_holds(4,I+1) :- 
           _n_fluent(inertial, 4), 
           -_n_holds(4,I),
           not _n_holds(4,I+1), step(I).

_n_fluent(inertial, 5).
_n_holds(5,I+1) :- 
           _n_fluent(inertial, 5), 
           _n_holds(5,I),
           not -_n_holds(5,I+1), step(I).
-_n_holds(5,I+1) :- 
           _n_fluent(inertial, 5), 
           -_n_holds(5,I),
           not _n_holds(5,I+1), step(I).

_n_fluent(inertial, 6).
_n_holds(6,I+1) :- 
           _n_fluent(inertial, 6), 
           _n_holds(6,I),
           not -_n_holds(6,I+1), step(I).
-_n_holds(6,I+1) :- 
           _n_fluent(inertial, 6), 
           -_n_holds(6,I),
           not _n_holds(6,I+1), step(I).

_n_fluent(inertial, 7).
_n_holds(7,I+1) :- 
           _n_fluent(inertial, 7), 
           _n_holds(7,I),
           not -_n_holds(7,I+1), step(I).
-_n_holds(7,I+1) :- 
           _n_fluent(inertial, 7), 
           -_n_holds(7,I),
           not _n_holds(7,I+1), step(I).

_n_fluent(inertial, 8).
_n_holds(8,I+1) :- 
           _n_fluent(inertial, 8), 
           _n_holds(8,I),
           not -_n_holds(8,I+1), step(I).
-_n_holds(8,I+1) :- 
           _n_fluent(inertial, 8), 
           -_n_holds(8,I),
           not _n_holds(8,I+1), step(I).

_n_fluent(inertial, b1).
_n_holds(b1,I+1) :- 
           _n_fluent(inertial, b1), 
           _n_holds(b1,I),
           not -_n_holds(b1,I+1), step(I).
-_n_holds(b1,I+1) :- 
           _n_fluent(inertial, b1), 
           -_n_holds(b1,I),
           not _n_holds(b1,I+1), step(I).

_n_fluent(inertial, b2).
_n_holds(b2,I+1) :- 
           _n_fluent(inertial, b2), 
           _n_holds(b2,I),
           not -_n_holds(b2,I+1), step(I).
-_n_holds(b2,I+1) :- 
           _n_fluent(inertial, b2), 
           -_n_holds(b2,I),
           not _n_holds(b2,I+1), step(I).

_n_fluent(inertial, b3).
_n_holds(b3,I+1) :- 
           _n_fluent(inertial, b3), 
           _n_holds(b3,I),
           not -_n_holds(b3,I+1), step(I).
-_n_holds(b3,I+1) :- 
           _n_fluent(inertial, b3), 
           -_n_holds(b3,I),
           not _n_holds(b3,I+1), step(I).

_n_action(do1).
-_n_occurs(do1,I) :- not _n_occurs(do1,I), step(I).

_n_action(do2).
-_n_occurs(do2,I) :- not _n_occurs(do2,I), step(I).

_n_action(do3).
-_n_occurs(do3,I) :- not _n_occurs(do3,I), step(I).

_n_action(dob1).
-_n_occurs(dob1,I) :- not _n_occurs(dob1,I), step(I).

_n_action(do4).
-_n_occurs(do4,I) :- not _n_occurs(do4,I), step(I).

_n_action(do5).
-_n_occurs(do5,I) :- not _n_occurs(do5,I), step(I).

_n_action(dob2).
-_n_occurs(dob2,I) :- not _n_occurs(dob2,I), step(I).

_n_action(do6).
-_n_occurs(do6,I) :- not _n_occurs(do6,I), step(I).

_n_action(do7).
-_n_occurs(do7,I) :- not _n_occurs(do7,I), step(I).

_n_action(dob3).
-_n_occurs(dob3,I) :- not _n_occurs(dob3,I), step(I).

_n_action(do8).
-_n_occurs(do8,I) :- not _n_occurs(do8,I), step(I).

_n_action(do8).
-_n_occurs(do8,I) :- not _n_occurs(do8,I), step(I).

_n_action(do8).
-_n_occurs(do8,I) :- not _n_occurs(do8,I), step(I).

-_n_holds(1,0).
-_n_holds(2,0).
-_n_holds(3,0).
-_n_holds(4,0).
-_n_holds(5,0).
-_n_holds(6,0).
-_n_holds(7,0).
-_n_holds(8,0).
-_n_holds(b1,0).
-_n_holds(b2,0).
-_n_holds(b3,0).

_n_holds(1,T+1) :- 
          -_n_holds(1,T),
           _n_occurs(do1,T).

_n_occurs(do1,0).

_n_holds(2,T+1) :- 
           _n_holds(1,T),
          -_n_holds(2,T),
           _n_occurs(do2,T).

_n_occurs(do2,1).

_n_holds(3,T+1) :- 
           _n_holds(1,T),
          -_n_holds(3,T),
           _n_occurs(do3,T).

_n_occurs(do3,1).

_n_holds(b1,T+1) :- 
           _n_holds(1,T),
          -_n_holds(b1,T),
           _n_occurs(dob1,T).

_n_occurs(dob1,1).

_n_holds(4,T+1) :- 
           _n_holds(2,T),
          -_n_holds(4,T),
           _n_occurs(do4,T).

_n_occurs(do4,2).

_n_holds(5,T+1) :- 
           _n_holds(3,T),
          -_n_holds(5,T),
           _n_occurs(do5,T).

_n_occurs(do5,2).

_n_holds(b2,T+1) :- 
           _n_holds(b1,T),
          -_n_holds(b2,T),
           _n_occurs(dob2,T).

_n_occurs(dob2,2).

_n_holds(6,T+1) :- 
           _n_holds(4,T),
          -_n_holds(6,T),
           _n_occurs(do6,T).

_n_occurs(do6,3).

_n_holds(7,T+1) :- 
           _n_holds(5,T),
          -_n_holds(7,T),
           _n_occurs(do7,T).

_n_occurs(do7,3).

_n_holds(b3,T+1) :- 
           _n_holds(b2,T),
          -_n_holds(b3,T),
           _n_occurs(dob3,T).

_n_occurs(dob3,3).

_n_holds(8,T+1) :- 
           _n_holds(6,T),
          -_n_holds(8,T),
           _n_occurs(do8,T).

_n_holds(8,T+1) :- 
           _n_holds(7,T),
          -_n_holds(8,T),
           _n_occurs(do8,T).

_n_holds(8,T+1) :- 
           _n_holds(b3,T),
          -_n_holds(8,T),
           _n_occurs(do8,T).

_n_occurs(do8,4).

