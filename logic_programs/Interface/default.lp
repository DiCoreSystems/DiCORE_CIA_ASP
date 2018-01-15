% Inputs, Outputs and Faults are all treated as types.
% This allows the user to check if new types were declared or removed.
type(date, xs:date).
type(xs:string, string).
type(xsd:string, string).
type(xs:decimal, decimal).
type(float, xs:float).
type(boolean, xs:boolean).
type(time, xs:time).
type(integer, xs:integer).
type(anyType, xs:anyType).

type(N, T) :- input(N, T).
type(N, T) :- output(N, T).
type(N, T) :- fault(N, T).

type(X, Z) :- type(X, Y), type(Y, Z).

complexType(N) :- complexType(N, S), sequence(S).

sequence(S) :- sequence(S, N, T), type(N, T).

% Each type is coupled to an operation or an interface, that
% uses said type as input, output or fault.
% If a type is declared correctly it is then known as an correct type.
input(N, T) :- input(O, N, T), operation(O).
output(N, T) :- output(O, N, T), operation(O).
fault(N, T) :- fault(O, N, T), operation(O).

input(N, T) :- input(I, N, T), interface(I).
output(N, T) :- output(I, N, T), interface(I).
fault(N, T) :- fault(I, N, T), interface(I).

% A service requires a name, an interface and an endpoint.
service(N) :- service(N, I, E), interface(I), endpoint(E).

endpoint(N) :- endpoint(N, B, A), binding(B), address(A).

% An interface can be declared with or without a fault type.
interface(N) :- interface(N, O), operation(O).
interface(N) :- interface(N, F, O), fault(N, F, T), operation(O).

message(M) :- messagePart(M, P), type(P, T).

% An operation has a name, and like th interface three types attached.
operation(O) :- operation(O, I, Out, F), input(O, I, T1), output(O, Out, T2), fault(O, F, T3).
operation(O) :- operation(O, I, Out), input(O, I, T1), output(O, Out, T2).
operation(O) :- operation(O, I, Out), message(I), message(O).