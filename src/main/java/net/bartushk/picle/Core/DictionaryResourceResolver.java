package net.bartushk.picle.Core;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * Simple dictinary backed resource resolver.
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public class DictionaryResourceResolver<T> implements IResourceResolver<T>
{
    private ConcurrentHashMap<String, T> dict;

    public T getResource(String key){
        return this.dict.get(key);
    }

    public void putResource(String key, T object){
        this.dict.put(key, object);        
    }
}
