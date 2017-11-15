interface(latestTutorialInterface, invalidDateFault, latestTutorialOperation).

fault(latestTutorialInterface, invalidDateFault, stns:invalidDateError).

operation(latestTutorialOperation, latestTutorialRequest, Out, Out).
input(latestTutorialOperation, latestTutorialRequest, typeLatestTutorialRequest).
output(latestTutorialOperation, latestTutorialResponse, string).
fault(latestTutorialOperation, invalidDateFault, invalidDateError).

type(typeLatestTutorialRequest, date).

service(latestTutorialService, latestTutorialInterface, latestTutorialEndpoint).

endpoint(latestTutorialEndpoint, latestTutorialSOAPBinding, http://jenkov.com/latestTutorial).
binding(latestTutorialSOAPBinding).
address(http://jenkov.com/latestTutorial).

fault(invalidDateError, string).




