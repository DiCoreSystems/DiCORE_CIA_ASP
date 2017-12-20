remove(T) :- type(T, N), not _n_type(T, N).
remove(S) :- service(S), not _n_service(S).
remove(E) :- endpoint(E), not _n_endpoint(E).
remove(O) :- operation(O), not _n_operation(O).
remove(I) :- interface(I), not _n_interface(I).

add(T) :- not type(T, N), _n_type(T, N).
add(S) :- not service(S), _n_service(S).
add(E) :- not endpoint(E), _n_endpoint(E).
add(O) :- not operation(O), _n_operation(O).
add(I) :- not interface(I), _n_interface(I).

#show remove/1.
#show add/1.
#show rename/2.