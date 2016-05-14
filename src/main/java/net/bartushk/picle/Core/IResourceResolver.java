package net.bartushk.picle.Core;


/**
 *
 * Given a key, finds a resource and returns it.
 *
 * @author Kyle Bartush
 * @see 
 * @since 0.1
 */
public interface IResourceResolver<T>
{
    T getResource(String key);

    void putResource(String key, T object);
}
