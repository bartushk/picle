package net.bartushk.picle.Core;

import java.util.List;
import java.util.HashMap;

/**
 *
 * Interface used as a basic structure that takes keyed inputs, processes them,
 * and returns keyed outputs.
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public interface IOperation<T>
{
    List<String> getInputKeys();

    List<String> getOutputKeys();

    HashMap<String, T> process( HashMap<String, T> inputs);
}
