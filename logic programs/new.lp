#const n = 6.
step(0..n).

fluent(inertial,POI).
holds(POI,I+1) :- 
           fluent(inertial,POI), 
           holds(POI,I),
           not -holds(POI,I+1), step(I).
-holds(POI,I+1) :- 
           fluent(inertial,POI), 
           -holds(POI,I),
           not holds(POI,I+1), step(I).

fluent(inertial,GPSCoord).
holds(GPSCoord,I+1) :- 
           fluent(inertial,GPSCoord), 
           holds(GPSCoord,I),
           not -holds(GPSCoord,I+1), step(I).
-holds(GPSCoord,I+1) :- 
           fluent(inertial,GPSCoord), 
           -holds(GPSCoord,I),
           not holds(GPSCoord,I+1), step(I).

fluent(inertial,type1).
holds(type1,I+1) :- 
           fluent(inertial,type1), 
           holds(type1,I),
           not -holds(type1,I+1), step(I).
-holds(type1,I+1) :- 
           fluent(inertial,type1), 
           -holds(type1,I),
           not holds(type1,I+1), step(I).

fluent(inertial,chooseMapType).
holds(chooseMapType,I+1) :- 
           fluent(inertial,chooseMapType), 
           holds(chooseMapType,I),
           not -holds(chooseMapType,I+1), step(I).
-holds(chooseMapType,I+1) :- 
           fluent(inertial,chooseMapType), 
           -holds(chooseMapType,I),
           not holds(chooseMapType,I+1), step(I).

fluent(inertial,GeoMap).
holds(GeoMap,I+1) :- 
           fluent(inertial,GeoMap), 
           holds(GeoMap,I),
           not -holds(GeoMap,I+1), step(I).
-holds(GeoMap,I+1) :- 
           fluent(inertial,GeoMap), 
           -holds(GeoMap,I),
           not holds(GeoMap,I+1), step(I).

fluent(inertial,PolMap).
holds(PolMap,I+1) :- 
           fluent(inertial,PolMap), 
           holds(PolMap,I),
           not -holds(PolMap,I+1), step(I).
-holds(PolMap,I+1) :- 
           fluent(inertial,PolMap), 
           -holds(PolMap,I),
           not holds(PolMap,I+1), step(I).

fluent(inertial,extractRelevantData).
holds(extractRelevantData,I+1) :- 
           fluent(inertial,extractRelevantData), 
           holds(extractRelevantData,I),
           not -holds(extractRelevantData,I+1), step(I).
-holds(extractRelevantData,I+1) :- 
           fluent(inertial,extractRelevantData), 
           -holds(extractRelevantData,I),
           not holds(extractRelevantData,I+1), step(I).

fluent(inertial,extractRelevantData).
holds(extractRelevantData,I+1) :- 
           fluent(inertial,extractRelevantData), 
           holds(extractRelevantData,I),
           not -holds(extractRelevantData,I+1), step(I).
-holds(extractRelevantData,I+1) :- 
           fluent(inertial,extractRelevantData), 
           -holds(extractRelevantData,I),
           not holds(extractRelevantData,I+1), step(I).

fluent(inertial,showData).
holds(showData,I+1) :- 
           fluent(inertial,showData), 
           holds(showData,I),
           not -holds(showData,I+1), step(I).
-holds(showData,I+1) :- 
           fluent(inertial,showData), 
           -holds(showData,I),
           not holds(showData,I+1), step(I).

fluent(inertial,showData).
holds(showData,I+1) :- 
           fluent(inertial,showData), 
           holds(showData,I),
           not -holds(showData,I+1), step(I).
-holds(showData,I+1) :- 
           fluent(inertial,showData), 
           -holds(showData,I),
           not holds(showData,I+1), step(I).

action(getPOI).
-occurs(getPOI,I) :- not occurs(getPOI,I), step(I).
action(getGPSCoord).
-occurs(getGPSCoord,I) :- not occurs(getGPSCoord,I), step(I).
action(getGeoMap).
-occurs(getGeoMap,I) :- not occurs(getGeoMap,I), step(I).
action(getPolMap).
-occurs(getPolMap,I) :- not occurs(getPolMap,I), step(I).
action(getextractRelevantData).
-occurs(getextractRelevantData,I) :- not occurs(getextractRelevantData,I), step(I).

holds(-POI,0).
holds(-GPSCoord,0).
holds(type1,0).
holds(chooseMapType,0).
holds(-GeoMap,0).
holds(-PolMap,0).
holds(-extractRelevantData,0).
holds(-extractRelevantData,0).
holds(showData,0).
holds(showData,0).

