service(name).

service_interface(S, I) :- service(S), interface(I).

endpoint(name).

endpoint_binding(E, B) :- endpoint(E), binding(B).

endpoint_address(E, A) :- endpoint(E), address(A).

type(N, T) :- input(O, N, T), operation(O).
type(N, T) :- output(O, N, T), operation(O).
type(N, T) :- fault(O, N, T), operation(O).

interface(name).

interface_operation(I, O) :- interface(I), operation(O).

input(O, name, typeName) :- operation(O).

output(O, name, typeName) :- operation(O).

fault(O, name, typeName) :- operation(O).