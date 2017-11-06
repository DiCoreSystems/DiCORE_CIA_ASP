service(latestTutorialService, latestTutorialInterface, latestTutorialEndpoint).

endpoint(latestTutorialEndpoint, latestTutorialSOAPBinding).

% Variables are declared here.
% For example: The first line declares a variable named 'latestTutorialRequest' with the type 'typeLatestTutorialRequest'.
type(latestTutorialRequest, typeLatestTutorialRequest).
type(latestTutorialResponse, string).
type(invalidDateError, string).
type(typeLatestTutorialRequest, date).

interface(latestTutorialInterface).

% Read: fault(interface, name, type).
fault(latestTutorialInterface, invalidDateFault, invalidDateError).

% Read: operation(name, inputType, outputType, faultType)
operation(latestTutorialOperation, latestTutorialRequest, latestTutorialResponse, invalidDateFault).

% Read: binding(name, interface, type, protocol)
binding(latestTutorialSOAPBinding, latestTutorialInterface, soap, HTTP).

fault(latestTutorialSOAPBinding, Sender).

bindingOperation(latestTutorialOperation, soap-response).