holds(POI,T+1) :- 
           holds(-POI,T),
           holds(-GPSCoord,T),
           holds(type1,T),
           holds(chooseMapType,T),
           holds(-GeoMap,T),
           holds(-PolMap,T),
           holds(-extractRelevantData,T),
           holds(-extractRelevantData,T),
           holds(showData,T),
           holds(showData,T),
           occurs(getPOI,T).

holds(-POI,T+1) :- 
           holds(POI,T),
           holds(-GPSCoord,T),
           holds(type1,T),
           holds(chooseMapType,T),
           holds(-GeoMap,T),
           holds(-PolMap,T),
           holds(-extractRelevantData,T),
           holds(-extractRelevantData,T),
           holds(showData,T),
           holds(showData,T),
           occurs(getGPSCoord,T).

holds(GPSCoord,T+1) :- 
           holds(POI,T),
           holds(-GPSCoord,T),
           holds(type1,T),
           holds(chooseMapType,T),
           holds(-GeoMap,T),
           holds(-PolMap,T),
           holds(-extractRelevantData,T),
           holds(-extractRelevantData,T),
           holds(showData,T),
           holds(showData,T),
           occurs(getGPSCoord,T).

holds(-GPSCoord,T+1) :- 
           holds(-POI,T),
           holds(GPSCoord,T),
           holds(type1,T),
           holds(chooseMapType,T),
           holds(-GeoMap,T),
           holds(-PolMap,T),
           holds(-extractRelevantData,T),
           holds(-extractRelevantData,T),
           holds(showData,T),
           holds(showData,T),
           occurs(getGeoMap,T).

holds(GeoMap,T+1) :- 
           holds(-POI,T),
           holds(GPSCoord,T),
           holds(type1,T),
           holds(chooseMapType,T),
           holds(-GeoMap,T),
           holds(-PolMap,T),
           holds(-extractRelevantData,T),
           holds(-extractRelevantData,T),
           holds(showData,T),
           holds(showData,T),
           occurs(getGeoMap,T).

holds(-GPSCoord,T+1) :- 
           holds(-POI,T),
           holds(GPSCoord,T),
           holds(type1,T),
           holds(chooseMapType,T),
           holds(-GeoMap,T),
           holds(-PolMap,T),
           holds(-extractRelevantData,T),
           holds(-extractRelevantData,T),
           holds(showData,T),
           holds(showData,T),
           occurs(getPolMap,T).

holds(PolMap,T+1) :- 
           holds(-POI,T),
           holds(GPSCoord,T),
           holds(type1,T),
           holds(chooseMapType,T),
           holds(-GeoMap,T),
           holds(-PolMap,T),
           holds(-extractRelevantData,T),
           holds(-extractRelevantData,T),
           holds(showData,T),
           holds(showData,T),
           occurs(getPolMap,T).

holds(-GeoMap,T+1) :- 
           holds(-POI,T),
           holds(-GPSCoord,T),
           holds(type1,T),
           holds(chooseMapType,T),
           holds(GeoMap,T),
           holds(-PolMap,T),
           holds(-extractRelevantData,T),
           holds(-extractRelevantData,T),
           holds(showData,T),
           holds(showData,T),
           occurs(getextractRelevantData,T).

holds(extractRelevantData,T+1) :- 
           holds(-POI,T),
           holds(-GPSCoord,T),
           holds(type1,T),
           holds(chooseMapType,T),
           holds(GeoMap,T),
           holds(-PolMap,T),
           holds(-extractRelevantData,T),
           holds(-extractRelevantData,T),
           holds(showData,T),
           holds(showData,T),
           occurs(getextractRelevantData,T).

holds(extractRelevantData,T+1) :- 
           holds(-POI,T),
           holds(-GPSCoord,T),
           holds(type1,T),
           holds(chooseMapType,T),
           holds(GeoMap,T),
           holds(-PolMap,T),
           holds(-extractRelevantData,T),
           holds(-extractRelevantData,T),
           holds(showData,T),
           holds(showData,T),
           occurs(getextractRelevantData,T).

holds(extractRelevantData,T+1) :- 
           holds(-POI,T),
           holds(-GPSCoord,T),
           holds(type1,T),
           holds(chooseMapType,T),
           holds(GeoMap,T),
           holds(-PolMap,T),
           holds(-extractRelevantData,T),
           holds(-extractRelevantData,T),
           holds(showData,T),
           holds(showData,T),
           occurs(getextractRelevantData,T).

holds(extractRelevantData,T+1) :- 
           holds(-POI,T),
           holds(-GPSCoord,T),
           holds(type1,T),
           holds(chooseMapType,T),
           holds(GeoMap,T),
           holds(-PolMap,T),
           holds(-extractRelevantData,T),
           holds(-extractRelevantData,T),
           holds(showData,T),
           holds(showData,T),
           occurs(getextractRelevantData,T).

occurs(getPOI,0).
