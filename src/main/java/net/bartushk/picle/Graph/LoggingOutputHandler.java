package net.bartushk.picle.Graph;


public class LoggingOutputHandler<T> implements IGraphOutputHandler<T>
{
    public void handleOutput(String keyName, T data){
        System.out.println("Received output: " + keyName); 
    }
}
