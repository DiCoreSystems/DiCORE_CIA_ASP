#const n = 7.
step(0..n).

_n_fluent(inertial, poi).
_n_holds(poi,I+1) :- 
           _n_fluent(inertial, poi), 
           _n_holds(poi,I),
           not -_n_holds(poi,I+1), step(I).
-_n_holds(poi,I+1) :- 
           _n_fluent(inertial, poi), 
           -_n_holds(poi,I),
           not _n_holds(poi,I+1), step(I).

_n_fluent(inertial, gpsCoord).
_n_holds(gpsCoord,I+1) :- 
           _n_fluent(inertial, gpsCoord), 
           _n_holds(gpsCoord,I),
           not -_n_holds(gpsCoord,I+1), step(I).
-_n_holds(gpsCoord,I+1) :- 
           _n_fluent(inertial, gpsCoord), 
           -_n_holds(gpsCoord,I),
           not _n_holds(gpsCoord,I+1), step(I).

_n_fluent(inertial, chooseMapType).
_n_holds(chooseMapType,I+1) :- 
           _n_fluent(inertial, chooseMapType), 
           _n_holds(chooseMapType,I),
           not -_n_holds(chooseMapType,I+1), step(I).
-_n_holds(chooseMapType,I+1) :- 
           _n_fluent(inertial, chooseMapType), 
           -_n_holds(chooseMapType,I),
           not _n_holds(chooseMapType,I+1), step(I).

_n_fluent(inertial, geoMap).
_n_holds(geoMap,I+1) :- 
           _n_fluent(inertial, geoMap), 
           _n_holds(geoMap,I),
           not -_n_holds(geoMap,I+1), step(I).
-_n_holds(geoMap,I+1) :- 
           _n_fluent(inertial, geoMap), 
           -_n_holds(geoMap,I),
           not _n_holds(geoMap,I+1), step(I).

_n_fluent(inertial, polMap).
_n_holds(polMap,I+1) :- 
           _n_fluent(inertial, polMap), 
           _n_holds(polMap,I),
           not -_n_holds(polMap,I+1), step(I).
-_n_holds(polMap,I+1) :- 
           _n_fluent(inertial, polMap), 
           -_n_holds(polMap,I),
           not _n_holds(polMap,I+1), step(I).

_n_fluent(inertial, extractRelevantData).
_n_holds(extractRelevantData,I+1) :- 
           _n_fluent(inertial, extractRelevantData), 
           _n_holds(extractRelevantData,I),
           not -_n_holds(extractRelevantData,I+1), step(I).
-_n_holds(extractRelevantData,I+1) :- 
           _n_fluent(inertial, extractRelevantData), 
           -_n_holds(extractRelevantData,I),
           not _n_holds(extractRelevantData,I+1), step(I).

_n_fluent(inertial, showData).
_n_holds(showData,I+1) :- 
           _n_fluent(inertial, showData), 
           _n_holds(showData,I),
           not -_n_holds(showData,I+1), step(I).
-_n_holds(showData,I+1) :- 
           _n_fluent(inertial, showData), 
           -_n_holds(showData,I),
           not _n_holds(showData,I+1), step(I).

_n_action(dopoi).
-_n_occurs(dopoi,I) :- not _n_occurs(dopoi,I), step(I).

_n_action(dogpsCoord).
-_n_occurs(dogpsCoord,I) :- not _n_occurs(dogpsCoord,I), step(I).

_n_action(dochooseMapType).
-_n_occurs(dochooseMapType,I) :- not _n_occurs(dochooseMapType,I), step(I).

_n_action(dogeoMap).
-_n_occurs(dogeoMap,I) :- not _n_occurs(dogeoMap,I), step(I).

_n_action(dopolMap).
-_n_occurs(dopolMap,I) :- not _n_occurs(dopolMap,I), step(I).

_n_action(doextractRelevantData).
-_n_occurs(doextractRelevantData,I) :- not _n_occurs(doextractRelevantData,I), step(I).

_n_action(doshowData).
-_n_occurs(doshowData,I) :- not _n_occurs(doshowData,I), step(I).

-_n_holds(poi,0).
-_n_holds(gpsCoord,0).
-_n_holds(chooseMapType,0).
-_n_holds(geoMap,0).
-_n_holds(polMap,0).
-_n_holds(extractRelevantData,0).
-_n_holds(showData,0).

_n_holds(poi,T+1) :- 
          -_n_holds(poi,T),
           _n_occurs(dopoi,T).

_n_occurs(dopoi,0).

_n_holds(gpsCoord,T+1) :- 
           _n_holds(poi,T),
          -_n_holds(gpsCoord,T),
           _n_occurs(dogpsCoord,T).

_n_occurs(dogpsCoord,1).

_n_holds(chooseMapType,T+1) :- 
           _n_holds(gpsCoord,T),
          -_n_holds(chooseMapType,T),
           _n_occurs(dochooseMapType,T).

_n_occurs(dochooseMapType,2).

_n_holds(geoMap,T+1) :- 
           _n_holds(chooseMapType,T),
          -_n_holds(geoMap,T),
           _n_occurs(dogeoMap,T).

_n_occurs(dogeoMap,3).

_n_holds(polMap,T+1) :- 
           _n_holds(chooseMapType,T),
          -_n_holds(polMap,T),
           _n_occurs(dopolMap,T).

_n_occurs(dopolMap,3).

_n_holds(extractRelevantData,T+1) :- 
           _n_holds(geoMap,T),
          -_n_holds(extractRelevantData,T),
           _n_occurs(doextractRelevantData,T).

_n_occurs(doextractRelevantData,4).

_n_holds(showData,T+1) :- 
           _n_holds(extractRelevantData,T),
          -_n_holds(showData,T),
           _n_occurs(doshowData,T).

_n_occurs(doshowData,5).

