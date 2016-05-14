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
    private String fromKey;
    private String toKey;

    public Edge(){

    }

    public Edge(Node from, Node to, String fromKey, String toKey){
        this.from = from;
        this.to = to;
        this.fromKey = fromKey;
        this.toKey = toKey;
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

    public String getFromKey(){
        return fromKey;
    }
    
    public void setFromKey(String fromKey){
        this.fromKey = fromKey;
    }

    public String getToKey(){
        return toKey;
    }
    
    public void setToKey(String toKey){
        this.toKey = toKey;
    }
}
