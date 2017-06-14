fluent(inertial,type).
fluent(inertial,geo).
fluent(inertial,pol).

action(getGeoMap).
action(getPollutionMap).

holds(type,0).
holds(-geo,0).
holds(-pol,0).

holds(-geo,t+1) :- holds(type,t),
holds(-geo,t),
holds(-pol,t),
occurs(getGeoMap,t).

holds(-pol,t+1) :- holds(type,t),
holds(-geo,t),
holds(-pol,t),
occurs(getPollutionMap,t).

occurs(getGeoMap,0).
occurs(getPollutionMap,0).
