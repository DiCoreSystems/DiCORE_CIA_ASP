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
 * This class takes care of translating a WorkflowGraph to a corresponding TransitionDiagram.
 */
public class WorkflowGraph extends Graph {

    List<Vertex> visitedVertices = new ArrayList<>();
    List<Vertex> vertices = new ArrayList<>();
    List<Fluent> fluents = new ArrayList<>();
    List<State> states = new ArrayList();
    List<Action> actions = new ArrayList();
    Vertex startingVertex = null;

    public WorkflowGraph(List<Vertex> vertices, List<Edge> edges){
        super(vertices, edges);
    }

    public TransitionDiagram translate() {

        Queue<Vertex> verticesToCheck = new LinkedBlockingQueue<>();
        verticesToCheck.add(this.getVertices().get(0));

        vertices = this.getVertices();

        // Create a fluent for each vertex (except for the start vertex)
        for (Vertex v: vertices){
            if(v.getName() != "start"){
                Fluent f = new Fluent(v.getName());
                // At the start of our Workflow, no Actions have been resolved yet.
                // Because of this their fluents are negative at first.
                fluents.add(f.getNegation());
            } else {
                startingVertex = v;
            }
        }

        // Create a start state (used only for orientation)
        State startState = new State(UUID.randomUUID(), "start", fluents);
        states.add(startState);

        // Now we follow the graph vertex by vertex, until we reach the end.
        // Each passed vertex will yield a new state.
        verticesToCheck.offer(startingVertex);

        while(!verticesToCheck.isEmpty()){
            Vertex currentVertex = verticesToCheck.poll();

            if(visitedVertices.contains(currentVertex)){
                continue;
            }

            for(Edge e: currentVertex.getOutgoingEdges()){
                Vertex nextVertex = e.getEnd();
                verticesToCheck.offer(nextVertex);
                if(nextVertex == null)
                    break;

                // Find out which fluent is changed by our action.
                List<Fluent> newFluents = new ArrayList<>();
                for(Fluent f: fluents){
                    // TODO
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

                // Check if our vertex already has a corresponding state.
                State nextState = checkForName(nextVertex.getName());
                if(nextState == null){
                    // Our state does not exist. Create a new one.
                    nextState = new State(UUID.randomUUID(), nextVertex.getName(), newFluents);
                    states.add(nextState);
                }

                Action a = new Action(UUID.randomUUID(), currentState, nextState, "do" + nextVertex.getName());
                actions.add(a);
                currentState.addOutgoingAction(a);
                nextState.addIngoingAction(a);
            }
            visitedVertices.add(currentVertex);
        }

        List<State> start = new ArrayList<>();
        start.add(startState);
        states.remove(startState);

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
}
