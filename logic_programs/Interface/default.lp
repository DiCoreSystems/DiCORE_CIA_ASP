service(N) :- service(N, I, E), interface(I), endpoint(E).

endpoint(N) :- endpoint(N, B, A), binding(B), address(A).

type(N, T) :- input(N,T).
type(N, T) :- output(N, T).
type(N, T) :- fault(N, T).

input(N, T) :- input(O, N, T), operation(O).
output(N, T) :- output(O, N, T), operation(O).
fault(N, T) :- fault(O, N, T), operation(O).

input(N, T) :- input(I, N, T), interface(I).
output(N, T) :- output(I, N, T), interface(I).
fault(N, T) :- fault(I, N, T), interface(I).

interface(N) :- interface(N, O), operation(O).

interface(N) :- interface(N, F, O), fault(N, F, T), operation(O).

operation(O) :- operation(O, I, Out, F), input(O, I, T), output(O, Out, T), fault(O, F, T).