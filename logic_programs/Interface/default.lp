% Type Definitions
type(N, T) :- input(N, T).
type(N, T) :- output(N, T).
type(N, T) :- fault(N, T).

type(X, Z) :- type(X, Y), type(Y, Z).

complexType(N) :- complexType(N, S), sequence(S).

sequence(S) :- sequence(S, N, T), type(N, T).

% Input/Output/Fault Definitions.
input(N, T) :- input(O, N, T), operation(O).
output(N, T) :- output(O, N, T), operation(O).
fault(N, T) :- fault(O, N, T), operation(O).

input(N, T) :- input(I, N, T), interface(I).
output(N, T) :- output(I, N, T), interface(I).
fault(N, T) :- fault(I, N, T), interface(I).

% Service Definitions
service(N) :- service(N, I, E), interface(I), endpoint(E).
service(N) :- service(N, B), binding(B).

endpoint(N) :- endpoint(N, B, A), binding(B), address(A).

% Interface Definitions
interface(N) :- interface(N, O), operation(O).
interface(N) :- interface(N, F, O), fault(N, F, T), operation(O).

% Message Definitions
messageName(M) :- messagePart(M, P), type(P, T).

% Operation Definitions
operation(O) :- operation(O, I, Out, F), input(O, I, T1), output(O, Out, T2), fault(O, F, T3).
operation(O) :- operation(O, I, Out), input(O, I, T1), output(O, Out, T2).
operation(O) :- operation(O, I, Out), messageName(I), messageName(O).