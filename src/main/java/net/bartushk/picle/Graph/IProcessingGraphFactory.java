package net.bartushk.picle.Graph;

import net.bartushk.picle.Core.IResourceResolver;

public interface IProcessingGraphFactory<T>
{
    EntryNode<T> getEntryNode();

    ExitNode<T> getExitNode();

    IGraphOutputHandler<T> getOutputHandler();

    IResourceResolver<T> getResourceResolver();
}
