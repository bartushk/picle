package net.bartushk.picle.Graph;


/**
 *
 * Class modeling a basic graph edge.
 *
 * @author Kyle Bartush
 * @see 
 * @since 0.1
 */
public class Edge
{
    private Node from;
    private Node to;


    public Edge(){

    }

    public Edge(Node from, Node to){
        this.from = from;
        this.to = to;
    }


    public Node getFromNode(){
        return from;
    }
    
    public void setFromNode(Node from){
        this.from = from;
    }

    public Node getToNode(){
        return to;
    }
    
    public void setToNode(Node to){
        this.to = to;
    }
}
