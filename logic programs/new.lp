#const n = 8.
step(0..n).

holds(POI,I+1) :- 
           holds(POI,I),
           not -holds(POI,I+1), step(I).
-holds(POI,I+1) :- 
           -holds(POI,I),
           not holds(POI,I+1), step(I).

holds(GPSCoord,I+1) :- 
           holds(GPSCoord,I),
           not -holds(GPSCoord,I+1), step(I).
-holds(GPSCoord,I+1) :- 
           -holds(GPSCoord,I),
           not holds(GPSCoord,I+1), step(I).

holds(chooseMapType,I+1) :- 
           holds(chooseMapType,I),
           not -holds(chooseMapType,I+1), step(I).
-holds(chooseMapType,I+1) :- 
           -holds(chooseMapType,I),
           not holds(chooseMapType,I+1), step(I).

holds(GeoMap,I+1) :- 
           holds(GeoMap,I),
           not -holds(GeoMap,I+1), step(I).
-holds(GeoMap,I+1) :- 
           -holds(GeoMap,I),
           not holds(GeoMap,I+1), step(I).

holds(PolMap,I+1) :- 
           holds(PolMap,I),
           not -holds(PolMap,I+1), step(I).
-holds(PolMap,I+1) :- 
           -holds(PolMap,I),
           not holds(PolMap,I+1), step(I).

holds(extractRelevantData,I+1) :- 
           holds(extractRelevantData,I),
           not -holds(extractRelevantData,I+1), step(I).
-holds(extractRelevantData,I+1) :- 
           -holds(extractRelevantData,I),
           not holds(extractRelevantData,I+1), step(I).

holds(showData,I+1) :- 
           holds(showData,I),
           not -holds(showData,I+1), step(I).
-holds(showData,I+1) :- 
           -holds(showData,I),
           not holds(showData,I+1), step(I).

action(doPOI).
-occurs(doPOI,I) :- not occurs(doPOI,I), step(I).

action(doGPSCoord).
-occurs(doGPSCoord,I) :- not occurs(doGPSCoord,I), step(I).

action(dochooseMapType).
-occurs(dochooseMapType,I) :- not occurs(dochooseMapType,I), step(I).

action(doGeoMap).
-occurs(doGeoMap,I) :- not occurs(doGeoMap,I), step(I).

action(doPolMap).
-occurs(doPolMap,I) :- not occurs(doPolMap,I), step(I).

action(doextractRelevantData).
-occurs(doextractRelevantData,I) :- not occurs(doextractRelevantData,I), step(I).

action(doshowData).
-occurs(doshowData,I) :- not occurs(doshowData,I), step(I).

-holds(POI,0).
-holds(GPSCoord,0).
-holds(chooseMapType,0).
-holds(GeoMap,0).
-holds(PolMap,0).
-holds(extractRelevantData,0).
-holds(showData,0).

holds(POI,T+1) :- 
           holds(start,T),
          -holds(POI,T),
           occurs(doPOI,T).

occurs(doPOI,0).

holds(GPSCoord,T+1) :- 
           holds(POI,T),
          -holds(GPSCoord,T),
           occurs(doGPSCoord,T).

occurs(doGPSCoord,1).

holds(chooseMapType,T+1) :- 
           holds(GPSCoord,T),
          -holds(chooseMapType,T),
           occurs(dochooseMapType,T).

occurs(dochooseMapType,2).

holds(GeoMap,T+1) :- 
           holds(chooseMapType,T),
          -holds(GeoMap,T),
           occurs(doGeoMap,T).

occurs(doGeoMap,3).

holds(PolMap,T+1) :- 
           holds(chooseMapType,T),
          -holds(PolMap,T),
           occurs(doPolMap,T).

occurs(doPolMap,3).

holds(extractRelevantData,T+1) :- 
           holds(GeoMap,T),
          -holds(extractRelevantData,T),
           occurs(doextractRelevantData,T).

occurs(doextractRelevantData,4).

holds(showData,T+1) :- 
           holds(extractRelevantData,T),
          -holds(showData,T),
           occurs(doshowData,T).

occurs(doshowData,5).

