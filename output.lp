#const n = 3.
step(0..n).
fluent(inertial,type1).
fluent(inertial,geoMap).
fluent(inertial,polMap).

action(getgeoMap).
action(getpolMap).

holds(type1,I+1) :- 
           fluent(inertial,type1), 
           holds(type1,I),
           not -holds(type1,I+1), step(I).
-holds(type1,I+1) :- 
           fluent(inertial,type1), 
           -holds(type1,I),
           not holds(type1,I+1), step(I).
holds(geoMap,I+1) :- 
           fluent(inertial,geoMap), 
           holds(geoMap,I),
           not -holds(geoMap,I+1), step(I).
-holds(geoMap,I+1) :- 
           fluent(inertial,geoMap), 
           -holds(geoMap,I),
           not holds(geoMap,I+1), step(I).
holds(polMap,I+1) :- 
           fluent(inertial,polMap), 
           holds(polMap,I),
           not -holds(polMap,I+1), step(I).
-holds(polMap,I+1) :- 
           fluent(inertial,polMap), 
           -holds(polMap,I),
           not holds(polMap,I+1), step(I).

-occurs(getgeoMap,I) :- not occurs(getgeoMap,I), step(I).
-occurs(getpolMap,I) :- not occurs(getpolMap,I), step(I).

holds(type1,0).
holds(-geoMap,0).
holds(-polMap,0).

holds(geoMap,T+1) :- holds(type1,T),
holds(-geoMap,T),
holds(-polMap,T),
occurs(getgeoMap,T).

holds(polMap,T+1) :- holds(type1,T),
holds(-geoMap,T),
holds(-polMap,T),
occurs(getpolMap,T).

occurs(getgeoMap,0).
occurs(getpolMap,0).
occurs(getgeoMap,0).
occurs(getpolMap,0).
