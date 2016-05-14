package net.bartushk.picle.Graph;

import java.util.ArrayList;

/**
 *
 * Class modeling a basic graph node.
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public class Node
{
    private ArrayList<Edge> fromEdges;
    private ArrayList<Edge> toEdges;

    public Node(){

    }

    
    public ArrayList<Edge> getFromEdges(){
        return fromEdges;
    }

    public ArrayList<Edge> getToEdges(){
        return toEdges;
    }
}
