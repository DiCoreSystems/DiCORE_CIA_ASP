#const n = 7.
step(0..n).

n_fluent(inertial, poi).
n_holds(poi,I+1) :- 
           n_fluent(inertial, poi), 
           n_holds(poi,I),
           not -n_holds(poi,I+1), step(I).
-n_holds(poi,I+1) :- 
           n_fluent(inertial, poi), 
           -n_holds(poi,I),
           not n_holds(poi,I+1), step(I).

n_fluent(inertial, gpsCoord).
n_holds(gpsCoord,I+1) :- 
           n_fluent(inertial, gpsCoord), 
           n_holds(gpsCoord,I),
           not -n_holds(gpsCoord,I+1), step(I).
-n_holds(gpsCoord,I+1) :- 
           n_fluent(inertial, gpsCoord), 
           -n_holds(gpsCoord,I),
           not n_holds(gpsCoord,I+1), step(I).

n_fluent(inertial, chooseMapType).
n_holds(chooseMapType,I+1) :- 
           n_fluent(inertial, chooseMapType), 
           n_holds(chooseMapType,I),
           not -n_holds(chooseMapType,I+1), step(I).
-n_holds(chooseMapType,I+1) :- 
           n_fluent(inertial, chooseMapType), 
           -n_holds(chooseMapType,I),
           not n_holds(chooseMapType,I+1), step(I).

n_fluent(inertial, geoMap).
n_holds(geoMap,I+1) :- 
           n_fluent(inertial, geoMap), 
           n_holds(geoMap,I),
           not -n_holds(geoMap,I+1), step(I).
-n_holds(geoMap,I+1) :- 
           n_fluent(inertial, geoMap), 
           -n_holds(geoMap,I),
           not n_holds(geoMap,I+1), step(I).

n_fluent(inertial, polMap).
n_holds(polMap,I+1) :- 
           n_fluent(inertial, polMap), 
           n_holds(polMap,I),
           not -n_holds(polMap,I+1), step(I).
-n_holds(polMap,I+1) :- 
           n_fluent(inertial, polMap), 
           -n_holds(polMap,I),
           not n_holds(polMap,I+1), step(I).

n_fluent(inertial, extractRelevantData).
n_holds(extractRelevantData,I+1) :- 
           n_fluent(inertial, extractRelevantData), 
           n_holds(extractRelevantData,I),
           not -n_holds(extractRelevantData,I+1), step(I).
-n_holds(extractRelevantData,I+1) :- 
           n_fluent(inertial, extractRelevantData), 
           -n_holds(extractRelevantData,I),
           not n_holds(extractRelevantData,I+1), step(I).

n_fluent(inertial, showData).
n_holds(showData,I+1) :- 
           n_fluent(inertial, showData), 
           n_holds(showData,I),
           not -n_holds(showData,I+1), step(I).
-n_holds(showData,I+1) :- 
           n_fluent(inertial, showData), 
           -n_holds(showData,I),
           not n_holds(showData,I+1), step(I).

n_action(dopoi).
-n_occurs(dopoi,I) :- not n_occurs(dopoi,I), step(I).

n_action(dogpsCoord).
-n_occurs(dogpsCoord,I) :- not n_occurs(dogpsCoord,I), step(I).

n_action(dochooseMapType).
-n_occurs(dochooseMapType,I) :- not n_occurs(dochooseMapType,I), step(I).

n_action(dogeoMap).
-n_occurs(dogeoMap,I) :- not n_occurs(dogeoMap,I), step(I).

n_action(dopolMap).
-n_occurs(dopolMap,I) :- not n_occurs(dopolMap,I), step(I).

n_action(doextractRelevantData).
-n_occurs(doextractRelevantData,I) :- not n_occurs(doextractRelevantData,I), step(I).

n_action(doshowData).
-n_occurs(doshowData,I) :- not n_occurs(doshowData,I), step(I).

-n_holds(poi,0).
-n_holds(gpsCoord,0).
-n_holds(chooseMapType,0).
-n_holds(geoMap,0).
-n_holds(polMap,0).
-n_holds(extractRelevantData,0).
-n_holds(showData,0).

n_holds(poi,T+1) :- 
          -n_holds(poi,T),
           n_occurs(dopoi,T).

n_occurs(dopoi,0).

n_holds(gpsCoord,T+1) :- 
           n_holds(poi,T),
          -n_holds(gpsCoord,T),
           n_occurs(dogpsCoord,T).

n_occurs(dogpsCoord,1).

n_holds(chooseMapType,T+1) :- 
           n_holds(gpsCoord,T),
          -n_holds(chooseMapType,T),
           n_occurs(dochooseMapType,T).

n_occurs(dochooseMapType,2).

n_holds(geoMap,T+1) :- 
           n_holds(chooseMapType,T),
          -n_holds(geoMap,T),
           n_occurs(dogeoMap,T).

n_occurs(dogeoMap,3).

n_holds(polMap,T+1) :- 
           n_holds(chooseMapType,T),
          -n_holds(polMap,T),
           n_occurs(dopolMap,T).

n_occurs(dopolMap,3).

n_holds(extractRelevantData,T+1) :- 
           n_holds(geoMap,T),
          -n_holds(extractRelevantData,T),
           n_occurs(doextractRelevantData,T).

n_occurs(doextractRelevantData,4).

n_holds(showData,T+1) :- 
           n_holds(extractRelevantData,T),
          -n_holds(showData,T),
           n_occurs(doshowData,T).

n_occurs(doshowData,5).

