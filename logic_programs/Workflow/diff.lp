remove(F) :- fluent(inertial,F), not _n_fluent(inertial,F).
add(F) :- not fluent(inertial,F), _n_fluent(inertial,F).
remove(A) :- action(A), not _n_action(A).
add(A) :- not action(A), _n_action(A).

unchanged(F) :- not add(F), not remove(F), fluent(inertial, F).
unchanged(A) :- not add(A), not remove(A), action(A).

rename(A, B) :- remove(A), add(B), action(A), _n_action(B),
                occurs(A,X), _n_occurs(B,X), step(X),
                action(C), occurs(C, X-1), _n_occurs(C, X-1), A != C, B != C,
                action(D), occurs(D, X+1), _n_occurs(D, X+1), A != D, B != D.

edge(A, B)   :- fluent(inertial,A), fluent(inertial,B), step(T),
                holds(A,T), -holds(B,T), holds(A,T+1), holds(B,T+1),
                -holds(A,T-1), -holds(B,T-1).

#show remove/1.
#show add/1.
#show unchanged/1.
#show rename/2.
#show edge/2.