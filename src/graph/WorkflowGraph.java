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

    public WorkflowGraph(List<Vertex> vertices, List<Edge> edges){
        super(vertices, edges);
    }

    public TransitionDiagram translate() {
        // TODO: You take the same name for both actions and fluents. They should be different.
        List<Vertex> visitedVertices = new ArrayList<>();
        List<Vertex> stateVertices = new ArrayList<>();
        List<Vertex> actionVertices = new ArrayList<>();
        List<Fluent> fluents = new ArrayList<>();
        List<State> states = new ArrayList();
        List<Action> actions = new ArrayList();
        Vertex startingVertex = null;
        int choiceNR = 1;

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
        State startingState = new State(UUID.randomUUID(), fluents);
        states.add(startingState);

        // Now we follow the graph vertex by vertex, until we reach the end.
        // Each passed vertex will yield an Action or a new state.
        verticesToCheck.offer(startingVertex);

        while(!verticesToCheck.isEmpty()){
            Vertex currentVertex = verticesToCheck.poll();
            for(Edge e: currentVertex.getOutgoingEdges()){
                Vertex actionVertex = e.getEnd();
                // v should always be an Action vertex
                Vertex nextVertex = actionVertex.getOutgoingEdges().get(0).getEnd();

                //Find out which fluent is changed by our action.
                // TODO: Look over this again. Fluents will not be correct (yet).
                List<Fluent> newFluents = new ArrayList<>();
                for(Fluent f: fluents){
                    // This one needs improvement. Currently we're connecting a action Vertex with
                    // the changed fluents just by the name. Also this doesn't allow us to create
                    // actions which change more than one fluent.
                    if(f.getName().contains(actionVertex.getName())){
                        newFluents.add(f.getNegation());
                    } else {
                        newFluents.add(f);
                    }
                }

                //Create the corresponding state.
                State newState = new State(UUID.randomUUID(), newFluents);
                states.add(newState);

                Action a = new Action(UUID.randomUUID(), startingState, newState, "get" + actionVertex.getName());
                actions.add(a);
                startingState.addAction(a);

                currentVertex = nextVertex;
            }

            for(Edge e: currentVertex.getOutgoingEdges()){
                if(e.getEnd() != null && !visitedVertices.contains(e.getEnd())){
                    verticesToCheck.offer(e.getEnd());
                }
            }
        }

        //TODO: A graph can have more than one entry point.
        List<State> start = new ArrayList<>();
        start.add(startingState);

        TransitionDiagram t = new TransitionDiagram(fluents, actions, states, start);

        return t;
    }

    //Planned: A method which is called once the graph changes and submits the changes to the TranstionDiagram.
}
