% If something is used as an input type, it should be declared as input,
% as ASP will be able to conclude that all inputs are types as well.

% complex types contain a sequence and a sequence can contain many types.

interface(latestTutorialInterface, invalidDateFault, latestTutorialOperation).

fault(latestTutorialInterface, invalidDateFault, stns:invalidDateError).

operation(latestTutorialOperation, latestTutorialRequest, Out, Out).
input(latestTutorialOperation, latestTutorialRequest, typeLatestTutorialRequest).
output(latestTutorialOperation, latestTutorialResponse, xs:string).
fault(latestTutorialOperation, invalidDateFault, invalidDateError).

type(typeLatestTutorialRequest, xs:date).

service(latestTutorialService, latestTutorialInterface, latestTutorialEndpoint).

endpoint(latestTutorialEndpoint, latestTutorialSOAPBinding, http://jenkov.com/latestTutorial).
binding(latestTutorialSOAPBinding).
address(http://jenkov.com/latestTutorial).

fault(invalidDateError, xs:string).