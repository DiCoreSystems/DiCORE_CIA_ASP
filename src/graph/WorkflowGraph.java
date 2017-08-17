package graph;

import transDiagram.Action;
import transDiagram.Fluent;
import transDiagram.State;
import transDiagram.TransitionDiagram;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by CSZ on 14.06.2017.
 */
public class WorkflowGraph extends Graph {

    // This class takes care of translating a WorkflowGraph to a corresponding TransitionDiagram.

    List<Vertex> visitedVertices = new ArrayList<>();
    List<Vertex> stateVertices = new ArrayList<>();
    List<Vertex> actionVertices = new ArrayList<>();
    List<Fluent> fluents = new ArrayList<>();
    List<State> states = new ArrayList();
    List<Action> actions = new ArrayList();
    Vertex startingVertex = null;
    int choiceNR = 1;
    int timeStep = 0;

    public WorkflowGraph(List<Vertex> vertices, List<Edge> edges){
        super(vertices, edges);
    }

    public TransitionDiagram translate() {


        // For this example I'm going to assume that our original vertices stand
        // first for States, then an Action, then a State again, and so on...

        //This while loop categorizes every vertex in our original graph.

        Queue<Vertex> verticesToCheck = new LinkedBlockingQueue<>();
        verticesToCheck.add(this.getVertices().get(0));

        while(!verticesToCheck.isEmpty()){
            Vertex v = verticesToCheck.poll();
            visitedVertices.add(v);

            if(v.IsAction()){
                // We have an Action Vertex
                actionVertices.add(v);
                // TODO: Actions may change more than one thing.
                // All action change something. So we need to create a fluent that is changed by this
                // action.
                Fluent aFluent = new Fluent(v.getName());
                // Why the negation? At the start of the workflow, we haven't retrieved that data yet.
                fluents.add(aFluent.getNegation());
            } else {
                // We have a State Vertex.
                stateVertices.add(v);

                if(v.getOutgoingEdges().size() > 1){
                    // TODO: Is that vertex needed?
                    // A State Vertex with two or more outgoing edges (read: two or more possible actions)
                    // contains a choice that has to be made. So we create a new fluent representing our
                    // choice at this point.
                    Fluent type = new Fluent("type" + choiceNR);
                    fluents.add(type);
                    choiceNR++;
                }

                if(v.getName().equals("start")){
                    startingVertex = v;
                } else {
                    Fluent f = new Fluent(v.getName());
                    fluents.add(f);
                }
            }

            for(Edge e: v.getOutgoingEdges()){
                if(e.getEnd() != null && !visitedVertices.contains(e.getEnd())){
                    verticesToCheck.offer(e.getEnd());
                } else if(e.getEnd() == null){
                    // Invalid graph.
                    return null;
                }
            }
        }

        visitedVertices.clear();

        // Transform all StateVertices to actual States.
        // First attach all fluents to our starting vertex.
        State startingState = new State(UUID.randomUUID(), "start", fluents);
        states.add(startingState);

        // Now we follow the graph vertex by vertex, until we reach the end.
        // Each passed vertex will yield an Action or a new state.
        verticesToCheck.offer(startingVertex);

        while(!verticesToCheck.isEmpty()){
            Vertex currentVertex = verticesToCheck.poll();
            State newState;

            for(Edge e: currentVertex.getOutgoingEdges()){
                Vertex nextVertex = e.getEnd();
                verticesToCheck.offer(nextVertex);
                if(nextVertex == null)
                    break;

                //Find out which fluent is changed by our action.
                // TODO: Look over this again. Fluents will not be correct (yet).
                List<Fluent> newFluents = new ArrayList<>();
                for(Fluent f: fluents){
                    // This one needs improvement. Currently we're connecting a action Vertex with
                    // the changed fluents just by the name.
                    if(f.getName().contains(nextVertex.getName())){
                        newFluents.add(f.getNegation());
                    } else {
                        newFluents.add(f);
                    }
                }

                // Find the corresponding state of our vertex.
                State currentState = checkForName(currentVertex.getName());

                for(Edge out: nextVertex.getOutgoingEdges()){
                    Vertex nextNextVertex = out.getEnd();
                    if(nextNextVertex.IsAction()){
                        // State- - - - - - -Action- - - - - - - -Action
                        // We're inserting a new State   ^^^^  ther to save the changes from the previous action.
                        if(checkForName(nextVertex.getName()) == null){
                            newState = new State(UUID.randomUUID(), nextVertex.getName(), newFluents);
                            states.add(newState);

                            Action a = new Action(UUID.randomUUID(), currentState, newState, "get" + nextVertex.getName());
                            actions.add(a);
                            newState.addAction(a);
                        }
                    } else {
                        // State- - - - - - -Action- - - - - - - -State
                        // No need to create a new State here,
                        // we can use the nextNextVertex as a state to save changes.
                        if(checkForName(nextNextVertex.getName()) == null){
                            newState = new State(UUID.randomUUID(), nextNextVertex.getName(), newFluents);
                            states.add(newState);

                            Action a = new Action(UUID.randomUUID(), currentState, newState, "get" +
                                    nextVertex.getName());
                            actions.add(a);
                            newState.addAction(a);
                        }
                    }
                }
            }
        }

        //TODO: A graph can have more than one entry point.
        List<State> start = new ArrayList<>();
        start.add(startingState);

        TransitionDiagram t = new TransitionDiagram(fluents, actions, states, start);

        return t;
    }

    private State checkForName(String name){
        for(State s: states){
            if(s.getName().equals(name)){
                return s;
            }
        }
        for(Action a: actions){
            if(a.getStartState().getName().equals(name)){
                return a.getStartState();
            }
        }
        return null;
    }

    //Planned: A method which is called once the graph changes and submits the changes to the TranstionDiagram.
}
