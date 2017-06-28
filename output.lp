#const n = 3.
step(0..3).
fluent(inertial,type1).
fluent(inertial,-geoMap).
fluent(inertial,-polMap).

action(geoMap).
action(polMap).

holds(type1,i+1) :- 
           fluent(inertial,type1), 
           holds(type1,i),
           not -holds(type1,i+1), step(i).
-holds(type1,i+1) :- 
           fluent(inertial,type1), 
           -holds(type1,i),
           not holds(type1,i+1), step(i).
holds(-geoMap,i+1) :- 
           fluent(inertial,-geoMap), 
           holds(-geoMap,i),
           not -holds(-geoMap,i+1), step(i).
-holds(-geoMap,i+1) :- 
           fluent(inertial,-geoMap), 
           -holds(-geoMap,i),
           not holds(-geoMap,i+1), step(i).
holds(-polMap,i+1) :- 
           fluent(inertial,-polMap), 
           holds(-polMap,i),
           not -holds(-polMap,i+1), step(i).
-holds(-polMap,i+1) :- 
           fluent(inertial,-polMap), 
           -holds(-polMap,i),
           not holds(-polMap,i+1), step(i).

-occurs(geoMap,i) :- not occurs(geoMap,i), step(i).
-occurs(polMap,i) :- not occurs(polMap,i), step(i).

holds(type1,0).
holds(-geoMap,0).
holds(-polMap,0).

holds(-geoMap,t+1) :- holds(type1,t),
holds(-geoMap,t),
holds(-polMap,t),
occurs(geoMap,t).

holds(-polMap,t+1) :- holds(type1,t),
holds(-geoMap,t),
holds(-polMap,t),
occurs(polMap,t).

occurs(geoMap,0).
occurs(polMap,0).
occurs(geoMap,0).
occurs(polMap,0).
