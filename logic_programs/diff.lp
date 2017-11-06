remove(F) :- fluent(inertial,F), not n_fluent(inertial,F).
add(F) :- not fluent(inertial,F), n_fluent(inertial,F).
remove(A) :- action(A), not n_action(A).
add(A) :- not action(A), n_action(A).

rename(A, B) :- remove(A), add(B), action(A), n_action(B),
                occurs(A,X), n_occurs(B,X), step(X),
                action(C), occurs(C, X-1), n_occurs(C, X-1), A != C, B != C
                action(D), occurs(D, X+1), n_occurs(D, X+1), A != D, B != D.

#show remove/1.
#show add/1.
#show rename/2.